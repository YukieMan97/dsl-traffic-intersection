package Main.Parsing;

import Main.Evaluating.Visitor;

public class ASTPedestrians extends ASTNode {
    private ASTBoolean bool;

    public void setBool(ASTBoolean bool) {
        this.bool = bool;
    }

    public ASTBoolean getBool() {
        return this.bool;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }
}
