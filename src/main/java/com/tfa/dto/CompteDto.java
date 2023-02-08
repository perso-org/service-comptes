package com.tfa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CompteDto {

	private Long id;
	private String detenteurId;
	private String compteType;
	private String numeroCompte;
	private String addressCompte;
	
	
}
