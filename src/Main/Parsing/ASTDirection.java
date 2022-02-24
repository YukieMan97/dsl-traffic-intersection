package Main.Parsing;

import Main.Evaluating.Visitor;

public class ASTDirection extends ASTNode {
    private String cardinal;

    public ASTDirection(String value) {
        this.cardinal = value;
    }

    public String getCardinal() {
        return this.cardinal;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }
}
