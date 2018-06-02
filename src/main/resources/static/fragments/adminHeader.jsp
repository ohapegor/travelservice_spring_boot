<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse bg-inverse">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleCenteredNav" aria-controls="navbarsExampleCenteredNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-center" id="navbarsExampleCenteredNav">
        <ul class="nav nav-fill nav-pills flex-column flex-sm-row">
            <li class="nav-item ">
                <a class="nav-link <c:if test="${'tours'.equals(from)}">active</c:if>" href="/admin/tours">Туры<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link  <c:if test="${'countries'.equals(from)}">active</c:if>" href="/admin/countries">Страны</a>
            </li>
            <li class="nav-item">
                <a class="nav-link  <c:if test="${'directions'.equals(from)}">active</c:if>" href="/admin/directions">Направления</a>
            </li>
        </ul>
    </div>
</nav>