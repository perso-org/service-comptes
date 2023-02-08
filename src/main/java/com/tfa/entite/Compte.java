package com.tfa.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comptes")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Compte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String detenteurId;
	@Column(nullable = false)
	private String compteType;
	private String numeroCompte;
	@Column(nullable = false)
	private String addressCompte;
}
