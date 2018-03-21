package fr.iutinfo.skeleton.api;

import java.security.Principal;
import java.security.SecureRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

import fr.iutinfo.skeleton.common.dto.EntrepriseDTO;

public class Entreprise implements Principal {
    final static Logger logger = LoggerFactory.getLogger(Entreprise.class);
    private static Entreprise anonymous = new Entreprise(-1, "Anonymous", "anonym");
    private String name;
    private String nomContact;
    private String prenomContact;
    private String tel;
    private String email;
    private int id = 0;
    private String lienPhoto;
    private String password;
    private String passwdHash;
    private String salt;
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


    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        passwdHash = buildHash(password, getSalt());
        this.password = password;
    }

    private String buildHash(String password, String s) {
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(password + s, Charsets.UTF_8);
        return hasher.hash().toString();
    }

    public boolean isGoodPassword(String password) {
        if (isAnonymous()) {
            return false;
        }
        String hash = buildHash(password, getSalt());
        return hash.equals(getPasswdHash());
    }

    public String getPasswdHash() {
        return passwdHash;
    }

    public void setPasswdHash(String passwdHash) {
        this.passwdHash = passwdHash;
    }

    @Override
    public boolean equals(Object arg) {
        if (getClass() != arg.getClass())
            return false;
        Entreprise user = (Entreprise) arg;
        return name.equals(user.name) && nomContact.equals(user.nomContact) && email.equals(user.email) && passwdHash.equals(user.getPasswdHash()) && salt.equals((user.getSalt()));
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

	public String getLienPhoto() {
		return lienPhoto;
	}

	public void setLienPhoto(String lienPhoto) {
		this.lienPhoto = lienPhoto;
	}

	public void setName(String nom) {
		this.name = nom;
	}

	public String getName() {
        return name;
    }

    public String getSalt() {
        if (salt == null) {
            salt = generateSalt();
        }
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putLong(random.nextLong());
        return hasher.hash().toString();
    }

    public void resetPasswordHash() {
        if (password != null && !password.isEmpty()) {
            setPassword(getPassword());
        }
    }

    public boolean isInUserGroup() {
        return !(id == anonymous.getId());
    }

    public boolean isAnonymous() {
        return this.getId() == getAnonymousUser().getId();
    }

    public String getSearch() {
        search = name + ":" + nomContact + "->" + email;
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void initFromDto(EntrepriseDTO dto) {
        this.setName(dto.getNom());
        this.setNomContact(dto.getNomContact());
        this.setPrenomContact(dto.getPrenomContact());
        this.setEmail(dto.getEmail());
        this.setId(dto.getId());
        this.setTel(dto.getTel());
        this.setLienPhoto(dto.getPhoto());
        this.setPassword(dto.getPassword());
    }

    public EntrepriseDTO convertToDto() {
        EntrepriseDTO dto = new EntrepriseDTO();
        dto.setNom(this.getName());
        dto.setNomContact(this.getNomContact());
        dto.setPrenomContact(this.getPrenomContact());
        dto.setEmail(this.getEmail());
        dto.setId(this.getId());
        dto.setTel(this.getTel());
        dto.setPhoto(this.getLienPhoto());
        dto.setPassword(this.getPassword());
        return dto;
    }

}
