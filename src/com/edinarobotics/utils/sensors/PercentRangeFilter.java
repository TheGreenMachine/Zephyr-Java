package com.edinarobotics.utils.sensors;

/**
 * This filter applies a given filter only to values that fall within a given
 * percentage-based segment of the range of recent values.
 * For example it can ignore all values that fall outside the top 20% of all
 * values applying a given filter only to those top 20% of readings.
 */
public class PercentRangeFilter implements FilterDouble{
    private double[] readings;
    private double minRangePercent;
    private double maxRangePercent;
    private FilterDouble finalFilter;
    private double lastAcceptedValue = 0;
    
    /**
     * Constructs a new PercentRangeFilter that includes values that fall above
     * {@code minRangePercent}% but below {@code maxRangePercent}%.
     * All percentages are computed from the top so {@code 0.1} is the top 10%
     * of the range. To use the lower 10%, enter {@code 0.9}.
     * @param numReadings the number of readings to include in computing
     * the range (all readings are included in this computation even those
     * that are outside the given range).
     * @param minRangePercent the top percentage of the range above which values
     * must fall to be included in further filtering. A value in the interval
     * [0,1].
     * @param maxRangePercent the top percentage of the range below which values
     * must fall to be included in further filtering. A value in the interval
     * [0,1].
     * @param finalFilter the {@link FilterDouble} that is applied to all values
     * that are within the given range.
     */
    public PercentRangeFilter(int numReadings, double minRangePercent, double maxRangePercent, FilterDouble finalFilter){
        this.readings = new double[numReadings];
        this.minRangePercent = minRangePercent;
        this.maxRangePercent = maxRangePercent;
        this.finalFilter = finalFilter;
    }
    
    /**
     * Constructs a new PercentRangeFilter that includes all values that fall
     * within the top {@code topPercent}% of the range.
     * @param numReadings the number of readings to include in computing the
     * range (all readings are included in this computation even those
     * that are outside the given range).
     * @param topPercent the top percentage of the range in which values must
     * fall in order to be included in further filtering. A value in the
     * interval [0,1].
     * @param finalFilter the {@link FilterDouble} that is applied to all values
     * that are within the given range.
     */
    public PercentRangeFilter(int numReadings, double topPercent, FilterDouble finalFilter){
        this(numReadings, topPercent, 1, finalFilter);
    }
    
    /**
     * Filters values, applying the {@link FilterDouble} given in the
     * constructor to the values that meet the range criteria specified in the
     * constructor.
     * @param latestValue the latest reading to be included in filtering.
     * @return the filtered value after including the given sensor reading.
     */
    public double filter(double latestValue){
        shiftArray();
        readings[0] = latestValue;
        double range = maxValueInArray() - minValueInArray();
        double upperLimit = range*(1-maxRangePercent);
        double lowerLimit = range*(1-minRangePercent);
        if(lowerLimit <= latestValue && latestValue <= upperLimit){
            lastAcceptedValue = finalFilter.filter(latestValue);
            return lastAcceptedValue;
        }
        return lastAcceptedValue;
    }
    
    /**
     * Returns the maximum value stored in the internal array of this class.
     * @return the maximum value in this class's internal array.
     */
    public double maxValueInArray(){
        double currentMax = Double.MIN_VALUE;
        for(int i=0; i<readings.length; i++){
            currentMax = Math.max(currentMax, readings[i]);
        }
        return currentMax;
    }
    
    /**
     * Returns the minimum value stored in the internal array of this class.
     * @return the minimum value stored in this class's internal array.
     */
    public double minValueInArray(){
        double currentMin = Double.MAX_VALUE;
        for(int i=0; i<readings.length; i++){
            currentMin = Math.min(currentMin, readings[i]);
        }
        return currentMin;
    }
    
    /**
     * Shifts each value of the internal array in this class over one index by
     * adding {@code 1} to the index of each current value and dropping
     * any values that overflow the array.
     * and sets the element at {@code [0]} to {@code 0}.
     */
    private void shiftArray(){
        double[] tempArray = new double[readings.length];
        for(int index = 1; index < tempArray.length; index++)
        {
           tempArray[index] = readings[index-1];
        }
        tempArray[0] = 0;
        
        readings = tempArray;
    }
}
