package fr.iutinfo.skeleton.api;

import static fr.iutinfo.skeleton.api.HelperEntreprise.*;
import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.iutinfo.skeleton.common.dto.EntrepriseDTO;


public class EntrepriseResourceTest extends JerseyTest {
    private static final String PATH = "/entreprise";
    private EntrepriseDAO dao = BDDFactory.getDbi().open(EntrepriseDAO.class);

    @Override
    protected Application configure() {
        return new Api();
    }

    @Before
    public void init() {
        HelperEntreprise.initDb();
    }

    @Test
    public void read_should_return_a_entreprise_as_object() {
        createEntrepriseWithName("cgi");
        EntrepriseDTO utilisateur = target(PATH + "/cgi").request().get(EntrepriseDTO.class);
        assertEquals("cgi", utilisateur.getNom());
    }

    @Test
    public void read_entreprise_should_return_good_nomContact() {
        createCGI();
        EntrepriseDTO entreprise = target(PATH + "/CGI").request().get(EntrepriseDTO.class);
        assertEquals("Toto", entreprise.getNomContact());
    }

    @Test
    public void read_entreprise_should_return_good_email() {
        createIut();
        EntrepriseDTO entreprise = target(PATH + "/IUT").request().get(EntrepriseDTO.class);
        assertEquals("titi@iut.com", entreprise.getEmail());
    }


    @Test
    public void create_should_return_the_entreprise_with_valid_id() {
    	EntrepriseDTO entreprise = new EntrepriseDTO(0, "CGI");
        Entity<EntrepriseDTO> entrepriseEntity = Entity.entity(entreprise, MediaType.APPLICATION_JSON);
        String json = target(PATH).request().post(entrepriseEntity).readEntity(String.class);
        assertEquals("{\"id\":1,\"name\":\"CGI\"", json.substring(0, 20));
    }

    @Test
    public void list_should_return_all_entreprise() {
        createEntrepriseWithName("cgi");
        createEntrepriseWithName("iut");
        List<EntrepriseDTO> entreprises = target(PATH + "/").request().get(listEntrepriseResponseType);
        assertEquals(2, entreprises.size());
    }

    @Test
    public void list_all_must_be_ordered() {
        createEntrepriseWithName("cgi");
        createEntrepriseWithName("iut");
        List<EntrepriseDTO> entreprises = target(PATH + "/").request().get(listEntrepriseResponseType);
        assertEquals("cgi", entreprises.get(0).getNom());
    }

    @Test
    public void after_delete_read_entreprise_sould_return_204() {
        Entreprise e = createEntrepriseWithName("cgi");
        int status = target(PATH + "/" + e.getId()).request().delete().getStatus();
        assertEquals(204, status);
    }

    @Test
    public void should_delete_user() {
        Entreprise e = createEntrepriseWithName("toto");
        target(PATH + "/" + e.getId()).request().delete();
        Entreprise entreprise = dao.findById(e.getId());
        Assert.assertEquals(null, entreprise);
    }

    @Test
    public void delete_unexisting_should_return_404() {
        int status = target(PATH + "/unexisting").request().delete().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void list_should_search_in_name_field() {
        createEntrepriseWithName("cgi");
        createEntrepriseWithName("iut");

        List<EntrepriseDTO> users = target(PATH + "/").queryParam("q", "iu").request().get(listEntrepriseResponseType);
        assertEquals("iut", users.get(0).getNom());
    }

    @Test
    public void list_should_search_in_nomContact_field() {
        createCGI();
        createIut();

        List<EntrepriseDTO> entreprises = target(PATH + "/").queryParam("q", "Toto").request().get(listEntrepriseResponseType);
        assertEquals("CGI", entreprises.get(0).getNom());
    }

    @Test
    public void list_should_search_in_email_field() {
    	 createCGI();
         createIut();
         createToto();

        List<EntrepriseDTO> entreprises = target(PATH + "/").queryParam("q", "iut").request().get(listEntrepriseResponseType);
        assertEquals(2, entreprises.size());
    }
}