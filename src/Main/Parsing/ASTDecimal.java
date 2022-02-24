package Main.Parsing;

import Main.Evaluating.Visitor;

public class ASTDecimal extends ASTNode {
    private ASTNum beforeDecimal;
    private ASTNum afterDecimal;

    public void setBeforeDecimal(ASTNum beforeDecimal) {
        this.beforeDecimal = beforeDecimal;
    }

    public void setAfterDecimal(ASTNum afterDecimal) {
        this.afterDecimal = afterDecimal;
    }

    public ASTNum getBeforeDecimal() {
        return beforeDecimal;
    }

    public ASTNum getAfterDecimal() {
        return afterDecimal;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }

}
