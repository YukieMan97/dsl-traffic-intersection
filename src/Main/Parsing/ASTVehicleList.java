package Main.Parsing;

import Main.Evaluating.Visitor;

import java.util.ArrayList;

public class ASTVehicleList extends ASTNode {

    private final ArrayList<ASTVehicle> ASTVehicles;

    public ASTVehicleList() {
        this.ASTVehicles = new ArrayList<ASTVehicle>();
    }

    public void addVehicle(ASTVehicle v) {
        this.ASTVehicles.add(v);
    }

    public ArrayList<ASTVehicle> getVehicles() {
        return this.ASTVehicles;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }
}
