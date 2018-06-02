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
    <script type="text/javascript">
        function confirmDelete() {
            if (confirm("Нажмите OK для удаления")) {
                return true;
            } else return false;
        }

        function progress() {
            var current_progress = 0;
            var interval = setInterval(function() {
                current_progress += 5;
                $("#dynamic")
                    .css("width", current_progress + "%")
                    .attr("aria-valuenow", current_progress)
                    .text(current_progress + "% Complete");
                if (current_progress >= 100)
                    clearInterval(interval);
            }, 500);
        };
    </script>
</head>
<body>
<jsp:include page="../fragments/adminHeader.jsp"/>
<div class="container">
    <h1 align="center" class="m-5">Список туров</h1>
    <a class="btn btn-primary m-2" href="/admin/tour/add"><i class="fa fa-paperclip" aria-hidden="true"></i> Добавить</a>
    <a class="btn btn-primary m-2" onclick="progress()" href="/admin/reload">Загрузить туры с сайта</a>
    <c:if test="${not empty reload}">
        <c:if test="${reload}">
            <span class="text-success m-2">Успешно обновлены</span>
        </c:if>
        <c:if test="${not reload}">
            <span class="text-danger m-2">Ошибка, попрробуйте снова</span>
        </c:if>
    </c:if>
    <div class="progress">
        <div id="dynamic" class="progress-bar bg-success progress-bar-striped" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
            <span id="current-progress"></span>
        </div>
    </div>
    <table id="tours" class="table table-hover table-striped">
        <thead class="table-dark">
        <tr>
            <th>Дата</th>
            <th>Страна</th>
            <th>Город</th>
            <th>Отель</th>
            <th>Цена</th>
            <th>Обновлена</th>
            <th>Картинка</th>
            <th>Удалить</th>
            <th>Редактировать</th>
        </thead>
        <tbody>
        <c:forEach items="${tours}" var="tour">
            <tr>
                <td>${tour.date}</td>
                <td>${tour.country}</td>
                <td>${tour.city}</td>
                <td>${tour.hotel}</td>
                <td>${tour.minPrice}</td>
                <td>${tour.printUpdated()}</td>
                <td>
                    <img src="${tour.imageHref}" height="100" width="100">
                </td>
                <td><a href="/admin/tour/delete?id=${tour.id}" onclick="return confirmDelete()" class="btn btn-danger btn-sm"><i class="fa fa-trash-o fa-lg"></i></a></td>
                <td><a href="/admin/tour/edit?id=${tour.id}" class="btn btn-secondary btn-sm"><i class="fa fa-cog"></i></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
