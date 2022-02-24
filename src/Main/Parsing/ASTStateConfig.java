package Main.Parsing;

import Main.Evaluating.Visitor;

public class ASTStateConfig extends ASTNode {
    private ASTGreenRoads ASTGreenRoads;
    private ASTRedRoads ASTRedRoads;
    private ASTPedestrians ASTPedestrians;

    public void setGreenRoads(ASTGreenRoads gr) {
        this.ASTGreenRoads = gr;
    }

    public ASTGreenRoads getGreenRoads() {
        return this.ASTGreenRoads;
    }

    public void setRedRoads(ASTRedRoads rr) {
        this.ASTRedRoads = rr;
    }

    public ASTRedRoads getRedRoads() {
        return this.ASTRedRoads;
    }

    public void setPedestrians(ASTPedestrians p) {
        this.ASTPedestrians = p;
    }

    public ASTPedestrians getPedestrians() {
        return this.ASTPedestrians;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }
}
