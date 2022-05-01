package seniorproject.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table (name = "orderProductRelation")
@IdClass(OPId2.class)
public class OrderProductRelation {
@Id
@Column
private int orderId;
@Id
@Column
private int productId;
@Column
private int count;


public OrderProductRelation() {
	super();
	// TODO Auto-generated constructor stub
}

public OrderProductRelation(int orderId, int productId, int count) {
	super();
	this.orderId = orderId;
	this.productId = productId;
	this.count = count;
}



public int getOrderId() {
	return orderId;
}
public void setOrderId(int orderId) {
	this.orderId = orderId;
}
public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}



}
