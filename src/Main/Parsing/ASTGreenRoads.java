package Main.Parsing;

import Main.Evaluating.Visitor;

public class ASTGreenRoads extends ASTNode {
    private ASTDirectionList ASTDirectionList;

    public void setDirectionList(ASTDirectionList dirList) {
        this.ASTDirectionList = dirList;
    }

    public ASTDirectionList getDirectionList() {
        return this.ASTDirectionList;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }
}
