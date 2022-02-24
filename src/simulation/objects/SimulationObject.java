package simulation.objects;

import java.awt.*;

public abstract class SimulationObject {

    protected int xPos, yPos;
    protected int xVelocity, yVelocity;

    public SimulationObject(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public int getXVelocity() {
        return xVelocity;
    }

    public void setXVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getYVelocity() {
        return yVelocity;
    }

    public void setYVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }
}
