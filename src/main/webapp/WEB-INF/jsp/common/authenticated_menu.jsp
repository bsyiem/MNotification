<sec:authorize access = "isAuthenticated()">
	<li class = "nav-item">
		<div class = "dropdown">
			<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
   				 ${user}
  			</button>
  			<div class = "dropdown-menu">
  				<a class = "dropdown-item" href="/change_password">Change Password</a>
  				<a class = "dropdown-item" href="/logout">Logout</a>
  			</div>
		</div>
	</li>
</sec:authorize> 