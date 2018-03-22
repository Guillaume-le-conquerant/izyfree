package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.FreelanceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.GenericType;
import java.util.List;

public class HelperFreelance {
    private final static Logger logger = LoggerFactory.getLogger(HelperFreelance.class);
    private static final FreelanceDao dao = BDDFactory.getDbi().open(FreelanceDao.class);
    static GenericType<List<FreelanceDto>> listUserResponseType = new GenericType<List<FreelanceDto>>() {
    };

    public static void initDb() {
        dao.dropFreelanceTable();
        dao.createFreelanceTable();
    }

    static Freelance createFreelanceWithName(String name) {
        Freelance free = new Freelance(0, name);
        return createFreelance(free);
    }


    static Freelance createFreelanceWithEmail(String name, String email) {
        Freelance free = new Freelance(0, name);
        free.setEmail(email);
        return createFreelance(free);
    }

    public static Freelance createFreelanceWithPassword(String name, String passwd, String salt) {
        Freelance free= new Freelance(0, name);
        free.setSalt(salt);
        free.setPassword(passwd);
        logger.debug("createFreelanceWithPassword Hash : " + free.getPasswdHash());
        return createFreelance(free);
    }

    private static Freelance createFreelance(Freelance free) {
        int id = dao.insert(free);
        free.setId(id);
        return free;
    }


    private static Freelance createFullFreelance(String name, String email, String paswword) {
        Freelance free = new Freelance(0, name);
        free.setEmail(email);
        free.setPassword(paswword);
        int id = dao.insert(free);
        free.setId(id);
        return free;
    }

    static void createRms() {
        createFullFreelance("Richard Stallman", "rms@fsf.org", "gnuPaswword");
    }

    static Freelance createRob() {
        return createFullFreelance("Robert Capillo","rob@fsf.org", "paswword");
    }

    static Freelance createLinus() {
        return createFullFreelance("Linus Torvalds", "linus@linux.org", "paswword");
    }

    static Freelance createIan() {
        return createFullFreelance("Ian Murdock", "ian@debian.org", "mot de passe");
    }
}
