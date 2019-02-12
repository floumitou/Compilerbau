package Visitors;

import java.util.HashSet;
import java.util.Set;

public abstract class SyntaxNode
{
    Visitor visitor = new Visitor();
    public Boolean nullable;
    public final Set<Integer> firstpos = new HashSet<>();
    public final Set<Integer> lastpos = new HashSet<>();
}
