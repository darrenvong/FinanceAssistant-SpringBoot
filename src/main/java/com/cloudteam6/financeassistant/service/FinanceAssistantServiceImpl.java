package com.cloudteam6.financeassistant.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cloudteam6.financeassistant.model.*;

@Service
public class FinanceAssistantServiceImpl implements FinanceAssistantService {

	@Override
	public double getMonthlyBudget(List<MonetaryItem> incomes,
			List<MonetaryItem> outgoings, int weeksUntilNextLoan) {
		return getWeeklyBudget(incomes, outgoings, weeksUntilNextLoan) * WEEKS_IN_A_MONTH;
	}

	@Override
	public double getWeeklyBudget(List<MonetaryItem> incomes,
			List<MonetaryItem> outgoings, int weeksUntilNextLoan) {
		double totalIncome = getWeeklyMonetaryItemSum(incomes, weeksUntilNextLoan);
		double totalOutgoings = getWeeklyMonetaryItemSum(outgoings, weeksUntilNextLoan);
		return totalIncome - totalOutgoings;
	}

	@Override
	public double getDailyBudget(List<MonetaryItem> incomes,
			List<MonetaryItem> outgoings, int weeksUntilNextLoan) {
		return getWeeklyBudget(incomes, outgoings, weeksUntilNextLoan) / DAYS_IN_A_WEEK;
	}
	
	private double getWeeklyMonetaryItemSum(List<MonetaryItem> monetaryItems,
												int weeksUntilNextLoan) {
		// Pseudocode:
		// if item is fixed/one-off (i.e. weeks == -1) =>
		//	# This works out the "average" amount they have spent in the month
		//	# until their next loan
		// 	itemSum = item * WEEKS_IN_A_MONTH/weeksUntilNextLoan
		// otherwise =>
		//	itemSum = item / weeks * WEEKS_IN_A_MONTH
		//
		double monetaryItemSum = 0;
		for (MonetaryItem item : monetaryItems) {
			if (item.getWeeks() == -1) {
				monetaryItemSum += (item.getAmount() / weeksUntilNextLoan);
			}
			else {
				monetaryItemSum += (item.getAmount() / item.getWeeks());
			}
		}
		return monetaryItemSum;
	}

}
