package com.example.domen;

public class Person {
	private String name;
	private String pwd;
	private int id;

	@Override
	public String toString() {
		return "Person [name=" + name + ", pwd=" + pwd + ", id=" + id + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person(String name, String pwd) {
		this.name = name;
		this.pwd = pwd;
	}

	public Person(int id,String name, String pwd) {
		this.name = name;
		this.pwd = pwd;
		this.id = id;
	}

	public Person() {

	}

}
