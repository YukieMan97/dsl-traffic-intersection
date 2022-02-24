package Main.Parsing;

import Main.Evaluating.Visitor;

import java.util.ArrayList;

public class ASTRoad extends ASTNode {

    private ASTDirection ASTDirection;
    private final ArrayList<ASTLane> ASTLanes;
    private ASTCrosswalk crosswalk;

    public ASTRoad() {
        this.ASTLanes = new ArrayList<ASTLane>();
    }

    public void setDirection(ASTDirection dir) {
        this.ASTDirection = dir;
    }

    public ASTDirection getDirection() {
        return this.ASTDirection;
    }

    public void addLane(ASTLane ASTLane) {
        this.ASTLanes.add(ASTLane);
    }

    public ArrayList<ASTLane> getLanes() {
        return this.ASTLanes;
    }

    public void setCrosswalk(ASTCrosswalk crosswalk) {
        this.crosswalk = crosswalk;
    }

    public ASTCrosswalk getCrosswalk() {
        return this.crosswalk;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }

}
