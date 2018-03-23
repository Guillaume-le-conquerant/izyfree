package fr.iutinfo.skeleton.api;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

import fr.iutinfo.skeleton.common.dto.StaffDto;
import fr.iutinfo.skeleton.common.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.security.SecureRandom;

public class Staff implements Principal {
    final static Logger logger = LoggerFactory.getLogger(Staff.class);
    private static Staff anonymous = new Staff("IzyFree");
    private String name;
    private String firstname;
	private String mail;
    private int id = 0;
	private String password;
    private String passwdHash;
    private String salt;
    private String search;

    public Staff(String name) {
        this.name = name;
    }
    
    public Staff() {
    }
    
    public int getId() {
  		return id;
  	}

  	public void setId(int id) {
  		this.id = id;
  	}


    public static Staff getAnonymousStaff() {
        return anonymous;
    }

    public String getMail() {
        return mail;
    }
    
    public String getFirstName() {
		return firstname;
	}

	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}

	public void setName(String name) {
		this.name = name;
	}


    public void setMail(String mail) {
        this.mail = mail;
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
        Staff staff = (Staff) arg;
        return name.equals(staff.name) && firstname.equals(staff.firstname) && mail.equals(staff.mail);
    }

    @Override
    public String toString() {
        return name + ": <" + mail + ">";
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

    public boolean isInStaffGroup() {
        return !(name == anonymous.getName());
    }

    public boolean isAnonymous() {
        return this.getName() == getAnonymousStaff().getName();
    }

    public String getSearch() {
        search = name + " " + mail;
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void initFromDto(StaffDto dto) {
        this.setName(dto.getName());
        this.setFirstName(dto.getFirstName());
        this.setMail(dto.getMail());
        this.setPassword(dto.getPassword());
    }

    public StaffDto convertToDto() {
        StaffDto dto = new StaffDto();
        dto.setName(this.getName());
        dto.setFirstName(this.getFirstName());
        dto.setMail(this.getMail());
        dto.setPassword(this.getPassword());
        return dto;
    }

	@Override
	public String getName() {
		return name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

}
