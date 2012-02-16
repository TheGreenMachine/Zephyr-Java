package com.edinarobotics.utils.autonomous;

/**
 * An AutonomousStep that runs another AutonomousStep repeatedly while a
 * {@link BooleanCondition} evaluates {@code true}.
 */
public class WhileLoopStep extends AutonomousStep{
    private AutonomousStep repeatedStep;
    private LoopCondition loopCondition;
    
    /**
     * Constructs a WhileLoopsStep that runs {@code repeatedStep} while
     * {@code loopCondition.get()} evaluates to {@code true}.
     * @param repeatedStep The {@link AutonomousStep} to run repeatedly.
     * @param loopCondition The {@link BooleanCondition} to use as the loop
     * condition.
     */
    public WhileLoopStep(AutonomousStep repeatedStep, LoopCondition loopCondition){
        this.repeatedStep = repeatedStep;
        this.loopCondition = loopCondition;
    }
    
    /**
     * Initially starts {@code repeatedStep} when the loop is first used.
     */
    public void start(){
        repeatedStep.start();
    }
    
    /**
     * Runs the loop for a single operation and restarts {@code repeatedStep} if
     * it has finished.
     */
    public void run(){
        if(!repeatedStep.isFinished()){
            repeatedStep.run();
        }
        else if(!isFinished()){
            repeatedStep.stop();
            repeatedStep.start();
        }
    }
    
    /**
     * Stops repeated step and resets {@code loopCondition}.
     */
    public void stop(){
        repeatedStep.stop();
        loopCondition.reset();
    }
    
    /**
     * Returns the value of: "{@code !loopCondition.get()}".
     * @return {@code true} if the loop has finished, {@code false} otherwise.
     */
    public boolean isFinished(){
        return !loopCondition.get();
    }
}
