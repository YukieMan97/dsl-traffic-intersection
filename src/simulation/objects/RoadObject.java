package simulation.objects;

import simulation.SimulationParameters;

import java.awt.*;
import java.util.ArrayList;

public class RoadObject extends SimulationObject {


    public ArrayList<LaneObject> getEntryLanes() {
        return entryLanes;
    }

    public ArrayList<LaneObject> getExitLanes() {
        return exitLanes;
    }

    private final ArrayList<LaneObject> entryLanes = new ArrayList<>();
    private final ArrayList<LaneObject> exitLanes = new ArrayList<>();

    private final int width;
    private final int length;
    private final Direction direction;
    private boolean isOpen;

    public RoadObject(int xPos, int yPos, int width, int length, Direction dir, ArrayList<LaneObject> lanes) {
        super(xPos, yPos);
        this.width = width;
        this.length = length;
        this.direction = dir;
        sortLanes(lanes);
        this.isOpen = true;
    }

    public void closeRoad() {
        this.isOpen = false;
    }

    public void openRoad() {
        this.isOpen = true;
    }

    public boolean isOpen() {
        return isOpen;
    }

    private void sortLanes(ArrayList<LaneObject> lanes) {
        for(LaneObject lane : lanes) {
            if (lane instanceof EntryLaneObject) {
                entryLanes.add(lane);
            } else if (lane instanceof ExitLaneObject) {
                exitLanes.add(lane);
            } else {
                System.out.println("ERROR: Incompatible Lane Type Found - " + lane.getClass());
            }
        }
    }

    @Override
    public void tick() {
        for (LaneObject lane : entryLanes) {
            lane.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        //Render Lanes
        for (LaneObject lane : entryLanes) {
            lane.render(g);
        }
        for (LaneObject lane : exitLanes) {
            lane.render(g);
        }
        //Render Outer Separators
        g.setColor(Color.yellow);
        if (direction == Direction.N || direction == Direction.S) {
            g.fillRect(xPos, yPos, 5, length);
            g.fillRect(xPos + width - 5, yPos, 5, length);
            g.setColor(Color.white);
            if (direction == Direction.N) {
                g.fillRect(xPos + SimulationParameters.GRID_SIZE * entryLanes.size() - 3, yPos, 6, length);
            } else {
                g.fillRect(xPos + SimulationParameters.GRID_SIZE * exitLanes.size() - 3, yPos, 6, length);
            }
        } else {
            g.fillRect(xPos, yPos, length, 5);
            g.fillRect(xPos, yPos + width - 5, length, 5);
            g.setColor(Color.white);
            if (direction == Direction.W) {
                g.fillRect(xPos, yPos + SimulationParameters.GRID_SIZE * exitLanes.size() - 3, length, 6);
            } else {
                g.fillRect(xPos, yPos + SimulationParameters.GRID_SIZE * entryLanes.size() - 3, length, 6);
            }
        }
        //Render Inner Separator

    }
}
