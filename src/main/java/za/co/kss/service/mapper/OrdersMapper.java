package za.co.kss.service.mapper;


import za.co.kss.domain.*;
import za.co.kss.service.dto.OrdersDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Orders} and its DTO {@link OrdersDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface OrdersMapper extends EntityMapper<OrdersDTO, Orders> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    OrdersDTO toDto(Orders orders);

    @Mapping(source = "userId", target = "user")
    Orders toEntity(OrdersDTO ordersDTO);

    default Orders fromId(Long id) {
        if (id == null) {
            return null;
        }
        Orders orders = new Orders();
        orders.setId(id);
        return orders;
    }
}
