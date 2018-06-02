<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div class="container_12">
        <div class="grid_12">
            <div class="menu_block">
                <nav class="horizontal-nav full-width horizontalNav-notprocessed">
                    <ul class="sf-menu">
                    <li <c:if test="${'home'.equals(from)}"> class="current"</c:if>><a
                            href="home">НАПРАВЛЕНИЯ</a></li>
                    <li<c:if test="${'tours'.equals(from)}"> class="current"</c:if>><a
                            href="tours">ПОИСК ТУРА</a></li>
                    <li <c:if test="${'news'.equals(from)}"> class="current"</c:if>><a
                            href="news">НОВОСТИ</a></li>
                    <li <c:if test="${'contacts'.equals(from)}"> class="current"</c:if>><a
                            href="contacts">КОНТАКТЫ</a></li>
                    <li<c:if test="${'about'.equals(from)}"> class="current"</c:if>><a
                            href="about">О НАС</a></li>
                   <%-- <li<c:if test="${'info'.equals(from)}"> class="current"</c:if>><a
                            href="info">ТУРИСТАМ</a></li>--%>
                </ul>
                </nav>
                <div class="clear"></div>
            </div>
        </div>
        <div align="center" style="padding-bottom: 20px">
                <a href="home">
                    <img src="images/logo/logo4.png">
                </a>
        </div>
    </div>
</header>