package com.tfa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DetenteurDto {

	private Long id;
	private String nom;
	private String prenom;
	private String email;
	private String typeDetenteur;
	private String numeroUnique;
	private String numeroCompte;
}
