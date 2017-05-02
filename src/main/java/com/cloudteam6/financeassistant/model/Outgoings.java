package com.cloudteam6.financeassistant.model;

import org.springframework.stereotype.Component;
import static com.cloudteam6.financeassistant.service.FinanceAssistantService.*;

@Component
public class Outgoings {
	
	private double bills;
	private double mobile;
	private double travel;
	private double rent;
	private double others;
	
	public Outgoings() {} // Provided to satisfy Java Bean convention
	
	public Outgoings(double bills, double mobile, double travel, double rent, double others) {
		this.bills = bills;
		this.mobile = mobile;
		this.travel = travel;
		this.rent = rent;
		this.others = others;
	}

	public double getBills() {
		return bills;
	}
	public void setBills(double bills) {
		this.bills = bills;
	}
	public double getMobile() {
		return mobile;
	}
	public void setMobile(double mobile) {
		this.mobile = mobile;
	}
	public double getTravel() {
		return travel;
	}
	public void setTravel(double travel) {
		this.travel = travel;
	}
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public double getOthers() {
		return others;
	}
	public void setOthers(double others) {
		this.others = others;
	}
	
	public double getTotalAnnualOutgoings() {
		return (bills + mobile + travel + rent + others) * MONTHS_IN_A_YEAR;
	}
}
