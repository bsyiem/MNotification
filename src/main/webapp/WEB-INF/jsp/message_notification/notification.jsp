<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@taglib prefix = "sform" uri = "http://www.springframework.org/tags/form" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Send Notification</title>
<link rel = "stylesheet" type="text/css" href="/css/signup_login.css"/>
</head>

<body>

<div class = "container-fluid unauthenticated-margin">

	<div class = "row">
		<div class = "col-sm-10 offset-sm-1">
			
			<form id="login_form" action="/message_notification" method="post">			
			
				<div class="card">
					
					<div class="card-header bg-primary text-white">
						<h3 align = "center">Upload Excel File</h3>
					</div>
					
					<div class="card-body">
						
						<table align="center" class = "table-row-buffer">
							<tr>
								<td>
									<div class = "form-group">
										<input type = "file" name = "fileLocation" class= "form-control" /> 
									</div>
								</td>
							</tr>

							<tr>
								<td colspan=2 align="center">
									<input class = "btn btn-primary" type="Submit" value="SEND" />
								</td>
							</tr>
						</table>
					</div>
				</div>

			</form>	

		</div>
	</div>
		
</div>
</body>
</html>