<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="headerLeft" style="text-align: right; color: green;">
	Welcome to our page <strong>${username}</strong>! <a
		href="<c:url value="/logout" />">Logout</a>
</div>