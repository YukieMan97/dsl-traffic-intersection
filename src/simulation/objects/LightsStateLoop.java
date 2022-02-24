package simulation.objects;

import java.util.ArrayList;

public class LightsStateLoop extends LightsLoop {

    private ArrayList<State> states;
    private int currentStateIndex;
    private double delta;
    private double currentStateDuration;

    public LightsStateLoop() {
        this.currentStateIndex = 0;
        this.lastTime = System.currentTimeMillis();
        this.delta = 0;
        this.currentStateDuration = states.get(currentStateIndex).getDuration();
    }

    public LightsStateLoop(ArrayList<State> states) {
        this.states = states;
        this.currentStateIndex = 0;
        this.lastTime = System.currentTimeMillis();
        this.delta = 0;
        this.currentStateDuration = states.get(currentStateIndex).getDuration();
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
         //   System.out.println("Changed state");
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
            openRoad(d);
        }
        for (Direction d : rr) {
            closeRoad(d);
        }
    }

    private void closeRoad(Direction d) {
        switch(d) {
            case N:
                this.intersection.getNorthRoad().closeRoad();
                break;
            case E:
                this.intersection.getEastRoad().closeRoad();
                break;
            case S:
                this.intersection.getSouthRoad().closeRoad();
                break;
            case W:
                this.intersection.getWestRoad().closeRoad();
                break;
        }
    }

    private void openRoad(Direction d) {
        switch(d) {
            case N:
                this.intersection.getNorthRoad().openRoad();
                break;
            case E:
                this.intersection.getEastRoad().openRoad();
                break;
            case S:
                this.intersection.getSouthRoad().openRoad();
                break;
            case W:
                this.intersection.getWestRoad().openRoad();
                break;
        }
    }

}
