package com.gsnotes.web.controllers;

import com.gsnotes.bo.*;
import com.gsnotes.bo.Modul;
import com.gsnotes.services.*;
import com.gsnotes.utils.export.ExcelExporter;
import com.gsnotes.web.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/cadreadmin")
public class UtilisateurCadreAdminController {
    //    injection des service autowired automatique
    @Autowired
    private IFiliereService filiereService;
    @Autowired
    private INiveauService niveauService;
    @Autowired
    private  IModuleService moduleService;
    @Autowired
    private IElementService elementService;
    @Autowired
    private IEnseignantService enseignantService;
    @Autowired
    private HttpSession httpSession;


    /**
     * Utilisé pour la journalisation
     */
    private Logger LOGGER = LoggerFactory.getLogger(getClass());


    public UtilisateurCadreAdminController() {


    }
    // cette methode nous permet d afficher le formulaire d'ajoute de filiere et ses association
    @RequestMapping(value = "showFormFiliere", method = RequestMethod.GET)
    public String filiereForm(Model model) {

        // Nous avons choisit d'utiliser une classe modèle personnalisée
        // définie par FiliereModel pour une meilleur flexibilité

        FiliereModel fmodel = new FiliereModel();
        model.addAttribute("filiereModel", fmodel);
        List<Filiere> filieres = filiereService.getAllFilieres();
        List<FiliereModel> modelFilieres = new ArrayList<FiliereModel>();
        List<Enseignant> enseignants = enseignantService.getAllEnseignants();

        // Copier les objets metier vers FiliereModel plus flexible

        for (int i = 0; i < filieres.size(); i++) {
            FiliereModel filiereModel = new FiliereModel();
            BeanUtils.copyProperties((Filiere) filieres.get(i), filiereModel);
            modelFilieres.add(filiereModel);

        }
        //        pour recuperer les cordinateurs , association avec le cordinateur de la filiere
        model.addAttribute("enseignantList",enseignants);
        //On ajoute également la liste des filieres  dans le modèle , pour extraire les filiere dans la vue
        model.addAttribute("filiereList", modelFilieres);
//
        return "cadreadmin/formFiliere";
    }
    @RequestMapping(value = "showFormNiveau", method = RequestMethod.GET)
    public String niveauForm(Model model) {

        NiveauModel nmodel = new NiveauModel();
        model.addAttribute("niveauModel", nmodel);


        List<Niveau> niveaus = niveauService.getAllNiveaus();
        List<NiveauModel> modelNiveaus = new ArrayList<NiveauModel>();
        List<Filiere> filieres = filiereService.getAllFilieres();

        // Copier les objets metier vers PersonModel plus flexible
        for (int i = 0; i < niveaus.size(); i++) {
            NiveauModel niveauModel = new NiveauModel();
            BeanUtils.copyProperties((Niveau) niveaus.get(i), niveauModel);
            modelNiveaus.add(niveauModel);

        }

        model.addAttribute("filiereList",filieres);
        model.addAttribute("niveauList", modelNiveaus);
        return "cadreadmin/formNiveau";
    }
    @RequestMapping(value = "showFormModule", method = RequestMethod.GET)
    public String moduleForm(Model model) {

        ModuleModel nmodel = new ModuleModel();
        model.addAttribute("moduleModel", nmodel);

        List<Modul> modules = moduleService.getAllModules();
        List<ModuleModel> modelModules = new ArrayList<ModuleModel>();
        List<Enseignant> enseignants = enseignantService.getAllEnseignants();

        // Copier les objets metier vers PersonModel plus flexible
        for (int i = 0; i < modules.size(); i++) {
            ModuleModel nm = new ModuleModel();
            BeanUtils.copyProperties((Modul) modules.get(i), nm);
            modelModules.add(nm);

        }
        model.addAttribute("niveauList",niveauService.getAllNiveaus());
        model.addAttribute("enseignantList",enseignants);
        model.addAttribute("moduleList", modelModules);
        return "cadreadmin/formModule";
    }
    @RequestMapping(value = "showFormElement", method = RequestMethod.GET)
    public String elementForm(Model model) {

        ElementModel elementModel = new ElementModel();
        model.addAttribute("elementModel", elementModel);

        List<Element> elements = elementService.getAllElements();
        List<ElementModel> modelElementes = new ArrayList<ElementModel>();
        List<Modul> moduls = moduleService.getAllModules();

        // Copier les objets metier vers PersonModel plus flexible
        for (int i = 0; i < elements.size(); i++) {
            ElementModel em = new ElementModel();
            BeanUtils.copyProperties((Element) elements.get(i), em);
            modelElementes.add(em);

        }
        model.addAttribute("moduleList",moduls);
        model.addAttribute("elementList", modelElementes);
        return "cadreadmin/formElement";
    }


