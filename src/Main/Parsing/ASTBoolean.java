package Main.Parsing;

import Main.Evaluating.Visitor;

public class ASTBoolean extends ASTNode {
    Boolean value;
    public ASTBoolean(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() { return this.value; }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }
}
