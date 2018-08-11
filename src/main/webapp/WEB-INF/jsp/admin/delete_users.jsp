<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>User Management - Delete Users</title>

<script type="text/javascript">

$(document).ready(function() {
	//create and populate the datatable
	var dataTable = $('#user_table').DataTable( {
        "ajax": {
            "url": "/admin/getUserLoginDetails",
            "dataSrc": ""
        },
        "columns": [
            { 
            	"data": "email" 
            },
            { 
            	"render" : function (data,type,row){
            		var checkBox = "<div class=\"form-check\"><input type=\"checkbox\" class=\"form-check-input\" name=\""+row.email+"\"></div>";
            		for(var i = 0;i<row.roles.length;i++){
        				if(row.roles[i].role == "ADMIN"){
        					checkBox = "<div class=\"form-check\"><input type=\"checkbox\" class=\"form-check-input\" name=\""+row.email+"\" disabled></div>";  					
        				}
        			}
            		return checkBox;
            	}
            }
        ]
    });
	
	
	//Onclick of bottom nav-bar button, submit our datatable form
	$('#datatable_form_submit').on('click',function(e){
		e.preventDefault();
		$('#user_enabled_form').submit();
	});
	
	//submit form via ajax post
	$('#user_enabled_form').on('submit',function(e){
		e.preventDefault();
		
		//var data = dataTable.$('input,select,textarea').serializeArray(); //used for input,select and textarea
		var data = dataTable.$('input').serializeArray(); //used for only input as we only have input form tag 

		sendData("/admin/removeUsers","POST",data);

		alert("Users have been deleted");
	});
	
} );
</script>

</head>
<body>

<%@ include file = "/WEB-INF/jsp/common/submitNavBar.jsp"%>

<div id = "user" style="display:hidden">${user}</div>
<div class ="container-fluid authenticated-margin">
	<form id = "user_enabled_form">
	
		<table id="user_table" class="table table-striped table-bordered">
		    <thead>
		        <tr>
		            <th>EMAIL</th>
		            <th>SELECT TO DELETE</th>
		        </tr>
		    </thead>
		</table>	
	</form>
</div>
</body>
</html>