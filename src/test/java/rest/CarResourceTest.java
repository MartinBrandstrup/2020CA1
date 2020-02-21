package rest;

import entities.Car;
import entities.Joke;
import facades.CarFacade;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class CarResourceTest
{

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";

    static private int numberOfDummies = 5;

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;
    private static CarFacade facade;

    static HttpServer startServer()
    {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass()
    {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.CREATE);

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
        
        facade = CarFacade.getCarFacade(emf);
    }

    @AfterAll
    public static void closeTestServer()
    {
        //System.in.read();
        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
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

    @Test
    public void testServerIsUp()
    {
        System.out.println("Testing is server UP");
        given().when().get("/api").then().statusCode(200);
    }

    //This test assumes the database contains two rows
    @Test
    public void testDummyMsg() throws Exception
    {
        given()
                .contentType("application/json")
                .get("/api/").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("msg", equalTo("Hello World"));
    }

    @Test
    public void countTest() throws Exception
    {
        given()
                .contentType("application/json")
                .get("/car/count").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("count", equalTo(numberOfDummies));
    }

    @Test
    public void allCarsTest() throws Exception
    {
        int nmbrTest1 = numberOfDummies - 1;
        int nmbrTest2 = numberOfDummies - 2;

        given()
                .contentType("application/json")
                .get("/car/all").then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body(CoreMatchers.containsString("make" + nmbrTest1))
                .and().assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body(CoreMatchers.containsString("make" + nmbrTest2));
    }

    @Test
    public void carIDTest() throws Exception
    {
        Car testCar = facade.persistCar(new Car("make", "model", LocalDate.now(), 100, "owner", "color", Car.FuelType.Diesel));
        
        int newestAdditionID = facade.getAllCars().get(numberOfDummies).getId();
        testCar.setId(newestAdditionID);
        
        given()
                .contentType("application/json")
                .get("/car/" + newestAdditionID).then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("model", equalTo(testCar.getModel()));
    }
}
