package com.cloudteam6.financeassistant.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.cloudteam6.financeassistant.resource.Request;
import com.cloudteam6.financeassistant.service.FinanceAssistantService;
import com.cloudteam6.financeassistant.validator.RequestValidator;
import com.cloudteam6.financeassistant.model.*;
import static com.cloudteam6.financeassistant.model.MonetaryType.*;

@Controller
public class FinanceAssistantController {
	
	@Autowired
	private RequestValidator requestValidator;
	
	@Autowired
	private FinanceAssistantService financeAssistantService;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("request", new Request());
		model.addAttribute("labels", getFieldLabelsMap());
		return "index";
	}
	
	@PostMapping("/")
	public String calculate(@ModelAttribute("request") Request request,
							BindingResult bindingResults, Model model) {
		model.addAttribute("labels", getFieldLabelsMap());
		requestValidator.validate(request, bindingResults);
		if (bindingResults.hasErrors()) {
			return "index";
		}
		
		List<MonetaryItem> incomes = buildMonetaryItemsList(INCOME, request.getIncome());
		List<MonetaryItem> outgoings = buildMonetaryItemsList(OUTGOINGS, request.getOutgoings());
		int weeksUntilNextLoan = Integer.parseInt(request.getWeeksuntilnextloan());
		
		model.addAttribute("monthlyBudget", financeAssistantService.getMonthlyBudget(incomes, outgoings, weeksUntilNextLoan));
		model.addAttribute("weeklyBudget", financeAssistantService.getWeeklyBudget(incomes, outgoings, weeksUntilNextLoan));
		model.addAttribute("dailyBudget", financeAssistantService.getDailyBudget(incomes, outgoings, weeksUntilNextLoan));
		
		// Interact with peanut bank here?
		
		
		return "index";
	}
	
	private List<MonetaryItem> buildMonetaryItemsList(MonetaryType type, Map<String, String> mapping) {
		List<MonetaryItem> monetaryItemsList = new ArrayList<>();
		for (Entry<String, String> entry : mapping.entrySet()) {
			if (entry.getKey().contains("Period")) {
				continue;
			}
			else {
				MonetaryItem income = new MonetaryItem();
				if (type == INCOME) {
					income.setType(INCOME);
				}
				else {
					income.setType(OUTGOINGS);
				}
				income.setName(entry.getKey());
				income.setAmount(Double.parseDouble(entry.getValue()));
				income.setWeeks( Integer.parseInt(mapping.get(entry.getKey()+"Period")) );
				monetaryItemsList.add(income);
			}
		}
		return monetaryItemsList;
	}
	
	private Map<String, String> getFieldLabelsMap() {
		Map<String, String> fieldLabels = new HashMap<>();
		
		fieldLabels.put("balance", "Your current bank balance: ");
		fieldLabels.put("weeksUntilNextLoan", "Next loan installment in: ");
		fieldLabels.put("employment", "Part-time job (per week): ");
		fieldLabels.put("parents", "Parents (per week): ");
		fieldLabels.put("incomeOthers", "Other one-off income: ");
		
		fieldLabels.put("bills", "Bills (Gas, Electricity, Water) (per month): ");
		fieldLabels.put("mobile", "Mobile phone (per month): ");
		fieldLabels.put("rent", "Rent (in this installment): ");
		fieldLabels.put("travel", "Travel expenses (per month): ");
		fieldLabels.put("outgoingOthers", "Other one-off outgoings: ");
		
		return fieldLabels;
	}
	
}
