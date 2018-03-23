package fr.iutinfo.skeleton.api;

import static fr.iutinfo.skeleton.api.BDDFactory.getDbi;
import static fr.iutinfo.skeleton.api.BDDFactory.tableExist;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.OffreDto;

@Path("/offre")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OffreResource {
	final static Logger logger = LoggerFactory.getLogger(OffreResource.class);
	private static OffreDao dao = getDbi().open(OffreDao.class);

	public OffreResource() throws SQLException {
		if (!tableExist("offres")) {
			logger.debug("Create table offres");
			dao.createOffreTable();
			dao.insert(new Offre(0, "Margaret Thatcher", "2018-03-21", "2018-06-21", "",
					new Entreprise().getId()));
		}
	}

	@POST
	public OffreDto createOffre(OffreDto dto) {
		Offre offre = new Offre();
		logger.debug("dto : " + dto.toString());
		offre.initFromDto(dto);
		logger.debug("offre : " + offre.toString());
		int id = dao.insert(offre);
		dto.setId(id);
		return dto;
	}

	@GET
	@Path("intitule/{intitule}")
	public OffreDto getOffre(@PathParam("intitule") String intitule) {
		Offre offre = dao.findByIntitule(intitule);
		if (offre == null) {
			throw new WebApplicationException(404);
		}
		System.out.println("OFFRE: "+ offre);
		return offre.convertToDto();
	}
	
	@GET
	@Path("id/{intitule}")
	public OffreDto getOffreId(@PathParam("id") int id) {
		Offre offre = dao.findById(id);
		if (offre == null) {
			throw new WebApplicationException(404);
		}
		System.out.println("OFFRE: "+ offre);
		return offre.convertToDto();
	}

	@GET
	public List<OffreDto> getAllOffres(@QueryParam("q") String query) {
		List<Offre> offres;
		if (query == null) {
			offres = dao.all();
		} else {
			logger.debug("Search offres with query: " + query);
			offres = dao.search("%" + query + "%");
		}
		return offres.stream().map(Offre::convertToDto).collect(Collectors.toList());
	}

	@DELETE
	@Path("{id}")
	public void deleteOffre(@PathParam("id") int id) {
		dao.delete(id);
	}

}
