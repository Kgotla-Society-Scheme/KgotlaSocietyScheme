package za.co.kss.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Address.
 */
@Entity
@Table(name = "address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "complex_name")
    private String complexName;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private Integer postalCode;

    @Column(name = "province")
    private String province;

    @OneToOne
    @JoinColumn(unique = true)
    private User userAddress;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public Address streetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getComplexName() {
        return complexName;
    }

    public Address complexName(String complexName) {
        this.complexName = complexName;
        return this;
    }

    public void setComplexName(String complexName) {
        this.complexName = complexName;
    }

    public String getCity() {
        return city;
    }

    public Address city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public Address postalCode(Integer postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getProvince() {
        return province;
    }

    public Address province(String province) {
        this.province = province;
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public User getUserAddress() {
        return userAddress;
    }

    public Address userAddress(User user) {
        this.userAddress = user;
        return this;
    }

    public void setUserAddress(User user) {
        this.userAddress = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        return id != null && id.equals(((Address) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Address{" +
            "id=" + getId() +
            ", streetName='" + getStreetName() + "'" +
            ", complexName='" + getComplexName() + "'" +
            ", city='" + getCity() + "'" +
            ", postalCode=" + getPostalCode() +
            ", province='" + getProvince() + "'" +
            "}";
    }
}
