package com.edinarobotics.utils.visiontracking;

import edu.wpi.first.wpilibj.image.*;

/**
 * A class that identifies possible targets based on locating particles. All 
 * members and methods of this class are static because it would be silly to 
 * instantiate something like this.
 */
abstract public class ParticleVT {
    
    private static ColorImage initialImage;
    private static BinaryImage afterMask;
    private static BinaryImage afterHull;
    private static BinaryImage afterFilter;
    private static double[] rectangleScores;
    private static double[] aspectRatioScores;
    private static double[] edgeScoresX; //These are calculated as pixels in orig/pixels in mask
    private static double[] edgeScoresY;
    private static ParticleAnalysisReport[] reports;
    /**
     * The {@link ParticleAnalysisReport}s of the particles that qualify as 
     * targets.
     */
    public static ParticleAnalysisReport[] fullyQualified;
    //I may want to add a removeLargeObjects() step
    
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
    public static void applyMask() throws NIVisionException {
        afterMask=initialImage.thresholdHSL(136,182,45,256,116,256);
    }
    
    /**
     * Performs a convex hull operation on the mask to (hopefully) fill in the 
     * breaks in the rectangle.
     * @throws NIVisionException if something bad happens.
     */
    public static void convexHull() throws NIVisionException {
        afterHull=afterMask.convexHull(true); //C8 should be better because we are forming a box
        reports=afterHull.getOrderedParticleAnalysisReports();
    }
    
    /**
     * Gets rid of every particle except for the largest four, which should be 
     * the back boards after the convex hull has been applied. This method is 
     * not required to be called.
     * @throws NIVisionException if something bad happens.
     */
    public static void filter() throws NIVisionException {
        afterFilter = afterHull.removeSmallObjects(true, afterHull.getNumberParticles()-4);
        reports=afterFilter.getOrderedParticleAnalysisReports();
    }
    
    /**
     * Calculates the rectangle score of each of the particles. The rectangle
     * score is a measurement of how well the particle fits its bounding box, 
     * and is calculated as follows:
     * rectangleScore = particleArea / rectangleArea
     * @throws NIVisionException if something bad happens.
     */
    public static void calculateRectScores() throws NIVisionException {
        for (int i=0; i<reports.length; i++) {
            rectangleScores[i] = reports[i].particleArea/(reports[i].boundingRectHeight*reports[i].boundingRectWidth);
        }
    }
    
    /**
     * Calculates the aspect ratio score of each of the particles. The aspect 
     * ratio score is a measure of how close the height-to-width proportions of
     * a particle compare to the height-to-width proportions of the back board.
     * This ratio is normalized to a percent, with 24:18 = 100%.
     * @throws NIVisionException if something bad happens.
     */
    public static void calculateAspectRatioScores() throws NIVisionException {
        for (int i=0; i<reports.length; i++) {
            aspectRatioScores[i] = (reports[i].boundingRectWidth/reports[i].boundingRectHeight)/((double)24/18)*100; //Normalized to a percent
        }
    }
    
    /**
     * @deprecated 
     * This is some loopy $%@!# that I can't figure out how to write. The method
     * is supposed to calculate something called the edge score for the X axis. 
     * The edge score gives a measure (or actually, half of a measure) of how
     * hollow the particle is and how that matches up with the ideal hollowishness
     * of the backboard. It goes something like this:
     * <ul>
     * <li> For every column in the pre-convex hull image, the method counts up
     * how many pixels are on (Remember, it's a binary image).
     * <li> It then counts up how many pixels are turned on in the post-convex 
     * hull image.
     * <li> It divides the pre-convex number by the post-convex number to 
     * essentially produce a hollowness number for each column.
     * <li> It then compares these numbers to a set of low and high boundaries.
     * <li> Based on how often the determined hollowness values fall between the
     * boundaries, it gives the particle a score, normalized as a percent.
     * </ul>
     * @param lowBoundary The set of cutoff values for minimum hollowness, 
     * arranged by column from left to right. This really shouldn't be passed in
     * because the width of the backboard in pixels changes with distance.
     * @param highBoundary The set of cutoff values for maximum hollowness, 
     * arranged by column from left to right. This really shouldn't be passed in
     * because the width of the backboard in pixels changes with distance.
     * @throws NIVisionException if something bad happens.
     */
    public static void calculateEdgeScoresX(int[] lowBoundary, int[] highBoundary) throws NIVisionException {
        for (int i=0; i<reports.length; i++) {
            //What I need to do is for every column, determine how many pixels are on in the original,
            //divide that by how many pixels are on in the hull, and convert that to a percent. Twice.
//            
        }
    }
    
    /**
     * @deprecated 
     * This is a method that is supposed to calculate the edge score for the Y 
     * axis. It's the same process as above, but it calculates for rows instead
     * of columns.
     * @param lowBoundary The set of cutoff values for minimum hollowness, 
     * arranged by column from top to bottom.
     * @param highBoundary The set of cutoff values for maximum hollowness, 
     * arranged by column from top to bottom.
     * @throws NIVisionException if something bad happens.
     */
    public static void calculateEdgeScoresY(int[] lowBoundary, int[] highBoundary) throws NIVisionException {
        for (int i=0; i<reports.length; i++) {
            //What I need to do is for every column, determine how many pixels are on in the original,
            //divide that by how many pixels are on in the hull, and convert that to a percent. Twice.
        }
    }
    
    /**
     * Determines which particles qualify as targets based on preceding 
     * processes and standards.
     * @param reports The {@link ParticleAnalysisReport}s of candidate 
     * particles.
     * @param qualifications The values needed in each field to approve that
     * field as satisfactory. The values needing to be attained are inputted as
     * follows:
     * <ul>
     * <li> The rectangle score 
     * <li> The aspect ratio score
     * <li> The X axis edge score
     * <li> The Y axis edge score
     * </ul>
     * These scores are normalized to return results between 1 and 100, 
     * inclusive.
     */
    public static void qualify(ParticleAnalysisReport[] reports, double[] qualifications) {
        int fqPlace=0;
        for (int i=0;i<reports.length;i++){
            try{
            if (rectangleScores[i]>qualifications[1]&&aspectRatioScores[i]>qualifications[2]&&edgeScoresX[i]>qualifications[3]&&edgeScoresY[i]>qualifications[4])
                fullyQualified[fqPlace++]=reports[i];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("ParticleVT(165):"+e.getMessage());
            }
        }
    }
    
    //This should run through all the steps of the processing, and maybe do something with the results.
    /**
     * We may not need this method, I just made it to streamline the process. 
     * Beware, you need to hard-code the rectangle-judging standards.
     * This method contains dummy code for the edge scores. 
     * @param image The initial image to be processed.
     * @param useFilter Whether or not to filter out all particles except for 
     * the four largest (which should be the targets).
     * @return ParticleAnalysisReport[] The ParticleAnalysisReports on all the 
     * particles that qualify as targets.
     */
    public static ParticleAnalysisReport[] process(ColorImage image, boolean useFilter) {
        initialImage=image;
        try{
        applyMask();
            convexHull();
            if (useFilter) filter();
            calculateRectScores();
            calculateAspectRatioScores();
        } catch (NIVisionException e) {
            System.err.println("ParticleVT(190):"+e.getMessage());
        }
        double[] standards = {90,90,0,0}; //dummy code here
        qualify(reports,standards);
        try {
        initialImage.free();
        afterMask.free();
        afterHull.free();
        afterFilter.free();
        } catch (NIVisionException e) {
            System.err.println("ParticleVT(200):"+e.getMessage());
        }
        return fullyQualified;
    }
    
}