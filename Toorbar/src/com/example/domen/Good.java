package com.example.domen;

import java.io.Serializable;


public class Good implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int univalent;
	private int quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUnivalent() {
		return univalent;
	}

	public void setUnivalent(int univalent) {
		this.univalent = univalent;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Good() {

	}

	public Good(int id, String name, int univalent, int quantity) {
		this.id = id;
		this.name = name;
		this.univalent = univalent;
		this.quantity = quantity;
	}

}
