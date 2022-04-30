package seniorproject.model;

import java.io.Serializable;
import java.util.Objects;

public class OPId implements Serializable{
	private int ordNumber;
	private int prodNumber;
	
	public OPId(int ordNumber, int prodNumber) {
		super();
		this.ordNumber = ordNumber;
		this.prodNumber = prodNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ordNumber, prodNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OPId other = (OPId) obj;
		return ordNumber == other.ordNumber && prodNumber == other.prodNumber;
	}

	public OPId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
