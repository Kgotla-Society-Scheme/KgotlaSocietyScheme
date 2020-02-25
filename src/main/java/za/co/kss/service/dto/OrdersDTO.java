package za.co.kss.service.dto;

import java.io.Serializable;
import java.util.Objects;
import za.co.kss.domain.enumeration.Size;
import za.co.kss.domain.enumeration.Status;

/**
 * A DTO for the {@link za.co.kss.domain.Orders} entity.
 */
public class OrdersDTO implements Serializable {

    private Long id;

    private String orderType;

    private String description;

    private Size size;

    private String color;

    private Integer quantity;

    private Status status;

    private String reason;


    private Long userId;

    private String userLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrdersDTO ordersDTO = (OrdersDTO) o;
        if (ordersDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ordersDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
            "id=" + getId() +
            ", orderType='" + getOrderType() + "'" +
            ", description='" + getDescription() + "'" +
            ", size='" + getSize() + "'" +
            ", color='" + getColor() + "'" +
            ", quantity=" + getQuantity() +
            ", status='" + getStatus() + "'" +
            ", reason='" + getReason() + "'" +
            ", userId=" + getUserId() +
            ", userLogin='" + getUserLogin() + "'" +
            "}";
    }
}
