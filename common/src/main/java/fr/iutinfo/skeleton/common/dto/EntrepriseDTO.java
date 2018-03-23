package fr.iutinfo.skeleton.common.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntrepriseDTO {
	
	final static Logger logger = LoggerFactory.getLogger(EntrepriseDTO.class);
	private String name;
	private String nomContact;// RH, responsable recrutement
	private String prenomContact;
	private String tel;
	private String email;
	private int id;
	private String fonctionsContact;
    private String profilRecherche;
    private String ville;
    private String champLibre;
	

	public EntrepriseDTO() {}
	
	public EntrepriseDTO(int id, String name) {
		this.name = name;
		this.id = id;
	}
	
	public String getNom() {
		return name;
	}
	public void setNom(String nom) {
		this.name = nom;
	}
	public String getNomContact() {
		return nomContact;
	}
	public void setNomContact(String nomContact) {
		this.nomContact = nomContact;
	}
	public String getPrenomContact() {
		return prenomContact;
	}
	public void setPrenomContact(String prenomContact) {
		this.prenomContact = prenomContact;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFonctionsContact() {
		return fonctionsContact;
	}

	public void setFonctionsContact(String fonctionsContact) {
		this.fonctionsContact = fonctionsContact;
	}

	public String getProfilRecherche() {
		return profilRecherche;
	}

	public void setProfilRecherche(String profilRecherche) {
		this.profilRecherche = profilRecherche;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getChampLibre() {
		return champLibre;
	}

	public void setChampLibre(String champLibre) {
		this.champLibre = champLibre;
	}
	
	

}
