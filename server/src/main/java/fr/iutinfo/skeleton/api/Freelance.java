package fr.iutinfo.skeleton.api;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import fr.iutinfo.skeleton.common.dto.FreelanceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.security.SecureRandom;
import java.util.List;

public class Freelance implements Principal {
    final static Logger logger = LoggerFactory.getLogger(Freelance.class);
    private static Freelance anonymous = new Freelance(-1, "Anonymous", "anonym");
    private String firstname;
    private String name;
    private int id = 0;
    private String email;
    private String password;
    private String phone;
    private String job;
    private String photo;
    private String cv;
    private String mots;
    private String champLibre;
	private String passwdHash;
    private String salt;
    private String search;

    public Freelance(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Freelance(int id, String name, String firstname) {
        this.id = id;
        this.name = name;
        this.setFirstName(firstname);
    }

    public Freelance() {
    }

    public static Freelance getAnonymousFreelance() {
        return anonymous;
    }

    public String getEmail() {
        return email;
    }
    
    public String getChampLibre() {
		return champLibre;
	}

	public void setChampLibre(String champLibre) {
		this.champLibre = champLibre;
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
        Freelance Freelance = (Freelance) arg;
        return name.equals(Freelance.name) && firstname.equals(Freelance.firstname) && email.equals(Freelance.email) && passwdHash.equals(Freelance.getPasswdHash()) && phone.equals(Freelance.getPhone()) && job.equals(Freelance.getJob()) && photo.equals(Freelance.getPhoto()) && cv.equals(Freelance.getCv()) && mots.equals(Freelance.getMots()) && salt.equals((Freelance.getSalt()));
    }

    @Override
    public String toString() {
        return id + ": " + firstname + ", " + name + " <" + email + ">, TEL: " + phone + " / " + job + " LISTE MOTS : " + mots;
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

    public boolean isInFreelanceGroup() {
        return !(id == anonymous.getId());
    }

    public boolean isAnonymous() {
        return this.getId() == getAnonymousFreelance().getId();
    }

    public String getSearch() {
        search = name + " " + firstname + " " + email;
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void initFromDto(FreelanceDto dto) {
        this.setEmail(dto.getEmail());
        this.setId(dto.getId());
        this.setName(dto.getName());
        this.setPassword(dto.getPassword());
        this.setFirstName(dto.getFirstName());
        this.setPhone(dto.getPhone());
        this.setJob(dto.getJob());
        this.setPhoto(dto.getPhoto());
        this.setCv(dto.getCv());
        this.setMots(dto.getMots());
    }

    public FreelanceDto convertToDto() {
        FreelanceDto dto = new FreelanceDto();
        dto.setEmail(this.getEmail());
        dto.setId(this.getId());
        dto.setName(this.getName());
        dto.setPassword(this.getPassword());
        dto.setFirstName(this.getFirstName());
        dto.setPhone(this.getPhone());
        dto.setJob(this.getJob());
        dto.setPhoto(this.getPhoto());
        dto.setCv(this.getCv());
        dto.setMots(this.getMots());
        return dto;
    }

	public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public String getMots() {
		return mots;
	}

	public void setMots(String mots) {
		this.mots = mots;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
}