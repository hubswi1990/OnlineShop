package pl.hubswi90.spring.OnlineShop.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "getAllProduct", query = "SELECT p from Product p")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_name")
    private String productName;

    @Lob
    @Column(name = "product_photo")
    private byte[] photo;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_price", scale = 7, precision = 2)
    private double productPrice;

    @Column(name = "product_status")
    private boolean productStatus;

    @Column(name = "product_shortDescription", columnDefinition = "text")
    private String shortDescription;

    @Column(name = "product_description", columnDefinition = "text")
    private String productFullDescription;

    @ManyToOne
    @JoinColumn(name = "product_category")
    private Category productCategory;


    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getProductFullDescription() {
        return productFullDescription;
    }

    public void setProductFullDescription(String productFullDescription) {
        this.productFullDescription = productFullDescription;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }
}
