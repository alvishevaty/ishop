package by.home.project.bean;

import java.io.Serializable;
import java.util.Date;

public class Delivery implements Serializable{
	
	private static final long serialVersionUID = 7530964906639156862L;
	
	private String deliveryType;
	private String deliveryDate;
	private int userId;
	
	public Delivery() {
		
	}
	
	public Delivery(String deliveryType, String deliveryDate, int userId) {
		this.deliveryType = deliveryType;
		this.deliveryDate = deliveryDate;
		this.userId = userId;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + ((deliveryType == null) ? 0 : deliveryType.hashCode());
		result = prime * result + userId;
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
		Delivery other = (Delivery) obj;
		if (deliveryDate == null) {
			if (other.deliveryDate != null)
				return false;
		} else if (!deliveryDate.equals(other.deliveryDate))
			return false;
		if (deliveryType == null) {
			if (other.deliveryType != null)
				return false;
		} else if (!deliveryType.equals(other.deliveryType))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Delivery [deliveryType=" + deliveryType + ", deliveryDate=" + deliveryDate + ", userId=" + userId + "]";
	}
	
	

}
