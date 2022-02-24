package simulation.objects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class VehicleObject extends SimulationObject {

    private Image vehicleImage;
    private Direction startDir;
    private Direction endDir;
    private IntersectionObject intersection;

    public VehicleObject(int xPos, int yPos, Image vehicleImage, Direction startDir,
                         Direction endDir, IntersectionObject intersection) {
        super(xPos, yPos);
        this.vehicleImage = vehicleImage;
        this.startDir = startDir;
        this.endDir = endDir;
        this.intersection = intersection;
        //TODO: DELETE THIS IS JUST FOR TESTING
        switch (startDir) {
            case N:
                yVelocity = 1;
                break;
            case S:
                yVelocity = -1;
                break;
            case W:
                xVelocity = 1;
                break;
            case E:
                xVelocity = -1;
                break;
        }
        //TODO: END DELETE
    }

    @Override
    public void tick() {
        xPos += xVelocity;
        yPos += yVelocity;
    }

    @Override
    public void render(Graphics g) {
        checkLeftTurns();
        checkRightTurns();
        Image rotatedVehicle = rotate((BufferedImage) vehicleImage, getRotationAngle());
        g.drawImage(rotatedVehicle, xPos, yPos, null);
    }

    private double getRotationAngle() {
        if (xVelocity > 0) {
            return Math.PI / 2;
        } else if (xVelocity < 0) {
            return 3 * Math.PI / 2;
        } else if (yVelocity > 0) {
            return Math.PI;
        } else {
            return 0;
        }
    }

    private void checkLeftTurns() {
        switch (startDir) {
            case N:
                if (endDir == Direction.E && intersection.getEastRoad().isOpen()) {
                    if (xPos == 357 && yVelocity == 1 && yPos >= 407) {
                        yVelocity = 0;
                        xVelocity = 1;
                    }
                }
                break;
            case S:
                if (endDir == Direction.W && intersection.getWestRoad().isOpen()) {
                    if (xPos == 407 && yVelocity == -1 && yPos <= 357) {
                        yVelocity = 0;
                        xVelocity = -1;
                    }
                }
                break;
            case W:
                if (endDir == Direction.N && intersection.getNorthRoad().isOpen()) {
                    if (xPos >= 407 && xVelocity == 1 && yPos == 407) {
                        yVelocity = -1;
                        xVelocity = 0;
                    }
                }
                break;
            case E:
                if (endDir == Direction.S && intersection.getSouthRoad().isOpen()) {
                    if (xPos <= 357 && xVelocity == -1 && yPos == 357) {
                        yVelocity = 1;
                        xVelocity = 0;
                    }
                }
                break;
        }
    }

    private void checkRightTurns() {
        int northExitLanesSize = intersection.getNorthRoad().getExitLanes().size();
        int eastExitLanesSize = intersection.getEastRoad().getExitLanes().size();

        LaneObject northRightTurnLane = intersection.getNorthRoad().getEntryLanes().get(0);
        LaneObject southRightTurnLane = intersection.getSouthRoad().getEntryLanes().get(0);
        LaneObject eastRightTurnLane = intersection.getEastRoad().getEntryLanes().get(0);
        LaneObject westRightTurnLane = intersection.getWestRoad().getEntryLanes().get(0);

        LaneObject northExitLane = intersection.getNorthRoad().getExitLanes().get(northExitLanesSize-1);
        LaneObject southExitLane = intersection.getSouthRoad().getExitLanes().get(0);
        LaneObject eastExitLane = intersection.getEastRoad().getExitLanes().get(eastExitLanesSize-1);
        LaneObject westExitLane = intersection.getWestRoad().getExitLanes().get(0);

        switch (startDir) {
            case N: {
                if (endDir == Direction.W && intersection.getWestRoad().isOpen()) {
                    if (xPos == (northRightTurnLane.xPos + 7) && yVelocity == 1 && yPos >= (westExitLane.yPos + 7)) {
                        System.out.println(xPos + "vs" + (northRightTurnLane.xPos + 7));
                        yVelocity = 0;
                        xVelocity = -1;
                    }
                }
                break;
            }
            case S: {
                if (endDir == Direction.E && intersection.getEastRoad().isOpen()) {
                    if (xPos > southRightTurnLane.xPos && yVelocity == -1 && yPos <= (eastExitLane.yPos + 7)) {
                        yVelocity = 0;
                        xVelocity = 1;
                    }
                }
                break;
            }
            case W: {
                if (endDir == Direction.S && intersection.getSouthRoad().isOpen()) {
                    if (xPos >= (southExitLane.xPos + 7) && xVelocity == 1 && yPos >= westRightTurnLane.yPos) {
                        yVelocity = 1;
                        xVelocity = 0;
                    }
                }
                break;
            }
            case E: {
                if (endDir == Direction.N && intersection.getNorthRoad().isOpen()) {
                    if (xPos <= (northExitLane.xPos + 7) && xVelocity == -1 && yPos <= eastRightTurnLane.yPos) {
                        yVelocity = -1;
                        xVelocity = 0;
                    }
                }
                break;
            }
        }
    }

    private static BufferedImage rotate(BufferedImage image, double radAngle) {
        double sin = Math.abs(Math.sin(radAngle)), cos = Math.abs(Math.cos(radAngle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int)Math.floor(w*cos+h*sin), newh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(radAngle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }

    private static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }
}
