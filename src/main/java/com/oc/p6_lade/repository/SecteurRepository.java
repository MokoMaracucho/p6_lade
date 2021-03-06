package com.oc.p6_lade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oc.p6_lade.entity.Secteur;

@Repository
public interface SecteurRepository extends JpaRepository<Secteur, Long> {

    Secteur getByNomSecteur(String nomSecteur);

    @Query(value="SELECT * FROM db_lade.secteur WHERE nom_secteur = :nomRecherche", nativeQuery=true)
    List<Secteur> rechercheNomSecteur(@Param("nomRecherche") String nomRecherche);
}
