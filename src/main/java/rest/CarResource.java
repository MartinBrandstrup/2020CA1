package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CarDTO;
import entities.Joke;
import facades.CarFacade;
import utils.EMF_Creator;
import facades.JokeFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Brandstrup
 */
@Path("car")
public class CarResource
{

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/2020CA1",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);

    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    private static final CarFacade FACADE = CarFacade.getCarFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(
            {
                MediaType.APPLICATION_JSON
            })
    public String demo()
    {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("/count")
    @GET
    @Produces(
            {
                MediaType.APPLICATION_JSON
            })
    public String carCount()
    {
        long count = FACADE.getCarCount();
        return "{\"count\":" + count + "}";
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCars()
    {
        List<CarDTO> cDTOList = FACADE.getAllCars();
        if (cDTOList != null)
        {
            return GSON.toJson(cDTOList);
        }
        else
        {
            return "{\"msg\":\"Operation getAllCars failed\"}";
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCarByID(@PathParam("id") int id)
    {
        CarDTO cDTO = FACADE.getCarDTOByID(id);
        if (cDTO != null)
        {
            return GSON.toJson(cDTO);
        }
        else
        {
            return "{\"msg\":\"Operation getCarById " + id + " failed\"}";
        }
    }

}
