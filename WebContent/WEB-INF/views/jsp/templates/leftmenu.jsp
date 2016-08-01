<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<div>
	<!-- Sidebar -->
	<div id="sidebar-wrapper">
		<ul class="sidebar-nav nav-pills nav-stacked" id="menu">

			<security:authorize access="hasRole('ADMIN')">
				<li class="active"><a
					href="${pageContext.request.contextPath}/admin"><span
						class="fa-stack fa-lg pull-left"><i
							class="fa fa-dashboard fa-stack-1x "></i></span> Dashboard</a></li>
			</security:authorize>		
			<security:authorize access="hasRole('ADMIN')">
				<li class="active"><a
					href="${pageContext.request.contextPath}/admin/role"><span
						class="fa-stack fa-lg pull-left"><i
							class="glyphicon glyphicon-lock fa-stack-1x "></i></span> Role</a></li>
				<li class="active"><a
					href="${pageContext.request.contextPath}/admin/user"><span
						class="fa-stack fa-lg pull-left"><i
							class="fa fa-cloud-download fa-stack-1x "></i></span>User</a></li>

				<li class="active"><a
					href="${pageContext.request.contextPath}/admin/schedular"><span
						class="fa-stack fa-lg pull-left"><i
							class="glyphicon glyphicon-repeat fa-stack-1x "></i></span>Scheduler</a></li>
			</security:authorize>
			
			<li class="active"><a
				href="${pageContext.request.contextPath}/setting"><span
					class="fa-stack fa-lg pull-left"><i
						class="fa fa-cog fa-stack-1x "></i></span>Setting</a></li>

			<li class="active"><a
				href="${pageContext.request.contextPath}/help"><span
					class="fa-stack fa-lg pull-left"><i
						class="glyphicon glyphicon-header fa-stack-1x "></i></span>Help</a></li>
		</ul>
	</div>
</div>