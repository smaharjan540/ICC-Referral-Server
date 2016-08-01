<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%-- <c:if test="${requestScope.id}"> --%>
<%-- </c:if> --%>
<div id="discussionFourm">
	<c:forEach var="q" items="${questions}">

		<fieldset>
			<legend>
				<strong>Course:</strong> ${q.course.name} <br> <strong>Type:</strong>
				${q.questiontype}<br> <strong>By:</strong> ${q.user.getName()}<br>
				<strong>Email:</strong> ${q.user.email}
			</legend>
			<div class="question">
				<label style="font-style: italic;">Question : </label>
				${q.questionbody}
			</div>
			<div>
				<ul>
					<c:forEach var="a" items="${q.answers}">
						<li>${a.answerbody}<i style="color: green; font-size: 8pt;">
								-By:${a.user.getName()}</i></li>
					</c:forEach>

				</ul>

			</div>
			<a class="btn btn-success answer"
				href="${pageContext.request.contextPath}/student/discussion/answer?q=${q.id}"
				style="margin-right: 10px;">Answer</a>

		</fieldset>


	</c:forEach>
</div>

<style>
fieldset {
	border: 1px groove #ddd !important;
	padding: 0 1.4em 1.4em 1.4em !important;
	margin: 0 0 1.5em 0 !important;
	-webkit-box-shadow: 0px 0px 0px 0px #000;
	box-shadow: 0px 0px 0px 0px #000;
	text-align: left;
	border-radius: 10px;
}

legend {
	width: 25%;
	border: 1px groove #ddd !important;
	border-radius: 7px;
	font-size: 8pt;
	font-style: italic;
}

.answer {
	float: right;
}
</style>
<script>
	function doDeleteQuestion(id) {
		$.ajax({
			type : 'POST',
			url : 'discussion/question/delete',
			data : 'id=' + id,
			success : function(message) {
				window.location.href = "discussion";
				//$('#roleDeleteMsg').html("Deleted!");
			},
			error : function(e) {
				//$('#roleDeleteMsg').html("Failed!");
			}
		});
	}
	
	$("#discussionFaculty").change(function(e) {

		loadDiscussionPageCourses();

	});
	
	$("#discussionCourse").change(function(e) {
		
		window.location.href = "discussion/course/"+$("#discussionCourse").val();
		

	});

	function loadDiscussionPageCourses() {
		$.getJSON("course/jsoncourselist/" + $("#discussionFaculty").val(), function(
				response) {
			$("#discussionCourse option").remove();
			var options = '<option value=""></option>';
			$.each(response, function(index, course) {
				options += '<option value="' + course.id + '">' + course.name
						+ '</option>';
				$("#discussionCourse").html(options);
			});
		});
	}
</script>
