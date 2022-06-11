<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
        <li class="nav-item"><a class="nav-link active"
                                aria-current="page"
                                href="${pageContext.request.contextPath}/cadreadmin/showHome">Home</a></li>

        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
                                         href="#" id="navbarScrollingDropdown" role="button"
                                         data-bs-toggle="dropdown" aria-expanded="false">Gestion des Filieres</a>
            <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/showFormFiliere">Ajouter Filiere</a>
                </li>
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/listFilieres">Liste des filieres</a></li>



            </ul>
        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
                                         href="#" id="navbarScrollingDropdown" role="button"
                                         data-bs-toggle="dropdown" aria-expanded="false"> Gestion des niveau</a>
            <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/showFormNiveau">Ajouter Niveau</a>
                </li>
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/listNiveaus">Liste des niveaus</a></li>



            </ul>
        </li>
        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
                                         href="#" id="navbarScrollingDropdown" role="button"
                                         data-bs-toggle="dropdown" aria-expanded="false">Gestion des modules</a>
            <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/showFormModule">Ajouter Module</a>
                </li>
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/listModules">Liste des modules</a></li>



            </ul>
        </li>

        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
                                         href="#" id="navbarScrollingDropdown" role="button"
                                         data-bs-toggle="dropdown" aria-expanded="false">Gestion des elements</a>
            <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/showFormElement">Ajouter element</a>
                </li>
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/listElements">Liste des elements</a></li>



            </ul>
        </li>
            <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                <li class="dropdown-item"><a class="nav-link"
                                             href="${pageContext.request.contextPath}/cadreadmin/exportData">Export Filieres</a></li>
            </ul></li>




        <li class="nav-item"><f:form
                action="${pageContext.request.contextPath}/logout" method="POST">

            <button type="submit" class="btn btn-link">logout</button>

        </f:form></li>

    </ul>



</div>


