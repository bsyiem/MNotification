<sec:authorize access = "hasAuthority('ADMIN')">
	<li class = "nav-item">
		<div class = "dropdown">
			<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
   				 Go To
  			</button>
  			<div class = "dropdown-menu">
  				<div class = "dropdown  dropright">
  					<button type="button" class="btn btn-link dropdown-toggle dropdown-item" data-toggle="dropdown">
   				 		User Management
  					</button>
  					<div class = "dropdown-menu">
  						<a class = "dropdown-item" href="/admin/pendingUsers">Pending Users</a>
  						<a class = "dropdown-item" href="#">Enable/Disable Users</a>
  					</div>
  				</div>
  				<a class = "dropdown-item" href="#">Search</a>
  				<a class = "dropdown-item" href="#">Report</a>
  			</div>
		</div>
	</li>
</sec:authorize> 