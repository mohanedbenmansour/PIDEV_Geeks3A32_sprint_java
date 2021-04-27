package com.esprit.models;

public class CategoryProduct {

	private int id;
	private String name;
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
	public CategoryProduct(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public CategoryProduct(String name) {
		super();
		this.name = name;
	}
	@Override
	public String toString() {
		return "CategoryProduct [id=" + id + ", name=" + name + "]";
	}
	
}
