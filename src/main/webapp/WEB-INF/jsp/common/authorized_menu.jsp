<sec:authorize access = "hasAuthority('AUTHORIZED')">
	<li class = "nav-item">
		<div class = "dropdown">
			<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
   				 Authorized Menu
  			</button>
  			<div class = "dropdown-menu">
  				<a class = "dropdown-item" href="/authorized/send_notification">Send Notification</a>
  			</div>
		</div>
	</li>
</sec:authorize> 