package simulation.objects;

import java.util.ArrayList;

public class State {
    private ArrayList<Direction> greenRoads;
    private ArrayList<Direction> redRoads;
    private double duration;

    public State(ArrayList<Direction> greenRoads, ArrayList<Direction> redRoads, double duration) {
        this.greenRoads = greenRoads;
        this.redRoads = redRoads;
        this.duration = duration;
    }

    public ArrayList<Direction> getGreenRoads() {
        return greenRoads;
    }

    public ArrayList<Direction> getRedRoads() {
        return redRoads;
    }

    public double getDuration() {return duration;}

}
