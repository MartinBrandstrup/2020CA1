/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Car;
import java.util.Date;

/**
 *
 * @author Brandstrup
 */
public class CarDTO
{
    private int id;
    private String make;
    private String model;
    private String year;
    private double price;
    private String color;
    private String fuel;

    public CarDTO(Car car)
    {
        this.id = car.getId();
        this.make = car.getMake();
        this.model = car.getModel();
        this.year = String.valueOf(car.getLocalDate().getYear());
        this.price = car.getPrice();
        this.color = car.getColor();
        this.fuel = car.getFuel().toString();
    }
    
    
}
