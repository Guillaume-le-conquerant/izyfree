package fr.iutinfo.skeleton.common.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FreelanceDto {
    final static Logger logger = LoggerFactory.getLogger(FreelanceDto.class);
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
    private String tarif;
    private String localisation;
    private String conditions;

    @Override
	public String toString() {
		return "FreelanceDto [firstname=" + firstname + ", name=" + name + ", id=" + id + ", email=" + email
				+ ", password=" + password + ", phone=" + phone + ", job=" + job + ", photo=" + photo + ", cv=" + cv
				+ ", mots=" + mots + ", champLibre=" + champLibre + ", tarif=" + tarif + ", localisation="
				+ localisation + ", conditions=" + conditions + "]";
	}

	public FreelanceDto(int id, String name) {
    	this.id=id;
    	this.name=name;
    }
    
    public FreelanceDto(int id, String name, String firstname) {
    	this.id=id;
    	this.name=name;
    	this.firstname=firstname;
    }
    
    public FreelanceDto() {
    	
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
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

	public String getChampLibre() {
		return champLibre;
	}

	public void setChampLibre(String champLibre) {
		this.champLibre = champLibre;
	}

	public String getTarif() {
		return tarif;
	}

	public void setTarif(String tarif) {
		this.tarif = tarif;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

}
