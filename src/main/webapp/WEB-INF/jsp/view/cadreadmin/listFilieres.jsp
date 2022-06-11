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
    <h3>Liste des filieres</h3>
  </div>

  <div>



  </div><p style="text-align:right"><a href="${pageContext.request.contextPath}/cadreadmin/exportFilieres"><img
        src="${pageContext.request.contextPath}/resources/img/excel.png" width="30">Export Excel</p></a>

  <table class="table">
    <thead>
    <tr>
      <th scope="col">Titre de filiere</th>
      <th scope="col">code de filiere</th>
      <th scope="col">Année d'accrédation</th>
      <th scope="col">Année de Fin d' accreditation</th>
      <th scope="col">Cordinateur de filiere</th>
      <th>Actions</th>
    </tr>
    </thead>

    <c:forEach items="${filiereList}" var="f">
      <tr>

        <td><c:out value="${f.titreFiliere}" /></td>
        <td><c:out value="${f.codeFiliere}" /></td>
        <td><c:out value="${f.anneeaccreditation}" /></td>
        <td><c:out value="${f.anneeFinaccreditation}" /></td>
        <td><c:out value="${f.enseignant.nom}" /></td>


        <td>
          <ul><a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/cadreadmin/deleteFiliere/${f.idFiliere}">Supprimer</a>

            <a class="btn btn-primary" href="${pageContext.request.contextPath}/cadreadmin/updateFiliereForm/${f.idFiliere}">Modifier</a>


          </ul>
        </td>

      </tr>

    </c:forEach>

  </table>
</div>

</html>
