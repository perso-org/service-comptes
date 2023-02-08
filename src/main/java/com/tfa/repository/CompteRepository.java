package com.tfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfa.entite.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long>{

	Compte findByDetenteurId(String detenteurId);
	Compte findByNumeroCompte(String numeroCompte);
}
