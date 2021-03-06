package com.oc.p6_lade.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oc.p6_lade.entity.Commentaire;
import com.oc.p6_lade.entity.Site;
import com.oc.p6_lade.entity.Utilisateur;
import com.oc.p6_lade.exception.ResourceNotFoundException;
import com.oc.p6_lade.form.FormAjoutSite;
import com.oc.p6_lade.service.CommentaireService;
import com.oc.p6_lade.service.SiteService;

@Controller
@RequestMapping("/site")
public class SiteController {

    public static final String ATT_FORM_AJOUT_SITE							= "formAjoutSite";

    public static final String ATT_UTILISATEUR								= "utilisateur";

    public static final String ATT_LISTE_SITES				 				= "listeSites";
    public static final String ATT_LISTE_COMMENTAIRES						= "listeCommentaires";

    @Autowired
    private SiteService siteService;

    @Autowired
    private CommentaireService commentaireService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/ajout_site")
    public String ajoutSite(Model model) {
        model.addAttribute(ATT_FORM_AJOUT_SITE, new FormAjoutSite());
        return "ajout_site";
    }

    @PostMapping("/traitement_formulaire_ajout_site")
    public String traitementAjoutSite(HttpServletRequest request, @Valid @ModelAttribute("formAjoutSite") FormAjoutSite formAjoutSite, BindingResult bindingResult, Model model) {
        HttpSession session = request.getSession();
        if(bindingResult.hasErrors()) {
            return "/ajout_site";
        } else {
            Utilisateur utilisateur = (Utilisateur) session.getAttribute(ATT_UTILISATEUR);
            siteService.enregistrerSite(formAjoutSite, utilisateur);
            return "redirect:/site/liste_sites";
        }
    }

    @GetMapping("/liste_sites")
    public String listeSites(Model model) {
        List<Commentaire> listeCommentaires = commentaireService.listeCommentaires();
        model.addAttribute(ATT_LISTE_COMMENTAIRES, listeCommentaires);
        List<Site> listeSites = siteService.listeSites();
        model.addAttribute(ATT_LISTE_SITES, listeSites);
        return "liste_sites";
    }

    @PostMapping("/supprimer_site")
    public String suppressionSiteParId(@RequestParam(name="idSite") Long idSite) throws ResourceNotFoundException {
        siteService.suppressionSiteParId(idSite);
        return "redirect:/site/liste_sites";
    }
}
