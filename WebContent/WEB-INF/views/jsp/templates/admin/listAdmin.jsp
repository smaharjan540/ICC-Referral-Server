<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="panel panel-default">
	<div class="formcontainer">
		<!-- Row start -->
		<div class="row">
			<div class="col-md-12 col-sm-6 col-xs-12">

				<div class="panel-body">
					<table class="table table-striped eaTable">
						<thead>
							<tr>
								<th>Name</th>
								<th>Email</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="ad" items="${adminUsers}">
								<tr>
									<td>${ad.firstname}${ad.lastname}</td>
									<td>${ad.email}</td>
									<td><input type="submit"
										onclick="javascript:doActiveAdminUser(${ad.id},${!ad.active});return false;"
										value="${!ad.active?'Activate':'Deactivate'}"
										class="btn ${!ad.active?'btn-success':'btn-danger'}"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
		</div>
		<!-- Row end -->

	</div>
</div>

<script type="text/javascript">
function doActiveAdminUser(id,active) {
		$.ajax({
			type : 'POST',
			url : 'admin/activate',
			data : 'id=' + id+'&active='+active,
			success : function(message) {
				window.location.href = "user";
				//$('#roleDeleteMsg').html("Deleted!");
			},
			error : function(e) {
				//$('#roleDeleteMsg').html("Failed!");
			}
		});
	}
</script>