package simulation.objects;

import java.awt.*;
import java.util.ArrayList;

public class LaneStateLoop extends LaneLoop {

    private ArrayList<State> states;
    private int currentStateIndex;
    private double delta;
    private double currentStateDuration;

    public LaneStateLoop(LaneObject lane) {
        super(lane);
        this.currentStateIndex = 0;
        this.lastTime = System.currentTimeMillis();
        this.delta = 0;
        this.currentStateDuration = states.get(currentStateIndex).getDuration();
    }

    public LaneStateLoop(LaneObject lane, ArrayList<State> states) {
        super(lane);
        this.currentStateIndex = 0;
        this.lastTime = System.currentTimeMillis();
        this.delta = 0;
        this.currentStateDuration = states.get(currentStateIndex).getDuration();
        this.states = states;
    }

    public void setStates(ArrayList<State> states) {
        this.states = states;
    }

    @Override
    public void tick(long currentTime) {
        delta = currentTime - lastTime;
        double durationInMS = currentStateDuration * 1000;
        if (delta >= durationInMS) {
            enactState();
            setNextState();
            lastTime = currentTime;
        }
    }

    public void setNextState() {
        currentStateIndex ++;
        if (currentStateIndex >= states.size()) {
            currentStateIndex = 0;
        }
        currentStateDuration = states.get(currentStateIndex).getDuration();
    }
    public void enactState() {
        State currState = states.get(currentStateIndex);
        ArrayList<Direction> gr = currState.getGreenRoads();
        ArrayList<Direction> rr = currState.getRedRoads();
        for (Direction d : gr) {
            openLane(d);
        }
        for (Direction d : rr) {
            closeLane(d);
        }
    }
    private void closeLane(Direction d) {
        switch(d) {
            case N:
                this.laneObject.closeNorth();
                break;
            case E:
                this.laneObject.closeEast();
                break;
            case S:
                this.laneObject.closeSouth();
                break;
            case W:
                this.laneObject.closeWest();
                break;
        }
    }
    private void openLane(Direction d) {
        switch(d) {
            case N:
                this.laneObject.openNorth();
                break;
            case E:
                this.laneObject.openEast();
                break;
            case S:
                this.laneObject.openSouth();
                break;
            case W:
                this.laneObject.openWest();
                break;
        }
    }
}