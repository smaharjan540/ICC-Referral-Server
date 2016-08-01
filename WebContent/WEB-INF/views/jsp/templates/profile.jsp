<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<div class="container">
	<!-- <div class="generic-container" ng-controller="UserController as ctrl"> -->
	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tabmyprofile">
		<li class="active"><a href="#myprofile" role="tab"
			data-toggle="tab"> <icon class="fa fa-user"></icon> My Profile
		</a></li>
		<li><a href="#editprofile" role="tab" data-toggle="tab"> <icon
					class="glyphicon glyphicon-pencil"></icon> Edit Profile
		</a></li>
		<li><a href="#changepassword" role="tab" data-toggle="tab"> <icon
					class="glyphicon glyphicon-edit"></icon> Change Password
		</a></li>
		<li><a href="#changephoto" role="tab" data-toggle="tab"> <icon
					class="glyphicon glyphicon-edit"></icon> Change Photo
		</a></li>
	</ul>



	<!-- Tab panes -->
	<div class="tab-content">

		<div class="tab-pane fade ${user.error?'':'active in'}" id="myprofile">
			<div class="panel panel-default">
				<table class="table table-striped eaTable">



					<c:if test="${!user.error}">
					</c:if>
					<tr>
						<td class="col-md-2 control-label">Photo</td>
						<td><img
							src="resources/${empty user.imageUrl?'no_image.jpg':user.imageUrl}"
							class="img-thumbnail" alt="${user.firstname}" width="120"></td>
					</tr>

					<tr>
						<td class="col-md-2 control-label">Your Identification Number</td>
						<td><span class="label label-default">${user.usernumber}</span></td>
					</tr>
					<tr>
						<td class="col-md-2 control-label">Name</td>
						<td><span class="label label-default">${user.prefix}
								${user.firstname} ${user.lastname}</span></td>
					</tr>
					<tr>
						<td class="col-md-2 control-label">Email</td>
						<td><span class="label label-default">${user.email}</span></td>
					</tr>

					<tr>
						<td class="col-md-2 control-label">Contact Number</td>
						<td><span class="label label-default">${user.contactNumber}</span></td>
					</tr>
					<%-- <tr>
						<td class="col-md-2 control-label">Home Country</td>
						<td>
							<div class="bfh-selectbox bfh-countries"
								data-country="${user.homeCountry}" data-flags="true">
								<input type="hidden" value="${user.homeCountry}"> <a
									class="bfh-selectbox-toggle" role="button"
									data-toggle="bfh-selectbox" href="#"> <span
									class="bfh-selectbox-option input-medium" data-option=""></span>
									<b class="caret"></b>
								</a>
								<div class="bfh-selectbox-options">
									<input type="text" class="bfh-selectbox-filter" name="country"
										disabled="disabled">
									<div role="listbox">
										<ul role="option">
										</ul>
									</div>
								</div>
							</div>
						</td>
					</tr> --%>

					<tr>
						<td class="col-md-2 control-label">Country</td>
						<td>
							<div class="bfh-selectbox bfh-countries"
								data-country="${user.country}" data-flags="true">
								<input type="hidden" value="${user.country}"> <a
									class="bfh-selectbox-toggle" role="button"
									data-toggle="bfh-selectbox" href="#"> <span
									class="bfh-selectbox-option input-medium" data-option=""></span>
									<b class="caret"></b>
								</a>
								<div class="bfh-selectbox-options">
									<input type="text" class="bfh-selectbox-filter" name="country"
										disabled>
									<div role="listbox">
										<ul role="option">
										</ul>
									</div>
								</div>
							</div>
						</td>
					</tr>

					<tr>
						<td class="col-md-2 control-label">City</td>
						<td><span class="label label-default">${user.city}</span></td>
					</tr>

					<tr>
						<td class="col-md-2 control-label">State</td>
						<td><span class="label label-default">${user.state}</span></td>
					</tr>

					<tr>
						<td class="col-md-2 control-label">Zip</td>
						<td><span class="label label-default">${user.zip}</span></td>
					</tr>
					<tr>
						<td class="col-md-2 control-label">Address</td>
						<td><span class="label label-default">${user.address}</span></td>
					</tr>

					<tr>
						<td class="col-md-2 control-label">Effective Date</td>
						<td><span class="label label-default">${user.effectivedate}</span></td>
					</tr>
				</table>
			</div>
		</div>

		<div class="tab-pane fade" id="changepassword">
			<jsp:directive.include file="changepassword.jsp" />
		</div>
		<div class="tab-pane fade" id="changephoto">

			<jsp:directive.include file="uploadphoto.jsp" />
			<%-- <jsp:directive.include file="changephoto.jsp" /> --%>
		</div>
		<div class="tab-pane fade ${user.error?'active in':''}"
			id="editprofile">
			<div class="panel panel-default">
				<div class="formcontainer">
					<!-- Row start -->
					<div class="row">
						<div class="col-md-12 col-sm-6 col-xs-12">

							<div class="panel-body">
								<form:form class="form-horizontal row-border" action="profile"
									method="post" modelAttribute="user">

									<form:hidden path="id" value="${user.id}" />

									<div class="form-group">
										<label class="col-md-2 control-label">Photo</label>
										<div class="col-md-10" style="text-align: left;">
											<img
												src="resources/${empty user.imageUrl?'no_image.jpg':user.imageUrl}"
												class="img-thumbnail" alt="Cinque Terre" width="120">
										</div>
									</div>

									<%-- <security:authorize
										access="hasRole('ADMIN')"> --%>
									<div class="form-group">
										<label class="col-md-2 control-label">First Name</label>
										<div class="col-md-10">
											<form:select path="prefix" class="form-control"
												style="width: 80px; float: left;margin-right: 10px;; ">
												<form:option value="Mr." selected="selected">Mr.</form:option>
												<form:option value="Mrs.">Mrs.</form:option>
												<form:option value="Prof.">Prof.</form:option>
												<form:option value="Dr.">Dr.</form:option>
												<form:option value="Asst.Prof.">Asst.Prof.</form:option>

											</form:select>
											<form:input path="firstname" class="form-control" />
											<form:errors path="firstname" cssClass="error" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Last Name</label>
										<div class="col-md-10">
											<form:input path="lastname" class="form-control" />
										</div>
									</div>
									<%-- </security:authorize> --%>
									<div class="form-group">
										<label class="col-md-2 control-label">Email</label>
										<div class="col-md-10">
											<form:input path="email" class="form-control" />
											<form:errors path="firstname" cssClass="error" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Contact Number</label>
										<div class="col-md-10">
											<form:input path="contactNumber" class="form-control" />
										</div>
									</div>
									<div class="form-group" style="text-align: left;">
										<label class="col-md-2 control-label">Country</label>
										<div class="col-md-10">
											<form:select path="country"
												class="input-medium bfh-countries"
												data-country="${user.country}" style="height:35px;" />
											<!-- <select class="input-medium bfh-countries" data-country="US"></select> -->
											<%-- <div class="bfh-selectbox bfh-countries" data-country="${user.country}"
												data-flags="true">
												<form:hidden path="country"/>
												<input type="hidden" value="${user.country}" name="country"> <a
													class="bfh-selectbox-toggle" role="button"
													data-toggle="bfh-selectbox" href="#"> <span
													class="bfh-selectbox-option input-medium" data-option=""></span>
													<b class="caret"></b>
												</a>
												<div class="bfh-selectbox-options">
												<form:input path="country" class="bfh-selectbox-filter"/>
													<!-- <input type="text" class="bfh-selectbox-filter" -->
														<!-- name="country"> -->
													<div role="listbox">
														<ul role="option">
														</ul>
													</div>
												</div>
											</div> --%>


										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">City</label>
										<div class="col-md-10">
											<form:input path="city" class="form-control" />

										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">State</label>
										<div class="col-md-10">
											<form:input path="state" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Zip</label>
										<div class="col-md-10">
											<form:input path="zip" class="form-control" />
										</div>
									</div>

									<div class="form-group">
										<label class="col-md-2 control-label">Address</label>
										<div class="col-md-10">
											<form:input path="address" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<form:button class="btn btn-primary btn-sm">Update</form:button>
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