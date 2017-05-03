<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Finance Assistant</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
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
	        <form:form method="POST" modelAttribute="request">
		        <h3>INCOME</h3>
		        <c:forEach items="${request.income}" var="item">
		          <c:set var="errors">
		              <form:errors path="income[${item.key}]" cssClass="text-danger"></form:errors>
		          </c:set>
		          <div class="form-group ${not empty errors? 'has-error': ''}">
		            <form:label for="income[${item.key}]" path="income[${item.key}]">${labels[item.key]}</form:label>
		            <div class="input-group">
		              <div class="input-group-addon">£</div>
		              <form:input type="number" min="0" step="0.01" name="income[${item.key}]" id="income[${item.key}]" path="income[${item.key}]" cssClass="form-control" value="${item.value}"></form:input>
		            </div>
		            ${errors}
		          </div>
		        </c:forEach>
		        <br>
		        <h3>FIXED OUTGOINGS</h3>
		        <c:forEach items="${request.outgoings}" var="item">
		          <c:set var="errors">
                      <form:errors path="outgoings[${item.key}]" cssClass="text-danger"></form:errors>
                  </c:set>
		          <div class="form-group ${not empty errors? 'has-error': ''}">
                    <form:label for="outgoings[${item.key}]" path="outgoings[${item.key}]">${labels[item.key]}</form:label>
                    <div class="input-group">
                      <div class="input-group-addon">£</div>
                      <form:input type="number" min="0" step="0.01" name="outgoings[${item.key}]" id="outgoings[${item.key}]" path="outgoings[${item.key}]" cssClass="form-control" value="${item.value}"></form:input>
                    </div>
                    ${errors}
                  </div>
		        </c:forEach>
		        <button class="btn btn-primary" type="submit">Calculate</button>   
		    </form:form>
		 </div>
    </div>
    
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>
