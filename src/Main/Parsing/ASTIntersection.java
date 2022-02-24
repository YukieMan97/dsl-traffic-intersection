package Main.Parsing;

import Main.Evaluating.Visitor;

public class ASTIntersection extends ASTNode {

    private ASTRoadList ASTRoadList;
    private ASTLight ASTLight;

    public void setLight(ASTLight ASTLight) {
        this.ASTLight = ASTLight;
    }

    public ASTLight getLight() {
        return this.ASTLight;
    }

    public void setRoadList(ASTRoadList ASTRoadList) {
        this.ASTRoadList = ASTRoadList;
    }

    public ASTRoadList getRoadList() {
        return this.ASTRoadList;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }

}
