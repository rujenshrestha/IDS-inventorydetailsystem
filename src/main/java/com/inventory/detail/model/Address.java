package com.inventory.detail.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {

	@JsonProperty("street")
	private String streetName1;
	@JsonProperty("suite")
	private String streetName2;
	private String city;
	private String zipcode;

	public String getStreetName1() {
		return streetName1;
	}

	public void setStreetName1(String streetName1) {
		this.streetName1 = streetName1;
	}

	public String getStreetName2() {
		return streetName2;
	}

	public void setStreetName2(String streetName2) {
		this.streetName2 = streetName2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
