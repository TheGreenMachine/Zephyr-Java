package com.edinarobotics.utils.visiontracking;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCamera.ResolutionT;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;

/**
 * A class with functions for targeting the shooter based on distance-to-target
 * and the target's particle report.
 */
public class TargetingStuff {
    
    /**
     * Finds distance-to-target based on the width of the target in pixels. This
     * method does not correct for sideways distortion, but our current judgment
     * was that it wouldn't matter much for a key shot, where the distance is
     * pretty much constant.
     * @param target The target you want to determine the distance to.
     * @param res Resolution of your robot's camera.
     * @param model Camera model used on your robot, either {@code M1011} or 
     * {@code 206}.
     * @return Returns distance-to-target.
     */
    public static double determineDTT(ParticleAnalysisReport target, AxisCamera.ResolutionT res, String model) {
        try {
            
            //Step 1: Determine theta
            final int twidthFt = 2; //constant
            final int twidthPx = target.boundingRectWidth; //changes based on distance.
            final int fovwidthPx; //determined by camera resolution
            final double fovwidthFt; //determined by calculation
            //it would be nice to do a switch here...
            if (res.equals(ResolutionT.k160x120)) fovwidthPx=120;
            else if (res.equals(ResolutionT.k320x240)) fovwidthPx=240;
            else if (res.equals(ResolutionT.k640x360)) fovwidthPx=360;
            else if (res.equals(ResolutionT.k640x480)) fovwidthPx=480;
            else throw new AxisCameraException("Resolution not recognized");

            //target width in pixels/target width in feet = FOV width in pixels/FOV width in feet`
            fovwidthFt = ((double)twidthPx/(double)twidthFt*(double)fovwidthPx)/(double)2;

            //Step 2: Use theta to determine distance to target

            final double theta;

            //Damn it.
    //            switch (model) {
    //                case "M1011": theta = 47/2; break;
    //                case "206": theta = 54/2; break;
    //                default: throw new AxisCameraException("Camera model not recognized"); break;
    //            }
            if (model.equals("M1011")) theta = 47/2;
            else if (model.equals("206")) theta = 54/2;
            else throw new AxisCameraException("Camera model not recognized");

            //tan(theta) = w/d
            final double distance=fovwidthFt/Math.tan(theta);
            return distance;

        } catch (AxisCameraException e) {
            e.printStackTrace();
        }
        return -1.0;
    }
    
    /**
     * Suggests a shooter speed based on distance to target.
     * @deprecated This method does not actually have the formula right now.
     * @param distance Distance to target.
     * @return Suggested shooter velocity in RPM.
     */
    public static double determinePower(double distance) {
        //TODO: find formula
        return -1.0;
    }
    
    /**
     * Tells you which way you need to turn the shooter to get it perfectly 
     * lined up. It has a 1% tolerance in either direction (it is assumed that 
     * the driver will never get it perfect). Hey guys, look! It even compensates
     * for distortion!
     * @param report The ParticleAnalysisReport of the target you are aiming for.
     */
    public static String lockOn(ParticleAnalysisReport report) {
        //1% tolerance in either direction
        //This does actually work, because center_mass_x is a pixel coordinate.
        if (report.center_mass_x_normalized<-0.05) return("Turn right");
        if (report.center_mass_x_normalized<-0.01) return("Turn a little bit right");
        if (report.center_mass_x_normalized>0.05) return("Turn left");
        if (report.center_mass_x_normalized>0.01) return("Turn a little bit left");
        if (report.center_mass_x_normalized>-0.01&&report.center_mass_x_normalized<0.01) return("TARGET LOCKED");
        else return "lock on unavailible";
    }
    
    /**
     * Finds the closest target to the center. It is assumed that you are aiming
     * for the target closest to the center.
     * @param reports The {@link ParticleAnalysisReport}s of the targets.
     * @return The {@link ParticleAnalysisReport} of the target closest to the 
     * center.
     */
    public static ParticleAnalysisReport findClosest(ParticleAnalysisReport[] reports) {
        ParticleAnalysisReport toBeTracked=null;
        double center_massClosest=Double.MAX_VALUE;
        for (int i=0;i<reports.length;i++) {
            if (reports[i].center_mass_x_normalized<center_massClosest) {
                center_massClosest=reports[i].center_mass_x_normalized;
                toBeTracked=reports[i];
            }
        }
        return toBeTracked;
    }
}
