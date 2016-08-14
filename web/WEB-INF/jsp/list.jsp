<%@ page import="com.github.apska.webapp.web.HtmlUtil" %>
<%@ page import="com.github.apska.webapp.storage.XmlFileStorage" %>
<%@ page import="com.github.apska.webapp.model.ContactType" %>
<%@ page import="com.github.apska.webapp.model.Resume" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="css/style.css">
    <title>Список всех резюме</title>
</head>
<body>
    <jsp:include page="fragments/header.jsp"/>
    <section>
        <table>
            <tr>
                <td colspan="5" style="text-align: right"><a href="resume?action=create"><img src="img/add.png"/>Добавить Резюме</a></td>
            </tr>
            <tr>
                <td>
                    <table border="1" cellpadding="8" cellspacing="0">
                        <tr>
                            <th>Имя</th>
                            <th>Проживание</th>
                            <th>E-mail</th>
                            <th></th>
                            <th></th>
                        </tr>

                        <%
                            //request.setAttribute("resumeList", WebAppConfig.get().getStorage().getAllSorted());
                            XmlFileStorage storage = new XmlFileStorage("C:\\WEB_APP_JAVA\\JavaWebApp\\file_storage");
                            Collection<Resume> resumes = storage.getAllSorted();
                            request.setAttribute("resumeList", resumes);
                        %>

                        <c:forEach items="${resumeList}" var="resume">
                            <jsp:useBean id="resume" type="com.github.apska.webapp.model.Resume"/>
                            <tr>
                                <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                                <td>${resume.location}</td>
                                <td><%=HtmlUtil.getContact(resume, ContactType.MAIL)%></td>
                                <td><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png"/></a></td>
                                <td><a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"/></a></td>
                            </tr>
                        </c:forEach>

                        <%--
                        <% for(Resume r: storage.getAllSorted()){
                            request.setAttribute("r", r);
                        %>
                        <tr>
                            <td><a href="resume?uuid=${r.uuid}&action=view">${r.fullName}</a></td>
                            <td>${r.location}</td>
                            <td><%=HtmlUtil.getContact(r, ContactType.MAIL)%></td>
                            <td><a href="resume?uuid=${r.uuid}&action=delete"><img src="img/delete.png"/></a></td>
                            <td><a href="resume?uuid=${r.uuid}&action=edit"><img src="img/pencil.png"/></a></td>
                        </tr>
                        <%
                            }
                        %>
                        --%>
                    </table>
                </td>
            </tr>
        </table>
    </section>
    <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
