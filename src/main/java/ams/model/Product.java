package ams.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String imageUrl;
    
    @ManyToOne
    @JoinColumn(name="farmer_id")
    @JsonIgnoreProperties("products")
    private Farmer farmer;
    
    @OneToMany(mappedBy="product",cascade=CascadeType.ALL)
    @JsonIgnoreProperties("product")
    private List<CartItem> cartItems;
    
    @OneToMany(mappedBy="product",cascade=CascadeType.ALL)
    @JsonIgnoreProperties("product")
    private List<OrderItem> orderItems;

}
