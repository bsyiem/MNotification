<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix = "sform" uri = "http://www.springframework.org/tags/form" %>
    
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> 

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel = "stylesheet" type="text/css" href="/css/signup_login.css"/>
</head>
<body>

<div class = "container-fluid unauthenticated-margin">

	<div class = "row">
		<div class = "col-sm-10 offset-sm-1">
			
			<sform:form id="change_password_form" modelAttribute="changePassword" action="/change_password" method="post">			
			
				<div class="card">
					
					<div class="card-header bg-primary text-white">
						<h3 align = "center">Change Password</h3>
					</div>
					
					<div class="card-body">
						
						<table align="center" class = "table-row-buffer">
							<tr>
								<td>
									<div class = "form-group">
										<sform:password path="currentPassword" placeholder = "CURRENT PASSWORD" class= "form-control" /> 
										<sform:errors path="currentPassword" />
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class = "form-group">
										<sform:password path="password" placeholder = "PASSWORD" class= "form-control" /> 
										<sform:errors path="password" />
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class = "form-group">
										<sform:password path="confirmPassword" placeholder="CONFIRM PASSWORD" class = "form-control"/>
										<sform:errors path="confirmPassword" />
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

				<div align = "center" class = "error">
					<sform:errors path = ""/>
				</div>
				
			</sform:form>	

		</div>
	</div>
	
	<c:if test="${wrong_current_password}">
		<div align = "center" class = "error">
			<h3> Wrong Current Password!</h3>
		</div>
	</c:if>
	
</div>
</body>
</html>