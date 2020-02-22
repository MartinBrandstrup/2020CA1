package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.JokeDTO;
import entities.Joke;
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

@Path("joke")
public class JokeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/2020CA1",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    
    //An alternative way to get the EntityManagerFactory, whithout having to type the details all over the code
    //EMF = EMF_Creator.createEntityManagerFactory(DbSelector.DEV, Strategy.CREATE);
    
    private static final JokeFacade FACADE =  JokeFacade.getJokeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String jokeCount() {
        long count = FACADE.getJokeCount();
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }

    @Path("fill")
    @GET
     @Produces({MediaType.APPLICATION_JSON})
    public String fillDatabaseWithJokes() {
    
        FACADE.addJoke("plat", "Hvad er det værste ved at blive fyret fra et jobcenter? Man skal også møde op dagen efter.", 4, "børnevenlig");
        FACADE.addJoke("god", "Hvad er det værste ved at blive fyret fra et jobcenter? Man skal også møde op dagen efter.", 4, "børnevenlig");
     
        return GSON.toJson("Database filled");
        
    }
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeById(@PathParam("id")Long id) {
        
        Joke joke = FACADE.getJokeByID(id);
         return GSON.toJson(joke);
        
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllJokes(){
        
        List<JokeDTO> all = FACADE.getAllJokes();
        return GSON.toJson(all);
    }
 
}
