package za.co.kss.service.mapper;


import za.co.kss.domain.*;
import za.co.kss.service.dto.PaymentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Payment} and its DTO {@link PaymentDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface PaymentMapper extends EntityMapper<PaymentDTO, Payment> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    PaymentDTO toDto(Payment payment);

    @Mapping(source = "userId", target = "user")
    Payment toEntity(PaymentDTO paymentDTO);

    default Payment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Payment payment = new Payment();
        payment.setId(id);
        return payment;
    }
}
