package Visitors;

public class OperandNode extends SyntaxNode implements IVisitable
{
    public int position;
    public String symbol;
    public OperandNode(String symbol)
    {
        position = -1; // bedeutet: noch nicht initialisiert
        this.symbol = symbol;
    }
    @Override
    public void accept(Visitor vistor)
    {
        visitor.visit(this);
    }
}

