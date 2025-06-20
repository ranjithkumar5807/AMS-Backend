package ams.model;

import java.time.LocalDate;
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
@Data
@Table(name="orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Double totalPrice;
	private String status; //PENDING ACCEPTED REJECTED SHIPPED
	private LocalDate orderDate;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnoreProperties("orders")
	private User user;
	
	@OneToMany(mappedBy = "order",cascade=CascadeType.ALL)
	@JsonIgnoreProperties("order")
	private List<OrderItem> orderItems;

}
