<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title><spring:message code="index.title"/> - <spring:message code="calendar.results"/></title>
</head>

<body>

    <h1><spring:message code="calendar.results"/></h1>

    <h2>Created Campaign Data:</h2>
    <pre>
<c:out value="${campaignData}"/>
    </pre>

    <h2>Created Ad Sets:</h2>
    <pre>
<c:out value="${adSets}"/>
    </pre>

    <jsp:include page="bottom.jsp"/>
</body>

</html>