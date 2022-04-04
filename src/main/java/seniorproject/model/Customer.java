package seniorproject.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "customer")
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
	private String create_time;
	
	@Column
	private String last_update;
	
	public Customer() {
		super();
	}
}