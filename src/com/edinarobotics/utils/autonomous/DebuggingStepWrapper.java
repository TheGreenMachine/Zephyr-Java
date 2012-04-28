package com.edinarobotics.utils.autonomous;

/**
 *
 */
public class DebuggingStepWrapper extends AutonomousStep{
    private AutonomousStep wrappedStep;
    private String name;
    
    public DebuggingStepWrapper(AutonomousStep wrappedStep, String name){
        this.wrappedStep = wrappedStep;
        this.name = name;
    }
    
    public DebuggingStepWrapper(AutonomousStep wrappedStep){
        this(wrappedStep, "default");
    }
    
    public void start(){
        printMessage("Called start");
        wrappedStep.start();
    }
    
    public void run(){
        printMessage("Called run");
        wrappedStep.run();
    }
    
    public void stop(){
        printMessage("Called stop");
        wrappedStep.stop();
    }
    
    public boolean isFinished(){
        boolean finished = wrappedStep.isFinished();
        printMessage("Called isFinished - "+finished);
        return finished;
    }
    
    private void printMessage(String message){
        System.out.println(name+": "+message);
    }
}
