package Main.Evaluating;

import Main.Parsing.*;

import java.util.ArrayList;

public class Evaluator implements Visitor<Void> {
    private final ArrayList<ASTRoad> astRoads = new ArrayList<>();
    private final ArrayList<ASTLane> northRoadLanes = new ArrayList<>();
    private final ArrayList<ASTLane> eastRoadLanes = new ArrayList<>();
    private final ArrayList<ASTLane> southRoadLanes = new ArrayList<>();
    private final ArrayList<ASTLane> westRoadLanes = new ArrayList<>();

    @Override
    public Void visit(ASTBoolean astBoolean) {
        Boolean bool = astBoolean.getValue();
        return null;
    }

    // CROSSWALK ::= “Crosswalk {” SINGLE-LOOP “}”
    @Override
    public Void visit(ASTCrosswalk astCrosswalk) {
        astCrosswalk.getLoop().accept(this);
        return null;
    }

    @Override
    public Void visit(ASTDecimal astDecimal) {
        astDecimal.getBeforeDecimal().accept(this);
        ASTNum afterDecimal = astDecimal.getAfterDecimal();
        if (afterDecimal != null) {
            afterDecimal.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ASTDirection astDirection) {
        String cardinal = astDirection.getCardinal();
        return null;
    }

    // DIRECTIONS-LIST ::= “[” DIRECTION (“,” DIRECTION)* “]”
    @Override
    public Void visit(ASTDirectionList astDirectionList) {
        for (ASTDirection astDirection : astDirectionList.getDirections()) {
            astDirection.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ASTDuration astDuration) {
        astDuration.getDecimal().accept(this);
        return null;
    }

    // ENTRY-LANE ::= “entry-lane ” NUM “ {” DIRECTIONS-LIST “,” VEHICLE-LIST “,” LOOP “}”
    @Override
    public Void visit(ASTEntryLane astEntryLane) {
        astEntryLane.getNum().accept(this);
        int entryLaneNum = astEntryLane.getNum().getValue();
        astEntryLane.getDirectionList().accept(this);
        astEntryLane.getVehicleList().accept(this);
        astEntryLane.getLoop().accept(this);
        return null;
    }

    // EXIT-LANE ::= “exit-lane ” NUM
    @Override
    public Void visit(ASTExitLane astExitLane) {
        astExitLane.getNum().accept(this);
        return null;
    }

    // GREEN-ROADS ::= “green-roads: “ DIRECTIONS-LIST
    @Override
    public Void visit(ASTGreenRoads astGreenRoads) {
        astGreenRoads.getDirectionList().accept(this);
        return null;
    }

    // INTERSECTION ::= “Intersection {” ROAD-LIST “,” LIGHTS “}”
    @Override
    public Void visit(ASTIntersection astIntersection) {
        astIntersection.getRoadList().accept(this);
        astIntersection.getLight().accept(this);
        System.out.println("astRoads: " +  astRoads);
        System.out.println("northRoadLanes: " +  northRoadLanes);
        System.out.println("eastRoadLanes: " +  eastRoadLanes);
        System.out.println("southRoadLanes: " +  southRoadLanes);
        System.out.println("westRoadLanes: " +  westRoadLanes);
        return null;
    }

    // LIGHTS ::= “Lights {” LOOP “}”
    @Override
    public Void visit(ASTLight astLight) {
        astLight.getLoop().accept(this);
        return null;
    }

    @Override
    public Void visit(ASTNode astNode) {
        return null;
    }

    @Override
    public Void visit(ASTNum astNum) {
        int number = astNum.getValue();
        return null;
    }

    // PEDESTRIANS ::= “pedestrians: {” BOOLEAN “}”
    @Override
    public Void visit(ASTPedestrians astPedestrians) {
        astPedestrians.getBool().accept(this);
        return null;
    }

    // RED-ROADS ::= “red-roads: “     DIRECTIONS-LIST
    @Override
    public Void visit(ASTRedRoads astRedRoads) {
        astRedRoads.getDirectionList().accept(this);
        return null;
    }

    // ROAD ::= “Road ” DIRECTION “ {” LANE (“,” LANE)+ (“,” CROSSWALK)? “}”
    @Override
    public Void visit(ASTRoad astRoad) {
        // ASTDirection
        astRoad.getDirection().accept(this);
        String direction = astRoad.getDirection().getCardinal();
        // ASTLane
        for (ASTLane astLane : astRoad.getLanes()) {
            astLane.accept(this);
            switch (direction) {
                case "N" -> northRoadLanes.add(astLane);
                case "E" -> eastRoadLanes.add(astLane);
                case "S" -> southRoadLanes.add(astLane);
                case "W" -> westRoadLanes.add(astLane);
            }
        }
        // ASTCrosswalk
        ASTCrosswalk astCrosswalk = astRoad.getCrosswalk();
        if (astCrosswalk != null) {
            astCrosswalk.accept(this);
        }
        return null;
    }

    // ROAD-LIST ::= “Road-List {” ROAD “,” ROAD “,” ROAD “,” ROAD “}”
    @Override
    public Void visit(ASTRoadList astRoadList) {
        for (ASTRoad astRoad : astRoadList.getRoads()) {
            astRoad.accept(this);
            astRoads.add(astRoad);
        }
        return null;
    }

    // SINGLE LOOP ::= “Loop {” DURATION “}”
    @Override
    public Void visit(ASTSingleLoop astSingleLoop) {
        astSingleLoop.getDuration().accept(this);
        return null;
    }

    // STATE ::= “state {” STATE-CONFIG “,” DURATION “}”
    @Override
    public Void visit(ASTState astState) {
        astState.getStateConfig().accept(this);
        astState.getDuration().accept(this);
        return null;
    }

    // STATE-CONFIG ::= GREEN-ROADS “,” RED-ROADS “,” PEDESTRIANS
    @Override
    public Void visit(ASTStateConfig astStateConfig) {
        astStateConfig.getGreenRoads().accept(this);
        astStateConfig.getRedRoads().accept(this);
        astStateConfig.getPedestrians().accept(this);
        return null;
    }

    // STATE-LOOP ::= “Loop states {“ STATE (“,” STATE)* “}”
    @Override
    public Void visit(ASTStateLoop astStateLoop) {
        for (ASTState astState : astStateLoop.getStates()) {
            astState.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ASTVehicle astVehicle) {
        String vehicle  = astVehicle.getValue();
        return null;
    }

    // VEHICLES-LIST ::= “[” VEHICLE (“,” VEHICLE)* “]”
    @Override
    public Void visit(ASTVehicleList astVehicleList) {
        for (ASTVehicle astVehicle : astVehicleList.getVehicles()) {
            astVehicle.accept(this);
        }
        return null;
    }
}
