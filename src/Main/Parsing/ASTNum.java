package Main.Parsing;

import Main.Evaluating.Visitor;

public class ASTNum extends ASTNode {
    private int value;

    public ASTNum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }
}
