/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.utils.sensors;

/**
 *
 * @author Danny
 */
public class AveragingFilter {
    static double[] recVal = new double[100];
	
	public static double filter(double recent)
	{
		double[] tempArray = new double[recVal.length];
        for(int index = 1; index < tempArray.length; index++)
        {
           tempArray[index] = recVal[index-1];
        }
        tempArray[0] = recent;
        
        recVal = tempArray;
        double total = 0;
        for(int x = 0; x<tempArray.length; x++)
        {
        	total+=tempArray[x];
        }
        return total/=recVal.length;
	}

	
}
