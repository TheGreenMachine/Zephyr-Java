package com.edinarobotics.utils.sensors;

/**
 * This filter implements a simple average of several values. It will average a
 * maximum number of values given in the constructor. If less than this maximum
 * number of values have been provided through the {@link #filter} method,
 * values not provided will be replaced be {@code Double.NaN} and will not
 * affect the average.
 */
public class SimpleAverageFilter implements FilterDouble{
    private int maxReadings;
    double[] readings;
    
    /**
     * Constructs a SimpleAverageFilter that will average the given number
     * of readings.
     * @param maxReadingsToAverage the maximum number of readings to average.
     */
    public SimpleAverageFilter(int maxReadingsToAverage){
        this.maxReadings =  maxReadingsToAverage;
        readings = new double[maxReadingsToAverage];
        for(int i=0;i < readings.length; i++){
            readings[i] = Double.NaN;
        }
    }
    
    /**
     * Returns the simple average of this reading and the previous readings.
     * The maximum number of readings to include in this average is given in
     * the constructor.
     * @param latestValue the latest value to filter.
     * @return the current average of the provided values.
     */
    public double filter(double latestValue){
        shiftArray();
        readings[0] = latestValue;
        return addRealValues()/countRealValues();
    }
    
    /**
     * Sums all values stored in this class that are not {@code Double.NaN}.
     * @return the sum of all real values stored in this class.
     */
    private double addRealValues(){
        double sum = 0;
        for(int i=0; i < readings.length; i++){
            if(!(new Double(readings[i]).isNaN())){
                sum += readings[i];
            }
        }
        return sum;
    }
    
    /**
     * Returns the number of values stored in this class that are not
     * {@code Double.NaN}.
     * @return the number of non-{@code Double.NaN} values.
     */
    private int countRealValues(){
        int count = 0;
        for(int i=0; i < readings.length; i++){
            if(!(new Double(readings[i]).isNaN())){
                count++;
            }
        }
        return count;
    }
    
    /**
     * Shifts each value of the internal array in this class over one index by
     * adding {@code 1} to the index of each current value and dropping
     * any values that overflow the array.
     * and sets the element at {@code [0]} to {@code Double.NaN}.
     */
    private void shiftArray(){
        double[] tempArray = new double[readings.length];
        for(int index = 1; index < tempArray.length; index++)
        {
           tempArray[index] = readings[index-1];
        }
        tempArray[0] = Double.NaN;
        
        readings = tempArray;
    }
}
