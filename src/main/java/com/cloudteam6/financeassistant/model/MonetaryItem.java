package com.cloudteam6.financeassistant.model;

public class MonetaryItem {
	
	/**
	 * The period of time in which the item is over
	 */
	private String name;
	
	private double amount;
	
	/**
	 * The period of time in which the item is over. E.g. weeks = 4 for
	 * MonetaryType of "income" means the amount of money the person
	 * gets in every 4 weeks (month). -1 means the item is fixed (i.e.
	 * an one-off item).
	 */
	private int weeks;
	
	private MonetaryType type;

	public MonetaryItem() {}

	public MonetaryItem(String name, double amount, int weeks, MonetaryType type) {
		this.name = name;
		this.amount = amount;
		this.weeks = weeks;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getWeeks() {
		return weeks;
	}

	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}
	
	public MonetaryType getType() {
		return type;
	}

	public void setType(MonetaryType type) {
		this.type = type;
	}
}

