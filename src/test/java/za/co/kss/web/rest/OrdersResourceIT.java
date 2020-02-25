package za.co.kss.web.rest;

import za.co.kss.KgotlaSocietySchemeApp;
import za.co.kss.domain.Orders;
import za.co.kss.repository.OrdersRepository;
import za.co.kss.service.OrdersService;
import za.co.kss.service.dto.OrdersDTO;
import za.co.kss.service.mapper.OrdersMapper;
import za.co.kss.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static za.co.kss.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import za.co.kss.domain.enumeration.Size;
import za.co.kss.domain.enumeration.Status;
/**
 * Integration tests for the {@link OrdersResource} REST controller.
 */
@SpringBootTest(classes = KgotlaSocietySchemeApp.class)
public class OrdersResourceIT {

    private static final String DEFAULT_ORDER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Size DEFAULT_SIZE = Size.XXS;
    private static final Size UPDATED_SIZE = Size.XS;

    private static final String DEFAULT_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_COLOR = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final Status DEFAULT_STATUS = Status.OPEN;
    private static final Status UPDATED_STATUS = Status.RECEIVED;

    private static final String DEFAULT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REASON = "BBBBBBBBBB";

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restOrdersMockMvc;

    private Orders orders;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrdersResource ordersResource = new OrdersResource(ordersService);
        this.restOrdersMockMvc = MockMvcBuilders.standaloneSetup(ordersResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Orders createEntity(EntityManager em) {
        Orders orders = new Orders()
            .orderType(DEFAULT_ORDER_TYPE)
            .description(DEFAULT_DESCRIPTION)
            .size(DEFAULT_SIZE)
            .color(DEFAULT_COLOR)
            .quantity(DEFAULT_QUANTITY)
            .status(DEFAULT_STATUS)
            .reason(DEFAULT_REASON);
        return orders;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Orders createUpdatedEntity(EntityManager em) {
        Orders orders = new Orders()
            .orderType(UPDATED_ORDER_TYPE)
            .description(UPDATED_DESCRIPTION)
            .size(UPDATED_SIZE)
            .color(UPDATED_COLOR)
            .quantity(UPDATED_QUANTITY)
            .status(UPDATED_STATUS)
            .reason(UPDATED_REASON);
        return orders;
    }

    @BeforeEach
    public void initTest() {
        orders = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrders() throws Exception {
        int databaseSizeBeforeCreate = ordersRepository.findAll().size();

        // Create the Orders
        OrdersDTO ordersDTO = ordersMapper.toDto(orders);
        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersDTO)))
            .andExpect(status().isCreated());

        // Validate the Orders in the database
        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeCreate + 1);
        Orders testOrders = ordersList.get(ordersList.size() - 1);
        assertThat(testOrders.getOrderType()).isEqualTo(DEFAULT_ORDER_TYPE);
        assertThat(testOrders.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testOrders.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testOrders.getColor()).isEqualTo(DEFAULT_COLOR);
        assertThat(testOrders.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testOrders.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testOrders.getReason()).isEqualTo(DEFAULT_REASON);
    }

    @Test
    @Transactional
    public void createOrdersWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ordersRepository.findAll().size();

        // Create the Orders with an existing ID
        orders.setId(1L);
        OrdersDTO ordersDTO = ordersMapper.toDto(orders);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrdersMockMvc.perform(post("/api/orders")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Orders in the database
        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOrders() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get all the ordersList
        restOrdersMockMvc.perform(get("/api/orders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orders.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderType").value(hasItem(DEFAULT_ORDER_TYPE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.toString())))
            .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON)));
    }
    
    @Test
    @Transactional
    public void getOrders() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        // Get the orders
        restOrdersMockMvc.perform(get("/api/orders/{id}", orders.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orders.getId().intValue()))
            .andExpect(jsonPath("$.orderType").value(DEFAULT_ORDER_TYPE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.toString()))
            .andExpect(jsonPath("$.color").value(DEFAULT_COLOR))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON));
    }

    @Test
    @Transactional
    public void getNonExistingOrders() throws Exception {
        // Get the orders
        restOrdersMockMvc.perform(get("/api/orders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrders() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        int databaseSizeBeforeUpdate = ordersRepository.findAll().size();

        // Update the orders
        Orders updatedOrders = ordersRepository.findById(orders.getId()).get();
        // Disconnect from session so that the updates on updatedOrders are not directly saved in db
        em.detach(updatedOrders);
        updatedOrders
            .orderType(UPDATED_ORDER_TYPE)
            .description(UPDATED_DESCRIPTION)
            .size(UPDATED_SIZE)
            .color(UPDATED_COLOR)
            .quantity(UPDATED_QUANTITY)
            .status(UPDATED_STATUS)
            .reason(UPDATED_REASON);
        OrdersDTO ordersDTO = ordersMapper.toDto(updatedOrders);

        restOrdersMockMvc.perform(put("/api/orders")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersDTO)))
            .andExpect(status().isOk());

        // Validate the Orders in the database
        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeUpdate);
        Orders testOrders = ordersList.get(ordersList.size() - 1);
        assertThat(testOrders.getOrderType()).isEqualTo(UPDATED_ORDER_TYPE);
        assertThat(testOrders.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testOrders.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testOrders.getColor()).isEqualTo(UPDATED_COLOR);
        assertThat(testOrders.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testOrders.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testOrders.getReason()).isEqualTo(UPDATED_REASON);
    }

    @Test
    @Transactional
    public void updateNonExistingOrders() throws Exception {
        int databaseSizeBeforeUpdate = ordersRepository.findAll().size();

        // Create the Orders
        OrdersDTO ordersDTO = ordersMapper.toDto(orders);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrdersMockMvc.perform(put("/api/orders")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ordersDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Orders in the database
        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrders() throws Exception {
        // Initialize the database
        ordersRepository.saveAndFlush(orders);

        int databaseSizeBeforeDelete = ordersRepository.findAll().size();

        // Delete the orders
        restOrdersMockMvc.perform(delete("/api/orders/{id}", orders.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Orders> ordersList = ordersRepository.findAll();
        assertThat(ordersList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
