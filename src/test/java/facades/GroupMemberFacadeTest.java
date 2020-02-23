/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.GroupMemberDTO;
import entities.GroupMember;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author Christian
 */
public class GroupMemberFacadeTest {

    private static EntityManagerFactory emf;
    private static GroupMemberFacade facade;

    static private int numberOfDummies = 5;
    GroupMember grpMeb1 = new GroupMember("Test1", "TestId1", GroupMember.ColorType.Yellow/*, "NewMember.dk"*/);
    GroupMember grpMeb2 = new GroupMember("Test2", "TestId2", GroupMember.ColorType.Green/*, "Email.dk"*/);

    public GroupMemberFacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/2020CA1_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = GroupMemberFacade.getGroupmemberFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = GroupMemberFacade.getGroupmemberFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();
            em.createNamedQuery("GroupMember.deleteAllRows").executeUpdate();

            // what to presist to DB
            em.persist(grpMeb1);
            em.persist(grpMeb2);

            // what to commit
            em.getTransaction().commit();
        } // must allways close transations. 
        finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    /**
     * Test to see if number of expected groupMebers rows where added to DB and that the getGroupMember works. 
     */
    @Test
    public void GroupMemberCountTest() {
        System.out.println("Count all GroupMembers");
        assertEquals(2, facade.getGroupMemberCount(), "Expects 2 rows in the database");
    }
    
     @Test
    public void testAddGroupMember() {
        System.out.println("addGroupMember");
        GroupMemberFacade gmf = facade;
        Long groupMemberID = gmf.addGroupMember(grpMeb1.getName(), grpMeb1.getStudentId(), grpMeb1.getColor()).getId();
        System.out.println(groupMemberID);
        assertNotNull(groupMemberID);
    }
////////////////////////// tring to make more complex testing work \\\\\\\\\\\\\\\\\\\\\\\\\\\\
    /**
     * Tests if you can find a GroupMember by id. Not sure if this is valid way
     * Theacers must confrim
     *
     * @param id
     */
//    @Test
//    public void findGroupMemberById() {
//        long id = 5;
//        System.out.println("FindGroupMemberbyId");
//        GroupMember grp1 = facade.findGroupMemberById(id);
//        GroupMember expected = (grp1 == grpMeb1) ? grpMeb1 : grpMeb2;
//        assertEquals(expected, facade.findGroupMemberById(id), "Expects 2 rows in the database");
//        System.out.println("expected: " + expected + " Was: " + facade.findGroupMemberById(id));
//    }

   
    /**
     * Tests to see if all All GroupMembers can be acquired from DB Has been
     * confirmed to work (via stacktrace) but do to persistens delay, somethings
     * autoGen id gets mix up, need to fix this probly by sorting
     *
     */
//    @Test
//    public void getAllGroupMembers() {
//        System.out.println("allGroupMembers");
//        List<GroupMember> list = new ArrayList();
//        list.add(grpMeb1);
//        list.add(grpMeb2);
//        List<GroupMember> list2 = facade.getAllGroupMembers();
//        // need to find a way to find identification id generated by db
//        assertEquals(list, facade.getAllGroupMembers(), "Expects the 2 test persons");
//    }

//     /**
//     *
//     */
//    @Test
//    public void addGroupMember() {
//        EntityManager em = emf.createEntityManager();
//        System.out.println("addGroupMember");
//        //clear DB, so only one member can be found, therefor makeing id = 1
//        try {
//
//            em.getTransaction().begin();
//            em.createNamedQuery("GroupMember.deleteAllRows").executeUpdate();
//            em.getTransaction().commit();
//        } // must allways close transations. 
//        finally {
//            em.close();
//        }
//        //adding one member makes him esay to find
//        //GroupMember member = new GroupMember(grpMeb1.getName(), grpMeb1.getStudentId(), grpMeb1.getColor(), grpMeb1.getEmail());
//        facade.addGroupMember(grpMeb1.getName(), grpMeb1.getStudentId(), grpMeb1.getColor());
//        // using 1 as id, because there is only on member in DB at this point. 
//       
//        assertEquals(grpMeb1, facade.findGroupMemberById(5), "Expects to find grpMem1");
//
//    }
}
