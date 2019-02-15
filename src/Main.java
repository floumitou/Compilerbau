import TopDownParser.TopDownParser;
import Visitors.*;

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
        SyntaxTree = firstVisitor.visitTreeNodes(SyntaxTree);



        //Tom
        SortedMap<Integer, FollowposTableEntry> followPosTableEntries;

        SecondVisitor secondVisitor = new SecondVisitor();
        DepthFirstIterator.traverse(SyntaxTree, secondVisitor);
        followPosTableEntries = secondVisitor.getFollowposTableEntries();


        //Basti macht Sachen mit FollowPosTableEntries
    }
}
