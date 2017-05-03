package com.cloudteam6.financeassistant.model;

import org.springframework.stereotype.Component;

import static com.cloudteam6.financeassistant.service.FinanceAssistantService.*;

@Component
public class Income {
	
	private double loan;
	private double grant;
	private double employment;
	private double parents;
	private double others;
	
	protected Income() {} // Provided to satisfy Java Bean convention
	
	public Income(double loan, double grant, double employment, double parents,
					double others) {
		this.loan = loan;
		this.grant = grant;
		this.employment = employment;
		this.parents = parents;
		this.others = others;
	}
	
	public double getLoan() {
		return loan;
	}
	public void setLoan(double loan) {
		this.loan = loan;
	}
	public double getGrant() {
		return grant;
	}
	public void setGrant(double grant) {
		this.grant = grant;
	}
	public double getEmployment() {
		return employment;
	}
	public void setEmployment(double employment) {
		this.employment = employment;
	}
	public double getParents() {
		return parents;
	}
	public void setParents(double parents) {
		this.parents = parents;
	}
	public double getOthers() {
		return others;
	}
	public void setOthers(double others) {
		this.others = others;
	}
	
	public double getTotalAnnualIncome() {
		return loan + grant + (employment + parents + others) * MONTHS_IN_A_YEAR;
	}
	
}
