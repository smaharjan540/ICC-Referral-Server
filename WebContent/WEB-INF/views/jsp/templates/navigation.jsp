<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<security:authorize access="hasRole('PROFESSORr')">
	<jsp:directive.include file="professor/navigation.jsp" />
</security:authorize>