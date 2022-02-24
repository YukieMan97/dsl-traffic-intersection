package Main.Parsing;

import Main.Tokenize.DSLTokenizer;

public class Parser {
    private DSLTokenizer tokenizer;
    private ASTIntersection ASTIntersection;

    public Parser(DSLTokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public ASTNode parse() {
        ASTIntersection = parseIntersection();

        tokenizer.getAndCheckNext("^\\{$");
        ASTIntersection.setRoadList(parseRoadList());
        tokenizer.getAndCheckNext("^,$");
        ASTIntersection.setLight(parseLights());
        tokenizer.getAndCheckNext("^\\}$");
        return ASTIntersection;
    }

    private ASTLight parseLights() {
        tokenizer.getAndCheckNext("^Lights $");
        tokenizer.getAndCheckNext("^\\{$");
        ASTLight ASTLight = new ASTLight();
        ASTLight.setLoop(parseLoop());
        tokenizer.getAndCheckNext("^\\}$");
        return ASTLight;
    }


    public ASTIntersection parseIntersection() {
        tokenizer.getAndCheckNext("^Intersection $");
        return new ASTIntersection();
    }

    public ASTRoadList parseRoadList() {
        tokenizer.getAndCheckNext("^Road-List $");
        tokenizer.getAndCheckNext("^\\{$");
        ASTRoadList ASTRoadList = new ASTRoadList();
        ASTRoadList.addRoad(parseRoad());
        for (int i = 0; i < 3; i++) {
            tokenizer.getAndCheckNext("^,$");
            ASTRoadList.addRoad(parseRoad());
        }
        tokenizer.getAndCheckNext("^\\}$");
        return ASTRoadList;
    }

    public ASTRoad parseRoad() {
        tokenizer.getAndCheckNext("^Road $");
        ASTRoad ASTRoad = new ASTRoad();
        ASTRoad.setDirection(parseDirection());
        tokenizer.getAndCheckNext("^\\{$");
        parseRoadHelper(ASTRoad);
        tokenizer.getAndCheckNext("^\\}$");
        return ASTRoad;
    }

    private void parseRoadHelper(ASTRoad ASTRoad) {
        ASTRoad.addLane(parseLane());
            while (tokenizer.checkToken("^,$")) {
                tokenizer.getNext();
                //stuff for crosswalk, not needed
                /*if (tokenizer.checkToken("^Crosswalk $")) {
                    road.setCrosswalk(parseCrosswalk());
                    break;
                }*/
                if (tokenizer.checkToken("^entry-lane |exit-lane $")) {
                    ASTRoad.addLane(parseLane());
                }
            }
    }

    public ASTDirection parseDirection() {
        String dirVal = tokenizer.getAndCheckNext("^(W|N|E|S)$");
        return new ASTDirection(dirVal);
    }

    public ASTLane parseLane() {
        if (tokenizer.checkToken("^entry-lane $")) {
            return parseEntryLane();
        }
        if (tokenizer.checkToken("^exit-lane $")) {
            return parseExitLane();
        } else {
            throw new RuntimeException("was expecting exit-lane or entry-lane");
        }
    }

   /* public Crosswalk parseCrosswalk() {
        tokenizer.getAndCheckNext("^Crosswalk $");
        tokenizer.getAndCheckNext("^\\{$");
        Crosswalk crosswalk = new Crosswalk();
        crosswalk.setLoop(parseLoop());
        tokenizer.getAndCheckNext("^\\}$");
        return crosswalk;
    }*/

    public ASTEntryLane parseEntryLane() {
        tokenizer.getAndCheckNext("^entry-lane $");
        ASTEntryLane ASTEntryLane = new ASTEntryLane();
        ASTEntryLane.setNum(parseNum());
        tokenizer.getAndCheckNext("^\\{$");
        ASTEntryLane.setDirectionList(parseDirectionList());
        tokenizer.getAndCheckNext("^,$");
        ASTEntryLane.setVehicleList(parseVehicleList());
        tokenizer.getAndCheckNext("^,$");
        ASTEntryLane.setLoop(parseLoop());
        tokenizer.getAndCheckNext("^\\}$");
        return ASTEntryLane;
    }

    public ASTExitLane parseExitLane() {
        ASTExitLane ASTExitLane = new ASTExitLane();
        tokenizer.getAndCheckNext("^exit-lane $");
        ASTExitLane.setNum(parseNum());
        return ASTExitLane;
    }

    public ASTNum parseNum() {
        String numVal = tokenizer.getAndCheckNext("^ ?[0-9]+ ?$");
        numVal = numVal.trim();
        int intVal = Integer.parseInt(numVal);
        return new ASTNum(intVal);
    }

    public ASTDirectionList parseDirectionList() {
        tokenizer.getAndCheckNext("^\\[$");
        ASTDirectionList ASTDirectionList = new ASTDirectionList();
        ASTDirectionList.addDirection(parseDirection());
        while (tokenizer.checkToken("^,$")) {
            tokenizer.getAndCheckNext("^,$");
            ASTDirectionList.addDirection(parseDirection());
        }
        tokenizer.getAndCheckNext("^\\]$");
        return ASTDirectionList;
    }

    public ASTVehicleList parseVehicleList() {
        tokenizer.getAndCheckNext("^\\[$");
        ASTVehicleList ASTVehicleList = new ASTVehicleList();
        ASTVehicleList.addVehicle(parseVehicle());
        while (tokenizer.checkToken("^,$")) {
            tokenizer.getNext();
            ASTVehicleList.addVehicle(parseVehicle());
        }
        tokenizer.getAndCheckNext("^\\]$");
        return new ASTVehicleList();
    }

    public ASTVehicle parseVehicle() {
        String vehicleValue = tokenizer.getAndCheckNext("^(cars|buses|trucks|bikes|all)$");
        return new ASTVehicle(vehicleValue);
    }

    public ASTLoop parseLoop() {
        tokenizer.getAndCheckNext("^Loop $");
        if (tokenizer.checkToken("^states $")) {
            return parseStateLoop();
        } else {
            ASTSingleLoop singleLoop = new ASTSingleLoop();
            tokenizer.getAndCheckNext("^\\{$");
            singleLoop.setDuration(parseDuration());
            tokenizer.getAndCheckNext("^\\}$");
            return singleLoop;
        }
    }

    public ASTStateLoop parseStateLoop() {
        tokenizer.getAndCheckNext("^states $");
        tokenizer.getAndCheckNext("^\\{$");
        ASTStateLoop stateLoop = new ASTStateLoop();
        stateLoop.addState(parseState());
        while (tokenizer.checkToken("^,$")) {
            tokenizer.getNext();
            stateLoop.addState(parseState());
        }
        tokenizer.getAndCheckNext("^\\}$");
        return stateLoop;
    }

    public ASTDuration parseDuration() {
        tokenizer.getAndCheckNext("^duration: $");
        ASTDuration ASTDuration = new ASTDuration();
        ASTDuration.setDecimal(parseDecimal());
        return ASTDuration;
    }

    private ASTDecimal parseDecimal() {
        ASTDecimal ASTDecimal = new ASTDecimal();
        ASTDecimal.setBeforeDecimal(parseNum());
        if (tokenizer.checkToken("^.$")) {
            tokenizer.getAndCheckNext("^.$");
            ASTDecimal.setAfterDecimal(parseNum());
        }
        return ASTDecimal;
    }

    public ASTState parseState() {
        tokenizer.getAndCheckNext("^state $");
        tokenizer.getAndCheckNext("^\\{$");
        ASTState ASTState = new ASTState();
        ASTState.setStateConfig(parseStateConfig());
        tokenizer.getAndCheckNext("^,$");
        ASTState.setDuration(parseDuration());
        tokenizer.getAndCheckNext("^\\}$");
        return ASTState;
    }

    public ASTStateConfig parseStateConfig() {
        ASTStateConfig ASTStateConfig = new ASTStateConfig();
        ASTStateConfig.setGreenRoads(parseGreenRoads());
        tokenizer.getAndCheckNext("^,$");
        ASTStateConfig.setRedRoads(parseRedRoads());
        /*tokenizer.getAndCheckNext("^,$");
        ASTStateConfig.setPedestrians(parsePedestrians());*/
        return new ASTStateConfig();
    }

    public ASTGreenRoads parseGreenRoads() {
        tokenizer.getAndCheckNext("green-roads: ");
        ASTGreenRoads ASTGreenRoads = new ASTGreenRoads();
        ASTGreenRoads.setDirectionList(parseDirectionList());
        return ASTGreenRoads;
    }

    public ASTRedRoads parseRedRoads() {
        tokenizer.getAndCheckNext("red-roads: ");
        ASTRedRoads ASTRedRoads = new ASTRedRoads();
        ASTRedRoads.setDirectionList(parseDirectionList());
        return ASTRedRoads;
    }

 /*   public ASTPedestrians parsePedestrians() {
        tokenizer.getAndCheckNext("^pedestrians: ");
        tokenizer.getAndCheckNext("^\\{$");
        ASTPedestrians ASTPedestrians = new ASTPedestrians();
        ASTPedestrians.setBool(parseBoolean());
        tokenizer.getAndCheckNext("^\\}$");
        return ASTPedestrians;
    }*/

    public ASTBoolean parseBoolean() {
        if (tokenizer.checkToken("^true$")) {
            tokenizer.getAndCheckNext("^true$");
            return new ASTBoolean(true);
        }
        if (tokenizer.checkToken("^false$")) {
            tokenizer.getAndCheckNext("^false$");
            return new ASTBoolean(false);
        } else {
            throw new RuntimeException("Was expecting true or false");
        }
    }




}