package com.edinarobotics.utils.sensors;

import com.sun.squawk.util.MathUtils;

/**
 * takes a series of values and smooths them using FIR filtering
 * 
 */
public class FIRFilter implements FilterDouble{
    private double[] tapWeights;
    private double[] values;
    /**
     * constructs a FIR filter that has weights given by tapWeights[]
     * @param tapWeights is an array of the weights to be assigned to each tap
     */
    public FIRFilter(double[] tapWeights)
    {
        this.tapWeights = tapWeights;
        values = new double[this.tapWeights.length];
    }
    /**
     * Generates an FIRFilter with size taps as determined by the formula 2k/(n^2+n)
     * where n = size
     * @param size, the number of taps to be included in the filter
     * @return an FIRFilter object
     */
    public static FIRFilter autoWeightedFilter(int size)
    {
        double[] tempValues = new double[size];
        for(int x = 0; x<tempValues.length; x++)
        {
            tempValues[x] = (2*(size-x))/(MathUtils.pow(size, 2)+size);
        }
        return new FIRFilter(tempValues);
    }
    
    /**
     * Performs FIR smoothing on values[]
     * @param currentVal is the newest value
     * @return a double representing the FIR smoothed value
     */
    public double filter(double currentVal)
    {
        //Shift values over 1 index, then add currentVal to values
        shiftArray();
        values[0] = currentVal;
        //Filtering
        double FIRValue = 0.0;
        for(int index = 0; index<values.length&&index<tapWeights.length; index++)
        {
            FIRValue +=values[index]*tapWeights[index];
        }
        return FIRValue;
        
    }
    
    /**
     * Shifts the array down one
     */
    private void shiftArray()
    {
        double[] tempArray = new double[values.length];
        for(int index = 1; index < tempArray.length; index++)
        {
           tempArray[index] = values[index-1];
        }
        tempArray[0] = 0.0;
        
        values = tempArray;
    }
    
    
}
