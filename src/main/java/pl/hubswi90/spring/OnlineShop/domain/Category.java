package pl.hubswi90.spring.OnlineShop.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "categories")
@NamedQueries({
        @NamedQuery(name = "getCategoryByName", query = "SELECT c from Category c where c.categoryName = :name"),
        @NamedQuery(name = "getAllCategory", query = "SELECT c from Category c")
})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long categoryId;

    @Column(name = "category_name", unique = true)
    private String categoryName;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDate date;



    public Category() {}

    public Category(String categoryName) {

        this.categoryName = categoryName;
    }

    public Category(String categoryName, String description) {

        this.categoryName = categoryName;
        this.description = description;
    }

    public long getCategoryId() {

        return categoryId;
    }

    public void setCategoryId(long categoryId) {

        this.categoryId = categoryId;
    }

    public String getCategoryName() {

        return categoryName;
    }

    public void setCategoryName(String categoryName) {

        this.categoryName = categoryName;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
