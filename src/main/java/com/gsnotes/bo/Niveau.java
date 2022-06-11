package com.gsnotes.bo;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Niveau {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idNiveau;

	private String alias;

	private String titre;

	@OneToMany(mappedBy = "niveau" , cascade = CascadeType.ALL, targetEntity = Modul.class)
	private List<Modul> modules;

	@OneToMany(mappedBy = "niveau" , cascade = CascadeType.ALL, targetEntity = InscriptionAnnuelle.class)
	private List<InscriptionAnnuelle> inscriptions;

	@ManyToOne
	@JoinColumn(name="idFiliere")
	private Filiere filiere;

	public Long getIdNiveau() {
		return idNiveau;
	}

	public void setIdNiveau(Long idNiveau) {
		this.idNiveau = idNiveau;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public List<Modul> getModules() {
		return modules;
	}

	public void setModules(List<Modul> modules) {
		this.modules = modules;
	}

	public List<InscriptionAnnuelle> getInscriptions() {
		return inscriptions;
	}

	public void setInscriptions(List<InscriptionAnnuelle> inscriptions) {
		this.inscriptions = inscriptions;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}

	
	
}