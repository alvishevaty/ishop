package by.home.project.bean;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = -7792202455566982680L;

	private int id;
	private String country;
	private String city;
	private String street;
	private String house;
	private String flat;
	private String postCode;

	public Address() {
	}

	public Address(String country, String city, String street, String house, String flat, String postCode) {
		this.country = country;
		this.city = city;
		this.street = street;
		this.house = house;
		this.flat = flat;
		this.postCode = postCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((flat == null) ? 0 : flat.hashCode());
		result = prime * result + ((house == null) ? 0 : house.hashCode());
		result = prime * result + id;
		result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (flat == null) {
			if (other.flat != null)
				return false;
		} else if (!flat.equals(other.flat))
			return false;
		if (house == null) {
			if (other.house != null)
				return false;
		} else if (!house.equals(other.house))
			return false;
		if (id != other.id)
			return false;
		if (postCode == null) {
			if (other.postCode != null)
				return false;
		} else if (!postCode.equals(other.postCode))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", country=" + country + ", city=" + city + ", street=" + street + ", house="
				+ house + ", flat=" + flat + ", postCode=" + postCode + "]";
	}

}
