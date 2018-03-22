package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.FreelanceDto;
import fr.iutinfo.skeleton.common.dto.UserDto;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static fr.iutinfo.skeleton.api.HelperFreelance.*;
import static org.junit.Assert.assertEquals;

public class FreelanceResourceTest extends JerseyTest {
    private static final String PATH = "/freelance";
    private FreelanceDao dao = BDDFactory.getDbi().open(FreelanceDao.class);

    @Override
    protected Application configure() {
        return new Api();
    }

    @Before
    public void init() {
        HelperFreelance.initDb();
    }

    @Test
    public void read_should_return_a_freelance_as_object() {
        createFreelanceWithName("foo");
        FreelanceDto freelance = target(PATH + "/foo").request().get(FreelanceDto.class);
        assertEquals("foo", freelance.getName());
    }


    @Test
    public void read_freelance_should_return_good_email() {
        createIan();
        FreelanceDto free = target(PATH + "/Ian Murdock").request().get(FreelanceDto.class);
        assertEquals("ian@debian.org", free.getEmail());
    }

    @Test
    public void read_freelance_should_read_freelance_with_same_salt() {
        String expectedSalt = "graindesel";
        createFreelanceWithPassword("Mark Shuttleworth", "motdepasse", expectedSalt);
        Freelance free = dao.findByName("Mark Shuttleworth");
        assertEquals(expectedSalt, free.getSalt());
    }

    @Test
    public void read_freelance_should_return_hashed_password() throws NoSuchAlgorithmException {
        createFreelanceWithPassword("Loïc Dachary", "motdepasse", "grain de sable");
        Freelance free = dao.findByName("Loïc Dachary");
        assertEquals("dfeb21109fe5eab1b1db7369844921c44b87b44669b0742f3f73bd166b474779", free.getPasswdHash());
    }

    @Test
    public void create_should_return_the_freelance_with_valid_id() {
        FreelanceDto free = new FreelanceDto(0, "thomas");
        Entity<FreelanceDto> freeEntity = Entity.entity(free, MediaType.APPLICATION_JSON);
        String json = target(PATH).request().post(freeEntity).readEntity(String.class);
        System.out.println("PAPAPAAPAPAPAPAPAAP"+ json + " " + json.length());
        assertEquals("{\"id\":1,\"name\":\"thomas\"", json.substring(0, 23));
    }

    @Test
    public void list_should_return_all_freelance() {
        createFreelanceWithName("foo");
        createFreelanceWithName("bar");
        List<FreelanceDto> freelance = target(PATH + "/").request().get(listUserResponseType);
        assertEquals(2, freelance.size());
    }

    @Test
    public void list_all_must_be_ordered() {
        createFreelanceWithName("foo");
        createFreelanceWithName("bar");
        List<FreelanceDto> freelance = target(PATH + "/").request().get(listUserResponseType);
        assertEquals("foo", freelance.get(0).getName());
    }

    @Test
    public void after_delete_read_freelance_sould_return_204() {
        Freelance free = createFreelanceWithName("toto");
        int status = target(PATH + "/" + free.getId()).request().delete().getStatus();
        assertEquals(204, status);
    }

    @Test
    public void should_delete_freelance() {
        Freelance f = createFreelanceWithName("toto");
        target(PATH + "/" + f.getId()).request().delete();
        Freelance free = dao.findById(f.getId());
        Assert.assertEquals(null, free);
    }

    @Test
    public void delete_unexisting_should_return_404() {
        int status = target(PATH + "/unexisting").request().delete().getStatus();
        assertEquals(404, status);
    }

    @Test
    public void list_should_search_in_name_field() {
        createFreelanceWithName("foo");
        createFreelanceWithName("bar");

        List<FreelanceDto> freelance = target(PATH + "/").queryParam("q", "ba").request().get(listUserResponseType);
        assertEquals("bar", freelance.get(0).getName());
    }

    @Test
    public void list_should_search_in_alias_field() {
        createRms();
        createLinus();
        createRob();

        List<FreelanceDto> freelance = target(PATH + "/").queryParam("q", "RMS").request().get(listUserResponseType);
        assertEquals("Richard Stallman", freelance.get(0).getName());
    }

    @Test
    public void list_should_search_in_email_field() {
        createRms();
        createLinus();
        createRob();

        List<FreelanceDto> freelance = target(PATH + "/").queryParam("q", "fsf").request().get(listUserResponseType);
        assertEquals(2, freelance.size());
    }
}