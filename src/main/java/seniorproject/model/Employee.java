package seniorproject.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "employee")
public class Employee implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int number;
	
	@Column
	private String name;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	public Employee() {
		super();
	}

	public Employee(int number, String name, String username, String password) {
		super();
		this.number = number;
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public Employee(String name, String username, String password) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [number=" + number + ", name=" + name + ", username=" + username + ", password=" + password
				+ "]";
	}
}