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
        <h3>Liste des elements</h3>
    </div>

    <div>



    </div><p style="text-align:right"><a href="${pageContext.request.contextPath}/cadreadmin/exportFilieres"><img
        src="${pageContext.request.contextPath}/resources/img/excel.png" width="30">Export Excel</p></a>
    <div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Nom Element</th>
                <th scope="col">Code Element</th>
                <th scope="col">Cofficient Element</th>
                <th scope="col">Module</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <c:forEach items="${elementList}" var="e">
                <tr>
                    <td><c:out value="${e.nom}" /></td>
                    <td><c:out value="${e.code}" /></td>
                    <td><c:out value="${e.currentCoefficient}" /></td>
                    <td><c:out value="${e.module.titre}" /></td>
                    <td>
                        <ul>
                            <a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/cadreadmin/deleteElement/${e.idMatiere}">Supprimer</a></li>

                            <a class="btn btn-primary" href="${pageContext.request.contextPath}/cadreadmin/updateElementForm/${e.idMatiere}/">Modifier</a></li>


                        </ul>
                    </td>

                </tr>

            </c:forEach>

        </table>
    </div>

</div>
</html>
