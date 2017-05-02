package com.cloudteam6.financeassistant.controller;

import java.util.HashMap;
import java.util.Map;

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
		
		Income income = new Income(request.getIncomeDouble("loan"),
				request.getIncomeDouble("grant"), request.getIncomeDouble("employment"),
				request.getIncomeDouble("parents"), request.getIncomeDouble("incomeOthers"));
		Outgoings outgoings = new Outgoings(request.getOutgoingsDouble("bills"),
				request.getOutgoingsDouble("mobile"), request.getOutgoingsDouble("travel"),
				request.getOutgoingsDouble("rent"), request.getOutgoingsDouble("outgoingOthers"));
		
		model.addAttribute("monthlyBudget", financeAssistantService.getMonthlyBudget(income, outgoings));
		model.addAttribute("weeklyBudget", financeAssistantService.getWeeklyBudget(income, outgoings));
		model.addAttribute("dailyBudget", financeAssistantService.getDailyBudget(income, outgoings));
		
		// Can then potentially interact with the peanut bank here before returning,
		// once pricing is known...
		
		
		return "index";
	}
	
	private Map<String, String> getFieldLabelsMap() {
		Map<String, String> fieldLabels = new HashMap<>();
		
		fieldLabels.put("loan", "Maintenance Loan: ");
		fieldLabels.put("grant", "Maintenance Grant: ");
		fieldLabels.put("employment", "Employment: ");
		fieldLabels.put("parents", "Parents: ");
		fieldLabels.put("incomeOthers", "Other Income: ");
		
		fieldLabels.put("bills", "Bills (Gas, Electricity, Water): ");
		fieldLabels.put("mobile", "Mobile Phone: ");
		fieldLabels.put("rent", "Rent: ");
		fieldLabels.put("travel", "Travel Expenses: ");
		fieldLabels.put("outgoingOthers", "Other Monthly Fixed Outgoings: ");
		
		return fieldLabels;
	}
	
}
