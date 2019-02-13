package Visitors;

public interface IVisitable {
        void accept(IVisitor visitor);
        String toString();
}
