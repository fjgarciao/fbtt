<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="script" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title><spring:message code="index.title"/> - <spring:message code="calendar.campaignValues"/></title>

    <%--
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    --%>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</head>

<body>
    <h2><spring:message code="calendar.campaignValues"/></h2>

    <form:form method="POST" action="/calendar/selectCountries" modelAttribute="createCampaignQuery">
        <form:label path="campaignName">
            <spring:message code="calendar.campaignName"/>:
        </form:label>
        <form:input path="campaignName" size="30"/>
        <br/>

        <h3><spring:message code="calendar.segmentation"/></h3>

        <form:label path="minAge">
            <spring:message code="calendar.minAge"/>:
        </form:label>
        <form:input path="minAge" size="2"/>

        <form:label path="maxAge">
            <spring:message code="calendar.maxAge"/>:
        </form:label>
        <form:input path="maxAge" size="2"/>

        <h3><spring:message code="calendar.marketingDayValues"/></h3>

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
        <br/>

        <input type="submit" name="submit" class="button" value="<spring:message code="calendar.chooseCountries"/>" />
    </form:form>
</body>

</html>