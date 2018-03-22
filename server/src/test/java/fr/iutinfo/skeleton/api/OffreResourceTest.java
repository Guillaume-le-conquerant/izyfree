//package fr.iutinfo.skeleton.api;
//
//import static fr.iutinfo.skeleton.api.Helper.createIan;
//import static fr.iutinfo.skeleton.api.Helper.createLinus;
//import static fr.iutinfo.skeleton.api.Helper.createRms;
//import static fr.iutinfo.skeleton.api.Helper.createRob;
//import static fr.iutinfo.skeleton.api.Helper.createUserWithName;
//import static fr.iutinfo.skeleton.api.Helper.createUserWithPassword;
//import static fr.iutinfo.skeleton.api.Helper.listUserResponseType;
//import static org.junit.Assert.assertEquals;
//
//import java.security.NoSuchAlgorithmException;
//import java.util.Date;
//import java.util.List;
//
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.core.Application;
//import javax.ws.rs.core.MediaType;
//
//import org.glassfish.jersey.test.JerseyTest;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import fr.iutinfo.skeleton.common.dto.OffreDto;
//import fr.iutinfo.skeleton.common.dto.UserDto;
//
//public class OffreResourceTest extends JerseyTest {
//    private static final String PATH = "/offre";
//    private OffreDao dao = BDDFactory.getDbi().open(OffreDao.class);
//
//    @Override
//    protected Application configure() {
//        return new Api();
//    }
//
//    @Before
//    public void init() {
//        Helper.initDb();
//    }
//
//    @Test
//    public void read_should_return_a_offre_as_object() {
//        OffreDto offre = target(PATH + "/foo").request().get(OffreDto.class);
//        assertEquals("foo", offre.getIntitule());
//    }
//
//    @Test
//    public void read_offre_should_return_good_dateDeb() {
//        createRms();
//        OffreDto offre = target(PATH + new Date(2018,03,22).toString()).request().get(OffreDto.class);
//        assertEquals(new Date(2018,03,22).toString(), offre.getDateDeb());
//    }
//
//    @Test
//    public void read_offre_should_return_good_dateFin() {
//        createIan();
//        OffreDto user = target(PATH + "/Ian Murdock").request().get(OffreDto.class);
//        assertEquals("ian@debian.org", user.getEmail());
//    }
//
//    @Test
//    public void read_user_should_read_user_with_same_salt() {
//        String expectedSalt = "graindesel";
//        createUserWithPassword("Mark Shuttleworth", "motdepasse", expectedSalt);
//        User user = dao.findByName("Mark Shuttleworth");
//        assertEquals(expectedSalt, user.getSalt());
//    }
//
//    @Test
//    public void read_user_should_return_hashed_password() throws NoSuchAlgorithmException {
//        createUserWithPassword("Loïc Dachary", "motdepasse", "grain de sable");
//        User user = dao.findByName("Loïc Dachary");
//        assertEquals("dfeb21109fe5eab1b1db7369844921c44b87b44669b0742f3f73bd166b474779", user.getPasswdHash());
//    }
//
//    @Test
//    public void create_should_return_the_user_with_valid_id() {
//        User user = new User(0, "thomas");
//        Entity<User> userEntity = Entity.entity(user, MediaType.APPLICATION_JSON);
//        String json = target(PATH).request().post(userEntity).readEntity(String.class);
//        assertEquals("{\"id\":1,\"name\":\"thomas\"", json.substring(0, 23));
//    }
//
//    @Test
//    public void list_should_return_all_users() {
//        createUserWithName("foo");
//        createUserWithName("bar");
//        List<UserDto> users = target(PATH + "/").request().get(listUserResponseType);
//        assertEquals(2, users.size());
//    }
//
//    @Test
//    public void list_all_must_be_ordered() {
//        createUserWithName("foo");
//        createUserWithName("bar");
//        List<UserDto> users = target(PATH + "/").request().get(listUserResponseType);
//        assertEquals("foo", users.get(0).getName());
//    }
//
//    @Test
//    public void after_delete_read_user_sould_return_204() {
//        User u = createUserWithName("toto");
//        int status = target(PATH + "/" + u.getId()).request().delete().getStatus();
//        assertEquals(204, status);
//    }
//
//    @Test
//    public void should_delete_user() {
//        User u = createUserWithName("toto");
//        target(PATH + "/" + u.getId()).request().delete();
//        User user = dao.findById(u.getId());
//        Assert.assertEquals(null, user);
//    }
//
//    @Test
//    public void delete_unexisting_should_return_404() {
//        int status = target(PATH + "/unexisting").request().delete().getStatus();
//        assertEquals(404, status);
//    }
//
//    @Test
//    public void list_should_search_in_name_field() {
//        createUserWithName("foo");
//        createUserWithName("bar");
//
//        List<UserDto> users = target(PATH + "/").queryParam("q", "ba").request().get(listUserResponseType);
//        assertEquals("bar", users.get(0).getName());
//    }
//
//    @Test
//    public void list_should_search_in_alias_field() {
//        createRms();
//        createLinus();
//        createRob();
//
//        List<UserDto> users = target(PATH + "/").queryParam("q", "RMS").request().get(listUserResponseType);
//        assertEquals("Richard Stallman", users.get(0).getName());
//    }
//
//    @Test
//    public void list_should_search_in_email_field() {
//        createRms();
//        createLinus();
//        createRob();
//
//        List<UserDto> users = target(PATH + "/").queryParam("q", "fsf").request().get(listUserResponseType);
//        assertEquals(2, users.size());
//    }
//}