package Visitors;

interface IVisitable {
        void accept(IVisitor visitor);
        String toString();
}
