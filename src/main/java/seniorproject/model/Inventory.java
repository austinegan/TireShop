package seniorproject.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "inventory")
public class Inventory implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	
	@Column
	private String brand;
	
	@Column
	private String model_number;
	
	@Column
	private double sale_price;
	
	@Column
	private double purchase_price;
	
	@Column
	private int count;
	
	@Column
	private String size;
	
	@Column
	private int width;
	
	@Column
	private int aspectratio;
	
	@Column
	private int diameter;
	
	public Inventory() {
		super();
	}

	public Inventory(String brand, String model_number, double sale_price, double purchase_price, int count,
			String size, int width, int aspectratio, int diameter) {
		super();
		this.brand = brand;
		this.model_number = model_number;
		this.sale_price = sale_price;
		this.purchase_price = purchase_price;
		this.count = count;
		this.size = size;
		this.width = width;
		this.aspectratio = aspectratio;
		this.diameter = diameter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel_number() {
		return model_number;
	}

	public void setModel_number(String model_number) {
		this.model_number = model_number;
	}

	public double getSale_price() {
		return sale_price;
	}

	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
	}

	public double getPurchase_price() {
		return purchase_price;
	}

	public void setPurchase_price(double purchase_price) {
		this.purchase_price = purchase_price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getAspectratio() {
		return aspectratio;
	}

	public void setAspectratio(int aspectratio) {
		this.aspectratio = aspectratio;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", brand=" + brand + ", model_number=" + model_number + ", sale_price="
				+ sale_price + ", purchase_price=" + purchase_price + ", count=" + count + ", size=" + size + ", width="
				+ width + ", aspectratio=" + aspectratio + ", diameter=" + diameter + "]";
	}
}
