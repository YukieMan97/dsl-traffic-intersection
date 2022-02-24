package simulation.objects;

import simulation.SimulationParameters;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public abstract class LaneObject extends SimulationObject {

    private final int gridSize;
    private final int length;


    private boolean northOpen;
    private boolean eastOpen;
    private boolean southOpen;
    private boolean westOpen;

    protected final Direction roadDirection;
    protected RoadObject parentRoad;

    private Image laneImage;

    public LaneObject(int xPos, int yPos, int length, Direction roadDir) {
        super(xPos, yPos);
        this.gridSize = SimulationParameters.GRID_SIZE;
        this.length = length;
        this.roadDirection = roadDir;
        getLaneImage();
    }

    public LaneObject(int xPos, int yPos, int length, Direction roadDir, RoadObject parent) {
        super(xPos, yPos);
        this.gridSize = SimulationParameters.GRID_SIZE;
        this.length = length;
        this.roadDirection = roadDir;
        this.parentRoad = parent;
        this.northOpen = true;
        this.eastOpen = true;
        this.westOpen = true;
        this.southOpen = true;
    }

    private void getLaneImage() {
        try {
            if (roadDirection == Direction.N || roadDirection == Direction.S) {
                laneImage = ImageIO.read(new File("images/LaneVertical.png"));
            } else {
                laneImage = ImageIO.read(new File("images/LaneHorizontal.png"));
            }
        } catch (Exception e) {
            laneImage = null;
        }
    }

    public abstract void tick();

    public void openNorth() {
        northOpen = true;
    }

    public void closeNorth() {
        northOpen = false;
    }

    public boolean isNorthOpen() {
        return northOpen;
    }

    public void openEast() {
        eastOpen = true;
    }

    public void closeEast() {
        eastOpen = false;
    }

    public boolean isEastOpen() {
        return eastOpen;
    }

    public void openSouth() {
        southOpen = true;
    }

    public void closeSouth() {
        southOpen = false;
    }

    public boolean isSouthOpen() {
        return southOpen;
    }

    public void openWest() {
        westOpen = true;
    }

    public void closeWest() {
        westOpen = false;
    }

    public boolean isWestOpen() {
        return westOpen;
    }

    public RoadObject getParentRoad() {
        return this.parentRoad;
    }

    public void openLane() {
        northOpen = true;
        eastOpen = true;
        westOpen = true;
        southOpen = true;
    }

    public void closeLane() {
        northOpen = false;
        eastOpen = false;
        westOpen = false;
        southOpen = false;
    }

    public ArrayList<Direction> getOpenDirections() {
        ArrayList<Direction>results = new ArrayList<Direction>();
        if (northOpen) {
            results.add(Direction.N);
        }
        if (eastOpen) {
            results.add(Direction.E);
        }
        if (southOpen) {
            results.add(Direction.S);
        }
        if (westOpen) {
            results.add(Direction.W);
        }
        return results;
    }


    @Override
    public void render(Graphics g) {
        if (laneImage == null) {
            g.setColor(Color.darkGray);
            g.fillRect(xPos, yPos, gridSize, length);
        } else {
            int tileXPos = xPos;
            int tileYPos = yPos;
            for(int i = 0; i < (length / gridSize); i++) {
                if (roadDirection == Direction.N || roadDirection == Direction.S) {
                    tileYPos = yPos + i * gridSize;
                } else {
                    tileXPos = xPos + i * gridSize;
                }
                g.drawImage(laneImage, tileXPos, tileYPos, null);
            }
        }
    }

}
