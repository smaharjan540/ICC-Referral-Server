<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="panel panel-default">
	<div class="formcontainer">
		<!-- Row start -->
		<div class="row">
			<div class="col-md-12 col-sm-6 col-xs-12">

				<div class="panel-body">
					<form:form class="form-horizontal row-border" action="user/admin"
						method="post" modelAttribute="admin">
						<div class="form-group">
							<label class="col-md-2 control-label">First Name</label>
							<div class="col-md-10">
								<form:input path="firstname" class="form-control" id="firstname" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">Last Name</label>
							<div class="col-md-10">
								<form:input path="lastname" class="form-control" id="lastname" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">Username</label>
							<div class="col-md-10">
								<form:input path="username" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">password</label>
							<div class="col-md-10">
								<form:password path="password" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">Email</label>
							<div class="col-md-10">
								<form:input path="email" class="form-control" />
							</div>
						</div>

						<div class="form-group">
							<form:button class="btn btn-primary btn-sm">Add</form:button>
						</div>
					</form:form>
				</div>

			</div>
		</div>
		<!-- Row end -->

	</div>
</div>
