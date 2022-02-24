package Main.Parsing;

import Main.Evaluating.Visitor;

public class ASTEntryLane extends ASTLane {
    private ASTNum ASTNum;
    private ASTDirectionList ASTDirectionList;
    private ASTVehicleList ASTVehicleList;
    private ASTLoop ASTLoop;

    public ASTLoop getLoop() {
        return ASTLoop;
    }

    public void setLoop(ASTLoop ASTLoop) {
        this.ASTLoop = ASTLoop;
    }


    public void setNum(ASTNum ASTNum) {
        this.ASTNum = ASTNum;
    }

    public ASTNum getNum() {
        return this.ASTNum;
    }

    public void setDirectionList(ASTDirectionList dirList) {
        this.ASTDirectionList = dirList;
    }

    public ASTDirectionList getDirectionList() {
        return this.ASTDirectionList;
    }

    public void setVehicleList(ASTVehicleList vList) {
        this.ASTVehicleList = vList;
    }

    public ASTVehicleList getVehicleList() {
        return this.ASTVehicleList;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }
}
