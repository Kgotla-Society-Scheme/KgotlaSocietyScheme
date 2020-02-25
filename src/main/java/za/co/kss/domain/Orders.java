package za.co.kss.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

import za.co.kss.domain.enumeration.Size;

import za.co.kss.domain.enumeration.Status;

/**
 * A Orders.
 */
@Entity
@Table(name = "orders")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "order_type")
    private String orderType;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private Size size;

    @Column(name = "color")
    private String color;

    @Column(name = "quantity")
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "reason")
    private String reason;

    @ManyToOne
    @JsonIgnoreProperties("orders")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderType() {
        return orderType;
    }

    public Orders orderType(String orderType) {
        this.orderType = orderType;
        return this;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getDescription() {
        return description;
    }

    public Orders description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Size getSize() {
        return size;
    }

    public Orders size(Size size) {
        this.size = size;
        return this;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public Orders color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Orders quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Status getStatus() {
        return status;
    }

    public Orders status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public Orders reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public User getUser() {
        return user;
    }

    public Orders user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Orders)) {
            return false;
        }
        return id != null && id.equals(((Orders) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Orders{" +
            "id=" + getId() +
            ", orderType='" + getOrderType() + "'" +
            ", description='" + getDescription() + "'" +
            ", size='" + getSize() + "'" +
            ", color='" + getColor() + "'" +
            ", quantity=" + getQuantity() +
            ", status='" + getStatus() + "'" +
            ", reason='" + getReason() + "'" +
            "}";
    }
}
