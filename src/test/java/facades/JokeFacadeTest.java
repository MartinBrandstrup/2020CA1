//package facades;
//
//import dtos.JokeDTO;
//import utils.EMF_Creator;
//import entities.Joke;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Arrays;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.transaction.Transactional;
//import org.junit.Assert;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import utils.Settings;
//import utils.EMF_Creator.DbSelector;
//import utils.EMF_Creator.Strategy;
//
//Uncomment the line below, to temporarily disable this test
//@Disabled
//public class JokeFacadeTest
//
//
//
//{
//
//    private static EntityManagerFactory emf;
//    private static JokeFacade facade;
//
//    static private int numberOfJokes = 5;
//      
//           Joke joke1 = new Joke("plat", "endnu en joke", 6, "farlig"); 
//           Joke joke2 = new Joke("plat", "endnu en joke", 6, "farlig");
//
//    public JokeFacadeTest()
//    {
//    }
//
//
//    /**
//     * Test of getAllJokes method, of class JokeFacade.
//     */
//    @org.junit.Test
//    public void testGetAllJokes() {
//   
//    
//    {
//        Joke joke = new Joke();
//    
//        
//        joke.setType("plat");
//        joke.setJoke("Dette er en joke");
//        joke.setFunnyness(6);
//        joke.setDescription("Meget speciel");
//        facade.addJoke(expectedResult, expectedResult, numberOfJokes, expectedResult);
//         List<JokeDTO> jokelist =  facade.getAllJokes();
//        Assert.assertEquals(1, jokelist.size());
//        Assert.assertEquals()
//        
//}
//
//         
//    /**
//     * Test of getByID method, of class JokeFacade.
//     */
//    
////    }
//
//}
