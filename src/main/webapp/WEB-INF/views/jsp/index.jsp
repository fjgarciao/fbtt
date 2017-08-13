<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title><spring:message code="index.title"/></title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</head>

<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#"><spring:message code="index.title"/></a>
            </div>
        </div>
    </nav>

    <div class="jumbotron">
        <div class="container">
            <h1><spring:message code="index.title"/></h1>
            <%--
            <p>
                <c:if test="${not empty msg}">
                    Hello ${msg}
                </c:if>

                <c:if test="${empty msg}">
                    Welcome Welcome!
                </c:if>
            </p>
            <p>
                <a class="btn btn-primary btn-lg"
                   href="#" role="button">Learn more</a>
            </p>
            --%>
        </div>
    </div>

    <div class="container">

        <div class="row">
            <div class="col-md-4">
                <h2>Heading</h2>
                <p>ABC</p>
                <p>
                    <a class="btn btn-default" href="#" role="button">View details</a>
                </p>
            </div>
            <div class="col-md-4">
                <h2>Heading</h2>
                <p>ABC</p>
                <p>
                    <a class="btn btn-default" href="#" role="button">View details</a>
                </p>
            </div>
            <div class="col-md-4">
                <h2>Heading</h2>
                <p>ABC</p>
                <p>
                    <a class="btn btn-default" href="#" role="button">View details</a>
                </p>
            </div>
        </div>


        <hr>
        <footer>
            <p>© Mkyong.com 2015</p>
        </footer>
    </div>
</body>

</html>