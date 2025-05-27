package ams.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	private String password;
	private String phone;
	private String address;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	@JsonIgnoreProperties("user")
	private List<CartItem> cartitems;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	@JsonIgnoreProperties("user")
	private List<Order> orders;
	
	
}
