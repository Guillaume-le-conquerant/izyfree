package fr.iutinfo.skeleton.common.dto;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OffreDto {
    final static Logger logger = LoggerFactory.getLogger(OffreDto.class);
    private int id=0;
    private String intitule;
    private Date dateDeb;
    private Date dateFin;
    private List<String> listeMots;

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

}