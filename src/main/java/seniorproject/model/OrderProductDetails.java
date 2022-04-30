package seniorproject.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.Table;
import javax.persistence.IdClass;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@IdClass(OPId.class)
public class OrderProductDetails{

	@Id
	@Column (name = "order_number")
	private int ordNumber;
	
	@Id
	@Column (name = "product_number")
	private int prodNumber;

	@Column
	private String brand;
	
	@Column
	private String model_number;

	@Column
	private String size;
	
	@Column
	private int count;
	
	public OrderProductDetails() {
		super();
	}
	
	

public OrderProductDetails(int ordNumber, int prodNumber, String brand, String model_number, String size,
			int count) {
		super();
		this.ordNumber = ordNumber;
		this.prodNumber = prodNumber;
		this.brand = brand;
		this.model_number = model_number;
		this.size = size;
		this.count = count;
	}


	public int getOrdNumber() {
		return ordNumber;
	}
	
	public int getProdNumber() {
		return prodNumber;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel_number() {
		return model_number;
	}

	public String getSize() {
		return size;
	}

	public int getCount() {
		return count;
	}

	@Override
	public String toString() {
		return "OrderProductDetails [ordNumber=" + ordNumber + ", brand=" + brand + ", model_number=" + model_number
				+ ", size=" + size + ", count=" + count + "]";
	}
	
	
}
