//package simulation;
//
//import simulation.objects.*;
//
//import java.awt.*;
//import java.util.ArrayList;
//
//public class IntersectionGenerator {
//
//    private final ObjectHandler handler;
//    private final int simWidth;
//    private final int simHeight;
//    private final int gridSize;
//    private final Point center;
//
//    public IntersectionGenerator(ObjectHandler handler) {
//        this.handler = handler;
//        simWidth = SimulationParameters.SIM_WIDTH;
//        simHeight = SimulationParameters.SIM_HEIGHT;
//        gridSize = SimulationParameters.GRID_SIZE;
//        center = new Point(simWidth / 2, simHeight / 2);
//    }
//
//    public IntersectionObject generateIntersection(int nentry, int nexit, int sentry, int sexit, int wentry, int wexit, int eentry, int eexit) {
//        // TODO: DELETE SETUP PARTS AND SUBSTITUTE INPUT PARAMETERS
//        int roadN_entry = nentry;
//        int roadN_exit  = nexit;
//        int roadS_entry = sentry;
//        int roadS_exit  = sexit;
//        int roadW_entry = wentry;
//        int roadW_exit  = wexit;
//        int roadE_entry = eentry;
//        int roadE_exit  = eexit;
//        // TODO: END OF SETUP
//
//        //Create Intersection
//        int maxLanesVertical = Math.max(roadN_entry + roadN_exit, roadS_entry + roadS_exit);
//        int maxLanesHorizontal = Math.max(roadW_entry + roadW_exit, roadE_entry + roadE_exit);
//        int intersectionWidth = maxLanesVertical * gridSize;
//        int intersectionHeight = maxLanesHorizontal * gridSize;
//        IntersectionObject intersection = new IntersectionObject(center.x - intersectionWidth / 2, center.y - intersectionHeight / 2,
//                intersectionWidth, intersectionHeight);
//        handler.addObject(intersection);
//
//
//        //Create Road Parameters
//        int xPos, yPos, length, duration = 5;   // TODO: FIX
//        //Create Road N
//        xPos = center.x - (int) (((double) (roadN_entry + roadN_exit) / 2) * gridSize);
//        yPos = 0;
//        length = (simHeight - intersectionHeight) / 2;
//        intersection.setNorthRoad(generateLanes(roadN_entry, roadN_exit, xPos, yPos, length, duration, Direction.N, intersection));
//        //Create Road S
//        xPos = center.x - (int) (((double) (roadS_entry + roadS_exit) / 2) * gridSize);
//        yPos = center.y + intersectionHeight / 2;
//        intersection.setSouthRoad(generateLanes(roadS_entry, roadS_exit, xPos, yPos, length, duration, Direction.S, intersection));
//        //Create Road W
//        xPos = 0;
//        yPos = center.y - (int) (((double) (roadW_entry + roadW_exit) / 2) * gridSize);
//        length = (simWidth - intersectionWidth) / 2;
//        intersection.setWestRoad(generateLanes(roadW_entry, roadW_exit, xPos, yPos, length, duration, Direction.W, intersection));
//        //Create Road E
//        xPos = center.x + intersectionWidth / 2;
//        yPos = center.y - (int) (((double) (roadE_entry + roadE_exit) / 2) * gridSize);
//        length = (simWidth - intersectionWidth) / 2;
//        intersection.setEastRoad(generateLanes(roadE_entry, roadE_exit, xPos, yPos, length, duration, Direction.E, intersection));
//
//        /*//Create simple singleloop tied to intersection;
//        LightObject lights = new LightObject(center.x, center.y, new LightsSingleLoop(5.2),intersection);
//        handler.addObject(lights);*/
//
//        //create stateloop tied to intersection
//        /*ArrayList<Direction> gr1 = new ArrayList<Direction>();
//        gr1.add(Direction.S);
//        gr1.add(Direction.N);
//        ArrayList<Direction> rr1 = new ArrayList<Direction>();
//        rr1.add(Direction.E);
//        rr1.add(Direction.W);
//        State state1 = new State(gr1,rr1,10.0);
//
//        ArrayList<Direction> gr2 = new ArrayList<Direction>();
//        gr2.add(Direction.S);
//        gr2.add(Direction.W);
//        ArrayList<Direction> rr2 = new ArrayList<Direction>();
//        rr2.add(Direction.E);
//        rr2.add(Direction.N);
//        State state2 = new State(gr2,rr2,3.0);
//
//        ArrayList<State> states = new ArrayList<State>();
//        states.add(state1);
//        states.add(state2);
//
//        LightsStateLoop loop = new LightsStateLoop(states);
//        LightObject lights = new LightObject(center.x, center.y, loop,intersection);
//        handler.addObject(lights);*/
//        return intersection;
//    }
//
//    private RoadObject generateLanes(int entryLanes, int exitLanes, int xPos, int yPos, int length, long duration,
//                                     Direction dir,
//                                     IntersectionObject intersection) {
//        ArrayList<LaneObject> roadLanes = new ArrayList<>();
//        if (dir == Direction.N || dir == Direction.E) { //Entry Lanes First
//            addEntryLanes(0, entryLanes, xPos, yPos, length, duration, dir, roadLanes, intersection);
//            addExitLanes(entryLanes, entryLanes + exitLanes, xPos, yPos, length, dir, roadLanes);
//        } else { //Exit Lanes First
//            addExitLanes(0, exitLanes, xPos, yPos, length, dir, roadLanes);
//            addEntryLanes(exitLanes, entryLanes + exitLanes, xPos, yPos, length, duration, dir, roadLanes, intersection);
//        }
//        RoadObject road = new RoadObject(xPos, yPos, gridSize * (entryLanes + exitLanes), length, dir, roadLanes);
//        handler.addObject(road);
//        return road;
//    }
//
//    private void addEntryLanes(int val, int numLanes, int xPos, int yPos, int length, long duration,
//                               Direction dir, ArrayList<LaneObject> roadLanes, IntersectionObject intersection) {
//        for (int i = val; i < numLanes; i++) {
//            ArrayList<Direction> allowed = getDirectionForLane(dir);
//            if (dir == Direction.N || dir == Direction.S) {
//                roadLanes.add(new EntryLaneObject(xPos + i * gridSize, yPos, length, dir, allowed, intersection));
//            } else {
//                roadLanes.add(new EntryLaneObject(xPos, yPos + i * gridSize, length, dir, allowed, intersection));
//            }
//        }
//    }
//
//    private void addExitLanes(int val, int numLanes, int xPos, int yPos, int length,
//                              Direction dir, ArrayList<LaneObject> roadLanes) {
//        for (int i = val; i < numLanes; i++) {
//            if (dir == Direction.N || dir == Direction.S) {
//                roadLanes.add(new ExitLaneObject(xPos + i * gridSize, yPos, length, dir));
//            } else {
//                roadLanes.add(new ExitLaneObject(xPos, yPos + i * gridSize, length, dir));
//            }
//        }
//    }
//
//    //TODO: DELETE THIS METHOD - IT IS JUST FOR TESTING PURPOSES
//    private ArrayList<Direction>  getDirectionForLane(Direction roadDir) {
//        ArrayList<Direction> directionArray = new ArrayList<>();
//        switch (roadDir) {
//            case N -> {
//                directionArray.add(Direction.S);
//                directionArray.add(Direction.E);
//                directionArray.add(Direction.W);
//            }
//            case S -> {
//                directionArray.add(Direction.N);
//                directionArray.add(Direction.W);
//                directionArray.add(Direction.E);
//            }
//            case E -> {
//                directionArray.add(Direction.W);
//                directionArray.add(Direction.N);
//                directionArray.add(Direction.S);
//            }
//            case W -> {
//                directionArray.add(Direction.E);
//                directionArray.add(Direction.N);
//                directionArray.add(Direction.S);
//            }
//        }
//
//        return directionArray;
//    }
//}
