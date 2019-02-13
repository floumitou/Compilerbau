package Visitors;

import java.util.HashSet;
import java.util.Set;
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

    public void visitTreeNodes(IVisitable root) {

        DepthFirstIterator.traverse(root, this);
    }


    public void visit(OperandNode node) {  // OperandNode

        FollowposTableEntry entry = new FollowposTableEntry(node.position, node.symbol);

        entry.followpos.clear();

        followposTableEntries.put(node.position, entry);
    }

    public void visit(UnaryOpNode node) {  // UnaryOpNode

        switch (node.operator) {
            case "*":
                for (int i : node.lastpos) {
                    for (int j : node.firstpos) {
                        followposTableEntries.get(i).followpos.add(j);
                    }
                }

                break;

            case "+":
                for (int i : node.lastpos) {
                    for (int j : node.firstpos) {
                        followposTableEntries.get(i).followpos.add(j);
                    }
                }

                break;

            case "?":
                break;

            default:
                System.out.println("Sth unexpected Happened: " + node.getClass().toGenericString() + " " + node.operator);
                break;
        }
    }

    public void visit(BinOpNode node) {  // BinOpNode
        switch (node.operator) {
            case "°":
                for (int i : ((SyntaxNode)node.left).lastpos) {
                    for (int j : ((SyntaxNode) node.right).firstpos) {
                        followposTableEntries.get(i).followpos.add(j);
                    }
                }

                break;

            case "|":
                break;
            default:
                System.out.println("Sth unexpected Happened: " + node.getClass().toGenericString() + " " + node.operator);
                break;
    }
    
}}