package bussineslogic.model;
import java.util.Map;
import java.util.Objects;

public class Client {

    private ShoppingBasket shoppingBasket = new ShoppingBasket();
    private String id;
    private String firstName;
    private String lastName;
    private String adress;
    private String phone;
    private String email;

    public Client(String id, String firstName, String lastName, String adress, String phone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adress = adress;
        this.phone = phone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

 

    public void addToShoppingBasket(Product product) {

        Map<Product, Integer> productMap = shoppingBasket.getProductMap();

        for (Product p : productMap.keySet()) {
            if (p.equals(product)) {
                productMap.put(p, productMap.get(p) + 1);
            } else {
                productMap.put(product, 1);
            }
        }
    }


    public void removeFromShoppingBasket(Product product) {

        Map<Product, Integer> productMap = shoppingBasket.getProductMap();

        for (Product p : productMap.keySet()) {
            if (p.equals(product)) {
                if (productMap.get(p) > 1) {
                    productMap.put(p, productMap.get(p) - 1);
                } else {
                    productMap.remove(p);
                }
            }
        }
    }

    public ShoppingBasket getShoppingBasket() {
        return shoppingBasket;
    }

    public void setShoppingBasket(ShoppingBasket shoppingBasket) {
        this.shoppingBasket = shoppingBasket;
    }
    
    public String browseShoppingBasket(String[] filters){
        return shoppingBasket.browseBasket(filters);
    }

    @Override
    public String toString() {
        return "Client{" + "Shopping Basket=" + shoppingBasket.toString() + ", Id=" + id + ", First Name=" + firstName + ", Last Name=" + lastName + ", Adress=" + adress + ", Phone=" + phone + ", Email=" + email + '}';
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
        final Client other = (Client) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
