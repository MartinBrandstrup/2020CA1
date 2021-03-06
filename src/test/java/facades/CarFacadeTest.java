package facades;

import dtos.CarDTO;
import entities.Car;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import utils.EMF_Creator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class CarFacadeTest
{

    private static EntityManagerFactory emf;
    private static CarFacade facade;

    static private int numberOfDummies = 5;

    public CarFacadeTest()
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
        facade = CarFacade.getCarFacade(emf);
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
        facade = CarFacade.getCarFacade(emf);
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
//        EntityManager em = emf.createEntityManager();
//        try
//        {
//            em.getTransaction().begin();
//            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            facade.populateDatabaseWithCars(numberOfDummies);
//            em.getTransaction().commit();
//        }
//        finally
//        {
//            em.close();
//        }
    }

    @AfterEach
    public void tearDown()
    {
//        Remove any data after each test was run
    }

    @Test
    public void carCountTest()
    {
        assertEquals(numberOfDummies, facade.getCarCount(), "Expects " + numberOfDummies + " rows in the database");
    }

    @Test
    public void getAllCarsTest()
    {
        //populating database twice in order to get access to the objects I am
        //persisting to the database
//        List<Car> carListToPersist = facade.populateDatabaseWithCars(numberOfDummies);
//        List<CarDTO> carListToCompare = new ArrayList<>();
        List<CarDTO> databaseContent = facade.getAllCars();

        assertFalse(databaseContent == null);
        assertFalse(databaseContent.isEmpty());
        assertEquals(numberOfDummies, databaseContent.size(), "Expects " + numberOfDummies + " rows in the database");

        //Setting the ID of all objects to 0 to circumvent the issue of
        //autogenerated IDs in the database
//        int currentID = 0;
//
//        for (Car c : carListToPersist)
//        {
//            CarDTO cDTO = new CarDTO(c);
//            cDTO.setId(currentID);
////            currentID += 1;
//            carListToCompare.add(cDTO);
//        }
//
//        currentID = 0;
//
//        for (CarDTO cDTO : databaseContent)
//        {
//            cDTO.setId(currentID);
////            currentID += 1;
//        }
//
//        assertTrue(databaseContent.size() == carListToCompare.size()
//                && databaseContent.containsAll(carListToCompare)
//                && carListToCompare.containsAll(databaseContent));

        //Doesn't work since, while the objects may be identical, they are not
        //the same. Would have to compare every attribute of every object to verify
    }

    @Test
    public void persistCarTest()
    {
        facade.persistCar(new Car("make", "model", LocalDate.now(), 100, "owner", "color", Car.FuelType.Diesel));
        assertEquals(numberOfDummies + 1, facade.getAllCars().size(), "Expects " + numberOfDummies + 1 + " rows in the database");
    }

    @Test
    public void getCarByIDTest()
    {
        //Persisting a new car to the database after the SetUp to make sure it
        //has been added last
        Car testCar = facade.persistCar(new Car("make", "model", LocalDate.now(), 100, "owner", "color", Car.FuelType.Diesel));
        assertEquals(numberOfDummies + 1, facade.getAllCars().size(), "Expects " + numberOfDummies + 1 + " rows in the database");

        //Getting the id of the last added entry in database
        int newestAdditionID = facade.getAllCars().get(numberOfDummies).getId();
        testCar.setId(newestAdditionID);

        Car testCarFromDatabase = facade.getCarByID(newestAdditionID);

        assertEquals(testCarFromDatabase.getId(), testCar.getId());
        assertEquals(testCarFromDatabase.getMake(), testCar.getMake());
        assertEquals(testCarFromDatabase.getModel(), testCar.getModel());
        assertEquals(testCarFromDatabase.getDate(), testCar.getDate());
    }

    @Test
    public void getCarDTOByIDTest()
    {
        //Persisting a new car to the database after the SetUp to make sure it
        //has been added last
        Car testCar = facade.persistCar(new Car("make", "model", LocalDate.now(), 100, "owner", "color", Car.FuelType.Diesel));
        assertEquals(numberOfDummies + 1, facade.getAllCars().size(), "Expects " + numberOfDummies + 1 + " rows in the database");

        //Converting testCar to a DTO object
        CarDTO testCarDTO = new CarDTO(testCar);

        //Getting the id of the last added entry in database
        int newestAdditionID = facade.getAllCars().get(numberOfDummies).getId();
        testCar.setId(newestAdditionID);

        CarDTO testCarFromDatabase = facade.getCarDTOByID(newestAdditionID);

        assertEquals(testCarFromDatabase.getId(), testCarDTO.getId());
        assertEquals(testCarFromDatabase.getMake(), testCarDTO.getMake());
        assertEquals(testCarFromDatabase.getModel(), testCarDTO.getModel());
        assertEquals(testCarFromDatabase.getYear(), testCarDTO.getYear());
    }

}
