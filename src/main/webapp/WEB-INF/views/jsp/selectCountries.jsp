<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title><spring:message code="index.title"/> - <spring:message code="calendar.chooseCountries"/></title>
</head>

<body>

    <form:form method="POST" action="/calendar/createCampaign" modelAttribute="countrySelectionQuery">

        <h2><spring:message code="calendar.campaignValues"/></h2>

        <p>
            <label>
                <spring:message code="calendar.campaignName"/>
            </label>
            <input value="<c:out value="${createCampaignQuery.campaignName}"/>" size="30" readonly/>

            <h3><spring:message code="calendar.segmentation"/></h3>

            <label>
                <spring:message code="calendar.ageMin"/>:
            </label>
            <input value="<c:out value="${createCampaignQuery.ageMin}"/>" size="2" readonly/>

            <label>
                <spring:message code="calendar.ageMax"/>:
            </label>
            <input value="<c:out value="${createCampaignQuery.ageMax}"/>" size="2" readonly/>

            <h3><spring:message code="calendar.budget"/></h3>

            <label>
                <spring:message code="calendar.lifeTimeBudget"/>:
            </label>
            <input value="<c:out value="${createCampaignQuery.lifeTimeBudget}"/>" size="7" readonly/>
            <spring:message code="calendar.lifeTimeBudget.noCents"/>

            <h3><spring:message code="calendar.marketingDayValues"/></h3>

            <label>
                <spring:message code="calendar.marketingDay"/>:
            </label>
            <input value="<c:out value="${createCampaignQuery.marketingDay}"/>" size="20" readonly/>

            <label>
                <spring:message code="calendar.year"/>:
            </label>
            <input value="<c:out value="${createCampaignQuery.year}"/>" size="4" readonly/>
        </p>

        <h2><spring:message code="calendar.chooseCountries"/></h2>

        <p>
            <form:label path="countries">
                <spring:message code="calendar.countries"/>:
            </form:label>
            <form:select path="countries" multiple="true" size="10" cssStyle="vertical-align: middle;">
                <c:forEach items="${countriesData}" var="entry">
                    <fmt:formatDate value="${entry.value}" pattern="EEE, d MMM" var="date"/>
                    <form:option value="${entry.key.alpha2}|${entry.value.time}" label="${entry.key.name} [ ${date} ]"/>
                </c:forEach>
            </form:select>
            <br/>

            <form:label path="startOffsetR">
                <spring:message code="calendar.startOffset"/>:
            </form:label>
            <form:radiobutton path="startOffsetR" value="0"/><spring:message code="calendar.startOffset.before"/>:
            <form:radiobutton path="startOffsetR" value="1"/><spring:message code="calendar.startOffset.after"/>:
            <form:label path="startOffsetDays">
                <spring:message code="calendar.startOffset.days"/>:
            </form:label>
            <form:input path="startOffsetDays" size="3" value="0"/>
            <br/>

            <form:label path="endOffsetR">
                <spring:message code="calendar.endOffset"/>:
            </form:label>
            <form:radiobutton path="endOffsetR" value="0"/><spring:message code="calendar.endOffset.before"/>:
            <form:radiobutton path="endOffsetR" value="1"/><spring:message code="calendar.endOffset.after"/>:
            <form:label path="endOffsetDays">
                <spring:message code="calendar.endOffset.days"/>:
            </form:label>
            <form:input path="endOffsetDays" size="3" value="0"/>
            <br/>
        </p>

        <p>
            <input type="submit" name="submit" class="button" value="<spring:message code="calendar.createCampaign"/>"/>
        </p>
    </form:form>

    <jsp:include page="bottom.jsp"/>
</body>

</html>