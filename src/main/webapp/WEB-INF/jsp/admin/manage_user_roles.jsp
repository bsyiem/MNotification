<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>User Management - User Roles</title>

<script type="text/javascript">
$(document).ready(function() {

	//create and populate the datatable
	var dataTable = $('#user_table').DataTable( {
        "ajax": {
            "url": "/admin/getUserRoles",
            "dataSrc": ""
        },
        "columns": [
            { 
            	"data": "email" 
            },
            { 
            	"data" : "roles",
            	"render" : function (data,type,row){
            		var checkBox = "<div class=\"form-check\"><input type=\"checkbox\" class=\"form-check-input\" name=\""+row.email+"_ADMIN\"></div>";
            		for(var i = 0; i<data.length;i++){
            			if(data[i].role == "ADMIN"){
            				checkBox = "<div class=\"form-check\"><input type=\"checkbox\" class=\"form-check-input\" name=\""+row.email+"_ADMIN\" checked></div>";
            				if(row.email == $("#user").html()){
            					checkBox = "<div class=\"form-check\"><input type=\"checkbox\" class=\"form-check-input\" name=\""+row.email+"_ADMIN\" checked readonly></div>";
            					
            				}
            			}
            		}
            		return checkBox;
            	}
            },
            { 
            	"data" : "roles",
            	"render" : function (data,type,row){
            		var checkBox = "<div class=\"form-check\"><input type=\"checkbox\" class=\"form-check-input\" name=\""+row.email+"_AUTHORIZED\"></div>";
            		for(var i = 0; i<data.length;i++){
            			if(data[i].role == "AUTHORIZED"){
            				checkBox = "<div class=\"form-check\"><input type=\"checkbox\" class=\"form-check-input\" name=\""+row.email+"_AUTHORIZED\" checked></div>";
            			}
            		}
            		return checkBox;
            	}
            }
        ]
    } );
	
	
	//Onclick of bottom nav-bar button, submit our datatable form
	$('#datatable_form_submit').on('click',function(e){
		e.preventDefault();
		$('#user_role_form').submit();
	});
	
	//submit form via ajax post
	$('#user_role_form').on('submit',function(e){
		e.preventDefault();
		
		//var data = dataTable.$('input,select,textarea').serializeArray(); //used for input,select and textarea
		var data = dataTable.$('input').serialize(); //used for only input as we only have input form tag 
		//data = JSON.stringify(data);

		sendData("/admin/manageUserRoles","POST",data);

		alert("User Roles Saved");
	});
} );
</script>

</head>
<body>

<%@ include file = "/WEB-INF/jsp/common/submitNavBar.jsp"%>

<div id = "user" style="display:hidden">${user}</div>
<div class ="container-fluid authenticated-margin">
	<form id = "user_role_form">
	
		<table id="user_table" class="table table-striped table-bordered">
		    <thead>
		        <tr>
		            <th>EMAIL</th>
		            <th>Role(ADMIN)</th>
		            <th>Role(AUTHORIZED)</th>
		        </tr>
		    </thead>
		</table>	
	</form>
</div>
</body>
</html>