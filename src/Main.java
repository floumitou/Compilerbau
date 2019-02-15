import DEAGenerator.DFAState;
import DEAGenerator.DFATableBuilder;
import TopDownParser.TopDownParser;
import Visitors.*;

import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;

public class Main {

    public static void main(String[] args) {

        //Diego
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a regular Expression:");
        String regExp = input.next();
        TopDownParser parser = new TopDownParser(regExp);

        IVisitable SyntaxTree = parser.start();


        //Florian
        FirstVisitor firstVisitor = new FirstVisitor();
        firstVisitor.visitTreeNodes(SyntaxTree);



        //Tom
        SortedMap<Integer, FollowposTableEntry> followPosTableEntries;

        SecondVisitor secondVisitor = new SecondVisitor();
        DepthFirstIterator.traverse(SyntaxTree, secondVisitor);
        followPosTableEntries = secondVisitor.getFollowposTableEntries();


        //Basti

        DFATableBuilder stateBuilder = new DFATableBuilder();
        SortedMap<DFAState, Map<Character, DFAState>> stateMap = stateBuilder.createDFATable(followPosTableEntries);
    }
}
