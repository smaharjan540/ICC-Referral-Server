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
								<th>First Name</th>
								<th>Last Name</th>
								<th>Email</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="ru" items="${recruiterUsers}">
								<tr>
									<td>${ru.firstname}</td>
									<td>${ru.lastname}</td>
									<td>${ru.email}</td>
									<td><input type="submit"
										onclick="javascript:doActiveSchoolUser(${ru.id},${!ru.active});return false;"
										value="${!ru.active?'Activate':'Deactivate'}"
										class="btn ${!ru.active?'btn-success':'btn-danger'}"></td>
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
function doActiveSchoolUser(id,active) {
		$.ajax({
			type : 'POST',
			url : 'school/activate',
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