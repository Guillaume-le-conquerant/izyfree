package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.StaffDto;
import fr.iutinfo.skeleton.common.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

@Path("/staff")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StaffResource {
    final static Logger logger = LoggerFactory.getLogger(StaffResource.class);
    private static StaffDao dao = getDbi().open(StaffDao.class);

    public StaffResource() throws SQLException {
        if (!tableExist("staff")) {
            logger.debug("Create table staff");
            dao.createStaffTable();
            dao.insert(new Staff("Margarita"));
        }
    }

    @POST
    public StaffDto createStaff(StaffDto dto) {
        Staff staff= new Staff();
        staff.initFromDto(dto);
        staff.resetPasswordHash();
        int id = dao.insert(staff);
        return dto;
    }

    @GET
    @Path("{pseudo}")
    public StaffDto getStaff(@PathParam("pseudo") String pseudo) {
        Staff staff = dao.findByPseudo(pseudo);
        if (staff == null) {
            throw new WebApplicationException(404);
        }
        return staff.convertToDto();
    }

    @GET
    public List<StaffDto> getAllStaff(@QueryParam("q") String query) {
        List<Staff> staff;
        if (query == null) {
            staff = dao.all();
        } else {
            logger.debug("Search staff with query: " + query);
            staff = dao.search("%" + query + "%");
        }
        return staff.stream().map(Staff::convertToDto).collect(Collectors.toList());
    }

    @DELETE
    @Path("{pseudo}")
    public void deleteUser(@PathParam("pseudo") String pseudo) {
        dao.delete(pseudo);
    }

}