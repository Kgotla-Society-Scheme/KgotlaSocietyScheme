package za.co.kss.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link za.co.kss.domain.Address} entity.
 */
public class AddressDTO implements Serializable {

    private Long id;

    private String streetName;

    private String complexName;

    private String city;

    private Integer postalCode;

    private String province;


    private Long userAddressId;

    private String userAddressLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getComplexName() {
        return complexName;
    }

    public void setComplexName(String complexName) {
        this.complexName = complexName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Long getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(Long userId) {
        this.userAddressId = userId;
    }

    public String getUserAddressLogin() {
        return userAddressLogin;
    }

    public void setUserAddressLogin(String userLogin) {
        this.userAddressLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddressDTO addressDTO = (AddressDTO) o;
        if (addressDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), addressDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
            "id=" + getId() +
            ", streetName='" + getStreetName() + "'" +
            ", complexName='" + getComplexName() + "'" +
            ", city='" + getCity() + "'" +
            ", postalCode=" + getPostalCode() +
            ", province='" + getProvince() + "'" +
            ", userAddressId=" + getUserAddressId() +
            ", userAddressLogin='" + getUserAddressLogin() + "'" +
            "}";
    }
}
