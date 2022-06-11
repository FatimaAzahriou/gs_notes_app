<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>


<jsp:include page="../fragments/adminHeader.jsp" />

<div class="container">

    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">

            <jsp:include page="../fragments/menu2.jsp" />

        </div>
    </nav>






    <div>
        <h3>Liste des modules</h3>
    </div>

    <div>



    </div><p style="text-align:right"><a href="${pageContext.request.contextPath}/cadreadmin/exportFilieres"><img
        src="${pageContext.request.contextPath}/resources/img/excel.png" width="30">Export Excel</p></a>
    <div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Titre</th>
                <th scope="col">Alias</th>
                <th scope="col">Niveau</th>
                <th>Actions</th>
            </tr>
            </thead>

            <c:forEach items="${moduleList}" var="p">
                <tr>
                    <td><c:out value="${p.titre}" /></td>
                    <td><c:out value="${p.code}" /></td>
                    <td><c:out value="${p.niveau.titre}" /></td>
                        <%--                <td><c:out value="${p.filiere.titreFiliere}" /></td>--%>
                    <td>
                        <ul>
                            <a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/cadreadmin/deleteModule/${p.idModule}">Supprimer</a></li>

                                 <a class="btn btn-primary" href="${pageContext.request.contextPath}/cadreadmin/updateModuleForm/${p.idModule}/">Modifier</a></li>


                        </ul>
                    </td>

                </tr>

            </c:forEach>

        </table>
    </div>

</html>
