package com.edinarobotics.utils.autonomous;

public abstract class SwitchStep extends AutonomousStep {
    private AutonomousStep[] steps;
    private AutonomousStep selectedStep;
    
    /**
     * Computes the index for the step in the {@code steps} array.
     * Called once during start.
     * If the index is out of bounds, {@link start()} will throw an {@code OutOfBoundsException}.
     * @return the index of our step in the {@code steps} array.
     */
    protected abstract int getIndex();
    
    public SwitchStep(AutonomousStep[] steps) {
        this.steps = steps;
    }
    
    /**
     * Calls {@code getIndex()} and sets the selected step to the step at that index.
     * Calls the selected step's start method.
     */
    public void start() {
        int index = getIndex();
        selectedStep = steps[index];
        selectedStep.start();
    }
    
    /**
     * Calls the selected step's run method.
     */
    public void run() {
        selectedStep.run();
    }
    
    /**
     * Calls the selected step's stop method.
     */
    public void stop() {
        selectedStep.stop();
    }
    
    /**
     * @return the result of the selected step's {@code isFinished()} method.
     */
    public boolean isFinished() {
        return selectedStep.isFinished();
    }
}
