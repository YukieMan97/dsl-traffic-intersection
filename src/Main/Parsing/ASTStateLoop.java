package Main.Parsing;

import Main.Evaluating.Visitor;

import java.util.ArrayList;

public class ASTStateLoop extends ASTLoop {
    private final ArrayList<ASTState> ASTStates;

    public ASTStateLoop() {
        this.ASTStates = new ArrayList<ASTState>();
    }

    public void addState(ASTState ASTState) {
        this.ASTStates.add(ASTState);
    }

    public ArrayList<ASTState> getStates() {
        return this.ASTStates;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }
}
