package Main.Parsing;

import Main.Evaluating.Visitor;

public class ASTNode {
    public <T> T accept(Visitor<T> v) { return v.visit(this); }
    // private ArrayList<ASTNode> children = new ArrayList<ASTNode>();



    // public void addChild(ASTNode child) {
    //    this.children.add(child);
    // }

}
