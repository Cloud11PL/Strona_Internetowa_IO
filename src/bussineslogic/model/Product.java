package bussineslogic.model;
import bussineslogic.model.Category;

public class Product {

    private String name;
    private double price;
    private Category category;
    private Gender gender;
    private String size;
    private String brand;

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


    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (getName().equals(((Product)obj).getName())){
            if (getPrice() == ((Product)obj).getPrice()){
                if (getGender().equals(((Product)obj).getGender())){
                    if (getSize().equals(((Product)obj).getSize())){
                        if (getBrand().equals(((Product)obj).getBrand())){
                            result = true;
                        }
                    }
                }
            }
        }

        return result;
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
    
    public Product isValid(String[] filters){
        if((price>=Double.parseDouble(filters[0]) || filters[0].equals("")) && 
                (price<=Double.parseDouble(filters[1])|| filters[1].equals("")) && 
                (category.toString().equals(filters[2])|| filters[2].equals(""))&&
                (gender.toString().equals(filters[3])|| filters[3].equals("")) &&
                (size.equals(filters[4])|| filters[4].equals("")) &&
                (brand.equals(filters[5])|| filters[5].equals(""))) return this;
        
        else return null;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", gender=" + gender +
                ", size='" + size + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}