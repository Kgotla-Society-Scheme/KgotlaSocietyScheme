package za.co.kss.service.mapper;


import za.co.kss.domain.*;
import za.co.kss.service.dto.AddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Address} and its DTO {@link AddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {

    @Mapping(source = "userAddress.id", target = "userAddressId")
    @Mapping(source = "userAddress.login", target = "userAddressLogin")
    AddressDTO toDto(Address address);

    @Mapping(source = "userAddressId", target = "userAddress")
    Address toEntity(AddressDTO addressDTO);

    default Address fromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }
}
