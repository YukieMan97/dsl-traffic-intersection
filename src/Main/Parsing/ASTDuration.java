package Main.Parsing;

import Main.Evaluating.Visitor;

public class ASTDuration extends ASTNode {
    private ASTDecimal ASTDecimal;

    public void setDecimal(ASTDecimal ASTDecimal) {
        this.ASTDecimal = ASTDecimal;
    }

    public ASTDecimal getDecimal() {
        return this.ASTDecimal;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }

}
