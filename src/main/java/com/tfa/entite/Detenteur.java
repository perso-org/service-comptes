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
@Table(name = "detenteur_comptes")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Detenteur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nom;
	@Column(nullable = false)
	private String prenom;
	@Column(nullable = false,unique = true)
	private String numeroUnique;
	@Column(nullable = false)
	private String typeDetenteur;
	@Column(nullable = false,unique = true)
	private String email;
	@Column(nullable = false)
	private String numeroCompte;
	
}
