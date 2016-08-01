<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">

	<ul class="nav nav-tabs" role="tablist">
		<li class="active"><a href="#list" role="tab" data-toggle="tab">
				<icon class="glyphicon glyphicon-th-list"></icon>Email
		</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
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
											<th>Email From</th>
											<th>Email To</th>
											<th>Type</th>
											<th>Created Date</th>
											<th>Sent Date</th>
											<th>Status</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="e" items="${emails}">
											<tr>
												<td>${e.emailfrom}</td>
												<td>${e.emailto}</td>
												<td>${e.type}</td>
												<td>${e.createddate}</td>
												<td>${e.sentdate}</td>
												<td>${e.status}</td>
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