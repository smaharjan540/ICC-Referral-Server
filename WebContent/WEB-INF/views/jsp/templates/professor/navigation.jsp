<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="navigation">

	<nav class="navbar navbar-default">
		<div class="container-fluid" style="margin-left: -15px;">
			<div class="navbar-header">
				<%-- <c:forEach var="faculty" items="${navfaculties}">
					<a class="btn btn-primary btn-sm"
						href="${pageContext.request.contextPath}/professor/faculty/${faculty.getKey()}">${faculty.getValue()}</a>
				</c:forEach> --%>
				<%-- <div class="dropdown">
					<button class="btn btn-primary dropdown-toggle" type="button"
						data-toggle="dropdown" style="height: 50px;">
						Choose Faculty ${navfaculties.size()}<span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a
							href="${pageContext.request.contextPath}/professor/faculty/${faculty}">${navfaculties}</a></li>
						<c:forEach var="faculty" items="${navfaculties}">
							<li><a
								href="${pageContext.request.contextPath}/professor/faculty/${faculty}">${faculty}</a></li>
						</c:forEach>
					</ul>
					
					
				</div> --%>

				<c:forEach var="faculty" items="${navfaculties}">
					<ul class="nav navbar-nav">
						<li class="active" style="border-right: 1px solid gray;"><a
							href="${pageContext.request.contextPath}/professor/faculty/${faculty.getKey()}">${faculty.getValue()}</a></li>
						<!-- <li><a href="#">Page 1</a></li>
				<li><a href="#">Page 2</a></li>
				<li><a href="#">Page 3</a></li>
			</ul> 
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="#">Page 1</a></li>
				<li><a href="#">Page 2</a></li>
				<li><a href="#">Page 3</a></li> -->
					</ul>
				</c:forEach>
			</div>
		</div>
	</nav>


</div>