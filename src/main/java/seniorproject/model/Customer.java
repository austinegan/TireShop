package seniorproject.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;

@Entity
@Table (name = "customer")

@NamedNativeQueries({
	@NamedNativeQuery(name = "GET_CUSTOMERS_WITH_FIELDS", query = "SELECT * FROM customer WHERE name LIKE '%:nam%' AND  address LIKE '%:add%' AND phone LIKE '%:pho%' AND email LIKE '%:ema%'", resultClass = Customer.class),
	@NamedNativeQuery(name = "GET_CUSTOMERS_BY_NAME", query = "SELECT * FROM customer WHERE name LIKE %:nam%", resultClass = Customer.class)

})
public class Customer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String phone;
	
	@Column
	private String email;
	
	@Column
	private String address;
	
	@Column
	private java.sql.Timestamp create_time;
	
	@Column
	private java.sql.Timestamp last_update;
	

	/*@OneToMany(/*fetch = FetchType.EAGER, mappedBy="cust")
	private Set<WorkOrder> orders = new LinkedHashSet<WorkOrder>();
	*/


	public Customer() {
		super();
	}

	public Customer(String name, String phone, String email, String address, Timestamp create_time,
			Timestamp last_update) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.create_time = create_time;
		this.last_update = last_update;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public java.sql.Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(java.sql.Timestamp create_time) {
		this.create_time = create_time;
	}

	public java.sql.Timestamp getLast_update() {
		return last_update;
	}

	public void setLast_update(java.sql.Timestamp last_update) {
		this.last_update = last_update;
	}
	
	/* public Set<WorkOrder> getOrders() {
		return orders;
	}

	public void setOrders(Set<WorkOrder> orders) {
		this.orders = orders;
	} */
}