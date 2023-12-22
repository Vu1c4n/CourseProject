import java.io.Serializable;

public class Node implements Comparable<Node>, Serializable {
    private final int weight;
    private char ch;
    private Node lcn = null;
    private Node rcn = null;
    Node(int w,char c){
        this.weight = w;
        this.ch = c;
    }
    Node(int w, Node l, Node r){
        this.weight = w;
        this.lcn = l;
        this.rcn = r;
    }

    public int getWeight(){
        return this.weight;
    }
    public char getChar(){
        return this.ch;
    }
    public Node getLCN(){
        return this.lcn;
    }
    public Node getRCN(){
        return this.rcn;
    }
    public void setLCN(Node n){
        this.lcn = n;
    }
    public void setRCN(Node n){
        this.rcn = n;
    }
    @Override
    public String toString(){
        return ("{" + this.ch + ":" + this.weight + "}");
    }
    @Override
    public int compareTo(Node n){
        return (this.weight - n.weight);
    }
    public boolean isLeaf(){
        return (this.lcn == null && this.rcn == null);
    }
}
