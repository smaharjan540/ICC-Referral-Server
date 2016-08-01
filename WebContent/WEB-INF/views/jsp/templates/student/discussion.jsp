<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">

	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">

		<li class="active"><a href="#chooseCourse" role="tab"
			data-toggle="tab"> <icon class="glyphicon glyphicon-th"></icon>
				Choose Course
		</a></li>
		<li><a href="#forum" role="tab" data-toggle="tab"> <icon
					class="glyphicon glyphicon-th-list"></icon> Forum
		</a></li>
		<li><a href="#question" role="tab" data-toggle="tab"> <icon
					class="glyphicon glyphicon-question-sign"></icon> Post Question
		</a></li>

	</ul>

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
		<div class="tab-pane fade" id="forum">
			<div class="panel panel-default">
				<div class="formcontainer">
					<!-- Row start -->
					<div class="row">
						<div class="col-md-12 col-sm-6 col-xs-12">

							<div class="panel-body">
								<jsp:directive.include file="discussionforum.jsp" />
							</div>

						</div>
					</div>
					<!-- Row end -->

				</div>
			</div>

		</div>
		<div class="tab-pane fade" id="question">
			<div class="panel panel-default">
				<div class="formcontainer">
					<!-- Row start -->
					<div class="row">
						<div class="col-md-12 col-sm-6 col-xs-12">

							<div class="panel-body">
								<form:form class="form-horizontal row-border" action="question"
									method="post" modelAttribute="question">

									<div class="form-group">
										<label class="col-md-2 control-label">Courses</label>
										<div class="col-md-10">
											<%-- <select id="selectSaveCourse" class="form-control">
											<option value=""></option>
											<c:forEach var="c" items="${courses}">
												<option value="${c.getKey()}">${c.getValue()}</option>

											</c:forEach>

										</select> --%>

											<form:select path="course.id" class="form-control"
												style="width: 250px; float: left;margin-right: 10px;; ">
												<form:options items="${courses}" />

											</form:select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">Category</label>
										<div class="col-md-10">
											<form:select path="questiontype" class="form-control">
												<form:option value="General">General</form:option>
												<form:option value="Assignment">Assignment</form:option>
												<form:option value="Lecture">Lecture</form:option>

											</form:select>
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">Question</label>
										<div class="col-md-10">
											<form:textarea path="questionbody" class="form-control" />

										</div>

									</div>
									<div class="form-group">
										<form:button class="btn btn-primary btn-sm">Post</form:button>
									</div>

								</form:form>

							</div>
						</div>
						<!-- Row end -->

					</div>
				</div>
			</div>
		</div>

	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
	});

	$("#selectCourse").change(function(e) {
		window.location.href = "${pageContext.request.contextPath}/student/discussion/" + $("#selectCourse").val();

	});
</script>
