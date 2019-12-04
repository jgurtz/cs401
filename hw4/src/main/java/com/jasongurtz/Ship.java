package com.jasongurtz;

import java.io.*;
import java.util.Scanner;

/**
 * A generic Ship (vessel) class
 * <p>
 *  A basic definition of a ship, including the ship name and year of construction.
 * </p>
 *
 * {@link CruiseShip}
 * {@link CargoShip}
 *
 * @author Jason Gurtz-Cayls
 */
public class Ship {
	private String name;
	private String year;

    /**
     * Constructer sets both ship attributes
     *
     * @param nam The ship's name
     * @param yr The year the ship was built
     *
     * @author Jason Gurtz-Cayls
     */
    public Ship(String nam, String yr) {
        setName(nam);
        setYear(yr);
    }

    /**
     * Overridden toString() returns both Ship attributes as a String 
     *
     * @return String containing the values of the Ship's name and year of construction.
     *
     * @author Jason Gurtz-Cayla
     */
    @Override
    public String toString() {
        return String.format("%s, %s", this.getName(), this.getYear());
    }

    /**
     * Name getter
     *
     * @return The Ship's name
     *
     * @author Jason Gurtz-Cayla
     */
    public String getName() {
        return this.name;
    }

    /**
     * MaxPassenger getter
     *
     * @return The Ship's year of construction
     *
     * @author Jason Gurtz-Cayla
     */
    public String getYear() {
        return this.year;
    }

    /**
     * Name setter
     *
     * @param nam The Ship's name
     *
     * @author Jason Gurtz-Cayla
     */
    public void setName(String nam) {
        this.name = nam;
    }

    /**
     * Construction year setter
     *
     * @param yr The Ship's year of construction
     *
     * @author Jason Gurtz-Cayla
     */
    public void setYear(String yr) {
        this.year = yr;
    }

    /**
     * Main class method and entry point which tests the classes
     *
     * @param args Command line arguments (for future use)
     *
     * @author Jason Gurtz-Cayla
     */
    public static void main(String[] args) {
        Ship[] myFleet = new Ship[3];
        myFleet[0] = new Ship("Calamity Jones", "1995");
        myFleet[1] = new CruiseShip("Yardarm Buffoon", "2015", 4650);
        myFleet[2] = new CargoShip("Shanghai Express", "1957", 800000);

        for (int i=0; i<myFleet.length; i++) {
            System.out.println(myFleet[i]);
        }
    }
}

/**
 * A Cruise Ship class, extending the generic Ship
 * <p>
 *  In addition to the basic attributes of a Ship, this adds a passenger capacity field
 * </p>
 *
 * {@link Ship}
 *
 * @author Jason Gurtz-Cayls
 */
class CruiseShip extends Ship {
    private int maxPass;

    /**
     * Constructer sets all three ship attributes
     *
     * @param nam The ship's name
     * @param yr The year the ship was built
     * @param mxp The maximum number of passengers supported
     *
     * @author Jason Gurtz-Cayla
     */
    public CruiseShip(String nam, String yr, int mxp) {
        super(nam, yr);
        setMaxPass(mxp);
    }

    /**
     * Overridden toString() returns the three attributes as a String 
     *
     * @return String containing the values of the Cruise ship's name, year of construction, and maximum passengers.
     *
     * @author Jason Gurtz-Cayla
     */
    @Override
    public String toString() {
        return String.format("%s, %s, %s", this.getName(), this.getYear(), this.getMaxPass() );
    }

    /**
     * MaxPassenger setter
     *
     * @param mxp The maximum number of passengers supported
     *
     * @author Jason Gurtz-Cayla
     */
    public void setMaxPass(int mxp) {
        this.maxPass = mxp;
    }

    /**
     * MaxPassenger getter
     *
     * @return The maximum number of passengers supported
     *
     * @author Jason Gurtz-Cayla
     */
    public int getMaxPass() {
        return this.maxPass;
    }
}

/**
 * A Cargo Ship class, extending the generic Ship
 * <p>
 *  In addition to the basic attributes of a Ship, this adds a cargo capacity field, in tonnes
 * </p>
 *
 * {@link Ship}
 *
 * @author Jason Gurtz-Cayls
 */
class CargoShip extends Ship {
    private int capacity;

    /**
     * Constructer sets all three ship attributes
     *
     * @param nam The ship's name
     * @param yr The year the ship was built
     * @param cap The maximum weight of cargo supported, in tonnes
     *
     * @author Jason Gurtz-Cayla
     */
    public CargoShip(String nam, String yr, int cap) {
        super(nam, yr);
        setCapacity(cap);
    }

    /**
     * Overridden toString() returns the three attributes as a String 
     *
     * @return String containing the values of the Cargo ship's name, year of construction, and cargo capacity
     *
     * @author Jason Gurtz-Cayla
     */
    @Override
    public String toString() {
        return String.format("%s, %s, %s", this.getName(), this.getYear(), this.getCapacity() );
    }

    /**
     * Capacity setter
     *
     * @param cap The maximum weight of cargo supported, in tonnes
     *
     * @author Jason Gurtz-Cayla
     */
    public void setCapacity(int cap) {
        this.capacity = cap;
    }

    /**
     * Capacity getter
     *
     * @return The maximum weight of cargo supported, in tonnes
     *
     * @author Jason Gurtz-Cayla
     */
    public int getCapacity() {
        return this.capacity;
    }
}
