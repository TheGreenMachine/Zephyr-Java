package com.edinarobotics.utils.sensors;

/**
 * This interface defines some class that can be used to filter a series
 * of double values.
 */
public interface FilterDouble {
    
    /**
     * Filters the given {@code double} value as defined by the implementing
     * class.
     * @param value the value to filter
     * @return the filtered value as defined by the implementing class.
     */
    public double filter(double value);
}
