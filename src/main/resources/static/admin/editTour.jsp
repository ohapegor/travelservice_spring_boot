<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../fragments/headTagAdmin.jsp"/>
</head>
<body>
<jsp:include page="../fragments/adminHeader.jsp"/>
<div class="container">
<c:if test="${not empty tour.id}">
    <h1 align="center" class="m-5">Редактирование горящего тура Id=${tour.id}</h1>
</c:if>
<c:if test="${empty tour.id}">
    <h1 align="center" class="m-5">Добавление горящего тура</h1>
</c:if>
    <div class="row">
        <form:form modelAttribute="tour" action="/admin/tour/save" method="post">
            <button type="submit" class="btn btn-success"><i class="fa fa-floppy-o" aria-hidden="false"></i> Сохранить</button>
            <div class="col-6">
                <table class="table table-hover">
                    <form:input path="id" type="hidden"/>
                    <form:input path="imageHref" type="hidden"/>
                    <tr>
                        <td><form:label path="date">Дата</form:label></td>
                        <td><form:input class="form-control" path="date" cssStyle="width: 400px"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="country">Страна</form:label></td>
                        <td><form:input class="form-control" path="country"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="city">Город</form:label></td>
                        <td><form:input class="form-control" path="city"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="hotel">Отель</form:label></td>
                        <td><form:input class="form-control" path="hotel"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="minPrice">Цена</form:label></td>
                        <td><form:input class="form-control" path="minPrice"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="updated">Обновлен</form:label></td>
                        <td><form:input disabled="true" class="form-control" path="updated"/></td>
                    </tr>
                    <c:if test="${not empty tour.id}">
                    <tr>
                        <td><label for="image">Картинка</label></td>
                        <td><p>${tour.imageHref}</p>
                            <p><img id="image" src="${tour.imageHref}" width="300px" height="300px"/></p>
                        </td>
                    </tr>
                    </c:if>
                </table>
            </div>
        </form:form>
        <c:if test="${not empty tour.id}">
            <div class="col-6 align-self-end">
                <h3>Сменить картинку</h3>
                <form method="post" enctype="multipart/form-data" action="/admin/tour/uploadImg?id=${tour.id}">
                    <div class="form-inline">
                        <input class="form-control-file" type="file" id="file" name="image"/>
                        <button class="btn btn-danger" type="submit"><i class="fa fa-upload" aria-hidden="true"></i> Загрузить</button>
                    </div>
                </form>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
