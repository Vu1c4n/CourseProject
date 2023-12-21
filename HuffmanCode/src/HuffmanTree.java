import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanTree {
    private Node root;
    HuffmanTree(Node n){
        this.root = n;
    };
    HuffmanTree(){
    }
    public void setRoot(Node n){
        this.root = n;
    }
    public void prtPreOrd(){
        prtPreOrdHelper(this.root,0);
    }

    private void prtPreOrdHelper(Node ptr,int depth){
        if(ptr.isLeaf()){
            printIndent(depth);
            System.out.println(ptr.toString());
            return;
        }
        else{
            printIndent(depth);
            System.out.println(ptr);
            prtPreOrdHelper(ptr.getLCN(),depth+1);
            prtPreOrdHelper(ptr.getRCN(),depth+1);
        }
    }
    private void printIndent(int depth){
        String table = "\t";
        table = table.repeat(depth);
        System.out.print(table);
    }
    public static HuffmanTree build(PriorityQueue<Node> nodes){
        // nodes = [{“a”:1},{"b":2},{"c":3}];
        while(nodes.size()>1){
            // 先取出weight最小的2个节点
            Node n1 = nodes.poll();
            Node n2 = nodes.poll();
            assert n1 != null;
            assert n2 != null;
            Node n3 = new Node(n1.getWeight()+n2.getWeight(),n1,n2); // 合并形成新节点，并且设置子节点
            nodes.offer(n3); // 新节点加入回queue中
        }
        Node root = nodes.poll(); // 此时nodes的大小为1，返回的就是最后的root节点
        return new HuffmanTree(root);
    }
    public static HashMap<Character,String> encode(HuffmanTree ht){
        HashMap<Character,String> codeMap = new HashMap<>();
        encodeHelper(ht.root, codeMap, "");
        return codeMap;
    }
    private static void encodeHelper(Node ptr, HashMap<Character,String> codeMap, String s){
        if(ptr.isLeaf()){
            codeMap.put(ptr.getChar(),s);
        }
        else{
            encodeHelper(ptr.getLCN(),codeMap,s+'0');
            encodeHelper(ptr.getRCN(),codeMap,s+'1');
        }
    }
    public static void decode(){

    }
}
