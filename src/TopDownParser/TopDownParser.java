package TopDownParser;

import Visitors.BinOpNode;
import Visitors.IVisitable;
import Visitors.OperandNode;
import Visitors.UnaryOpNode;

/**
 * @author: Diego Kaltdorf
 */

public class TopDownParser {
    private String regEx;
    private char sign;
    private int i;

    public TopDownParser(String regEx) {
        this.regEx = regEx;
        this.i = 0;
        sign = regEx.charAt(0);
    }

    private void next() {
        i++;
        if (i < regEx.length())
            sign = regEx.charAt(i);
    }

    public IVisitable start() {
        if (regEx.length() < 1) {
            throw new RuntimeException("Expression is empty");
        } else if (sign == '#') {
            return new OperandNode("#");
        } else if (sign == '(') {
            next();
            OperandNode leaf = new OperandNode("#");
            return new BinOpNode("°", regExp(), leaf);
        }
        throw new RuntimeException("No regular Expression.");
    }

    private IVisitable regExp() {
        if (Character.isLetterOrDigit(sign) || sign == '(') {
            return rE(term(null));
        } else throw new RuntimeException("No regular Expression");

    }

    private IVisitable term(IVisitable parameter) {
        if (sign == ')' || sign == '|') {
            return parameter;
        }
        if (Character.isLetterOrDigit(sign) || sign == '(') {
            if (parameter != null) {
                IVisitable root = new BinOpNode("°", parameter, factor());
                return term(root);
            } else {
                return term(factor());
            }
        } else throw new RuntimeException("No regular Expression");
    }

    private IVisitable rE(IVisitable parameter) {
        if (sign == ')') {
            next();
            return parameter;
        } else if (sign == '|') {
            next();
            return rE(new BinOpNode("|", parameter, term(null)));
        } else throw new RuntimeException("No regular Expression");
    }

    private IVisitable factor() {
        return hop(elem());
    }

    private IVisitable elem() {
        if (sign == '(') {
            next();
            return regExp();
        } else {
            return alphanum();
        }
    }

    private IVisitable hop(IVisitable parameter) {
        if (Character.isLetterOrDigit(sign) || sign == '(' || sign=='|'|| sign == ')') {
            return parameter;
        } else if (sign == '*') {
            next();
            return new UnaryOpNode("*", parameter);
        } else if (sign == '+') {
            next();
            return new UnaryOpNode("+", parameter);
        } else if (sign == '?') {
            next();
            return new UnaryOpNode("?", parameter);
        } else throw new RuntimeException("No regular Expression");
    }

    private IVisitable alphanum() {
        if (Character.isLetterOrDigit(sign)) {
            String symbol = Character.toString(sign);
            next();
            return new OperandNode(symbol);
        } else throw new RuntimeException("No regular Expression");
    }
}
