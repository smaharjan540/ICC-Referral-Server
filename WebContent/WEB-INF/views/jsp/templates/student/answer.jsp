<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">

	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li class="active"><a href="#answer" role="tab" data-toggle="tab">
				<icon class="glyphicon glyphicon-folder-open"></icon> Answer
		</a></li>

	</ul>

	<div class="tab-content">
		<div class="tab-pane fade active in" id="answer">
			<div class="panel panel-default">
				<div class="formcontainer">
					<!-- Row start -->
					<div class="row">
						<div class="col-md-12 col-sm-6 col-xs-12">

							<div class="panel-body">

								<form:form class="form-horizontal row-border" action=""
									method="post" modelAttribute="answer">
									
									<%-- <form:input path="id"/> --%>

									<div class="form-group">
										<label class="col-md-2 control-label">Answer</label>
										<div class="col-md-10">
											<form:textarea path="answerbody" class="form-control" />

										</div>

									</div>
									<div class="form-group">
										<form:button class="btn btn-primary btn-sm">Post</form:button>
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