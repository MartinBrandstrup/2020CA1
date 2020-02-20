package facades;

import dtos.CarDTO;
import entities.Car;
import entities.Joke;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Brandstrup
 */
public class CarFacade
{

    private static CarFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private CarFacade()
    {
    }

    /*
    This class contains the following methods in this order:
    getCarFacade(EntityManagerFactory _emf)
    getEntityManager()
    getCarCount()
    getAllCars()
    getCarByID(int id)
    getCarDTOByID(int id)
    persistCar(Car c)
    populateDatabaseWithCars(int numberOfEntries)
     */
    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CarFacade getCarFacade(EntityManagerFactory _emf)
    {
        if (instance == null)
        {
            emf = _emf;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public long getCarCount()
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            long carCount = (long) em.createQuery("SELECT COUNT(c) FROM Car c").getSingleResult();
            return carCount;
        }
        finally
        {
            em.close();
        }

    }

    /**
     * Retrieves all Cars from the database as CarDTO objects. Returns null if 
     * failed.
     *
     * @return a List<CarDTO>
     */
    public List<CarDTO> getAllCars()
    {
        EntityManager em = getEntityManager();
        try
        {
            List<CarDTO> carDTOList = new ArrayList<>();
            TypedQuery<Car> query
                    = em.createQuery("SELECT c FROM Car c", Car.class);

            query.getResultList().forEach((c) ->
            {
                carDTOList.add(new CarDTO(c));
            });

            return carDTOList;
        }
        catch (Exception ex)
        {
            System.out.println("Operation getAllCars failed.");
            return null;
        }
        finally
        {
            em.close();
        }
    }
    
    /**
     * Attempts to retrieve a Car object from the database corresponding to
     * the provided ID. Used mainly for back-end work, since not all information 
     * of the Car object should be displayed on the front-end.
     * Returns null if operation fails
     * 
     * @param id The provided ID to search the database for.
     * @return a Car object containing all information.
     */
    public Car getCarByID(int id)
    {
        EntityManager em = getEntityManager();
        try
        {
            Car c = em.find(Car.class, id);
            return c;
        }
        catch (Exception ex)
        {
            System.out.println("Failed to find the specified Car object.");
            return null;
        }
        finally
        {
            em.close();
        }
    }

    /**
     * Attempts to retrieve a CarDTO object from the database corresponding to 
     * the provided ID. Used mainly for front-end since not all necessary info 
     * is provided with a DTO object.
     * Returns null if operation fails.
     * 
     * @param id The provided ID to search the database for.
     * @return a CarDTO object containing necessary information to be displayed 
     * on the front-end.
     */
    public CarDTO getCarDTOByID(int id)
    {
        EntityManager em = getEntityManager();
        try
        {
            Car c = em.find(Car.class, id);
            CarDTO cDTO = new CarDTO(c);
            return cDTO;
        }
        catch (Exception ex)
        {
            System.out.println("Failed to find the specified Car object.");
            return null;
        }
        finally
        {
            em.close();
        }
    }

    /**
     * Attempts to persist a Car object to the DB. Returns the persisted object
     * if successful, null if the operation fails.
     *
     * @param c The Car object to persist.
     * @return the same Car object that has been persisted.
     */
    public Car persistCar(Car c)
    {
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        }
        catch (Exception ex)
        {
            System.out.println("Failed to persist provided Car object.");
            return null;
        }
        finally
        {
            em.close();
        }
    }

    /**
     * Populates the database with a set of dummy entries for testing. Returns
     * null if failed. WARNING: empties the database of any current entries!
     *
     * @param numberOfEntries The number of entries to populate with.
     * @return a List<Car> containing the same objects that has just been 
     * persisted to the database (except the objects' IDs).
     */
    public List<Car> populateDatabaseWithCars(int numberOfEntries)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            
            List<Car> carList = new ArrayList<>();
            
            for (int i = 0; i < numberOfEntries; i++)
            {
                String make = "make" + i;
                String model = "model" + i;
                LocalDate localD = LocalDate.of(2001, Month.MARCH, 13);
//                LocalDate localD = LocalDate.now();
                double price = 1000 + i;
                String owner = "owner" + i;
                String color = "color" + i;
                Car c = new Car(make, model, localD, price, owner, color, Car.FuelType.Diesel);
                carList.add(c);
                em.persist(c);
            }
            em.getTransaction().commit();
            return carList;
        }
        catch (Exception ex)
        {
            System.out.println("Operation populateDatabaseWithCars failed.");
            return null;
        }
        finally
        {
            em.close();
        }
    }

}
