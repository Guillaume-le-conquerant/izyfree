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
    final static Logger logger = LoggerFactory.getLogger(User.class);
    private static Staff anonymous = new Staff("IzyFree");
    private String pseudo;
    private String phone;
    private String mail;
    private String password;
    private String passwdHash;
    private String salt;
    private String search;

    public Staff(String pseudo) {
        this.setPseudo(pseudo);
    }
    
    public Staff() {
    }

    public static Staff getAnonymousStaff() {
        return anonymous;
    }

    public String getMail() {
        return mail;
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
        return pseudo.equals(staff.pseudo) && mail.equals(staff.mail) && phone.equals(staff.phone);
    }

    @Override
    public String toString() {
        return pseudo + ": TEL : " + phone + ", <" + mail + ">";
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
        return !(pseudo == anonymous.getPseudo());
    }

    public boolean isAnonymous() {
        return this.getPseudo() == getAnonymousStaff().getPseudo();
    }

    public String getSearch() {
        search = pseudo + " " + phone + " " + mail;
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void initFromDto(StaffDto dto) {
        this.setPseudo(dto.getPseudo());
        this.setMail(dto.getMail());
        this.setPhone(dto.getPhone());
        this.setPassword(dto.getPassword());
    }

    public StaffDto convertToDto() {
        StaffDto dto = new StaffDto();
        dto.setPseudo(this.getPseudo());
        dto.setMail(this.getMail());
        dto.setPhone(this.getPhone());
        dto.setPassword(this.getPassword());
        return dto;
    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
