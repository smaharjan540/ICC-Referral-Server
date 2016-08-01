<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:getAsString name="title" /></title>
<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link href="<c:url value='/resources/css/leftmenu.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/resources/css/ea.css' />" rel="stylesheet"></link>
<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
<link href="<c:url value='/resources/css/bootstrap-formhelpers.css' />"
	rel="stylesheet"></link>
<link
	href="<c:url value='/resources/css/bootstrap-formhelpers.min.css' />"
	rel="stylesheet"></link>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
<script src="<c:url value='/resources/js/ea.js' />"></script>
<script src="<c:url value='/resources/js/service/user_service.js' />"></script>
<script src="<c:url value='/resources/js/jquery.js' />"></script>
<script src="<c:url value='/resources/js/bootstrap.js' />"></script>
<script src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/resources/js/leftmenu.js' />"></script>
<script src="<c:url value='/resources/js/bootstrap-formhelpers.js' />"></script>
<script
	src="<c:url value='/resources/js/bootstrap-formhelpers.min.js' />"></script>
<script src="<c:url value='/resources/js/vendor/jquery.ui.widget.js' />"></script>
<script src="<c:url value='/resources/js/jquery.iframe-transport.js' />"></script>
<script src="<c:url value='/resources/js/jquery.fileupload.js' />"></script>

<script
	src="<c:url value='/resources/js/controller/user_controller.js' />"></script>
</head>
<body ng-app="enterpriseApplication" class="ng-cloak">
	<div id="mainWrapper">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div id="body">

			<div id="leftmenu">
				<tiles:insertAttribute name="leftmenu" />
			</div>
			<div id="content">
				<tiles:insertAttribute name="navigation" />
				<tiles:insertAttribute name="content" />
			</div>
		</div>


		<div class="col-md-12" id="footer" style="border-top: 1px solid gray">
			<tiles:insertAttribute name="footer" />
		</div>

	</div>
</body>
</html>