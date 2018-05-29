package pl.hubswi90.spring.OnlineShop.domain;

import javax.persistence.*;

@Entity
@Table(name = "address")
@NamedQueries({
        @NamedQuery(name = "getAllAddress", query = "SELECT a FROM Address a"),
        @NamedQuery(name = "getAddressById", query = "SELECT a FROM Address a WHERE a.addressId = :id")
})
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long addressId;

    @Column(name = "address_firstname")
    private String firstname;

    @Column(name = "address_lastname")
    private String lastname;

    @Column(name = "address_street")
    private String street;

    @Column(name = "address_city")
    private String city;

    @Column(name = "address_postalCode")
    private String postalCode;

    @Column(name = "address_country")
    private String country;

    @Column(name = "address_alias")
    private String alias;

    public Address() {}

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
