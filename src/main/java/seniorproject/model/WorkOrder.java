package seniorproject.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "work_order")
public class WorkOrder implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int number;
	
	@Column
	private String total_cost;
	
	@Column
	private String status;
	
	@Column
	private int customer_id;
	
	@Column
	private String note;
	
	@Column
	private java.sql.Timestamp time_create;
	
	@Column
	private java.sql.Timestamp time_update_status;
	/*
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer cust;
*/


	public WorkOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WorkOrder(String total_cost, String status, int customer_id, String note, Timestamp time_create,
			Timestamp time_update) {
		super();
		this.total_cost = total_cost;
		this.status = status;
		this.customer_id = customer_id;
		this.note = note;
		this.time_create = time_create;
		this.time_update_status = time_update;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(String total_cost) {
		this.total_cost = total_cost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public java.sql.Timestamp getTime_create() {
		return time_create;
	}

	public void setTime_create(java.sql.Timestamp time_create) {
		this.time_create = time_create;
	}

	public java.sql.Timestamp getTime_update() {
		return time_update_status;
	}

	public void setTime_update(java.sql.Timestamp time_update) {
		this.time_update_status = time_update;
	}

	/*
	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}
	*/
}