package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.GroupMemberDTO;
import entities.GroupMember;
import facades.GroupMemberFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author Christian
 */
@Path("groupmembers")
public class GroupMemberResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/2020CA1",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final GroupMemberFacade FACADE = GroupMemberFacade.getGroupmemberFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllGroupMembers(){
        List<GroupMemberDTO> dtos = new ArrayList<>();
        List<GroupMember> members = FACADE.getAllGroupMembers();
        for (GroupMember member : members) {
            dtos.add(new GroupMemberDTO(member));
        }
        return GSON.toJson(dtos);
    }
    
    @Path("fill")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String fill(){
        
        FACADE.addGroupMember("Christian A Kehr", "cph-ck209", GroupMember.ColorType.Green);
        FACADE.addGroupMember("Claus Findinge", "cph-cf124", GroupMember.ColorType.Green);
        FACADE.addGroupMember("MartinBrandstrup", "cph-mb604", GroupMember.ColorType.Yellow);
        return GSON.toJson("Database filled with members");
    }
}