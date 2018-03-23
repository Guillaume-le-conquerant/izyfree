package fr.iutinfo.skeleton.api;

import static fr.iutinfo.skeleton.api.HelperOffre.createOffre1;
import static fr.iutinfo.skeleton.api.HelperOffre.createOffre2;
import static fr.iutinfo.skeleton.api.HelperOffre.createOffre3;
import static fr.iutinfo.skeleton.api.HelperOffre.createOffre4;
import static fr.iutinfo.skeleton.api.HelperOffre.createOffreWithIntitule;
import static fr.iutinfo.skeleton.api.HelperOffre.listOffreResponseType;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.iutinfo.skeleton.common.dto.OffreDto;

public class OffreResourceTest extends JerseyTest {
    private static final String PATH = "/offre";
    private OffreDao dao = BDDFactory.getDbi().open(OffreDao.class);

    @Override
    protected Application configure() {
        return new Api();
    }

    @Before
    public void init() {
        HelperOffre.initDb();
    }

    @Test
    public void read_should_return_an_offre_as_object() {
        createOffreWithIntitule("foo");
        OffreDto offre = target(PATH + "/intitule/foo").request().get(OffreDto.class);
        assertEquals("foo", offre.getIntitule());
    }

    @Test
    public void read_offre_should_return_good_dateDeb() {
        Offre offre = createOffre1();
        OffreDto offredto = target(PATH + "/intitule/Offre_1").request().get(OffreDto.class);
        assertEquals("2018-03-22", offredto.getDateDeb());
    }

    @Test
    public void read_offre_should_return_good_dateFin() {
        createOffre2();
        OffreDto offre = target(PATH + "/intitule/Offre_2").request().get(OffreDto.class);
        assertEquals("2018-11-22", offre.getDateFin());
    }
    
    @Test
    public void read_offre_should_return_good_listeMots() {
        createOffre3();
        OffreDto offre = target(PATH + "/intitule/Offre_3").request().get(OffreDto.class);
        String[] mots = offre.getListeMots().split("\\?");
        assertEquals("Mot1", mots[0]);
        assertEquals("Mot2", mots[1]);
    }
    
    @Test
    public void read_offre_should_return_good_idEntreprise() {
        createOffre4();
        OffreDto offre = target(PATH + "/intitule/Offre_4").request().get(OffreDto.class);
        assertEquals(new Entreprise().getId(), offre.getIdEntreprise());
    }

    @Test
    public void create_should_return_the_offre_with_valid_id() {
        OffreDto offre = new OffreDto();
        offre.setIntitule("thomas");
        Entity<OffreDto> offreEntity = Entity.entity(offre, MediaType.APPLICATION_JSON);
        String json = target(PATH).request().post(offreEntity).readEntity(String.class);
        assertEquals("{\"id\":1,\"idEntreprise\":0,\"intitule\":\"thomas\"}", json.substring(0, json.length()));
    }

    @Test
    public void list_should_return_all_offres() {
        createOffreWithIntitule("foo");
        createOffreWithIntitule("bar");
        List<OffreDto> offres= target(PATH + "/").request().get(listOffreResponseType);
        assertEquals(2, offres.size());
    }

    @Test
    public void list_all_must_be_ordered() {
    	createOffreWithIntitule("foo");
        createOffreWithIntitule("bar");
        List<OffreDto> offres = target(PATH + "/").request().get(listOffreResponseType);
        assertEquals("foo", offres.get(0).getIntitule());
    }

    @Test
    public void after_delete_read_offre_sould_return_204() {
        Offre o = createOffreWithIntitule("toto");
        int status = target(PATH + "/" + o.getId()).request().delete().getStatus();
        assertEquals(204, status);
    }

    @Test
    public void should_delete_offre() {
        Offre o = createOffreWithIntitule("toto");
        target(PATH + "/" + o.getId()).request().delete();
        Offre offre = dao.findById(o.getId());
        Assert.assertEquals(null, offre);
    }

    @Test
    public void delete_unexisting_should_return_404() {
        int status = target(PATH + "/unexisting").request().delete().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void list_should_search_in_intitule_field() {
        createOffreWithIntitule("foo");
        createOffreWithIntitule("bar");

        List<OffreDto> offres = target(PATH + "/").queryParam("q", "ba").request().get(listOffreResponseType);
        assertEquals("bar", offres.get(0).getIntitule());
    }
}
