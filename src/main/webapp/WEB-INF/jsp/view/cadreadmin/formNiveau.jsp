<%@page import="com.gsnotes.web.models.FiliereModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
        <h3>Formulaire d'ajout d'un niveau</h3>
    </div>
    <div>
        <f:form action="addNiveau" method="POST" modelAttribute="niveauModel" >
            <div class="row">
                <div class="col">
                    <label>Titre de Niveau</label>
                    <f:input path="titre" type="text" class="form-control"
                             placeholder="titreNiveau" />
                    <f:errors path="titre" class="text-danger" />
                </div>
                <div class="col">
                    <label>Alias de niveau</label>
                    <f:input path="alias" type="text" class="form-control"
                             placeholder="aliasNiveau" />
                    <f:errors path="alias" class="text-danger" />
                </div>
                <div class="col">
                    <label>Associer ce niveau a une filiere</label>

                    <f:select path="filiere" multiple="false" size="1"
                              class="form-control">
                        <f:options items="${filiereList}" itemValue="idFiliere" itemLabel="titreFiliere" />
                    </f:select>


                </div>
            </div>
            <div style="text-align: right">
                <button type="submit" class="btn btn-primary">Enregistrer</button>
                <button type="reset" class="btn btn-secondary">Annuler</button>
            </div>
        </f:form>
    </div>

</div>
</div>
<div class="container">

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Titre de niveau</th>
            <th scope="col">Alias de niveau</th>
            <th scope="col">Filiere de niveau</th>
            <th>Actions</th>
        </tr>
        </thead>

        <c:forEach items="${niveauList}" var="n">
            <tr>
                <td><c:out value="${n.titre}" /></td>
                <td><c:out value="${n.alias}" /></td>
                <td><c:out value="${n.filiere.titreFiliere}" /></td>
                <td>


                    <ul><a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/cadreadmin/deleteNiveau/${n.idNiveau}">Supprimer</a></li>

                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/cadreadmin/updateNiveauForm/${n.idNiveau}/">Modifier</a></li>
                    </ul>
                </td>

            </tr>

        </c:forEach>

    </table>
</div>


