import TopDownParser.TopDownParser;
import Visitors.IVisitable;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a regular Expression:");
        String regExp = input.next();
        TopDownParser parser = new TopDownParser(regExp);

        IVisitable SyntaxTree = parser.start();
    }
}
