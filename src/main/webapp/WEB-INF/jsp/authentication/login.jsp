<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@taglib prefix = "sform" uri = "http://www.springframework.org/tags/form" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel = "stylesheet" type="text/css" href="/css/signup_login.css"/>
</head>

<body>

<div class = "container-fluid unauthenticated-margin">

	<div class = "row">
		<div class = "col-sm-10 offset-sm-1">
			
			<sform:form id="login_form" modelAttribute="userLogin" action="/login" method="post">			
			
				<div class="card">
					
					<div class="card-header bg-primary text-white">
						<h3 align = "center">Login</h3>
					</div>
					
					<div class="card-body">
						
						<table align="center" class = "table-row-buffer">
							<tr>
								<td>
									<div class = "form-group">
										<sform:input path="email" placeholder = "EMAIL" class= "form-control" /> 
										<sform:errors path="email" />
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class = "form-group">
										<sform:password path="password" placeholder="PASSWORD" class = "form-control"/>
										<sform:errors path="password" />
									</div>
								</td>
							</tr>
							<tr>
								<td colspan=2 align="center">
									<input class = "btn btn-primary" type="submit" value="Go" />
								</td>
							</tr>
						</table>
					</div>
				</div>

			</sform:form>	

		</div>
	</div>
	
	<c:if test="${authenticationError}">
		<div align = "center" class = "error">
			<h3> Invalid username or password!</h3>
		</div>
	</c:if>
		
</div>
</body>
</html>