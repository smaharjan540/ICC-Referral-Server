<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="formcontainer">
	<div class="row">
		<div class="col-md-12 col-sm-6 col-xs-12">

			<div class="panel-body">
				<form:form method="POST" action="profile/savephoto"
					modelAttribute="user">
					<form:hidden path="id" value="${user.id}" />
					<div class="form-group">
						<img src="resources/${empty user.imageUrl?'no_image.jpg':user.imageUrl}" class="img-thumbnail"
							alt="${user.firstname}" width="120"
							style="float: left; margin-right: 10px;">
						<div id="photoDragDropHandler" class="dragDropHandler">Drag
							& Drop!</div>
						<form:hidden path="imageUrl" class="form-control"
							value="${user.imageUrl}" />
						<div id="statusPhoto"></div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">

							<button type="submit" id="bth-search"
								class="btn btn-primary btn-sm">Upload</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var photo = $("#photoDragDropHandler");
		initializeDragDrop(photo);

		/* initializeTask(); */
	});
</script>
<script src="<c:url value='/resources/js/myuploadfunction.js' />"></script>