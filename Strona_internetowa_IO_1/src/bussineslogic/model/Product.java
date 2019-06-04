package bussineslogic.model;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Product implements Serializable{
private static final long serialVersionUID=1L;
    private String name;
    private double price;
    @Enumerated(EnumType.STRING)  
    private Category category;
    @Enumerated(EnumType.STRING)  
    private Gender gender;
    private String size;
    private String brand;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    public Product(String name, double price, Category category, Gender gender, String size, String brand) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.gender = gender;
        this.size = size;
        this.brand = brand;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Product isValid(String[] filters) {
        if (filters[0].equals("")) {
            filters[0] = "0";
        }
        if (filters[1].equals("")) {
            filters[1] = String.valueOf(Double.MAX_VALUE);
        }
        if ((price >= Double.parseDouble(filters[0])) && (price <= Double.parseDouble(filters[1]))
                && (category.toString().equals(filters[2]) || filters[2].equals("")) && (gender.toString().equals(filters[3])
                || filters[3].equals("")) && (size.equals(filters[4]) || filters[4].equals("")) && (brand.equals(filters[5])
                || filters[5].equals("")) && (name.equals(filters[6]) || filters[6].equals("")))  {
            return this;
        } else {
            return null;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 43 * hash + Objects.hashCode(this.category);
        hash = 43 * hash + Objects.hashCode(this.gender);
        hash = 43 * hash + Objects.hashCode(this.size);
        hash = 43 * hash + Objects.hashCode(this.brand);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.size, other.size)) {
            return false;
        }
        if (!Objects.equals(this.brand, other.brand)) {
            return false;
        }
        if (this.gender != other.gender) {
            return false;
        }
        return true;
    }    

    @Override
    public String toString() {
        return "Product{"
                + "name='" + name + '\''
                + ", price=" + price
                + ", category=" + category
                + ", gender=" + gender
                + ", size='" + size + '\''
                + ", brand='" + brand + '\''
                + '}';
    }

}
