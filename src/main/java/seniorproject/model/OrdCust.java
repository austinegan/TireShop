package seniorproject.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class OrdCust {

	@Id
	@Column (name = "order_number")
	private int ordNumber;

	@Column
	private String cust_name;
	
	@Column
	private String cust_phone;

	@Column
	private Timestamp time_create;
	
	@Column
	private Timestamp time_update_status;
	
	@Column
	private String note;
	
	@Column
	private String status;
	
	public OrdCust() {
		super();
		// TODO Auto-generated constructor stub
	}



	public OrdCust(int ordNumber, String cust_name, String cust_phone, Timestamp time_create,
			Timestamp time_update_status, String note, String status) {
		super();
		this.ordNumber = ordNumber;
		this.cust_name = cust_name;
		this.cust_phone = cust_phone;
		this.time_create = time_create;
		this.time_update_status = time_update_status;
		this.note = note;
		this.status = status;
	}



	public int getOrdNumber() {
		return ordNumber;
	}

	public String getCust_name() {
		return cust_name;
	}

	public String getCust_phone() {
		return cust_phone;
	}

	public Timestamp getTime_create() {
		return time_create;
	}

	public Timestamp getTime_update_status() {
		return time_update_status;
	}

	public String getNote() {
		return note;
	}

	public String getStatus() {
		return status;
	}
	

}