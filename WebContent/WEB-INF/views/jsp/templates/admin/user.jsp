<div class="container">

	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li class="active"><a href="#adminlist" role="tab"
			data-toggle="tab"> <icon class="glyphicon glyphicon-th-list"></icon>
				Admin List
		</a></li>
		<li><a href="#adduseradmin" role="tab" data-toggle="tab"> <icon
					class="glyphicon glyphicon-plus"></icon> Add Admin
		</a></li>
		<li><a href="#recruiterlist" role="tab" data-toggle="tab"> <icon
					class="glyphicon glyphicon-th-list"></icon> Recruiter List
		</a></li>

		<li><a href="#adduserrecruiter" role="tab" data-toggle="tab"> <icon
					class="glyphicon glyphicon-plus"></icon> Add Recruiter
		</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<div class="tab-pane fade active in" id="adminlist">
			<jsp:directive.include file="listAdmin.jsp" />
		</div>
		<div class="tab-pane fade" id="adduseradmin">
			<jsp:directive.include file="addAdminUser.jsp" />
		</div>
		<div class="tab-pane fade" id="recruiterlist">
			<jsp:directive.include file="listRecruiter.jsp" />
		</div>
		<div class="tab-pane fade" id="adduserrecruiter">
			<jsp:directive.include file="addRecruiterUser.jsp" />
		</div>
	</div>

</div>