package com.tfa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfa.entite.Detenteur;

public interface DetenteurRepository extends JpaRepository<Detenteur, Long>{

	List<Detenteur> findByNumeroCompte(String numeroCompte);
	Detenteur findByEmail(String email);
	Detenteur findByNumeroUnique(String numeroUnique);
}
