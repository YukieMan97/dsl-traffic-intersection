package Main.Parsing;

import Main.Evaluating.Visitor;

public class ASTVehicle extends ASTNode{
    private String value;

    public ASTVehicle(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }
}