    @RequestMapping(value = "addFiliere", method = RequestMethod.POST)
    public String ajouterFiliere(@Valid @ModelAttribute("filiereModel") FiliereModel filiere, BindingResult bindingResult,
                          Model model, HttpServletRequest rq) {

        // En cas d'erreur de validation
        if (bindingResult.hasErrors()) {
            return "formFiliere";
        }
        Filiere fil = new Filiere();
        BeanUtils.copyProperties(filiere, fil);
        filiereService.addFiliere(fil);


        return "redirect:/cadreadmin/showFormFiliere";
    }
    @RequestMapping(value = "addNiveau", method = RequestMethod.POST)
    public String ajouterNiveau(@Valid @ModelAttribute("niveauModel") NiveauModel niveau, BindingResult bindingResult,
                          Model model, HttpServletRequest rq) {

        // En cas d'erreur de validation
        if (bindingResult.hasErrors()) {
            return "cadreadmin/formNiveau";
        }
        Niveau niv = new Niveau();
        BeanUtils.copyProperties(niveau, niv);
        niveauService.addNiveau(niv);
        return "redirect:/cadreadmin/showFormNiveau";
    }
    @RequestMapping(value = "addModule", method = RequestMethod.POST)
    public String ajouterModule(@Valid @ModelAttribute("moduleModel") ModuleModel module, BindingResult bindingResult,
                          Model model, HttpServletRequest rq) {

        // En cas d'erreur de validation
        if (bindingResult.hasErrors()) {
            return "cadreadmin/formModule";
        }

        Modul m = new Modul();
        BeanUtils.copyProperties(module, m);
        moduleService.addModule(m);

        return "redirect:/cadreadmin/showFormModule";
    }

    @RequestMapping(value = "addElement", method = RequestMethod.POST)
    public String ajouterElement(@Valid @ModelAttribute("elementModel") ElementModel elementModel, BindingResult bindingResult,
                          Model model, HttpServletRequest rq) {

        // En cas d'erreur de validation
        if (bindingResult.hasErrors()) {

            return "cadreadmin/formElement";
        }
        Element el = new Element();
        BeanUtils.copyProperties(elementModel, el);
        elementService.addElement(el);



        return "redirect:/cadreadmin/showFormElement";
    }
    @RequestMapping(value = "deleteModule/{idModule}", method = RequestMethod.GET)
    public String deleteModule(@PathVariable int idModule) {

        moduleService.deleteModule(Long.valueOf(idModule));

        return "redirect:/cadreadmin/showFormModule";
    }
    @RequestMapping(value = "deleteNiveau/{idNiveau}", method = RequestMethod.GET)
    public String deleteNiveau(@PathVariable int idNiveau) {

        niveauService.deleteNiveau(Long.valueOf(idNiveau));

        return "redirect:/cadreadmin/showFormNiveau";
    }
    @RequestMapping(value = "deleteElement/{idMatiere}", method = RequestMethod.GET)
    public String deleteElement(@PathVariable int idMatiere) {

        elementService.deleteElement(Long.valueOf(idMatiere));

        return "redirect:/cadreadmin/showFormElement";
    }
    @RequestMapping("listFilieres")
    public String listFiliere(Model model) {

        List<Filiere> filieres = filiereService.getAllFilieres();
        List<FiliereModel> modelFiliere = new ArrayList<FiliereModel>();

        // Copier les objets metier vers PersonModel plus flexible
        for (int i = 0; i < filieres.size(); i++) {
            FiliereModel fm = new FiliereModel();
            BeanUtils.copyProperties((Filiere) filieres.get(i), fm);
            modelFiliere.add(fm);

        }

        model.addAttribute("filiereList", modelFiliere);

        return "cadreadmin/listFilieres";
    }
    @RequestMapping("listNiveaus")
    public String listNiveau(Model model) {
        model.addAttribute("niveauList",niveauService.getAllNiveaus());

        return "cadreadmin/listNiveaus";
    }
    @RequestMapping("listModules")
    public String listModule(Model model) {

        List<Modul> modules = moduleService.getAllModules();
        List<ModuleModel> modelModules = new ArrayList<ModuleModel>();

        // Copier les objets metier vers PersonModel plus flexible
        for (int i = 0; i < modules.size(); i++) {
            ModuleModel mm = new ModuleModel();
            BeanUtils.copyProperties((Modul) modules.get(i), mm);
            modelModules.add(mm);

        }

        model.addAttribute("moduleList", modelModules);

        return "cadreadmin/listModules";
    }
    @RequestMapping("listElements")
    public String listElement(Model model) {

        List<Element> elements = elementService.getAllElements();
        List<ElementModel> elementModels = new ArrayList<ElementModel>();

        // Copier les objets metier vers PersonModel plus flexible
        for (int i = 0; i < elements.size(); i++) {
           ElementModel em = new ElementModel();
            BeanUtils.copyProperties((Element) elements.get(i), em);
            elementModels.add(em);

        }

        model.addAttribute("elementList", elementModels);

        return "cadreadmin/listElements";
    }

