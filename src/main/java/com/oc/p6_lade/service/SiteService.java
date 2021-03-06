package com.oc.p6_lade.service;

import java.util.List;

import com.oc.p6_lade.entity.Site;
import com.oc.p6_lade.entity.Utilisateur;
import com.oc.p6_lade.exception.ResourceNotFoundException;
import com.oc.p6_lade.form.FormAjoutSite;

public interface SiteService {

    public void enregistrerSite(FormAjoutSite formAjoutSite, Utilisateur utilisateur);

    public List<Site> listeSites();

    public Site selectionnerSiteParId(Long idSite) throws ResourceNotFoundException;

    public void suppressionSiteParId(Long idSite) throws ResourceNotFoundException;

    public List<Site> rechercheNomSite(String nomRecherche) throws ResourceNotFoundException;

    public List<Site> rechercheRegionSite(String regionRecherche) throws ResourceNotFoundException;

    public List<Site> rechercheNomRegionSite(String nomRecherche, String regionRecherche) throws ResourceNotFoundException;
}
