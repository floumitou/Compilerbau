package Visitors;

public class BinOpNode extends SyntaxNode implements IVisitable
{
    public String operator;
    public IVisitable left;
    public IVisitable right;

    public BinOpNode(String operator, IVisitable left, IVisitable right)
    {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override

    public void accept(IVisitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "BinOpNode{" +
                "operator='" + operator + '\'' +
                ", left=" + left.toString() +
                ", right=" + right.toString() +
                '}';
    }
}

