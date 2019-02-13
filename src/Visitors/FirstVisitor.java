package Visitors;

import java.util.HashSet;
import java.util.Set;

public class FirstVisitor implements IVisitor {

    private static int posCounter = 0;

    public void visit(OperandNode pNode) {
        pNode.nullable = isOperandNullable(pNode);

        pNode.firstpos.addAll(setFirstAndLastPos(pNode));
        pNode.lastpos.addAll(setFirstAndLastPos(pNode));
    }

    public void visit(BinOpNode pNode) {
        pNode.nullable = isBinOpNullable(pNode);

        pNode.firstpos.addAll(setFirstPos(pNode));
        pNode.lastpos.addAll(setLastPos(pNode));
    }

    public void visit(UnaryOpNode pNode) {
        pNode.nullable = isUnaryNullable(pNode);

        pNode.firstpos.addAll(setFirstPos(pNode));
        pNode.lastpos.addAll(setLastPos(pNode));
    }


    // method to traverse the nodes (in DepthFirstIterator)
    public void visitTreeNodes(IVisitable root) {
        DepthFirstIterator.traverse(root, this);
    }


    public Set<Integer> setFirstAndLastPos(OperandNode pNode){
        Set<Integer> set = new HashSet<>();
        if (pNode.symbol.equals("epsilon")){
            set.clear();
        }
        else{
            posCounter++;
            set.add(posCounter);
        }
        return set;
    }

    public boolean isOperandNullable(OperandNode node){
        // operands are always leafs
        return node.symbol.equals("epsilon");
    }


    public Set<Integer> setFirstPos (BinOpNode pNode){
        Set<Integer> set = new HashSet<>();
        if (pNode.operator.equals("|")){
            set.addAll(((SyntaxNode)pNode.left).firstpos);
            set.addAll(((SyntaxNode)pNode.right).firstpos);
        }
        else if(pNode.operator.equals("°")){
            if (((SyntaxNode)pNode.left).nullable){
                set.addAll(((SyntaxNode)pNode.left).firstpos);
                set.addAll(((SyntaxNode)pNode.right).firstpos);
            }
            else{
                set.addAll(((SyntaxNode)pNode.left).firstpos);
            }
        }
        return set;
    }

    public Set<Integer> setLastPos (BinOpNode pNode){
        Set<Integer> set = new HashSet<>();
        if (pNode.operator.equals("|")){
            set = ((SyntaxNode)pNode.left).lastpos;
            set.addAll(((SyntaxNode)pNode.right).lastpos);
        }
        else if (pNode.operator.equals("°")){
            if(((SyntaxNode)pNode.right).nullable){
                set = ((SyntaxNode)pNode.left).lastpos;
                set.addAll(((SyntaxNode)pNode.right).lastpos);
            }
            else{
                set = (((SyntaxNode)pNode.right).lastpos);
            }
        }
        return set;
    }

    public boolean isBinOpNullable(BinOpNode pNode) {
        // node has descendants
        if (pNode.operator.equals("|")) {
            return (((SyntaxNode) pNode.left).nullable) || ((SyntaxNode) pNode.right).nullable;
        }
        // concatenation
        else if (pNode.operator.equals("°")) {
            return (((SyntaxNode) pNode.left).nullable) && ((SyntaxNode) pNode.right).nullable;
        } else if (pNode.operator.equals("?")) {
            return true;
        }
        else return pNode.operator.equals("*");
    }


    public Set<Integer> setFirstPos (UnaryOpNode pNode){
        Set<Integer> set = new HashSet<>();
        if (pNode.operator.equals("*") || (pNode.operator.equals("+") || (pNode.operator.equals("?")))){
            set = ((SyntaxNode)pNode.subNode).firstpos;
        }
        return set;
    }

    public Set<Integer> setLastPos (UnaryOpNode pNode){
        Set<Integer> set = new HashSet<>();
        if (pNode.operator.equals("*") || (pNode.operator.equals("+") || (pNode.operator.equals("?")))){
            set = ((SyntaxNode)pNode.subNode).lastpos;
        }
        return set;
    }

    // check if node is nullable
    public boolean isUnaryNullable(UnaryOpNode pNode) {
        // node has one descendant
        if (pNode.operator.equals("*") || pNode.operator.equals("?")) {
            return true;
        } else if (pNode.operator.equals("?")) {
            // node is automatically a leaf = no children
            return ((OperandNode) pNode.subNode).symbol.equals("epsilon");
        }
        return false;
    }

}