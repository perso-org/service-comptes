package com.tfa.service.Impl;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tfa.dto.CompteDto;
import com.tfa.dto.DetenteurDto;
import com.tfa.entite.Compte;
import com.tfa.entite.Detenteur;
import com.tfa.repository.CompteRepository;
import com.tfa.repository.DetenteurRepository;
import com.tfa.service.CompteDetenteurService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompteDetenteurServiceImpl implements CompteDetenteurService {

	private final CompteRepository crepo;
	private final DetenteurRepository drepo;
	private final ModelMapper mapper;

	@Override
	public CompteDto ajoutDetenteur(DetenteurDto dto, String numeroCompte) {

		List<Detenteur> detenteurs = drepo.findByNumeroCompte(numeroCompte);
		boolean iPresent = detenteurs.stream()
				.anyMatch(p -> StringUtils.equals(dto.getNumeroUnique(), p.getNumeroUnique()));
		if (iPresent) {
			log.info("Le détenteur est deja associé à ce compte...");
			return null;
		}
		
		Detenteur detenteurz = Detenteur.builder()
				.nom(dto.getNom())
				.email(dto.getEmail())
				.prenom(dto.getPrenom())
				.numeroCompte(numeroCompte)
				.numeroUnique(numeroUniqueDetenteur(9))
				.typeDetenteur(dto.getTypeDetenteur())
				.build();
		
		Detenteur detenteur = drepo.save(detenteurz);

		Compte compte = crepo.findByNumeroCompte(numeroCompte);

		Compte comptesecondaire = Compte.builder().id(1L).addressCompte(compte.getAddressCompte())
				.compteType(compte.getCompteType()).detenteurId(detenteur.getNumeroUnique()).numeroCompte(numeroCompte)
				.build();
		Compte compteSaved = crepo.save(comptesecondaire);
		return mapper.map(compteSaved, CompteDto.class);
	}

	@Override
	public CompteDto creerCompte(CompteDto dto, String numeroUnique) {
		Detenteur detenteur = drepo.findByNumeroUnique(numeroUnique);

		if (detenteur == null) {
			log.warn("Le detenteur avec numero: {} n'a pas encore été validé par le SI");
			return null;
		}
		Compte compte = mapper.map(dto, Compte.class);
		compte.setNumeroCompte(detenteur.getNumeroCompte());
		compte.setDetenteurId(numeroUnique);
		Compte cmp = crepo.save(compte);
		return mapper.map(cmp, CompteDto.class);
	}

	@Override
	public DetenteurDto creerDetenteur(DetenteurDto dto) {
		Detenteur detenteur = drepo.findByEmail(dto.getEmail());

		if (Objects.nonNull(detenteur)) {
			log.info("Ce client existe deja chez nous!");
			return null;
		}
		Detenteur de = mapper.map(dto, Detenteur.class);
		de.setNumeroCompte(numeroUnique(12));
		de.setNumeroUnique(numeroUniqueDetenteur(9));
		Detenteur detenteurcree = drepo.save(de);
		return mapper.map(detenteurcree, DetenteurDto.class);
	}

	@Override
	public CompteDto modifieCompte(String numeroCompte, CompteDto dto) {
		return null;
	}

	@Override
	public DetenteurDto modifieDetenteur(String email, DetenteurDto dto) {
		return null;
	}

	private String numeroUnique(int taille) {
		while (true) {
			String numeroUnique = RandomStringUtils.randomNumeric(taille);
			Compte compte = crepo.findByNumeroCompte(numeroUnique);
			if (compte == null) {
				return numeroUnique;
			}
		}
	}

	private String numeroUniqueDetenteur(int taille) {
		while (true) {
			String numeroUnique = RandomStringUtils.randomNumeric(taille);
			Detenteur detenteur = drepo.findByNumeroUnique(numeroUnique);
			if (detenteur == null) {
				return numeroUnique;
			}
		}
	}

	@Override
	public CompteDto obtenirCompte(String numeroCompte) {
		Compte compte = crepo.findByNumeroCompte(numeroCompte);

		if (Objects.isNull(compte)) {
			log.warn("Il n'existe pas de compte avec le numero {}", numeroCompte);
			return null;
		}

		return mapper.map(compte, CompteDto.class);
	}

	@Override
	public CompteDto obtenirCompteParNumeroClient(String numeroUnique) {
		Compte compte = crepo.findByDetenteurId(numeroUnique);

		if (Objects.isNull(compte)) {
			log.warn("Il n'existe pas de compte avec le numero {}", numeroUnique);
			return null;
		}

		return mapper.map(compte, CompteDto.class);
	}

	@Override
	public List<CompteDto> obtenirComptes() {
		List<Compte> comptes = crepo.findAll();
		
		if(CollectionUtils.isEmpty(comptes)) {
			log.info("Il n'existe pas de comptes enregistrés!");
			return Collections.emptyList();
		}
		
		return comptes.stream().map(p -> mapper.map(p, CompteDto.class)).toList();
	}

	@Override
	public DetenteurDto obtenirDetenteurParIDUnique(String numeroUnique) {
		Detenteur dto = drepo.findByNumeroUnique(numeroUnique);
		if (Objects.isNull(dto)) {
			log.warn("Le détenteur n'existe pas dans notre base");
			return null;
		}
		return mapper.map(dto, DetenteurDto.class);
	}

	@Override
	public DetenteurDto obtenirDetenteurParMail(String email) {
		Detenteur dto = drepo.findByEmail(email);
		if (Objects.isNull(dto)) {
			log.warn("Le détenteur n'existe pas dans notre base");
			return null;
		}
		return mapper.map(dto, DetenteurDto.class);
	}

	@Override
	public List<DetenteurDto> obtenirDetenteurParNumeroCompte(String numeroCompte) {

		List<Detenteur> detenteurs = drepo.findByNumeroCompte(numeroCompte);
		if(CollectionUtils.isEmpty(detenteurs)) {
			log.info("Il n'existe pas de détenteurs lié à ce compte!");
			return Collections.emptyList();
		}
		return detenteurs.stream().map(p -> mapper.map(p, DetenteurDto.class)).toList();
	}

	@Override
	public List<DetenteurDto> obtenirDetenteurs() {
		List<Detenteur> detenteurs = drepo.findAll();
		if(CollectionUtils.isEmpty(detenteurs)) {
			log.info("Il n'existe pas de détenteurs enregistré pour le moment!");
			return Collections.emptyList();
		}
		return detenteurs.stream().map(p -> mapper.map(p, DetenteurDto.class)).toList();
	}

	@Override
	public Boolean supprimerCompte(String numeroCompte) {
		
		return null;
	}

	@Override
	public Boolean supprimerDetenteur(String email) {
		return null;
	}

}
