package Visitors.SecondVisitor;


import Visitors.FollowposTableEntry;
import Visitors.IVisitor;

import java.util.SortedMap;
import java.util.TreeMap;



public class SecondVisitor implements IVisitor {


    private DepthFirstIterator depthFirstIterator;

    private SortedMap<Integer, FollowposTableEntry> followposTableEntries = new TreeMap<>();

    public SortedMap<Integer, FollowposTableEntry> getFollowposTableEntries() {
        return followposTableEntries;
    }



    public SecondVisitor() {

        followposTableEntries = new TreeMap<>();
        depthFirstIterator = new DepthFirstIterator();
    }

    // traverse method
    public void visitTreeNodes(Visitable root) {

   //todo     DepthFirstIterator.traverse(root, this);
    }

    // visit methods

    public void visit(OperandNode node) {

        FollowposTableEntry entry = new FollowposTableEntry(node.position, node.symbol);

        entry.followpos.clear();

        followPosTableEntries.put(node.position, entry);
    }

    public void visit(UnaryOpNode node) {

        Set<Integer> followPosValues = new HashSet<>(); // todo Why did I add this???

        // if operation is Kleene star or Kleene plus
        if (node.operator.equals("*") || node.operator.equals("+")) {

            // iterate through all nodes in lastpos
            for (int lastPosValue : node.lastpos) {

                // followpos(node at lastPosValue) += firstpos(node)
                // and update entry set
                // todo How to update the node at the position lastPosValue?
                followPosTableEntries.get(lastPosValue).followpos.addAll(node.firstpos);
            }
        }
    }

    public void visit(BinOpNode node) {

        // if operation is concatenation
        if (node.operator.equals("°")) {

            // iterate through all nodes in lastpos of this node's left child
            for (int lastPosValue : ((SyntaxNode) node.left).lastpos) {

                // followpos(node at lastPosValue) += lastpos(right child)
                // and update entry set
                followPosTableEntries.get(lastPosValue).followpos.addAll(((SyntaxNode) node.right).lastpos);
            }
        }
    }




}