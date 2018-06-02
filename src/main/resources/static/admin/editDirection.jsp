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
<c:if test="${not empty direction.id}">
    <h1 align="center" class="m-5">Редактирование страны</h1>
</c:if>
<c:if test="${empty direction.id}">
    <h1 align="center" class="m-5">Добавление направления</h1>
</c:if>
    <div class="row">
        <div class="col-6">
            <form:form modelAttribute="direction" action="/admin/direction/save" method="post">
                <button type="submit" class="btn btn-success"><i class="fa fa-floppy-o" aria-hidden="true"></i> Сохранить</button>
                <form:input path="id" type="hidden"/>
                <form:input path="imageHref" type="hidden"/>
                <div class="form-group form-inline">
                    <form:label class="form-control" path="name">Название</form:label>
                    <form:input class="form-control" path="name" placeholder="направление"/>
                </div>
                <c:if test="${not empty direction.id}">
                <div class="form-group form-inline">
                    <p>${direction.imageHref}</p>
                    <p><img id="image" height="300" width="300" src="${direction.imageHref}"/></p>
                </div>
                </c:if>
            </form:form>
        </div>
        <c:if test="${not empty direction.id}">
        <div class="col-6 align-self-end">
            <h3>Сменить картинку</h3>
            <form method="post" enctype="multipart/form-data" action="/admin/direction/uploadImg?id=${direction.id}">
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
