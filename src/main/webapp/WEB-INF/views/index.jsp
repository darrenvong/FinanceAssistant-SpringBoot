<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <c:choose>
        <c:when test="${not empty monthlyBudget && not empty weeklyBudget && not empty dailyBudget}">
        <meta name="requestInfo" id="requestInfo" data-success="1">
        </c:when>
        <c:otherwise>
        <meta name="requestInfo" id="requestInfo" data-success="0">
        </c:otherwise>
    </c:choose>
    <title>Finance Assistant</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        form {margin-bottom: 15px;}
    </style>
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <!-- Fixed navbar -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="${pageContext.request.contextPath}">Finance Assistant</a>
        </div>
      </div>
    </nav>
    <div class="container" style="margin-top: 70px;">
        <div class="row">
          <c:if test="${not empty monthlyBudget && not empty weeklyBudget && not empty dailyBudget}">
            <%@include file="result.jsp" %>
          </c:if>
          <div class="col-xs-12">
	        <form:form method="POST" modelAttribute="request" cssClass="form-horizontal">
		        <h3>INCOME</h3>
		        <c:forEach items="${request.income}" var="item">
		          <c:if test="${not fn:contains(item.key, 'Period')}">
		          <c:set var="errors">
		              <form:errors path="income[${item.key}]" cssClass="text-danger"></form:errors>
		          </c:set>
		          <div class="form-group ${not empty errors? 'has-error': ''}">
		            <form:label for="income[${item.key}]" path="income[${item.key}]" cssClass="control-label col-xs-12 col-sm-5">${labels[item.key]}</form:label>
		            <div class="col-xs-8 col-sm-3">
			            <div class="input-group">
	                      <div class="input-group-addon">£</div>
	                      <form:input type="number" min="0" step="0.01" name="income[${item.key}]" id="income[${item.key}]" path="income[${item.key}]" cssClass="form-control" value="${item.value}"></form:input>
	                      <form:hidden path="income[${item.key.concat('Period')}]"/>
	                    </div>
	                    ${errors}
                    </div>
		          </div>
			          <c:if test="${item.key eq 'balance'}">
			             <c:set var="weeksError">
			                 <form:errors path="weeksuntilnextloan" cssClass="text-danger"></form:errors>
			             </c:set>
			              <div class="form-group ${not empty weeksError? 'has-error': ''}">
		                   <form:label for="weeksuntilnextloan" path="weeksuntilnextloan" cssClass="control-label col-xs-12 col-sm-5">${labels['weeksUntilNextLoan']}</form:label>
		                   <div class="col-xs-8 col-sm-3">
		                      <form:input type="number" min="1" name="weeksuntilnextloan" id="weeksuntilnextloan" path="weeksuntilnextloan" cssClass="form-control"></form:input>
		                      ${weeksError}
		                   </div>
		                   <p class="col-xs-2" style="margin-top:5px;padding-left:0;">week(s)</p>
		                  </div>
			          </c:if>
		          </c:if>
		        </c:forEach>
		        
		        <br>
		        
		        <h3>FIXED OUTGOINGS</h3>
		        <c:forEach items="${request.outgoings}" var="item">
		          <c:if test="${not fn:contains(item.key, 'Period')}">
		          <c:set var="errors">
                      <form:errors path="outgoings[${item.key}]" cssClass="text-danger"></form:errors>
                  </c:set>
		          <div class="form-group ${not empty errors? 'has-error': ''}">
                    <form:label for="outgoings[${item.key}]" path="outgoings[${item.key}]" cssClass="control-label col-xs-12 col-sm-5">${labels[item.key]}</form:label>
                    <div class="col-xs-8 col-sm-3">
	                    <div class="input-group">
	                      <div class="input-group-addon">£</div>
	                      <form:input type="number" min="0" step="0.01" name="outgoings[${item.key}]" id="outgoings[${item.key}]" path="outgoings[${item.key}]" cssClass="form-control" value="${item.value}"></form:input>
	                      <form:hidden path="outgoings[${item.key.concat('Period')}]"/>
	                    </div>
	                    ${errors}
                    </div>
                  </div>
                  </c:if>
		        </c:forEach>
		        <button class="btn btn-primary" id="calculate" type="submit">Calculate</button>   
		    </form:form>
		 </div>
		 </div>
    </div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script>
    $(function() {
    	function charge(userId, devId) {
            $.ajax({
                url: "/cloudteam6/peanutbank/bill",
                type: 'post',
                data: JSON.stringify({
                    userId: userId,
                    developerId: devId
                }),
                contentType: "application/json",
                dataType: "json",
                success: function(data) {
                    console.log(data);
                }
            });
        }
    	
    	$(document).ready(() => {
    		let userId, devId;
    		$.get("/cloudteam6/user/current").done((data) => {
    			console.log(data);
    			userId = data.id;
    			
    			let appName = location.pathname;
    			appName = appName.substring(0, appName.length - 1);
    			$.get("/cloudteam6/appInfo?appName=" + appName, (data) => {
                    console.log(data);
                    devId = data.user.id;
                }).done(() => {
                	if ($("#requestInfo").data("success")) {
                		charge(userId, devId);
                	}
                });
    		});
    	});
    });
    </script>
</body>
</html>
