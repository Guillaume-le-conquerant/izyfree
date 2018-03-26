package fr.iutinfo.skeleton.api;


import java.util.List;

import javax.ws.rs.core.GenericType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.OffreDto;

public class HelperOffre {
//    private final static Logger logger = LoggerFactory.getLogger(HelperOffre.class);
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

    public static Offre createOffreWithDateDeb(String name, String dateDeb) {
        Offre offre= new Offre(0, name);
        offre.setDateDeb(dateDeb);
        return createOffre(offre);
    }
    public static Offre createOffreWithDateFin(String name, String dateFin) {
        Offre offre= new Offre(0, name);
        offre.setDateFin(dateFin);
        return createOffre(offre);
    }
    


    private static Offre createOffre(Offre offre) {
        int id = dao.insert(offre);
        offre.setId(id);
        return offre;
    }

    private static Offre createFullOffre(String intitule, String dateDeb, String dateFin,Entreprise entreprise) {
        Offre offre = new Offre(0, intitule);
        offre.setDateDeb(dateDeb);
        offre.setDateFin(dateFin);
        offre.setNomEntreprise(entreprise.getNomContact());
        int id = dao.insert(offre);
        offre.setId(id);
        return offre;
    }

    static Offre createOffre1() {
        return createFullOffre("Offre_1", "2018-03-22", "2018-10-22", new Entreprise(1, "CGI"));
    }

    static void createOffre2() {
        createFullOffre("Offre_2", "2018-04-22", "2018-11-22", new Entreprise(1, "CGI"));
    }

    static void createOffre3() {
         createFullOffre("Offre_3","2018-05-22", "2018-9-22", new Entreprise(1, "CGI"));
    }

    static void createOffre4() {
        createFullOffre("Offre_4", "2018-06-22", "2018-12-22", new Entreprise(1, "CGI"));
    }
}
