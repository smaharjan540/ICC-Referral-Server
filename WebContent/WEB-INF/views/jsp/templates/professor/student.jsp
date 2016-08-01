<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">

	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li class="active"><a href="#studentlist" role="tab"
			data-toggle="tab"> <icon class="glyphicon glyphicon-th-list"></icon>
				Student Lists
		</a></li>
		<li><a href="#addstudent" role="tab" data-toggle="tab"> <icon
					class="glyphicon glyphicon-plus"></icon> Add Student
		</a></li>

	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<div class="tab-pane fade active in" id="studentlist">
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
											<th>Courses</th>
											<th>Status</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="st" items="${studentUsers}">
											<tr>
												<td>${st.firstname}${st.lastname}</td>
												<td>${st.email}</td>
												<td>
													<ul>
														<c:forEach var="c" items="${st.getCourses()}">
															<li>${c.name}-${c.faculty.name}</li>
														</c:forEach>
													</ul>


												</td>
												<td><input type="submit"
													onclick="javascript:doActiveAdminUser(${st.id},${!st.active});return false;"
													value="${!st.active?'Activate':'Deactivate'}"
													class="btn ${!st.active?'btn-success':'btn-danger'}"></td>
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

		</div>
		<div class="tab-pane fade" id="addstudent">
			<div class="panel panel-default">
				<div class="formcontainer">
					<!-- Row start -->
					<div class="row">
						<div class="col-md-12 col-sm-6 col-xs-12">

							<div class="panel-body">
								<form:form class="form-horizontal row-border"
									action="student/student" method="post" modelAttribute="student">

									<div class="form-group">
										<label class="col-md-2 control-label">Faculty</label>
										<div class="col-md-10">
											<form:select id="facultyId" path="" class="form-control"
												style="width: 80px; float: left;margin-right: 10px;">
												<form:options items="${facultiesmap}" />

											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Courses</label>
										<div class="col-md-10">
											<form:select id="coursesId" path="coursesids"
												class="form-control">
												<form:option value="" />

											</form:select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">First Name</label>
										<div class="col-md-10">
											<form:input path="firstname" class="form-control"
												id="firstname" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Last Name</label>
										<div class="col-md-10">
											<form:input path="lastname" class="form-control"
												id="lastname" />
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
		</div>
	</div>

</div>



<script type="text/javascript">
$(document).ready(function () {
	loadCourses();
});
function doActiveAdminUser(id,active) {
		$.ajax({
			type : 'POST',
			url : 'student/activate',
			data : 'id=' + id+'&active='+active,
			success : function(message) {
				window.location.href = "student";
				//$('#roleDeleteMsg').html("Deleted!");
			},
			error : function(e) {
				//$('#roleDeleteMsg').html("Failed!");
			}
		});
	}
	
$("#facultyId").change(function(e){
	
	loadCourses();
	
});

function loadCourses(){
	 $.getJSON("course/jsoncourselist/"+$("#facultyId").val(), function(response){ 
        $("#coursesId option").remove(); 
            var options = '';
            $.each(response, function(index, course) {
                options += '<option value="' + course.id + '">' + course.name + '</option>';
                $("#coursesId").html(options);
            });
    });
}
</script>

