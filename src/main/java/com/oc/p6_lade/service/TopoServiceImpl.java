package com.oc.p6_lade.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.p6_lade.entity.ReservationTopo;
import com.oc.p6_lade.entity.StatutReservationTopo;
import com.oc.p6_lade.entity.Topo;
import com.oc.p6_lade.entity.Utilisateur;
import com.oc.p6_lade.exception.ResourceNotFoundException;
import com.oc.p6_lade.form.FormAjoutTopo;
import com.oc.p6_lade.repository.ReservationTopoRepository;
import com.oc.p6_lade.repository.TopoRepository;

@Service
public class TopoServiceImpl implements TopoService {

    private TopoRepository topoRepository;

    private ReservationTopoRepository reservationTopoRepository;

    @Autowired
    public TopoServiceImpl(TopoRepository topoRepository, ReservationTopoRepository reservationTopoRepository) {
        this.topoRepository = topoRepository;
        this.reservationTopoRepository = reservationTopoRepository;
    }

    @Override
    public void enregistrerTopo(FormAjoutTopo formAjoutTopo, Utilisateur utilisateur) {
        Topo topo = new Topo();
        topo.setNomTopo(formAjoutTopo.getNomTopo());
        topo.setRegionTopo(formAjoutTopo.getRegionTopo());
        topo.setDescriptionTopo(formAjoutTopo.getDescriptionTopo());
        topo.setDateParutionTopo(new Timestamp(System.currentTimeMillis()));
        topo.setDisponibiliteTopo(true);
        topo.setUtilisateur(utilisateur);
        topoRepository.save(topo);
    }

    @Override
    public void enregistrerReservationTopo(Long idTopo, Utilisateur utilisateur) {
        Topo topo = topoRepository.getOne(idTopo);
        topo.setDisponibiliteTopo(false);
        topoRepository.save(topo);
        ReservationTopo reservationTopo = new ReservationTopo();
        reservationTopo.setStatutReservationTopo(StatutReservationTopo.EN_ATTENTE);
        reservationTopo.setTopo(topo);
        reservationTopo.setDemandeurReservationTopo(utilisateur);
        reservationTopoRepository.save(reservationTopo);
    }

    @Override
    public List<Topo> listeTopos() {
        return topoRepository.findAll();
    }

    @Override
    public List<ReservationTopo> listeReservationsTopo() {
        return reservationTopoRepository.findAll();
    }

    @Override
    public List<ReservationTopo> listeReservationsTopo(Long id) {
        return reservationTopoRepository.findAllByIdProprietaireTopo(id);
    }

    @Override
    public List<ReservationTopo> listeDemandesReservationTopo(Long idDemandeurReservationTopo) {
        return reservationTopoRepository.findAllByIdDemandeurReservationTopo(idDemandeurReservationTopo);
    }

    @Override
    public void majReservationTopo(Long idReservationTopo, Utilisateur utilisateur, StatutReservationTopo reponseReservationTopo) {
        ReservationTopo reservationTopo = reservationTopoRepository.getOne(idReservationTopo);
        Topo topo = reservationTopo.getTopo();
        switch(reponseReservationTopo) {
            case ACCEPTEE:
                reservationTopo.setStatutReservationTopo(StatutReservationTopo.ACCEPTEE);
                reservationTopoRepository.save(reservationTopo);
                break;
            case REFUSEE:
                reservationTopo.setStatutReservationTopo(StatutReservationTopo.REFUSEE);
                reservationTopoRepository.save(reservationTopo);
                topoRepository.majDisponibilteTopo(topo.getIdTopo(), true);
                break;
            case TERMINEE:
                reservationTopo.setStatutReservationTopo(StatutReservationTopo.TERMINEE);
                reservationTopoRepository.save(reservationTopo);
                topoRepository.majDisponibilteTopo(topo.getIdTopo(), true);
                break;
            default:
                break;
        }
    }

    @Override
    public List<Topo> rechercheNomTopo(String nomRecherche) throws ResourceNotFoundException {
        return topoRepository.rechercheNomTopo(nomRecherche);
    }

    @Override
    public List<Topo> rechercheRegionTopo(String regionRecherche) throws ResourceNotFoundException {
        return topoRepository.rechercheRegionTopo(regionRecherche);
    }

    @Override
    public List<Topo> rechercheNomRegionTopo(String nomRecherche, String regionRecherche) throws ResourceNotFoundException {
        return topoRepository.rechercheNomRegionTopo(nomRecherche, regionRecherche);
    }
}
