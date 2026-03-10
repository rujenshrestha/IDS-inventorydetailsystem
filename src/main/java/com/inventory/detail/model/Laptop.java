package com.inventory.detail.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "laptop")
public class Laptop implements Inventory {

	@Id
	@GeneratedValue
	protected int id;
	protected String type;
	protected String name;
	protected String model;
	protected double price;

	public Laptop() {
	}

	public Laptop(String name, String type, String model, double price) {
		this.name = name;
		this.type = type;
		this.model = model;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Laptop [id=" + id + ", type=" + type + ", name=" + name + ", model=" + model + ", price=" + price + "]";
	}

}
