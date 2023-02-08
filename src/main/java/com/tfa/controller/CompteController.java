package com.tfa.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfa.dto.CompteDto;
import com.tfa.dto.DetenteurDto;
import com.tfa.service.CompteDetenteurService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comptes")
public class CompteController {

	private final CompteDetenteurService service;

	@PostMapping("cc/{numeroUnique}")
	public ResponseEntity<CompteDto> creerCompte(@RequestBody CompteDto dto,
			@PathVariable("numeroUnique") String numeroUnique) {
		CompteDto compteDto = service.creerCompte(dto, numeroUnique);

		return Objects.isNull(compteDto) ? new ResponseEntity<>(compteDto, HttpStatus.NOT_ACCEPTABLE)
				: new ResponseEntity<>(compteDto, HttpStatus.CREATED);
	}

	@PostMapping("detenteur")
	public ResponseEntity<DetenteurDto> creerDetenteur(@RequestBody DetenteurDto dto) {

		DetenteurDto detenteurDto = service.creerDetenteur(dto);

		return Objects.isNull(detenteurDto)
				? new ResponseEntity<>(service.obtenirDetenteurParMail(dto.getEmail()), HttpStatus.FOUND)
				: new ResponseEntity<>(detenteurDto, HttpStatus.CREATED);
	}

	@PostMapping("/ajout/{numeroCompte}")
	public ResponseEntity<CompteDto> ajoutDetenteur(@RequestBody DetenteurDto dto,
			@PathVariable("numeroCompte") String numeroCompte) {

		CompteDto compteDto = service.ajoutDetenteur(dto, numeroCompte);

		return Objects.isNull(compteDto) ? new ResponseEntity<>(HttpStatus.FOUND)
				: new ResponseEntity<>(compteDto, HttpStatus.OK);
	}

	@GetMapping("detenteur/all")
	public ResponseEntity<List<DetenteurDto>> obtenirDetenteurs() {
		return ResponseEntity.ok(service.obtenirDetenteurs());
	}

	@GetMapping("detenteurs/cc/{numeroCompte}")
	public ResponseEntity<List<DetenteurDto>> obtenirDetenteurParNumeroCompte(@PathVariable String numeroCompte) {
		return ResponseEntity.ok(service.obtenirDetenteurParNumeroCompte(numeroCompte));
	}
	
	@GetMapping("detenteur/{email}")
	public ResponseEntity<DetenteurDto> obtenirDetenteurParMail(@PathVariable String email) {
		return ResponseEntity.ok(service.obtenirDetenteurParMail(email));
	}
	
	@GetMapping("detenteur/cc/{numeroCompte}")
	public ResponseEntity<DetenteurDto> obtenirDetenteurParIDUnique(@PathVariable String numeroUnique) {
		return ResponseEntity.ok(service.obtenirDetenteurParIDUnique(numeroUnique));
	}
}
