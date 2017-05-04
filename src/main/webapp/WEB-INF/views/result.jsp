<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="alert alert-info" role="alert">
    <p>You have roughly <strong><fmt:formatNumber value="${weeklyBudget}" type="currency" currencySymbol="£"></fmt:formatNumber></strong>
    to spend per week.</p>
    <p>That's about <strong><fmt:formatNumber value="${monthlyBudget}" type="currency" currencySymbol="£"></fmt:formatNumber></strong>
    per month, or equivalent to around <strong><fmt:formatNumber value="${dailyBudget}" type="currency" currencySymbol="£"></fmt:formatNumber></strong>
    per day!
</div>