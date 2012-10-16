package com.edinarobotics.utils.visiontracking;

import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

abstract public class ParticleFilter {
    
    /**
     * Creates a binary mask to the image, according to the HSL color space. The
     * mask is created as follows:
     * <ul>
     * <li>Hue is greater than 136 and less than 182,
     * <li>Saturation is greater than 45, and
     * <li>Luminance is greater than 116.
     * </ul>
     * @throws NIVisionException if something bad happens.
     */
    private static BinaryImage applyMask(ColorImage image) throws NIVisionException {
        return image.thresholdHSL(136,182,45,256,116,256);
    }
    
    /**
     * Performs a convex hull operation on the mask to (hopefully) fill in the 
     * breaks in the rectangle.
     * @throws NIVisionException if something bad happens.
     */
    private static BinaryImage convexHull(BinaryImage image) throws NIVisionException {
        return image.convexHull(true); //C8 should be better because we are forming a box
    }
    
    /**
     * Removes particles until only 4 remain. If some of those 4 is smaller than 
     * 1/4 of the area of the next-smallest one, they are removed as well.
     * @param image The image to be filtered.
     * @return The 4 largest particles, or possibly less.
     * @throws NIVisionException if something bad happens.
     */
    private static ParticleAnalysisReport[] filter(BinaryImage image) throws NIVisionException {
        ParticleAnalysisReport[] unfilteredReports = image.getOrderedParticleAnalysisReports(4);
        ParticleAnalysisReport[] filteredReports= new ParticleAnalysisReport[4];
        int validTargets=0;
        for (int i=0;i<4;i++){
            if (unfilteredReports[i].particleArea>=4*unfilteredReports[i+1].particleArea) {
                validTargets++;
            }
        }
        System.arraycopy(unfilteredReports, 0, filteredReports, 0, validTargets);
        return filteredReports;
    }
    
    /**
     * Processes the image and returns particles big enough to be qualified as targets.
     * @param image The image to be processed.
     * @return Qualifying particles, probably targets.
     * @throws NIVisionException if something bad happens.
     */
    public static ParticleAnalysisReport[] process(ColorImage image) throws NIVisionException{
        return filter(convexHull(applyMask(image)));
    }
        
}
