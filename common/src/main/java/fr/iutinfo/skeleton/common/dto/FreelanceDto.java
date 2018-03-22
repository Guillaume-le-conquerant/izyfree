package fr.iutinfo.skeleton.common.dto;

import java.util.List;

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
    private List<String> mots;

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

	public List<String> getMots() {
		return mots;
	}

	public void setMots(List<String> mots) {
		this.mots = mots;
	}

}