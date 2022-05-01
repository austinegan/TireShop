package seniorproject.model;

import java.io.Serializable;
import java.util.Objects;

public class OPId2 implements Serializable{
	private int orderId;
	private int productId;
	
	public OPId2(int ordNumber, int prodNumber) {
		super();
		this.orderId = orderId;
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OPId2 other = (OPId2) obj;
		return orderId == other.orderId && productId == other.productId;
	}

	public OPId2() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}