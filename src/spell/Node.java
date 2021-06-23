package spell;

public class Node implements INode{

    Node[] children = new Node[26];
    int count = 0;


    @Override
    public int getValue() {
        return count;
    }

    @Override
    public void incrementValue() {
        count += 1;
    }

    @Override
    public INode[] getChildren() {
        return children;
    }

}
