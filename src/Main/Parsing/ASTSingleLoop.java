package Main.Parsing;

import Main.Evaluating.Visitor;

public class ASTSingleLoop extends ASTLoop {

    private ASTDuration ASTDuration;

    public void setDuration(ASTDuration dur) {
        this.ASTDuration = dur;
    }

    public ASTDuration getDuration() {
        return this.ASTDuration;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }

}
