package fr.iutinfo.skeleton.api;

import fr.iutinfo.skeleton.common.dto.EntrepriseDTO;
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

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntrepriseRessource {
    final static Logger logger = LoggerFactory.getLogger(EntrepriseRessource.class);
    private static EntrepriseDAO dao = getDbi().open(EntrepriseDAO.class);

    public EntrepriseRessource() throws SQLException {
        if (!tableExist("users")) {
            logger.debug("Crate table users");
            dao.createUserTable();
            dao.insert(new Entreprise(0, "Izyfree", "Benjamin"));
        }
    }

    @POST
    public EntrepriseDTO createEntreprise(EntrepriseDTO dto) {
    	Entreprise entre = new Entreprise();
        entre.initFromDto(dto);
        entre.resetPasswordHash();
        int id = dao.insert(entre);
        dto.setId(id);
        return dto;
    }

    @GET
    @Path("/{name}")
    public EntrepriseDTO getEntreprise(@PathParam("name") String name) {
        Entreprise entre = dao.findByName(name);
        if (entre == null) {
            throw new WebApplicationException(404);
        }
        return entre.convertToDto();
    }

    @GET
    public List<EntrepriseDTO> getAllEntreprise(@QueryParam("q") String query) {
        List<Entreprise> users;
        if (query == null) {
            users = dao.all();
        } else {
            logger.debug("Search entreprise with query: " + query);
            users = dao.search("%" + query + "%");
        }
        return users.stream().map(Entreprise::convertToDto).collect(Collectors.toList());
    }

    @DELETE
    @Path("/{id}")
    public void deleteEntreprise(@PathParam("id") int id) {
        dao.delete(id);
    }

}
