package com.gsnotes.bo;



import javax.persistence.*;
import java.util.List;


/**
 * Represente un enseignant.
 * 
 * Un enseignant est un cas spéciale de l'Utilisateur
 * 
 * @author T. BOUDAA
 *
 */


@Entity
@PrimaryKeyJoinColumn(name = "idEnseignant")
public class Enseignant extends Utilisateur {



	private String specialite;

	@OneToMany(mappedBy = "enseignant" , cascade = CascadeType.ALL, targetEntity = Modul.class)
	private List<Modul> modules;
	@OneToMany(mappedBy = "enseignant" , cascade = CascadeType.ALL, targetEntity = Filiere.class)
	private List<Filiere> filieres;


	public List<Modul> getModules() {
		return modules;
	}

	public void setModules(List<Modul> modules) {
		this.modules = modules;
	}

	public List<Filiere> getFilieres() {
		return filieres;
	}

	public void setFilieres(List<Filiere> filieres) {
		this.filieres = filieres;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}





}