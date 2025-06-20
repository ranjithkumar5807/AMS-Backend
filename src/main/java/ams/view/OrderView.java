package ams.view;

import java.time.LocalDate;
import java.util.List;

public class OrderView {
    private Long id;
    private LocalDate orderDate;
    private Double totalAmount;
    private List<OrderItemView> orderItems;
    
    public OrderView(Long id,LocalDate orderDate,Double totalAmount) {
    	this.id=id;
    	this.orderDate=orderDate;
    	this.totalAmount=totalAmount;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public List<OrderItemView> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItemView> orderItems) { this.orderItems = orderItems; }
}
