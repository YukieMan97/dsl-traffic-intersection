package Main.Parsing;

import Main.Evaluating.Visitor;

import java.util.ArrayList;

public class ASTDirectionList extends ASTNode {

    private final ArrayList<ASTDirection> ASTDirections;

    public ASTDirectionList() {
        this.ASTDirections = new ArrayList<ASTDirection>();
    }

    public void addDirection(ASTDirection dir) {
        this.ASTDirections.add(dir);
    }

    public ArrayList<ASTDirection> getDirections() {
        return this.ASTDirections;
    }

    public <T> T accept(Visitor<T> v) { return v.visit(this); }
}
