package fr.iutinfo.skeleton.api;

import java.util.List;

import javax.ws.rs.core.GenericType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.EntrepriseDTO;

public class HelperEntreprise {
    private final static Logger logger = LoggerFactory.getLogger(HelperEntreprise.class);
    private static final EntrepriseDAO dao = BDDFactory.getDbi().open(EntrepriseDAO.class);
    static GenericType<List<EntrepriseDTO>> listEntrepriseResponseType = new GenericType<List<EntrepriseDTO>>() {
    };

    public static void initDb() {
        dao.dropEntrepriseTable();
        dao.createEntrepriseTable();
    }


    static Entreprise createEntrepriseWithName(String name) {
        Entreprise user = new Entreprise(0, name);
        return createEntreprise(user);
    }

    static Entreprise createEntrepriseWithNomContact(String name, String nomContact) {
        Entreprise user = new Entreprise(0, name, nomContact);
        return createEntreprise(user);
    }

    static Entreprise createEntrepriseWithEmail(String name, String email) {
        Entreprise entre = new Entreprise(0, name);
        entre.setEmail(email);
        return createEntreprise(entre);
    }
    
    private static Entreprise createEntreprise(Entreprise entreprise) {
    	int id = dao.insert(entreprise);
    	entreprise.setId(id);
    	return entreprise;
    }
    
    private static Entreprise createFullEntreprise(String name, String nomContact, String prenomContact, String tel, String email, String fonctionsContact, String profilRecherche, String ville, String champLibre) {
    	Entreprise entreprise = new Entreprise(0, name);
    	entreprise.setNomContact(nomContact);
    	entreprise.setPrenomContact(prenomContact);
    	entreprise.setTel(tel);
    	entreprise.setEmail(email);
    	entreprise.setFonctionsContact(fonctionsContact);
    	entreprise.setProfilRecherche(profilRecherche);
    	entreprise.setVille(ville);
    	entreprise.setChampLibre(champLibre);
    	int id = dao.insert(entreprise);
    	return entreprise;
    }


    
    // Creation d'entreprise pour les test
    
    static void createCGI() {
    	createFullEntreprise("CGI", "Toto", "Tata", "06 85 74 65 41", "toto@cgi.com", "developpeur", "java","Lille", "php");
    }
    
    static void createIut() {
    	createFullEntreprise("IUT", "Titi", "tutu", "07 87 65 74 83", "titi@iut.com", "developpeur", "java","Lille", "php");
    }
    
    static void createToto() {
    	createFullEntreprise("TOTO Companie", "toto", "tata", "07 04 54 34 74","toto@iut.com", "developpeur", "java","Lille", "php");
    }

}
