package simulation;

import Main.Parsing.*;
import simulation.objects.*;

import java.awt.*;
import java.util.ArrayList;

public class IntersectionEvaluator {
    private int roadN_entry = 0;
    private int roadN_exit  = 0;
    private int roadS_entry = 0;
    private int roadS_exit  = 0;
    private int roadW_entry = 0;
    private int roadW_exit  = 0;
    private int roadE_entry = 0;
    private int roadE_exit  = 0;

    private final ObjectHandler handler;
    private final int simWidth;
    private final int simHeight;
    private final int gridSize;
    private final Point center;

    public IntersectionEvaluator(ObjectHandler handler) {
        this.handler = handler;
        simWidth = SimulationParameters.SIM_WIDTH;
        simHeight = SimulationParameters.SIM_HEIGHT;
        gridSize = SimulationParameters.GRID_SIZE;
        center = new Point(simWidth / 2, simHeight / 2);
    }

    public IntersectionObject evaluateIntersection(ASTIntersection intersection) {
        ArrayList<ASTRoad> roads = intersection.getRoadList().getRoads();
        for (ASTRoad r : roads) {
            String dir = r.getDirection().getCardinal();
            ArrayList<ASTLane> lanes = r.getLanes();
            for (ASTLane l : lanes) {
                if (l instanceof ASTEntryLane) {
                    countEntryLane(dir);
                } else {
                    countExitLane(dir);
                }
            }
        }
        IntersectionObject finalIntersection =  generateIntersection(roadN_entry,roadN_exit,roadS_entry,
                roadS_exit,roadW_entry,roadW_exit,roadE_entry,roadE_exit, intersection);
        return finalIntersection;


    }

    public void countEntryLane(String dir) {
        switch(dir) {
            case "N":
                roadN_entry ++;
                break;
            case "E":
                roadE_entry++;
                break;
            case "S":
                roadS_entry++;
                break;
            case "W":
                roadW_entry++;
                break;
        }
    }

    public void countExitLane(String dir) {
        switch(dir) {
            case "N":
                roadN_exit ++;
                break;
            case "E":
                roadE_exit++;
                break;
            case "S":
                roadS_exit++;
                break;
            case "W":
                roadW_exit++;
                break;
        }
    }

    public IntersectionObject generateIntersection(int roadN_entry, int roadN_exit, int roadS_entry, int roadS_exit, int roadW_entry, int roadW_exit, int roadE_entry, int roadE_exit,
                                                   ASTIntersection ast) {

        //Create Intersection
        int maxLanesVertical = Math.max(roadN_entry + roadN_exit, roadS_entry + roadS_exit);
        int maxLanesHorizontal = Math.max(roadW_entry + roadW_exit, roadE_entry + roadE_exit);
        int intersectionWidth = maxLanesVertical * gridSize;
        int intersectionHeight = maxLanesHorizontal * gridSize;
        IntersectionObject intersection = new IntersectionObject(center.x - intersectionWidth / 2, center.y - intersectionHeight / 2,
                intersectionWidth, intersectionHeight);
        handler.addObject(intersection);
        //sort Roads into NSWE order, neccessary
        ArrayList<ASTRoad> sortedRoads = sortRoads(ast.getRoadList().getRoads());

        //Create Road Parameters
        int xPos, yPos, length, duration = 5;
        //Create Road N
        xPos = center.x - (int) (((double) (roadN_entry + roadN_exit) / 2) * gridSize);
        yPos = 0;
        length = (simHeight - intersectionHeight) / 2;
        intersection.setNorthRoad(generateLanes(roadN_entry, roadN_exit, xPos, yPos, length, Direction.N,sortedRoads.get(0), intersection, duration));
        //Create Road S
        xPos = center.x - (int) (((double) (roadS_entry + roadS_exit) / 2) * gridSize);
        yPos = center.y + intersectionHeight / 2;
        intersection.setSouthRoad(generateLanes(roadS_entry, roadS_exit, xPos, yPos, length, Direction.S,sortedRoads.get(1), intersection, duration));
        //Create Road W
        xPos = 0;
        yPos = center.y - (int) (((double) (roadW_entry + roadW_exit) / 2) * gridSize);
        length = (simWidth - intersectionWidth) / 2;
        intersection.setWestRoad(generateLanes(roadW_entry, roadW_exit, xPos, yPos, length, Direction.W,sortedRoads.get(2), intersection, duration));
        //Create Road E
        xPos = center.x + intersectionWidth / 2;
        yPos = center.y - (int) (((double) (roadE_entry + roadE_exit) / 2) * gridSize);
        length = (simWidth - intersectionWidth) / 2;
        intersection.setEastRoad(generateLanes(roadE_entry, roadE_exit, xPos, yPos, length, Direction.E,sortedRoads.get(3), intersection, duration));
        intersection.setLights(evaluateLights(ast.getLight(), intersection));
        return intersection;
    }

