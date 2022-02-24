package simulation.objects;

import java.awt.*;

public class LightObject extends SimulationObject {
    private LightsLoop lightsLoop;
    private IntersectionObject intersection;

    public LightObject(int xPos, int yPos, LightsLoop lightsLoop, IntersectionObject intersection) {
        super(xPos, yPos);
        this.lightsLoop = lightsLoop;
        this.intersection = intersection;
        this.lightsLoop.setIntersection(intersection);
    }


    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {


    }

}
