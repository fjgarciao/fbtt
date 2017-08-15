<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="script" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title><spring:message code="index.title"/> - <spring:message code="calendar.campaignValues"/></title>
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

        <form:label path="ageMin">
            <spring:message code="calendar.ageMin"/>:
        </form:label>
        <form:input path="ageMin" size="2"/>

        <form:label path="ageMax">
            <spring:message code="calendar.ageMax"/>:
        </form:label>
        <form:input path="ageMax" size="2"/>

        <h3><spring:message code="calendar.budget"/></h3>

        <form:label path="lifeTimeBudget">
            <spring:message code="calendar.lifeTimeBudget"/>:
        </form:label>
        <form:input path="lifeTimeBudget" size="7"/>
        <spring:message code="calendar.lifeTimeBudget.noCents"/>

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

        <p>
            <input type="submit" name="submit" class="button" value="<spring:message code="calendar.chooseCountries"/>" />
        </p>
    </form:form>

    <jsp:include page="bottom.jsp"/>
</body>

</html>