package Main.Validation;

import Main.Parsing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DSLValidator {

    private static final String ENTRY_LANE = "Entry Lane";
    private static final String EXIT_LANE = "Exit Lane";

    private ASTIntersection intersection;

    public DSLValidator(ASTIntersection intersection) {
        this.intersection = intersection;
    }

    public void validate() throws IncorrectNumberOfRoadsException, DuplicationDirectionException, ExceededMaxLanes, InvalidLaneException, InvalidStateConfigException {
        validateRoadList(intersection.getRoadList());
        for (ASTRoad road : intersection.getRoadList().getRoads()) {
            validateRoad(road);
        }
        if (intersection.getLight().getLoop() instanceof ASTStateLoop) {
            ArrayList<ASTState> states = ((ASTStateLoop) intersection.getLight().getLoop()).getStates();
            for (ASTState state : states) {
                validateStateConfig(state);
            }
        }
    }

    // ROAD-LIST
    // There must be exactly 4 roads
    // One road for each direction: N, S, W, E
    private void validateRoadList(ASTRoadList roadList) throws IncorrectNumberOfRoadsException, DuplicationDirectionException {
        if (roadList.getRoads().size() != 4) {
            throw new IncorrectNumberOfRoadsException("Road-List must contain exactly 4 roads.");
        }

        List<ASTDirection> directions = new ArrayList<>();
        for (ASTRoad road : roadList.getRoads()) {
            ASTDirection roadDirection = road.getDirection();
            if (directions.contains(roadDirection)) {
                throw new DuplicationDirectionException("More than one road in direction: " + roadDirection);
            }
            else {
                directions.add(roadDirection);
            }
        }
    }

    // ROAD
    // Max # of lanes is 6
    // Max 3 entry lanes
    // Max 3 exit lanes
    private void validateRoad(ASTRoad road) throws ExceededMaxLanes, InvalidLaneException {
        List<ASTLane> lanes = road.getLanes();
        if (lanes.size() > 6) {
            throw new ExceededMaxLanes("Maximum number of lanes exceeded. Only allow maximum 6 lanes per road");
        }

        Map<String, Integer> laneMap = new HashMap<>();
        laneMap.put(ENTRY_LANE, 0);
        laneMap.put(EXIT_LANE, 0);

        for (ASTLane lane : lanes) {
            Integer counter;
            if (lane instanceof ASTEntryLane) {
                counter = laneMap.get(ENTRY_LANE);
                laneMap.put(ENTRY_LANE, counter + 1);
            }
            if (lane instanceof ASTExitLane) {
                counter = laneMap.get(EXIT_LANE);
                laneMap.put(EXIT_LANE, counter + 1);
            }
        }

        Integer numOfEntryLanes = laneMap.get(ENTRY_LANE);
        Integer numOfExitLanes = laneMap.get(EXIT_LANE);

        if (numOfEntryLanes > 3) {
            throw new ExceededMaxLaneType("Exceeded number of Entry Lanes, maximum allowed: 3");
        }
        if (numOfExitLanes > 3) {
            throw new ExceededMaxLaneType("Exceeded number of Exit Lanes, maximum allowed: 3");
        }

        // LANE
        // LANE can have a REGION and/or REST_DIRECTION
        // Ex. A lane that goes “S” can also go right
        // We must double-check that this lane is the right-most lane, otherwise reject
        ASTDirection roadDirection = road.getDirection();
        for (ASTLane lane : lanes) {
            if (lane instanceof ASTEntryLane) {
                List<ASTDirection> laneDirections = ((ASTEntryLane) lane).getDirectionList().getDirections();
                if (laneDirections.size() < 1) {
                    throw new InvalidLaneException("Lane does not contain a direction");
                }
                /// Cannot have lanes with 3 directions (only straight, straight+right, or left)
                if (laneDirections.size() > 2) {
                    throw new InvalidLaneException("Lane contains too many directions. Lanes can only go: straight, straight+right, or left.");
                }
                /// Lanes can only go STRAIGHT, STRAIGHT+RIGHT, or LEFT
                switch (roadDirection.getCardinal()) {
                    case "N":
                        if (laneDirections.size() == 2) {
                            for (ASTDirection laneDirection : laneDirections) {
                                if (!laneDirection.getCardinal().equals("S") && !laneDirection.getCardinal().equals("W")) {
                                    throw new InvalidLaneException("Lane is not going straight+right");
                                }
                            }
                        }
                        if (laneDirections.size() == 1) {
                            if (!laneDirections.get(0).getCardinal().equals("S") && !laneDirections.get(0).getCardinal().equals("E")) {
                                throw new InvalidLaneException("Lane is not going straight or left");
                            }
                        }
                        break;
                    case "W":
                        if (laneDirections.size() == 2) {
                            for (ASTDirection laneDirection : laneDirections) {
                                if (!laneDirection.getCardinal().equals("E") && !laneDirection.getCardinal().equals("S")) {
                                    throw new InvalidLaneException("Lane is not going straight+right");
                                }
                            }
                        }
                        if (laneDirections.size() == 1) {
                            if (!laneDirections.get(0).getCardinal().equals("N") && !laneDirections.get(0).getCardinal().equals("E")) {
                                throw new InvalidLaneException("Lane is not going straight or left");
                            }
                        }
                        break;
                    case "S":
                        if (laneDirections.size() == 2) {
                            for (ASTDirection laneDirection : laneDirections) {
                                if (!laneDirection.getCardinal().equals("N") && !laneDirection.getCardinal().equals("E")) {
                                    throw new InvalidLaneException("Lane is not going straight+right");
                                }
                            }
                        }
                        if (laneDirections.size() == 1) {
                            if (!laneDirections.get(0).getCardinal().equals("N") && !laneDirections.get(0).getCardinal().equals("W")) {
                                throw new InvalidLaneException("Lane is not going straight or left");
                            }
                        }
                        break;
                    case "E":
                        if (laneDirections.size() == 2) {
                            for (ASTDirection laneDirection : laneDirections) {
                                if (!laneDirection.getCardinal().equals("W") && !laneDirection.getCardinal().equals("N")) {
                                    throw new InvalidLaneException("Lane is not going straight+right");
                                }
                            }
                        }
                        if (laneDirections.size() == 1) {
                            if (!laneDirections.get(0).getCardinal().equals("W") && !laneDirections.get(0).getCardinal().equals("S")) {
                                throw new InvalidLaneException("Lane is not going straight or left");
                            }
                        }
                        break;
                }
            }
        }
    }

    private void validateStateConfig(ASTState state) throws InvalidStateConfigException {
        ASTStateConfig stateConfig = state.getStateConfig();
        ASTGreenRoads greenRoads = stateConfig.getGreenRoads();
        ASTRedRoads redRoads = stateConfig.getRedRoads();

        List<ASTDirection> greenDirections = greenRoads.getDirectionList().getDirections();
        List<ASTDirection> redDirections = redRoads.getDirectionList().getDirections();

        List<String> redCardinals = new ArrayList<>();
        List<String> greenCardinals = new ArrayList<>();

        for (ASTDirection direction : redDirections) {
            redCardinals.add(direction.getCardinal());
        }

        for (ASTDirection direction : greenDirections) {
            greenCardinals.add(direction.getCardinal());
        }

        for (String greenDirection : greenCardinals) {
            switch (greenDirection) {
                case "N":
                    if (greenCardinals.contains("E") || greenCardinals.contains("W")) {
                        throw new InvalidStateConfigException("Orthogonal lanes cannot be green while Road N is green");
                    }
                    if (redCardinals.contains("N")) {
                        throw new InvalidStateConfigException("Road cannot be both green and red");
                    }
                    break;
                case "E":
                    if (greenCardinals.contains("S") || greenCardinals.contains("N")) {
                        throw new InvalidStateConfigException("Orthogonal lanes cannot be green while Road E is green");
                    }
                    if (redCardinals.contains("E")) {
                        throw new InvalidStateConfigException("Road cannot be both green and red");
                    }
                    break;
                case "S":
                    if (greenCardinals.contains("E") || greenCardinals.contains("W")) {
                        throw new InvalidStateConfigException("Orthogonal lanes cannot be green while Road S is green");
                    }
                    if (redCardinals.contains("S")) {
                        throw new InvalidStateConfigException("Road cannot be both green and red");
                    }
                    break;
                case "W":
                    if (greenCardinals.contains("S") || greenCardinals.contains("N")) {
                        throw new InvalidStateConfigException("Orthogonal lanes cannot be green while Road W is green");
                    }
                    if (redCardinals.contains("W")) {
                        throw new InvalidStateConfigException("Road cannot be both green and red");
                    }
                    break;
            }
        }
    }
}
