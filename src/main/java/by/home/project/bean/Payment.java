package by.home.project.bean;

import java.io.Serializable;

public class Payment implements Serializable {

	private static final long serialVersionUID = 2326267518107451795L;
	
	private String paymentMethod;
	private int amount;
	private int deliveryId;
	
	public Payment() {
		
	}
	
	public Payment(String paymentMethod, int amount) {
		this.paymentMethod = paymentMethod;
		this.amount = amount;
	}

	public int getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + deliveryId;
		result = prime * result + ((paymentMethod == null) ? 0 : paymentMethod.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		if (amount != other.amount)
			return false;
		if (deliveryId != other.deliveryId)
			return false;
		if (paymentMethod == null) {
			if (other.paymentMethod != null)
				return false;
		} else if (!paymentMethod.equals(other.paymentMethod))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Payment [paymentMethod=" + paymentMethod + ", amount=" + amount + ", deliveryId=" + deliveryId + "]";
	}
	
}
