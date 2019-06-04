package bussineslogic.model;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Client implements Serializable{
    
    private static final long serialVersionUID=1L;
    @Transient
    private ShoppingBasket shoppingBasket = new ShoppingBasket();
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID_client;
    
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

    public Client() {
    }
    
    

    public Long getID() {
        return ID_client;
    }

    public void setID(Long ID) {
        this.ID_client = ID;
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
        shoppingBasket.addProductToShoppingBasket(product);        
    }

    public String removeFromShoppingBasket(Product product) {
         return shoppingBasket.removeProductFromShoppingBasket(product);
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
        return firstName + " " + lastName + " " + email;
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
        if (!Objects.equals(this.firstName, other.firstName)){
            return false;  
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;   
        }
        if (!Objects.equals(this.adress, other.adress)) {
            return false;  }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;  
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;   
        }
        return true;
    }
}
