package com.example.sinhntpk00491_assignment;

public class Item {
	private String title;
	private int icon;
	public Item(String title, int icon) {
		super();
		this.title = title;
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getIcon() {
		return icon;
	}
	public void setIcon(int icon) {
		this.icon = icon;
	}
}
