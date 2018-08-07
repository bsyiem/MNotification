<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<!-- bootstrap meta -->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		
		<title>
			<sitemesh:write property='title'/>
		</title>
		
		<!-- bootstrap 4 -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
		

		<!-- shifts the main page down to accommodate the header navigation bar -->
		<link rel="stylesheet" type = "text/css" href="/css/margins.css">
		
		<!-- submenu dropdown on hover -->
		<link rel="stylesheet" type = "text/css" href="/css/submenu.css">
		 
		<!-- jquery css -->
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> 
		  		
  		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script type = "text/javascript" src = /js/DatePicker.js></script>
		
		<!-- js to check which navbar link is active -->
		<script type = "text/javascript" src = /js/NavBarActive.js></script>
		
		<!-- hides the navBar on smaller devices when onscreen keyboard is present -->
		<script type = "text/javascript" src = /js/MobileNavBarHide.js></script>
				
		<sitemesh:write property='head'/>
	</head>
	<body>
		<%@ include file = "/WEB-INF/jsp/common/headerNavBar.jsp"%>			
		<sitemesh:write property='body'/>
	</body>
</html>