<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">

	<ul class="nav nav-tabs" role="tablist">
		<li class="active"><a href="#list" role="tab" data-toggle="tab">
				<icon class="glyphicon glyphicon-th-list"></icon>Faculty List
		</a></li>
		<li><a href="#add" role="tab" data-toggle="tab"> <icon
					class="glyphicon glyphicon-plus"></icon>Add Faculty
		</a></li>

		<li><a href="#delete" role="tab" data-toggle="tab"> <icon
					class="glyphicon glyphicon-trash"></icon>Delete Faculty
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
								<form:form class="form-horizontal row-border" action="faculty"
									method="post" modelAttribute="faculty">


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
										</tr>
									</thead>
									<tbody>
										<c:forEach var="f" items="${faculties}">
											<tr>
												<td>${f.name}</td>
												<td>${f.description}</td>
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
											<c:forEach var="f" items="${faculties}">
												<tr>
													<td>${f.name}</td>
													<td>${f.description}</td>
													<%-- <td><form id="${r.id}-delete"
															action="role/delete.htm?${r.id}" method="POST">
															<input id="id" name="id" type="hidden" value="${r.id}" />
															<button type="submit" id="bth-search"
																class="btn btn-primary btn-sm">Delete</button>

														</form></td> --%>
													<td><input class="delete" type="image" width="24"
														value="submit" src="../resources/img/Delete24.gif"
														onClick="javascript:doDeleteFaculty(${f.id});return false;"></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<div id="roleDeleteMsg"></div>

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
function doDeleteFaculty(id) {
		$.ajax({
			type : 'POST',
			url : 'faculty/delete',
			data : 'id=' + id,
			success : function(message) {
				window.location.href = "faculty";
				//$('#roleDeleteMsg').html("Deleted!");
			},
			error : function(e) {
				//$('#roleDeleteMsg').html("Failed!");
			}
		});
	}
</script>

