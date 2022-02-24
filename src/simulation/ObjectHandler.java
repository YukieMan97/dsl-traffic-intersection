package simulation;

import simulation.objects.LoopObject;
import simulation.objects.SimulationObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class ObjectHandler {

    public static ObjectHandler globalHandler;

    private final LinkedList<SimulationObject> simulationObjects;
    private final LinkedList<SimulationObject> objectsToAdd;
    private final LinkedList<SimulationObject> objectsToRemove;
    private ArrayList<LoopObject> loops;


    public ObjectHandler() {
        simulationObjects = new LinkedList<>();
        objectsToAdd = new LinkedList<>();
        objectsToRemove = new LinkedList<>();
        globalHandler = this;
        loops = new ArrayList<LoopObject>();
    }

    public void tick() {
        for(SimulationObject object : simulationObjects) {
            object.tick();
        }
        long currentTime = System.currentTimeMillis();
        for(LoopObject loop : loops ) {
            loop.tick(currentTime);
        }
        simulationObjects.addAll(objectsToAdd);
        objectsToAdd.clear();
        simulationObjects.removeAll(objectsToRemove);
        objectsToRemove.clear();
    }

    public void render(Graphics g) {
        for(SimulationObject object : simulationObjects) {
            object.render(g);
        }
    }

    public void addObject(SimulationObject object) {
        objectsToAdd.add(object);
    }

    public void addObject(LoopObject loop) {
        this.loops.add(loop);
    }


    public void removeObject(SimulationObject object) {
        objectsToRemove.add(object);
    }
}
