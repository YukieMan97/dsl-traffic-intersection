package simulation.objects;

import java.awt.*;

public class IntersectionObject extends SimulationObject {

    private final int width;
    private final int height;

    private RoadObject northRoad;
    private RoadObject eastRoad;
    private RoadObject southRoad;
    private RoadObject westRoad;
    private LightObject lights;

    public IntersectionObject(int xPos, int yPos, int width, int height) {
        super(xPos, yPos);
        this.width = width;
        this.height = height;
    }

    public IntersectionObject(int xPos, int yPos, int width, int height,
                              RoadObject north, RoadObject east, RoadObject south, RoadObject west) {
        super(xPos, yPos);
        this.width = width;
        this.height = height;
        this.northRoad = north;
        this.eastRoad = east;
        this.westRoad = west;
        this.southRoad = south;
    }

    public void setLights(LightObject lights) {
        this.lights = lights;
    }

    public RoadObject getNorthRoad() {
        return this.northRoad;
    }

    public RoadObject getEastRoad() {
        return this.eastRoad;
    }

    public RoadObject getSouthRoad() {
        return this.southRoad;
    }

    public RoadObject getWestRoad() {
        return this.westRoad;
    }

    public void setNorthRoad(RoadObject road) {
         this.northRoad = road;
    }

    public void setEastRoad(RoadObject road) {
        this.eastRoad = road;
    }

    public void setSouthRoad(RoadObject road) {
        this.southRoad = road;
    }

    public void setWestRoad(RoadObject road) {
        this.westRoad = road;
    }

    @Override
    public void tick() {
        //Does Nothing
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(xPos, yPos, width, height);
        g.setColor(Color.darkGray);
        g.fillRect(xPos + 5, yPos + 5, width - 10, height - 10);
    }
}
