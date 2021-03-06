package com.oc.p6_lade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oc.p6_lade.entity.Longueur;

@Repository
public interface LongueurRepository extends JpaRepository<Longueur, Long> {

    @Query(value="SELECT * FROM db_lade.longueur WHERE nom_longueur = :nomRecherche", nativeQuery=true)
    List<Longueur> rechercheNomLongueur(@Param("nomRecherche") String nomRecherche);
}
