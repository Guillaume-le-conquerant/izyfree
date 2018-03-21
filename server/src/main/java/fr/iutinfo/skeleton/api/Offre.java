package fr.iutinfo.skeleton.api;

import java.security.Principal;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

import fr.iutinfo.skeleton.common.dto.OffreDto;
import fr.iutinfo.skeleton.common.dto.UserDto;

public class Offre implements Principal {
    final static Logger logger = LoggerFactory.getLogger(OffreDto.class);
    private int id=0;
    private String intitule;
    private Date dateDeb;
    private Date dateFin;
    private List<String> listeMots;

    public Offre(int id, String intitule) {
        this.id = id;
        this.intitule = intitule;
    }

    public Offre(int id, String intitule, Date dateDeb, Date dateFin, List<String> listeMots) {
        this.id = id;
        this.intitule = intitule;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.listeMots=listeMots;
    }

    public Offre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Date getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
    }

    public Date getDateFin() {
        return this.dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    public List<String> getListeMots() {
        return listeMots;
    }

    public void setListeMots(List<String> listeMots) {
        this.listeMots = listeMots;
    }

    private String buildHash(String password, String s) {
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(password + s, Charsets.UTF_8);
        return hasher.hash().toString();
    }


    @Override
    public boolean equals(Object arg) {
        if (getClass() != arg.getClass())
            return false;
        Offre offre = (Offre) arg;
        return intitule.equals(offre.intitule) && dateDeb.equals(offre.dateDeb) && dateFin.equals(offre.dateFin) && listeMots.equals(offre.listeMots);
    }

    @Override
    public String toString() {
        String res = id + ": " + intitule + ", " + dateDeb + ", " + dateFin;
        for(String s : listeMots) {
        	res +=", " + s;
        }
        return res;
    }

    public void initFromDto(OffreDto dto) {
        this.setIntitule(dto.getIntitule());
        this.setDateDeb(dto.getDateDeb());
        this.setId(dto.getId());
        this.setDateFin(dto.getDateFin());
        this.setListeMots(dto.getListeMots());
    }

    public OffreDto convertToDto() {
        OffreDto dto = new OffreDto();
        this.setIntitule(dto.getIntitule());
        this.setDateDeb(dto.getDateDeb());
        this.setId(dto.getId());
        this.setDateFin(dto.getDateFin());
        this.setListeMots(dto.getListeMots());
        return dto;
    }

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
