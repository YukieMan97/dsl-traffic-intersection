package simulation.objects;

import simulation.ObjectHandler;
import simulation.SimulationParameters;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class EntryLaneObject extends LaneObject {

    private final int spawnOffset = 7;

    private final long duration;
    private long startTime;

    private final ArrayList<Direction> allowedDirections;
    private final ArrayList<Image> vehicleImages = new ArrayList<>();
    private final Random randomGenerator = new Random();
    private IntersectionObject intersection;

    public EntryLaneObject(int xPos, int yPos, int length, Direction roadDir,
                           ArrayList<Direction> allowedPaths, IntersectionObject intersection, long duration) {
        super(xPos, yPos, length, roadDir);
        this.allowedDirections = allowedPaths;
        this.intersection = intersection;
        this.duration = duration;
        findVehicleImages();
    }

    private void findVehicleImages() {
        try {
            for (int  i = 0; i < 4; i++) {
                Image vehicleImage = ImageIO.read(new File("images/Car" + i + ".png"));
                vehicleImages.add(vehicleImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick() {
        if (startTime == 0) {
            startTime = System.nanoTime();
        }
        long currentTime = System.nanoTime() - startTime;
        if (currentTime > TimeUnit.SECONDS.toNanos(duration)) {
            SpawnVehicle();
            startTime = System.nanoTime();
        }
    }

    private void SpawnVehicle() {
        System.out.println("Spawning Vehicle");
        if (vehicleImages.size() >= 0) {
            int xPosVehicle = GetVehicleXPosition(roadDirection);
            int yPosVehicle = GetVehicleYPosition(roadDirection);
            Image vehicleImage = vehicleImages.get(randomGenerator.nextInt(vehicleImages.size()));
            Direction targetDirection = allowedDirections.get(randomGenerator.nextInt(allowedDirections.size()));
            VehicleObject vehicle = new VehicleObject(xPosVehicle, yPosVehicle, vehicleImage, roadDirection, targetDirection, intersection);
            ObjectHandler.globalHandler.addObject(vehicle);
        }
    }

    private int GetVehicleXPosition(Direction roadDirection) {
        return switch (roadDirection) {
            case N, S -> xPos + spawnOffset;
            case W -> 0;
            case E -> SimulationParameters.SIM_WIDTH;
        };
    }
    private int GetVehicleYPosition(Direction roadDirection) {
        return switch (roadDirection) {
            case N -> 0;
            case S -> SimulationParameters.SIM_HEIGHT;
            case W, E -> yPos + spawnOffset;
        };
    }
}
