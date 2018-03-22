package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.FreelanceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

@Path("/freelance")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FreelanceResource {
    final static Logger logger = LoggerFactory.getLogger(FreelanceResource.class);
    private static FreelanceDao dao = getDbi().open(FreelanceDao.class);

    public FreelanceResource() throws SQLException {
        if (!tableExist("freelance")) {
            logger.debug("Create table freelance");
            dao.createUserTable();
            dao.insert(new Freelance(0, "Petit", "Benjamin"));
        }
    }

    @POST
    public FreelanceDto createFreelance(FreelanceDto dto) {
    	Freelance free = new Freelance();
        free.initFromDto(dto);
        free.resetPasswordHash();
        int id = dao.insert(free);
        dto.setId(id);
        return dto;
    }

    @GET
    @Path("{name}")
    public FreelanceDto getFreelance(@PathParam("name") String name) {
        Freelance free = dao.findByName(name);
        if (free == null) {
            throw new WebApplicationException(404);
        }
        return free.convertToDto();
    }

    @GET
    public List<FreelanceDto> getAllFreelance(@QueryParam("q") String query) {
        List<Freelance> freelance;
        if (query == null) {
            freelance = dao.all();
        } else {
            logger.debug("Search entreprise with query: " + query);
            freelance = dao.search("%" + query + "%");
        }
        return freelance.stream().map(Freelance::convertToDto).collect(Collectors.toList());
    }

    @DELETE
    @Path("{id}")
    public void deleteFreelance(@PathParam("id") int id) {
        dao.delete(id);
    }

}