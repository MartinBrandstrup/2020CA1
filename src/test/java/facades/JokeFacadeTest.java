package facades;

import utils.EMF_Creator;
import entities.Joke;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class JokeFacadeTest
{

    private static EntityManagerFactory emf;
    private static JokeFacade facade;

    static private int numberOfDummies = 5;

    public JokeFacadeTest()
    {
    }

    //@BeforeAll
    public static void setUpClass()
    {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/2020CA1_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = JokeFacade.getJokeFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2()
    {
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = JokeFacade.getJokeFacade(emf);
    }

    @AfterAll
    public static void tearDownClass()
    {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp()
    {
        EntityManager em = emf.createEntityManager();

        try
        {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();

            for (int i = 0; i < numberOfDummies; i++)
            {
                String title = "title" + i;
                String body = "body" + i;
//                em.persist(new Joke(title, body));
            }
//            jokeForIdTest = new Joke("titleId", "bodyId");
//            em.persist(jokeForIdTest);

            em.getTransaction().commit();
        }
        finally
        {
            em.close();
        }
    }

    @AfterEach
    public void tearDown()
    {
//        Remove any data after each test was run
    }

//    @Test
//    public void jokeCountTest()
//    {
//        assertEquals(2, facade.getJokeCount(), "Expects" + numberOfDummies + "rows in the database");
//    }

}