    @RequestMapping(value = "deleteFiliere/{idFiliere}", method = RequestMethod.GET)
    public String delete(@PathVariable int idFiliere) {

        filiereService.deleteFiliere(Long.valueOf(idFiliere));

        return "redirect:/cadreadmin/showFormFiliere";
    }


    @RequestMapping(path = "updateModuleForm/{idModule}", method = RequestMethod.GET)
    public ModelAndView updateModuleForm(@PathVariable int idModule, Model model) {

        Modul m = moduleService.getModuleById(Long.valueOf(idModule));
        // On reoit comme paramètre l'id de la personne à mettre à jour

        ModuleModel moduleModel = new ModuleModel();
        BeanUtils.copyProperties(m, moduleModel);
        List<Niveau> niveaus = niveauService.getAllNiveaus();
        List<Enseignant> enseignants = enseignantService.getAllEnseignants();
        model.addAttribute("niveauList",niveaus);
        model.addAttribute("moduleModel", moduleModel);
//        System.out.println(model.getAttribute("filiereModel"));
        model.addAttribute("enseignantList",enseignants);
        model.addAttribute("moduleList",moduleService.getAllModules());
        return new ModelAndView("cadreadmin/updateModule","moduleModel",moduleModel);
    }

    @RequestMapping("updateModule")
    public String updatePerson(@Valid @ModelAttribute("moduleModel") ModuleModel moduleModel, BindingResult bindingResult,
                               Model model) {

        // En cas d'erreur
        if (bindingResult.hasErrors()) {

            return "cadreadmin/updateModule";
        }
        System.out.println(moduleModel.toString());
//        System.out.println(model.getAttribute("filiereModel"));
       Modul modul = new Modul();
        // On copie les données du modèle vers l'objet métier puis on appel le service
        // pour faire la mise à jour

        BeanUtils.copyProperties(moduleModel, modul);
        moduleService.updateModule(modul);
        List<Niveau> niveaus = niveauService.getAllNiveaus();
        List<Enseignant> enseignants = enseignantService.getAllEnseignants();
        model.addAttribute("niveauList",niveaus);
        model.addAttribute("enseignantList",enseignants);
        // Mettre le message de succès dans le modèle
        model.addAttribute("msg", "Opération effectuée avec succès");

        return "cadreadmin/updateModule";
    }
    @RequestMapping(path = "updateNiveauForm/{idNiveau}", method = RequestMethod.GET)
    public ModelAndView updateNiveauForm(@PathVariable int idNiveau, Model model) {
        // On reoit comme paramètre l'id de niveau à mettre à jour
        Niveau nv = niveauService.getNiveauById(Long.valueOf(idNiveau));

        List<Filiere> filieres = filiereService.getAllFilieres();

        // On construit le modèle
        NiveauModel niveauModel = new NiveauModel();
        BeanUtils.copyProperties(nv, niveauModel);
        model.addAttribute("niveauModel", niveauModel);
//        System.out.println(model.getAttribute("filiereModel"));
        model.addAttribute("filiereList",filieres);
        return new ModelAndView("cadreadmin/updateNiveau","niveauModel",niveauModel);
    }

