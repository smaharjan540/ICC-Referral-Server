<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">

	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li class="active"><a href="#chooseCourse" role="tab"
			data-toggle="tab"> <icon class="glyphicon glyphicon-th"></icon>
				Choose Course
		</a></li>
		<li><a href="#resourcelist" role="tab" data-toggle="tab"> <icon
					class="glyphicon glyphicon-th-list"></icon> Resource List
		</a></li>
		<li><a href="#addresource" role="tab" data-toggle="tab"> <icon
					class="glyphicon glyphicon-plus"></icon> Add Resource
		</a></li>

	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<div class="tab-pane fade active in" id="chooseCourse">
			<div class="panel panel-default">
				<div class="formcontainer">
					<!-- Row start -->
					<div class="row">
						<div class="col-md-12 col-sm-6 col-xs-12">

							<div class="panel-body">
								<div class="form-group">
									<label class="col-md-2 control-label">Choose your
										course</label>
									<div class="col-md-10">
										<select id="selectCourse" class="form-control">
											<option value=""></option>
											<c:forEach var="c" items="${courses}">
												<option value="${c.getKey()}">${c.getValue()}</option>

											</c:forEach>

										</select>
									</div>
								</div>

							</div>

						</div>
					</div>

				</div>
			</div>

		</div>
		<!-- Tab panes -->
		<div class="tab-pane fade" id="resourcelist">
			<div class="panel panel-default">
				<div class="formcontainer">
					<!-- Row start -->
					<div class="row">
						<div class="col-md-12 col-sm-6 col-xs-12">

							<div class="panel-body">
								<table class="table table-striped eaTable">
									<thead>
										<tr>
											<th>Posted By</th>
											<th>Posted Date</th>
											<th>Type</th>
											<th>Size</th>
											<th>File Name</th>
											<th>Course</th>
											<th>View/Download</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="re" items="${resources}">
											<tr>
												<td>${re.user.getFullName()}</td>
												<td>${re.postedDate}</td>
												<td>${re.resourcetype}</td>
												<td>${re.resourcesize}</td>
												<td>${re.resourceurl}</td>
												<td>${re.course.name}</td>
												<!-- <td><input type="submit" value="View"
													class="btn btn-success"></td> -->
												<td><a class="btn btn-success"
													href="${pageContext.request.contextPath}/resources/${re.resourceurl}"
													target="_blank">View/Download</a></td>
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
		<div class="tab-pane fade" id="addresource">
			<div class="panel panel-default">
				<div class="formcontainer">
					<!-- Row start -->
					<div class="row">
						<div class="col-md-12 col-sm-6 col-xs-12">

							<div class="panel-body">

								<form:form class="form-horizontal row-border" action=""
									method="post" modelAttribute="resource">

									<div class="form-group">
										<label class="col-md-2 control-label">Courses</label>
										<div class="col-md-10">
											<form:select id="coursesId" path="course.id"
												class="form-control"
												style="width: 250px; float: left;margin-right: 10px;">
												<form:options items="${courses}" />

											</form:select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">Description</label>
										<div class="col-md-10">
											<form:textarea path="description" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Upload Resource</label>
										<div id="studentResourceDragDrop" class="dragDropHandler">Drag
											& Drop!</div>
										<form:input path="resourceurl" class="form-control"
											value="${course.resourceurl}" />
										<form:input path="resourcetype" class="form-control"
											value="${course.resourceurl}" />
										<form:input path="resourcesize" class="form-control"
											value="${course.resourceurl}" />
										<div id="resourceStatus"></div>
									</div>

									<div class="form-group">
										<form:button class="btn btn-primary btn-sm">Add & Upload</form:button>
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
	$(document).ready(function() {
		var photo = $("#studentResourceDragDrop");
		initializeDragDrop(photo);
	});

	$("#selectCourse")
			.change(
					function(e) {
						window.location.href = "${pageContext.request.contextPath}/student/resource/"
								+ $("#selectCourse").val();

					});
</script>
<script src="<c:url value='/resources/js/myuploadfunction.js' />"></script>