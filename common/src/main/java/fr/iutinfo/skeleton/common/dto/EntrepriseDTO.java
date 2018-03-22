package fr.iutinfo.skeleton.common.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntrepriseDTO {
	
	final static Logger logger = LoggerFactory.getLogger(EntrepriseDTO.class);
	private String name;
	private String nomContact;// RH, responsable recrutement
	private String prenomContact;
	private String password;
	private String tel;
	private String email;
	private int id;
	private String photo; // Lien vers la photo
	
	
	public EntrepriseDTO() {}
	
	public EntrepriseDTO(int id, String name) {
		this.name = name;
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	

}
