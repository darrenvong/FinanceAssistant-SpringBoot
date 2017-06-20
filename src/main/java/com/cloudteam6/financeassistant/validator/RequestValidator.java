package com.cloudteam6.financeassistant.validator;

import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cloudteam6.financeassistant.resource.Request;

@Component
public class RequestValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return Request.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors e) {
		Request request = (Request) o;
		validateRequestMappings(request.getIncome().entrySet(), e, "income");
		validateRequestMappings(request.getOutgoings().entrySet(), e, "outgoings");
		
		try {
			int weeksUntilNextLoan = Integer.parseInt(request.getWeeksuntilnextloan());
			if (weeksUntilNextLoan < 0) {
				e.rejectValue("weeksuntilnextloan", "negative.weeks");
			}
		}
		catch (NumberFormatException ex) {
			e.rejectValue("weeksuntilnextloan", "invalid");
		}
	}
	
	private void validateRequestMappings(Set<Entry<String, String>> entries, Errors e, String mappingName) {
		for (Entry<String, String> entry : entries) {
			if (entry.getValue() == null || entry.getValue().equals("")) {
				e.rejectValue(mappingName + "['" + entry.getKey() + "']", "empty");
			}
			else {
				try {
					Double.parseDouble(entry.getValue());
				}
				catch (NumberFormatException ex) {
					e.rejectValue(mappingName + "['" + entry.getKey() + "']", "invalid");
				}
			}
		}
	}

}
