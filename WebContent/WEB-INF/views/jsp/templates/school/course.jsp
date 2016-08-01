<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">

	<ul class="nav nav-tabs" role="tablist">
		<li class="active"><a href="#list" role="tab" data-toggle="tab">
				<icon class="glyphicon glyphicon-th-list"></icon>Course List
		</a></li>
		<li><a href="#add" role="tab" data-toggle="tab"> <icon
					class="glyphicon glyphicon-plus"></icon>Add Course
		</a></li>

		<li><a href="#delete" role="tab" data-toggle="tab"> <icon
					class="glyphicon glyphicon-trash"></icon>Delete Course
		</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<div class="tab-pane fade" id="add">
			<div class="panel panel-default">
				<div class="formcontainer">
					<!-- Row start -->
					<div class="row">
						<div class="col-md-12 col-sm-6 col-xs-12">

							<div class="panel-body">
								<form:form class="form-horizontal row-border" id="courseForm"
									modelAttribute="course" action="course" method="post">
									<div class="form-group">
										<label class="col-md-2 control-label">Faculty</label>
										<div class="col-md-10">
											<form:select path="faculty.id" class="form-control"
												style="width: 80px; float: left;margin-right: 10px;; ">
												<form:options items="${facultiesmap}" />

											</form:select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Name</label>
										<div class="col-md-10">
											<form:input path="name" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Description</label>
										<div class="col-md-10">
											<form:textarea path="description" class="form-control" />
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
		<div class="tab-pane fade active in" id="list">
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
											<th>Description</th>
											<th>Faculty</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="c" items="${courses}">
											<tr>
												<td>${c.name}</td>
												<td>${c.description}</td>
												<td>${c.faculty.name}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="tab-pane fade" id="delete">
			<div class="panel panel-default">
				<div class="formcontainer">
					<!-- Row start -->
					<div class="row">
						<div class="col-md-12 col-sm-6 col-xs-12">

							<div class="panel-body">
								<div class="panel-body">
									<table class="table table-striped eaTable">
										<thead>
											<tr>
												<th>Name</th>
												<th>Description</th>
												<th>Delete</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="c" items="${courses}">
												<tr>
													<td>${c.name}</td>
													<td>${c.description}</td>
													<%-- <td><form id="${r.id}-delete"
															action="role/delete.htm?${r.id}" method="POST">
															<input id="id" name="id" type="hidden" value="${r.id}" />
															<button type="submit" id="bth-search"
																class="btn btn-primary btn-sm">Delete</button>

														</form></td> --%>
													<td><input class="delete" type="image" width="24"
														value="submit" src="../resources/img/Delete24.gif"
														onClick="javascript:doDeleteCourse(${c.id});return false;"></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>

								</div>


							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
function doDeleteCourse(id) {
		$.ajax({
			type : 'POST',
			url : 'course/delete',
			data : 'id=' + id,
			success : function(message) {
				window.location.href = "course";
			},
			error : function(e) {
			}
		});
	}
	
/* $("#courseForm").submit(function(e){
    $.ajax({
         url: 'course',
         type: 'post',
         async: false,
         dataType: "json",
         data:$(this).serialize(),
         success: function(data) {
        	 alert(data);
        	 window.location.href = "course";
         }
     })        
}); */
</script>

