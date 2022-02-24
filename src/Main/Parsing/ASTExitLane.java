package Main.Parsing;

import Main.Evaluating.Visitor;

public class ASTExitLane extends ASTLane {

    private ASTNum ASTNum;

    public void setNum(ASTNum ASTNum) {
        this.ASTNum = ASTNum;
    }

    public ASTNum getNum() {
        return this.ASTNum;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }
}
