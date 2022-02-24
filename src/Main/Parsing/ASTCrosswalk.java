package Main.Parsing;

import Main.Evaluating.Visitor;

public class ASTCrosswalk extends ASTNode {
    // Crosswalk should be ASTSingleLoop. Fix if have time.
    private ASTLoop ASTLoop;

    public ASTLoop getLoop() {
        return ASTLoop;
    }

    public void setLoop(ASTLoop ASTLoop) {
        this.ASTLoop = ASTLoop;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }

}
