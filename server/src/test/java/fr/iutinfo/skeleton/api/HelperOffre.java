package fr.iutinfo.skeleton.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.GenericType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.OffreDto;

public class HelperOffre {
    private final static Logger logger = LoggerFactory.getLogger(HelperOffre.class);
    private static final OffreDao dao = BDDFactory.getDbi().open(OffreDao.class);
    static GenericType<List<OffreDto>> listOffreResponseType = new GenericType<List<OffreDto>>() {
    };

    public static void initDb() {
        dao.dropOffreTable();
        dao.createOffreTable();
    }

    static Offre createOffreWithIntitule(String intitule) {
        Offre offre = new Offre(0, intitule);
        return createOffre(offre);
    }

    public static Offre createOffreWithDateDeb(String name, Date dateDeb) {
        Offre offre= new Offre(0, name);
        offre.setDateDeb(dateDeb);
        return createOffre(offre);
    }
    public static Offre createOffreWithDateFin(String name, Date dateFin) {
        Offre offre= new Offre(0, name);
        offre.setDateFin(dateFin);
        return createOffre(offre);
    }
    
    public static Offre createOffreWithListeMots(String name, List<String> listeMots) {
        Offre offre= new Offre(0, name);
        offre.setListeMots(listeMots);
        return createOffre(offre);
    }
    
    public static Offre createOffreWithIdEntreprise(String name, Entreprise entreprise) {
        Offre offre= new Offre(0, name);
        offre.setIdEntreprise(entreprise.getId());
        return createOffre(offre);
    }

    private static Offre createOffre(Offre offre) {
        int id = dao.insert(offre);
        offre.setId(id);
        return offre;
    }

    private static Offre createFullOffre(String intitule, Date dateDeb, Date dateFin, List<String> listeMots,Entreprise entreprise) {
        Offre offre = new Offre(0, intitule);
        offre.setDateDeb(dateDeb);
        offre.setDateFin(dateFin);
        offre.setListeMots(listeMots);
        offre.setIdEntreprise(entreprise.getId());
        int id = dao.insert(offre);
        offre.setId(id);
        return offre;
    }

    static void createOffre1() {
        createFullOffre("Offre 1", new Date(2018,03,22), new Date(2018,10,22), new ArrayList<String>(), new Entreprise());
    }

    static Offre createOffre2() {
        return createFullOffre("Offre 2", new Date(2018,04,22), new Date(2018,11,22), new ArrayList<String>(), new Entreprise());
    }

    static Offre createOffre3() {
        return createFullOffre("Offre 3",new Date(2018,05,22), new Date(2018,9,22), new ArrayList<String>(), new Entreprise());
    }

    static Offre createOffre4() {
        return createFullOffre("Offre 4", new Date(2018,06,22), new Date(2018,12,22), new ArrayList<String>(), new Entreprise());
    }
}
