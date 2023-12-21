import java.util.Calendar;
import java.util.PriorityQueue;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        test();
    }
    public static void test(){
        Node n1 = new Node(10,'a');
        Node n2 = new Node(2,'h');
        Node n3 = new Node(1,'p');
        Node n4 = new Node(3,' ');
        Node n5 = new Node(1,'.');
        Node[] nodesAry = new Node[]{n1,n2,n3,n4,n5};

        //
        PriorityQueue<Node> nodes = new PriorityQueue<>();
        for(Node n : nodesAry){
            nodes.offer(n);
        }

        /* BUILD AND COMPILE */
        HuffmanTree tree = HuffmanTree.build(nodes);
        // tree.prtPreOrd();
        HashMap<Character,String> map = HuffmanTree.compile(tree);
        System.out.println(map);

        /* ENCODE */
        String s = "a a aaaaaaaahph. ";
        String output = HuffmanTree.encode(map,s);
        System.out.println(output);

        /* DECODE */
        HashMap<String,Character> revMap = HuffmanTree.revMap(map);
        String content = HuffmanTree.decodeText(revMap,output);
        System.out.println(content);
    }
}
