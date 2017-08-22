package com.example.domen;

import com.example.toorbar.R;

public class Cele {
	private int id;
	private String title;
	private String content;
	private String comments;
	public Cele(int id, String title, String content, String comments) {
		this.id = id+R.drawable.ic_launcher;
		this.title = title;
		this.content = content;
		this.comments = comments;
	}
	public Cele() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
