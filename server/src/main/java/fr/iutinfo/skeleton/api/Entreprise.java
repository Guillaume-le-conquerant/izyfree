package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.EntrepriseDTO;

public class Entreprise{
    final static Logger logger = LoggerFactory.getLogger(Entreprise.class);
    private static Entreprise anonymous = new Entreprise(-1, "Anonymous", "anonym");
    private String name;
    private String nomContact;
    private String prenomContact;
    private String tel;
    private String email;
    private int id = 0;
	private String fonctionsContact;
    private String profilRecherche;
    private String ville;
    private String champLibre;
    private String search;


    public Entreprise(int id, String nom) {
        this.id = id;
        this.name = nom;
    }

    public Entreprise(int id, String nom, String nomContact) {
        this.id = id;
        this.name = nom;
        this.nomContact = nomContact;
    }
    
    public Entreprise() {
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

    public static Entreprise getAnonymousUser() {
        return anonymous;
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
	
    public String getSearch() {
        search = name + " " + nomContact + " " + email;
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

	public String getChampLibre() {
		return champLibre;
	}

	public void setChampLibre(String champLibre) {
		this.champLibre = champLibre;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String nom) {
		this.name = nom;
	}

	public String getName() {
        return name;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object arg) {
        if (getClass() != arg.getClass())
            return false;
        Entreprise user = (Entreprise) arg;
        return name.equals(user.name) && nomContact.equals(user.nomContact) && email.equals(user.email);
    }

    @Override
    public String toString() {
        return id + ": " + name + ", " + nomContact + " <" + email + ">";
    }

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}


    public boolean isInUserGroup() {
        return !(id == anonymous.getId());
    }

    public boolean isAnonymous() {
        return this.getId() == getAnonymousUser().getId();
    }


    public void initFromDto(EntrepriseDTO dto) {
        this.setName(dto.getNom());
        this.setNomContact(dto.getNomContact());
        this.setPrenomContact(dto.getPrenomContact());
        this.setEmail(dto.getEmail());
        this.setId(dto.getId());
        this.setTel(dto.getTel());
        this.setFonctionsContact(dto.getFonctionsContact());
        this.setProfilRecherche(dto.getProfilRecherche());
        this.setChampLibre(dto.getChampLibre());
        this.setVille(dto.getVille());
    }

    public EntrepriseDTO convertToDto() {
        EntrepriseDTO dto = new EntrepriseDTO();
        dto.setNom(this.getName());
        dto.setNomContact(this.getNomContact());
        dto.setPrenomContact(this.getPrenomContact());
        dto.setEmail(this.getEmail());
        dto.setId(this.getId());
        dto.setTel(this.getTel());
        dto.setVille(this.getVille());
        dto.setFonctionsContact(this.getFonctionsContact());
        dto.setChampLibre(this.getChampLibre());
        dto.setProfilRecherche(this.getProfilRecherche());
        return dto;
    }

}
