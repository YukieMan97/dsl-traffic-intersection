package Main.Parsing;

import Main.Evaluating.Visitor;

public class ASTState extends ASTNode {
    private ASTStateConfig ASTStateConfig;
    private ASTDuration ASTDuration;

    public void setDuration(ASTDuration dur) {
        this.ASTDuration = dur;
    }

    public ASTDuration getDuration() {
        return this.ASTDuration;
    }

    public void setStateConfig(ASTStateConfig config) {
        this.ASTStateConfig = config;
    }

    public ASTStateConfig getStateConfig() {
        return this.ASTStateConfig;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }
}
