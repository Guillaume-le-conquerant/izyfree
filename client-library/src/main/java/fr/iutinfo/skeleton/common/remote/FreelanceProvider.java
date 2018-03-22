package fr.iutinfo.skeleton.common.remote;

import fr.iutinfo.skeleton.common.dto.FreelanceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class FreelanceProvider {

    final static Logger logger = LoggerFactory.getLogger(FreelanceProvider.class);
    private String baseUrl;

    public FreelanceProvider(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    public List<FreelanceDto> readAllUsers() {
        try {
            return ClientBuilder.newClient()//
                    .target(baseUrl + "freelance/")
                    .request()
                    .get(new GenericType<List<FreelanceDto>>() {
                    });
        } catch (Exception e) {
            String message = ClientBuilder.newClient()
                    .target(baseUrl + "freelance/")
                    .request()
                    .get(String.class);

            logger.error(e.getMessage());
            throw new RuntimeException(message);
        }
    }

    public FreelanceDto addFreelance(FreelanceDto freelance) {
        logger.debug("Create user : " + freelance.getName());
        Entity<FreelanceDto> freelanceEntity = Entity.entity(freelance, MediaType.APPLICATION_JSON);

        return ClientBuilder.newClient()
                .target(baseUrl + "freelance/")
                .request()
                .post(freelanceEntity)
                .readEntity(FreelanceDto.class);
    }

    public FreelanceDto readFreelance(String name) {
        String url = baseUrl + "freelance/" + name;
        logger.debug("Read url : " + url);

        return ClientBuilder.newClient()//
                .target(url)
                .request()
                .get(FreelanceDto.class);
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}