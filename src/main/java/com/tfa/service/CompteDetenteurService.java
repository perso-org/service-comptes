package com.tfa.service;

import java.util.List;

import com.tfa.dto.CompteDto;
import com.tfa.dto.DetenteurDto;

public interface CompteDetenteurService {

	CompteDto creerCompte(CompteDto dto,String numeroUnique );
	DetenteurDto creerDetenteur(DetenteurDto dto);
	CompteDto ajoutDetenteur(DetenteurDto dto, String numeroCompte);
	Boolean supprimerCompte(String numeroCompte);
	CompteDto obtenirCompte(String numeroCompte);
	CompteDto obtenirCompteParNumeroClient(String numeroUnique);
	CompteDto modifieCompte(String numeroCompte,CompteDto dto);
	List<CompteDto> obtenirComptes();
	Boolean supprimerDetenteur(String numeroUnique);
	List<DetenteurDto> obtenirDetenteurs();
	List<DetenteurDto> obtenirDetenteurParNumeroCompte(String numeroCompte);
	DetenteurDto obtenirDetenteurParMail(String email);
	DetenteurDto obtenirDetenteurParIDUnique(String numeroUnique);
	DetenteurDto modifieDetenteur(String numeroUnique,DetenteurDto dto);
	
}
