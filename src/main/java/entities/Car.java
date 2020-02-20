package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import static javax.persistence.TemporalType.DATE;

/**
 *
 * @author Brandstrup
 */
@Entity
@NamedQuery(name = "Car.deleteAllRows", query = "DELETE from Car")
public class Car implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String make;
    private String model;
    @Temporal(TemporalType.DATE)
    private Date date;
    private double price;
    private String owner;
    private String color;
    private FuelType fuel;

    public enum FuelType
    {
        Diesel,
        Electric,
        Hybrid
    }

    private Date convertToDateViaInstant(LocalDate dateToConvert)
    {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant().plusSeconds(86400));
    }

    private LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert)
    {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public Car()
    {
    }

    /**
     * Creates a Car entity with a LocalDate. Used when persisting to DB.
     *
     * @param date LocalDate, use LocalDate.now() for the current time and use
     * LocalDate.of(2001, Month.MARCH, 13) for instance for specific dates.
     */
    public Car(String make, String model, LocalDate date, double price, String owner, String color, FuelType fuel)
    {
        this.make = make;
        this.model = model;
        this.date = convertToDateViaInstant(date);
        this.price = price;
        this.owner = owner;
        this.color = color;
        this.fuel = fuel;
    }

    /**
     * Creates a Car entity with a Date. Mostly used when retrieving from the
     * DB.
     *
     * @param date A regular java.util.Date, which is the SQL's preferred
     * format.
     */
    public Car(String make, String model, Date date, double price, String owner, String color, FuelType fuel)
    {
        this.make = make;
        this.model = model;
        this.date = date;
        this.price = price;
        this.owner = owner;
        this.color = color;
        this.fuel = fuel;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    /**
     * Retrieves a Car's Date as a LocalDate.
     *
     * @return a LocalDateTime.
     */
    public LocalDateTime getLocalDate()
    {
        return convertToLocalDateTimeViaInstant(date);
    }

    /**
     * Retrieves a Car's Date as a java.util.Date.
     *
     * @return a Date.
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * Updates a Car's Date using a LocalDate format.
     *
     * @param date LocalDate, use LocalDate.now() for the current time and use
     * LocalDate.of(2001, Month.MARCH, 13) for instance for specific dates.
     */
    public void setDate(LocalDate date)
    {
        this.date = convertToDateViaInstant(date);
    }

    /**
     * Updates a Car's Date using a java.util.Date format.
     *
     * @param date A regular java.util.Date, which is the SQL's preferred
     * format.
     */
    public void setDate(Date date)
    {
        this.date = date;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public FuelType getFuel()
    {
        return fuel;
    }

    public void setFuel(FuelType fuel)
    {
        this.fuel = fuel;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Car other = (Car) obj;
        if (this.id != other.id)
        {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price))
        {
            return false;
        }
        if (!Objects.equals(this.make, other.make))
        {
            return false;
        }
        if (!Objects.equals(this.model, other.model))
        {
            return false;
        }
        if (!Objects.equals(this.owner, other.owner))
        {
            return false;
        }
        if (!Objects.equals(this.color, other.color))
        {
            return false;
        }
        if (!Objects.equals(this.date, other.date))
        {
            return false;
        }
        if (this.fuel != other.fuel)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Car{" + "id=" + id + ", make=" + make + ", model=" + model
                + ", date=" + date.toString() + ", price=" + price + ", owner="
                + owner + ", color=" + color + ", fuel=" + fuel + '}';
    }
    
    

}
