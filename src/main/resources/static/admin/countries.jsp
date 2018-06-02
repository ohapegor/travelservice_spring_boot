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
    </script>
</head>
<body>
<jsp:include page="../fragments/adminHeader.jsp"/>
<div class="container">
    <h1 align="center" class="m-5">Список стран для горящих туров</h1>
    <a href="/admin/country/add" class="btn btn-primary"><i class="fa fa-paperclip" aria-hidden="true"></i> Добавить</a>
    <table id="countries" class="table table-hover table-striped">
        <thead class="table-dark">
        <tr>
            <th>Id</th>
            <th>Название</th>
            <th>Картинка</th>
            <th>Удалить</th>
            <th>Редактировать</th>
        </thead>
        <tbody>
        <c:forEach items="${countries}" var="country">
            <tr>
                <td>${country.id}</td>
                <td>${country.name}</td>
                <td><p>${country.imageHref}</p>
                    <p><img src="${country.imageHref}" height="200" width="200"></p>
                </td>
                <td>
                    <a href="/admin/country/delete?id=${country.id}" onclick="return confirmDelete()"
                       class="btn btn-danger btn-sm"><i class="fa fa-trash-o fa-lg"></i></a>
                </td>
                <td><a href="/admin/country/edit?id=${country.id}" class="btn btn-secondary btn-sm"><i class="fa fa-cog"></i></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
