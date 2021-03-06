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
        <h3>Formulaire d'ajout d'un module</h3>
    </div>
    <div>
        <f:form action="addModule" method="POST" modelAttribute="moduleModel" >
            <div class="row">
                <div class="col">
                    <label>Titre de Module</label>
                    <f:input path="titre" type="text" class="form-control"
                             placeholder="titre" />
                    <f:errors path="titre" class="text-danger" />
                </div>
                <div class="col">
                    <label>Code de module</label>
                    <f:input path="code" type="text" class="form-control"
                             placeholder="codModule" />
                    <f:errors path="code" class="text-danger" />
                </div>
                <div class="col">
                    <label>Niveau de module</label>

                    <f:select path="niveau" multiple="false" size="1"
                              class="form-control">
                        <f:options items="${niveauList}" itemValue="idNiveau" itemLabel="titre" />
                    </f:select>


                </div>
                <div class="col">
                    <label>L'enseignant de module</label>

                    <f:select path="enseignant" multiple="false" size="1"
                              class="form-control">
                        <f:options items="${enseignantList}" itemValue="idUtilisateur" itemLabel="nom" />
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
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Titre</th>
            <th scope="col">Alias</th>
            <th scope="col">Niveau</th>
            <th scope="col">Enseignant</th>
            <th>Actions</th>
        </tr>
        </thead>

        <c:forEach items="${moduleList}" var="m">
            <tr>
                <td><c:out value="${m.titre}" /></td>
                <td><c:out value="${m.code}" /></td>
                <td><c:out value="${m.niveau.titre}" /></td>
                <td><c:out value="${m.enseignant.nom}" /></td>

                <td>
                    <ul>
                        <a class="btn btn-outline-danger" href="${pageContext.request.contextPath}/cadreadmin/deleteModule/${m.idModule}">Supprimer</a></li>
                        <a class="btn btn-primary" href="${pageContext.request.contextPath}/cadreadmin/updateModuleForm/${m.idModule}/">Modifier</a></li>


                    </ul>
                </td>

            </tr>

        </c:forEach>

    </table>
</div>


