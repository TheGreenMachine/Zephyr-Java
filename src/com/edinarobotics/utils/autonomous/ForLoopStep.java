package com.edinarobotics.utils.autonomous;

import com.edinarobotics.utils.autonomous.conditions.CounterCondition;

/**
 * An {@link AutonomousStep} that can be used to run another
 * {@link AutonomousStep} a fixed number of times.
 */
public class ForLoopStep extends WhileLoopStep{
    private AutonomousStep repeatedStep;
    private int numRuns;
    
    /**
     * Constructs a ForLoopStep that will run {@code repeatedStep}
     * {@code numRuns} times.
     * @param repeatedStep The step to run multiple times.
     * @param numRuns The number of times to repeat {@code repeatedStep}.
     */
    public ForLoopStep(AutonomousStep repeatedStep, int numRuns){
        super(repeatedStep, new CounterCondition(numRuns));
    }
}
