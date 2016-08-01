<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="panel panel-default">
	<div class="formcontainer">
		<!-- Row start -->
		<div class="row">
			<div class="col-md-12 col-sm-6 col-xs-12">

				<div class="panel-body">
					<form:form class="form-horizontal row-border" action="user"
						method="post" modelAttribute="user">
						<div class="form-group">
							<label class="col-md-2 control-label">User Type</label>
							<div class="col-md-10">
								<form:select path="prefix" class="form-control" id="usertype"
									style="width: 120px; float: left;margin-right: 10px;; ">
									<form:option value="SCHOOL" selected="selected">SCHOOL</form:option>
									<form:option value="ADMIN">ADMIN</form:option>
								</form:select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label">School Name</label>
							<div class="col-md-10">
								<form:input path="schoolname" class="form-control"
									id="schoolname" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">First Name</label>
							<div class="col-md-10">
								<form:input path="firstname" class="form-control" id="firstname"
									disabled="true" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">Last Name</label>
							<div class="col-md-10">
								<form:input path="lastname" class="form-control" id="lastname"
									disabled="true" />
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
								<form:input path="password" class="form-control" />
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
<script>
	$('#usertype').change(function() {
		if ($('#usertype').val() == "ADMIN") {
			$('#schoolname').prop('disabled', true);
			$('#firstname').prop('disabled', false);
			$('#lastname').prop('disabled', false);
		} else if ($('#usertype').val() == "SCHOOL") {
			$('#schoolname').prop('disabled', false);
			$('#firstname').prop('disabled', true);
			$('#lastname').prop('disabled', true);
		}

	});
</script>