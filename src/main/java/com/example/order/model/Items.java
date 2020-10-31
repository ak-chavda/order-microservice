package com.example.order.model;

public class Items {

	private String itemid;
	private String itemname;
	private int qty = 1;
	private double price;

	public Items() {
		super();
	}

	public Items(String itemid, String itemname, int qty, double price) {
		this();
		this.itemid = itemid;
		this.itemname = itemname;
		this.qty = qty;
		this.price = price;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Items [itemid=" + itemid + ", qty=" + qty + ", price=" + price + "]";
	}

}