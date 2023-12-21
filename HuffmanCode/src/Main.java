import java.util.Calendar;
import java.util.PriorityQueue;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Node n1 = new Node(1,'a');
        Node n2 = new Node(10,'b');
        Node n3 = new Node(20,'c');
        Node n4 = new Node(10,'d');
        Node n5 = new Node(21,'e');
        Node[] nodesAry = new Node[]{n1,n2,n3,n4,n5};

        //
        PriorityQueue<Node> nodes = new PriorityQueue<>();
        for(Node n : nodesAry){
            nodes.offer(n);
        }
        //
        HuffmanTree tree = HuffmanTree.build(nodes);
        tree.prtPreOrd();
        HashMap<Character,String> map = HuffmanTree.encode(tree);
        System.out.println(map);
    }

}
