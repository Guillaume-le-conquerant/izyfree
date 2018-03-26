package fr.iutinfo.skeleton.common.dto;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OffreDto {
    final static Logger logger = LoggerFactory.getLogger(OffreDto.class);
    private int id=0;
    private String intitule;
    private String dateDeb;
    private String dateFin;
    private String listeMots;
    private String nomEntreprise;
    private String champLibre;

    public String getChampLibre() {
		return champLibre;
	}

	public void setChampLibre(String champLibre) {
		this.champLibre = champLibre;
	}

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
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

    public String getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(String dateDeb) {
        this.dateDeb = dateDeb;
    }

    public String getDateFin() {
        return this.dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getListeMots() {
        return listeMots;
    }

    public void setListeMots(String mots) {
       this.listeMots = mots;
    }

	@Override
	public String toString() {
		return "OffreDto [id=" + id + ", intitule=" + intitule + ", dateDeb=" + dateDeb + ", dateFin=" + dateFin
				+ ", listeMots=" + listeMots + ", Nom de la societe=" + nomEntreprise + "]";
	}
	

}