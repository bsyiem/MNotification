<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Management - Pending Users</title>

<script type="text/javascript">
$(document).ready(function() {
    $('#user_table').DataTable( {
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
            			}
            		}
            		return checkBox;
            	}
            },
            { 
            	"data" : "roles",
            	"render" : function (data,type,row){
            		var checkBox = "<div class=\"form-check\"><input type=\"checkbox\" class=\"form-check-input\" name=\""+row.email+"_ADMIN\"></div>";
            		for(var i = 0; i<data.length;i++){
            			if(data[i].role == "AUTHORIZED"){
            				checkBox = "<div class=\"form-check\"><input type=\"checkbox\" class=\"form-check-input\" name=\""+row.email+"_ADMIN\" checked></div>";
            			}
            		}
            		return checkBox;
            	}
            },
            { 
            	"data" : "roles",
            	"render" : function (data,type,row){
            		var checkBox = "<div class=\"form-check\"><input type=\"checkbox\" class=\"form-check-input\" name=\""+row.email+"_ADMIN\"></div>";
            		for(var i = 0; i<data.length;i++){
            			if(data[i].role == "PENDING"){
            				checkBox = "<div class=\"form-check\"><input type=\"checkbox\" class=\"form-check-input\" name=\""+row.email+"_ADMIN\" checked></div>";
            			}
            		}
            		return checkBox;
            	}
            }
        ]
    } );
} );
</script>

</head>
<body>
<div class ="container-fluid authenticated-margin">
	<table id="user_table" class="table table-striped table-bordered">
	    <thead>
	        <tr>
	            <th>EMAIL</th>
	            <th>Role(ADMIN)</th>
	            <th>Role(AUTHORIZED)</th>
	            <th>Role(PENDING)</th>
	        </tr>
	    </thead>
	</table> 
</div>
</body>
</html>