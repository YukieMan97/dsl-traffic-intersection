package Main.Parsing;

import Main.Evaluating.Visitor;

public class ASTLight extends ASTNode{

    private ASTLoop ASTLoop;

    public void setLoop(ASTLoop ASTLoop) {
        this.ASTLoop = ASTLoop;
    }

    public ASTLoop getLoop() {
        return this.ASTLoop;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }
}
