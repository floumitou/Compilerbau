package Visitors;

public class OperandNode extends SyntaxNode implements IVisitable
{
    public int position;
    public String symbol;

    public OperandNode(String symbol)
    {
        position = -1; //  noch nicht initialisiert
        this.symbol = symbol;
    }

    @Override
    public void accept(IVisitor visitor)
    {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "OperandNode{" +
                "position=" + position +
                ", symbol='" + symbol + '\'' +
                '}';
    }

}

