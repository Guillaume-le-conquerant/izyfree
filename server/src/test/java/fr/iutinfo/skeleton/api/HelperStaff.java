package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.StaffDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.GenericType;
import java.util.List;

public class HelperStaff {
    private final static Logger logger = LoggerFactory.getLogger(HelperStaff.class);
    private static final StaffDao dao = BDDFactory.getDbi().open(StaffDao.class);
    static GenericType<List<StaffDto>> listUserResponseType = new GenericType<List<StaffDto>>() {
    };

    public static void initDb() {
        dao.dropStaffTable();
        dao.createStaffTable();
    }
    

    static Staff createStaffWithName(String name) {
        Staff staff = new Staff(0, name);
        return createStaff(staff);
    }

    static Staff createStaffWithEmail(String name, String email) {
        Staff staff = new Staff(0, name);
        staff.setMail(email);
        return createStaff(staff);
    }

    public static Staff createStaffWithPassword(String name, String passwd, String salt) {
        Staff staff = new Staff(0, name);
        staff.setSalt(salt);
        staff.setPassword(passwd);
        logger.debug("createStaffWithPassword Hash : " + staff.getPasswdHash());
        return createStaff(staff);
    }

    private static Staff createStaff(Staff staff) {
        int id = dao.insert(staff);
        staff.setId(id);
        return staff;
    }
    


    private static Staff createFullStaff(String name, String email, String paswword) {
        Staff staff = new Staff(0, name);
        staff.setMail(email);
        staff.setPassword(paswword);
        int id = dao.insert(staff);
        staff.setId(id);
        return staff;
    }
    
    static Staff createBenjamin() {
        return createFullStaff("Petit", "benjamin.petit@izyfree.com", "izy");
    }

    
    static Staff createStaffWithPower(String name) {
    	Staff staff = new Staff(0, "Ballet");
    	staff.setFirstname("Audrey");
        staff.setMail("audrey.ballet@izyfree.com");
        staff.setPassword("izy");
        int id = dao.insert(staff);
        staff.setId(id);
        return staff;
    }
    

}