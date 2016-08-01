<%@page session="false"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="xx"></div>
<div class="formcontainer">
	<div class="row">
		<div class="col-md-12 col-sm-6 col-xs-12">

			<div class="panel-body">


				<form class="form-horizontal" id="changePasswordForm">
					<input type="hidden" value="${user.id}" id="changeId" />
					<div class="form-group">
						<label class="col-md-2 control-label">Old password</label>
						<div class="col-md-10">
							<input type="password" class="form-control" id="oldPassword">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">New password</label>
						<div class="col-md-10">
							<input type="password" class="form-control" id="newPassword">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">Confirm password</label>
						<div class="col-md-10">
							<input type="password" class="form-control" id="confirmPassword">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">

							<button type="submit" id="bth-search"
								class="btn btn-primary btn-sm">Change Password</button>
						</div>
						<div id="changePasswordResult"></div>
					</div>
				</form>





			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function($) {

		$("#changePasswordForm").submit(function(event) {

			// Prevent the form from submitting via the browser.
			event.preventDefault();

			changePassword();

		});

	});

	function changePassword() {
		var datas = {}
		datas["id"] = $("#changeId").val();
		datas["oldPassword"] = $("#oldPassword").val();
		datas["newPassword"] = $("#newPassword").val();
		datas["confirmPassword"] = $("#confirmPassword").val();
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "profile/changepassword",
			data : JSON.stringify(datas),
			success : function(data) {
				console.log("SUCCESS: ", data);
				$('#changePasswordResult').html(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				$('#changePasswordResult').html(e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});

	}
</script>