    private LightObject evaluateLights(ASTLight light, IntersectionObject intersection) {
        LightsLoop loop = evaluateLightsLoop(light.getLoop());
        LightObject lights = new LightObject(center.x, center.y, loop, intersection);
        handler.addObject(lights);
        return lights;
    }

    private LightsLoop evaluateLightsLoop(ASTLoop astLoop) {
        if (astLoop instanceof ASTSingleLoop) {
            int whole = ((ASTSingleLoop) astLoop).getDuration().getDecimal().getBeforeDecimal().getValue();
            int frac = ((ASTSingleLoop) astLoop).getDuration().getDecimal().getBeforeDecimal().getValue();
            double dur = Double.parseDouble(whole + "." + frac);
            LightsSingleLoop loop = new LightsSingleLoop(dur);
            return loop;
        } else {
            ArrayList<State> states = evaluateStates(((ASTStateLoop) astLoop).getStates());
            LightsStateLoop loop = new LightsStateLoop(states);
            return loop;
        }
    }

    //returns roads sorted into NSWE order
    private ArrayList<ASTRoad> sortRoads(ArrayList<ASTRoad> roads) {
        ArrayList<ASTRoad> result = new ArrayList<ASTRoad>();
        for (ASTRoad r : roads) {
            if (r.getDirection().getCardinal().equals("N")) {
                result.add(r);
            }
        }
        for (ASTRoad r : roads) {
            if (r.getDirection().getCardinal().equals("S")) {
                result.add(r);
            }
        }
        for (ASTRoad r : roads) {
            if (r.getDirection().getCardinal().equals("W")) {
                result.add(r);
            }
        }
        for (ASTRoad r : roads) {
            if (r.getDirection().getCardinal().equals("E")) {
                result.add(r);
            }
        }
        return result;

    }

    private RoadObject generateLanes(int entryLanes, int exitLanes, int xPos, int yPos, int length, Direction dir, ASTRoad astRoad, IntersectionObject intersection, long duration) {
        ArrayList<LaneObject> roadLanes = new ArrayList<>();
        ArrayList<ASTLoop> astloops = getLoops(astRoad);
        if (dir == Direction.N || dir == Direction.E) { //Entry Lanes First
            addEntryLanes(0, entryLanes, xPos, yPos, length, dir, roadLanes, astloops, intersection, duration);
            addExitLanes(entryLanes, entryLanes + exitLanes, xPos, yPos, length, dir, roadLanes);
        } else { //Exit Lanes First
            addExitLanes(0, exitLanes, xPos, yPos, length, dir, roadLanes);
            addEntryLanes(exitLanes, entryLanes + exitLanes, xPos, yPos, length, dir, roadLanes, astloops, intersection, duration);
        }
        RoadObject road = new RoadObject(xPos, yPos, gridSize * (entryLanes + exitLanes), length, dir, roadLanes);
        handler.addObject(road);
        return road;
    }

    private ArrayList<ASTLoop> getLoops(ASTRoad astRoad) {
        ArrayList<ASTLane> entryLanes = astRoad.getLanes();
        ArrayList<ASTLoop> result = new ArrayList<ASTLoop>();
        for (ASTLane l : entryLanes) {
            if (l instanceof ASTEntryLane) {
                result.add(((ASTEntryLane) l).getLoop());
            }
        }
        return result;
    }

