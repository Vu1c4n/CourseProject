import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree implements Serializable {
    /* 类成员 */
    private Node root;
    private HashMap<Character,String> ecdMap;
    private HashMap<String,Character> dcdMap;
    private String ecdData;

    HuffmanTree(){
    }
    /* 构造函数 */
    HuffmanTree(String content){
        assert content != null; // 用assert替代if语句
        PriorityQueue<Node> nodes = createNodes(content);
        while(nodes.size()>1){
            // 先取出weight最小的2个节点
            Node n1 = nodes.poll();
            Node n2 = nodes.poll();
            assert n2 != null;
            Node n3 = new Node(n1.getWeight()+n2.getWeight(),n1,n2); // 合并形成新节点，并且设置子节点
            nodes.offer(n3); // 新节点加入回queue中
        }
        this.root = nodes.poll(); // 此时nodes的大小为1，返回的就是最后的root节点
        compile(this); // 对树进行编译，获得映射表 encodeMap & decodeMap
        this.ecdData = encode(this,content); // 获得编码后的结果
    }

    /* 公共接口 */
    public static double compRate(String comp, String raw){
        return 100-(comp.length() / (raw.length()*14.0))*100;
    }
    public HashMap<Character, String> getEcdMap(){
        return this.ecdMap;
    }
    public HashMap<String,Character> getDcdMap(){
        return this.dcdMap;
    }
    public String getEcdData(){
        return this.ecdData;
    }
    public void prtPreOrd(){
        prtPreOrdHelper(this.root,0);
    }
    public void prtInOrd(){
        prtInOrdHelper(this.root,0);
    }

    /**
     * 由于huffmanCode压缩，压缩后生成的文件为“映射表+编码内容”，
     * 因此解码过程应该是不依赖于树实体的，只需要分别传入“映射表”和“编码内容”即可
     * 所以设置为static方法，"不"向decode()方法内传入HuffmanTree
     * @param dcdMap 用于解码的HashMap
     * @param codeText 需要解码的“编码内容”
     * @return 解码后的”文本原文“
     */
    public static String decode(HashMap<String,Character> dcdMap, String codeText){
        StringBuilder rtnContent = new StringBuilder();
        int endIdxPtr = 1;
        int headIdxPtr = 0;
        while(endIdxPtr <= codeText.length()){
            String codeSeg = codeText.substring(headIdxPtr,endIdxPtr);
            if(dcdMap.containsKey(codeSeg)) {
                rtnContent.append(dcdMap.get(codeSeg));
                headIdxPtr = endIdxPtr;
                endIdxPtr = headIdxPtr+1;
            }else{
                endIdxPtr += 1;
            }
        }
        return rtnContent.toString();
    }

    /* 辅助方法 */
    private static PriorityQueue<Node> createNodes(String content){
        PriorityQueue<Node> nodeQ = new PriorityQueue<>();
        HashMap<Character,Integer> nodeMap = new HashMap<>();
        for(int i = 0;i<content.length();i+=1){
            char ch = content.charAt(i);
            if(!nodeMap.containsKey(ch)){
                nodeMap.put(ch,1);
            }else{
                int old= nodeMap.get(ch);
                nodeMap.put(ch,old+1);
            }
        }
        for(Map.Entry<Character,Integer> e : nodeMap.entrySet()){
            Node n = new Node(e.getValue(),e.getKey());
            nodeQ.offer(n);
        }
        return nodeQ;
    }
    private static void compile(HuffmanTree tree){
        HashMap<Character,String> codeMap = new HashMap<>();
        compileHelper(tree.root, codeMap, "");
        tree.ecdMap = codeMap;
        tree.dcdMap = HuffmanTree.revMap(tree.ecdMap);
    }
    private static void compileHelper(Node ptr, HashMap<Character,String> codeMap, String s){
        if(ptr.isLeaf()){
            codeMap.put(ptr.getChar(),s);
        }
        else{
            compileHelper(ptr.getLCN(),codeMap,s+'0');
            compileHelper(ptr.getRCN(),codeMap,s+'1');
        }
    }
    private static String encode(HuffmanTree tree, String content){
        StringBuilder rtnCode = new StringBuilder();
        for(int i = 0;i<content.length();i+=1){
            Character ch = content.charAt(i);
            String code = tree.ecdMap.get(ch);
            rtnCode.append(code);
        }
        return rtnCode.toString();
    }
    private static HashMap<String,Character> revMap(HashMap<Character,String> map){
        HashMap<String,Character> revMap = new HashMap<>();
        for(Map.Entry<Character,String> e : map.entrySet()){
            revMap.put(e.getValue(),e.getKey());
        }
        return revMap;
    }
    private void prtPreOrdHelper(Node ptr,int depth){
        if(ptr.isLeaf()){
            printIndent(depth);
            System.out.println(ptr);
        } else{
            printIndent(depth);
            System.out.println(ptr);
            prtPreOrdHelper(ptr.getLCN(),depth+1);
            prtPreOrdHelper(ptr.getRCN(),depth+1);
        }
    }
    public void prtInOrdHelper(Node ptr, int depth){
        if(ptr.isLeaf()){
            printIndent(depth);
            System.out.println(ptr);
        } else{
            prtInOrdHelper(ptr.getLCN(), depth+1);
            printIndent(depth+1);
            System.out.println("/");
            printIndent(depth);
            System.out.println(ptr);
            printIndent(depth+1);
            System.out.println("\\");
            prtInOrdHelper(ptr.getRCN(), depth+1);
        }
    }
    private void printIndent(int depth){
        String table = "\t\t";
        table = table.repeat(depth);
        System.out.print(table);
    }
}


