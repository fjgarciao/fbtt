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

    <form:form method="POST" action="/calendar/createCampaign" modelAttribute="countrySelectionQuery">
        <form:errors path="*" cssClass="errorblock" element="div" />

        <p>
            <label>
                <spring:message code="calendar.marketingDay"/>:
            </label>
            <input value="<c:out value="${selectMarketingDayQuery.marketingDay}"/>" size="20" readonly/>

            <label>
                <spring:message code="calendar.year"/>:
            </label>
            <input value="<c:out value="${selectMarketingDayQuery.year}"/>" size="4" readonly/>
        </p>

        <p>
            <label>
                <spring:message code="calendar.chooseCountries"/>:
            </label>
        </p>
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

            <input type="submit" name="submit" class="button" value="<spring:message code="calendar.createCampaigns"/>"/>
        </p>
    </form:form>

</body>

</html>