    private void addEntryLanes(int val, int numLanes, int xPos, int yPos, int length,
                               Direction dir, ArrayList<LaneObject> roadLanes, ArrayList<ASTLoop> astLoops, IntersectionObject intersection, long duration) {
        for (int i = val; i < numLanes; i++) {
            ArrayList<Direction> allowed = getDirectionForLane(dir);
            if (dir == Direction.N || dir == Direction.S) {
                LaneObject lane = new EntryLaneObject(xPos + i * gridSize, yPos, length, dir, allowed, intersection, duration);
                roadLanes.add(lane);
                if (astLoops.size() < i) {
                    LaneLoop loopForLane = evaluateLaneLoop(astLoops.get(i),lane);
                    handler.addObject(loopForLane);
                }
            } else {
                roadLanes.add(new EntryLaneObject(xPos, yPos + i * gridSize, length, dir, allowed, intersection, duration));
            }
        }
    }

    private LaneLoop evaluateLaneLoop(ASTLoop astLoop, LaneObject lane) {
        if (astLoop instanceof ASTSingleLoop) {
            int whole = ((ASTSingleLoop) astLoop).getDuration().getDecimal().getBeforeDecimal().getValue();
            int frac = ((ASTSingleLoop) astLoop).getDuration().getDecimal().getBeforeDecimal().getValue();
            double dur = Double.parseDouble(whole + "." + frac);
            LaneSingleLoop loop = new LaneSingleLoop(lane, dur);
            return loop;
        } else {
            ArrayList<State> states = evaluateStates(((ASTStateLoop) astLoop).getStates());
            LaneStateLoop loop = new LaneStateLoop(lane, states);
            return loop;
        }
    }

    private ArrayList<State> evaluateStates(ArrayList<ASTState> astStates) {
        ArrayList<State> result = new ArrayList<State>();
        for (ASTState state: astStates) {
            int whole = state.getDuration().getDecimal().getBeforeDecimal().getValue();
            int frac = state.getDuration().getDecimal().getAfterDecimal().getValue();
            double dur = Double.parseDouble(whole + "." + frac);
            ArrayList<Direction> gr = getDirections(state.getStateConfig().getGreenRoads().getDirectionList().getDirections());
            ArrayList<Direction> rr = getDirections(state.getStateConfig().getRedRoads().getDirectionList().getDirections());
            result.add(new State(gr,rr,dur));
        }
        return result;
    }

    private ArrayList<Direction> getDirections(ArrayList<ASTDirection> astDirections) {
        ArrayList<Direction> result = new ArrayList<Direction>();
        for (ASTDirection d: astDirections) {
            switch(d.getCardinal()) {
                case "N":
                    result.add(Direction.N);
                    break;
                case "E":
                    result.add(Direction.E);
                    break;
                case "S":
                    result.add(Direction.S);
                    break;
                case "W":
                    result.add(Direction.W);
                    break;
            }
        }
        return result;
    }

    private void addExitLanes(int val, int numLanes, int xPos, int yPos, int length,
                              Direction dir, ArrayList<LaneObject> roadLanes) {
        for (int i = val; i < numLanes; i++) {
            if (dir == Direction.N || dir == Direction.S) {
                roadLanes.add(new ExitLaneObject(xPos + i * gridSize, yPos, length, dir));
            } else {
                roadLanes.add(new ExitLaneObject(xPos, yPos + i * gridSize, length, dir));
            }
        }
    }

    private ArrayList<Direction>  getDirectionForLane(Direction roadDir) {
        ArrayList<Direction> directionArray = new ArrayList<>();
        switch (roadDir) {
            case N -> {
                directionArray.add(Direction.E);
                directionArray.add(Direction.W);
                directionArray.add(Direction.S);
            }
            case S -> {
                directionArray.add(Direction.N);
                directionArray.add(Direction.E);
                directionArray.add(Direction.W);
            }
            case E -> {
                directionArray.add(Direction.N);
                directionArray.add(Direction.W);
                directionArray.add(Direction.S);
            }
            case W -> {
                directionArray.add(Direction.E);
                directionArray.add(Direction.N);
                directionArray.add(Direction.S);
            }
        }
        return directionArray;
    }
}
