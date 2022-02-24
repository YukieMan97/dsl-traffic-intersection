package Main.Parsing;

import Main.Evaluating.Visitor;

import java.util.ArrayList;

public class ASTRoadList extends ASTNode {
    private final ArrayList<ASTRoad> ASTRoads;

    public ASTRoadList() { this.ASTRoads = new ArrayList<ASTRoad>(); }

    public void addRoad(ASTRoad ASTRoad) {
        this.ASTRoads.add(ASTRoad);
    }

    public ArrayList<ASTRoad> getRoads() {
        return this.ASTRoads;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }
}
