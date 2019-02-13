package Visitors;

public class UnaryOpNode extends SyntaxNode implements IVisitable
{
    public String operator;
    public IVisitable subNode;

    public UnaryOpNode(String operator, IVisitable subNode)
    {
        this.operator = operator;
        this.subNode = subNode;
    }

    @Override
    public void accept(IVisitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "UnaryOpNode{" +
                "operator='" + operator + '\'' +
                ", subNode=" + subNode.toString() +
                '}';
    }

}

