package facades;

import entities.Car;
import entities.Joke;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
     * Populates the database with a set of dummy entries for testing.WARNING: empties the database of any current entries!
     * 
     * @param numberOfEntries The number of entries to populate with.
     * @return a String reporting the result.
     */
    public String populateDatabaseWithCars(int numberOfEntries)
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            for (int i = 0; i < numberOfEntries; i++)
            {
                String make = "make" + i;
                String model = "model" + i;
//                Date year = new SimpleDateFormat("yyyy").parse("2000");
                double price = 1000 + i;
                String owner = "owner" + i;
                String color = "color" + 1;
                em.persist(new Car(make, model, price, owner, color, Car.FuelType.Diesel));
            }
            em.getTransaction().commit();
            return numberOfEntries + " entries successfully added to database";
        }
        finally
        {
            em.close();
        }
    }

}
