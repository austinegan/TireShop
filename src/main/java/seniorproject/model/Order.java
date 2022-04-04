package seniorproject.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "order")
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int number;
	
	@Column
	private String create_time;
	
	@Column
	private String alter_time;
	
	@Column
	private String items;
	
	@Column
	private String total_cost;
	
	@Column
	private String status;
	
	@Column
	private int customer_id;
	
	public Order() {
		super();
	}
}