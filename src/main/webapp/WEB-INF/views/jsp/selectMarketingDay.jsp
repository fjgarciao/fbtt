<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title><spring:message code="index.title"/></title>

    <%--
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    --%>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</head>

<body>
    <form:form method="POST" action="/calendar/selectCountries" modelAttribute="selectMarketingDayQuery">
        <form:errors path="*" cssClass="errorblock" element="div" />

        <form:label path="marketingDay">
            <spring:message code="calendar.marketingDay"/>:
        </form:label>
        <form:select path="marketingDay">
            <form:option value="" label="--- Select ---"/>
            <form:options items="${marketingDays}" itemLabel="name" itemValue="name"/>
        </form:select>

        <form:label path="year">
            <spring:message code="calendar.year"/>:
        </form:label>
        <form:input path="year" size="4"/>

        <input type="submit" name="submit" class="button" value="<spring:message code="calendar.load"/>" />
    </form:form>
</body>

</html>