    @RequestMapping("updateNiveau")
    public String updateNiveau(@Valid @ModelAttribute("niveauModel") NiveauModel niveauModel, BindingResult bindingResult,
                               Model model) {

        // En cas d'erreur
        if (bindingResult.hasErrors()) {

            return "cadreadmin/updateNiveau";
        }
        Niveau nv = new Niveau();
        BeanUtils.copyProperties(niveauModel, nv);
        niveauService.updateNiveau(nv);
        List<Filiere> filieres = filiereService.getAllFilieres();
        // Mettre le message de succès dans le modèle
        model.addAttribute("filiereList",filieres);
        model.addAttribute("msg", "Opération effectuée avec succès");

        return "cadreadmin/updateNiveau";
    }
    @RequestMapping(path = "updateElementForm/{idMatiere}", method = RequestMethod.GET)
    public ModelAndView updateElementForm(@PathVariable int idMatiere, Model model) {
        Element el = elementService.getElementById(Long.valueOf(idMatiere));
        ElementModel elementModel = new ElementModel();
        BeanUtils.copyProperties(el, elementModel);
        List<Modul> moduls = moduleService.getAllModules();
        model.addAttribute("elementModel", elementModel);
        model.addAttribute("moduleList", moduls);
        return new ModelAndView("cadreadmin/updateElement","elementModel",elementModel);
    }

    @RequestMapping("updateElement")
    public String update(@Valid @ModelAttribute("elementModel") ElementModel elementModel, BindingResult bindingResult,
                               Model model) {

        // En cas d'erreur
        if (bindingResult.hasErrors()) {

            return "cadreadmin/updateElement";
        }
        System.out.println(elementModel.toString());
//        System.out.println(model.getAttribute("filiereModel"));
        Element el = new Element();
        BeanUtils.copyProperties(elementModel,el);
       elementService.updateElement(el);
        List<Modul> moduls = moduleService.getAllModules();
        model.addAttribute("moduleList", moduls);
        // Mettre le message de succès dans le modèle
        model.addAttribute("msg", "Opération effectuée avec succès");

        return "cadreadmin/updateElement";
    }
    @RequestMapping(path = "updateFiliereForm/{idFiliere}", method = RequestMethod.GET)
    public ModelAndView updatePersonForm(@PathVariable int idFiliere, Model model) {

        Filiere fil = filiereService.getFiliereById(Long.valueOf(idFiliere));
        // On construit le modèle
        FiliereModel fm = new FiliereModel();
        BeanUtils.copyProperties(fil, fm);
        List<Enseignant> enseignants = enseignantService.getAllEnseignants();
        model.addAttribute("filiereModel", fm);
        model.addAttribute("enseignantList",enseignants);
        return new ModelAndView("cadreadmin/updateForm","filiereModel",fm);
    }

    @RequestMapping("updateFiliere")
    public ModelAndView updateFiliere(@Valid @ModelAttribute("filiereModel") FiliereModel filiereModel, BindingResult bindingResult,
                               Model model) {

        // En cas d'erreur
        if (bindingResult.hasErrors()) {

            return new ModelAndView("cadreadmin/updateForm","filiereModel",filiereModel);
        }
        System.out.println(filiereModel.toString());
        Filiere filiere = new Filiere();
        BeanUtils.copyProperties(filiereModel, filiere);
        filiereService.updateFiliere(filiere);
        model.addAttribute("enseignantList",enseignantService.getAllEnseignants());
        // Mettre le message de succès dans le modèle
        model.addAttribute("msg", "Opération effectuée avec succès");

        return new ModelAndView("cadreadmin/updateForm","filiereModel",filiereModel);
    }



    @GetMapping("exportFilieres")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Filiere> filieres = filiereService.getAllFilieres();

        ExcelExporter excelExporter = filiereService.prepareFiliereExport(filieres);

        excelExporter.export(response);
    }
}

