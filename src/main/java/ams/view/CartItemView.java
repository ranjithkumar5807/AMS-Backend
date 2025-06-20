package ams.view;

public interface CartItemView {
	
    Long getId();
    Integer getQuantity();
    Long getProductId();
    String getProductName();
    Double getProductPrice();

}
