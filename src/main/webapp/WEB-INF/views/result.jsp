<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-xs-12">
	<div class="alert ${weeklyBudget > 0? 'alert-info': 'alert-danger'}" role="alert">
	    <p>You have roughly <strong><fmt:formatNumber value="${weeklyBudget}" type="currency" currencySymbol="£"></fmt:formatNumber></strong>
	    to spend per week.</p>
	    <p>That's about <strong><fmt:formatNumber value="${monthlyBudget}" type="currency" currencySymbol="£"></fmt:formatNumber></strong>
	    per month, or equivalent to around <strong><fmt:formatNumber value="${dailyBudget}" type="currency" currencySymbol="£"></fmt:formatNumber></strong>
	    per day!
	    <c:if test="${weeklyBudget <= 0}">
	    <p>You should probably cut back on any unnecessary spendings (most probably the night outs)!</p>
	    </c:if>
	</div>
</div>