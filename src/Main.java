import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            testFunc();
//        testIO();
    }
    public static void testIO(){
        HuffmanTree ht = new HuffmanTree();
        IOManager.writeTree(ht);
    }
    public static void testFunc(){
        String rawText1 = "Central South University Computer Science CJY";
        String rawText2 = "ababababaaaaaaaaaaaaaaaaaaaacdsaknaaaaaaaaaaa";

        /* 从终端输入String */
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        IOManager.writeFile(IOManager.TO_BE_TRAN,s);
        HuffmanTree tree = new HuffmanTree(s); // 创建树
        tree.prtInOrd(); // 打印树

        String ecdData = tree.getEcdData();
        System.out.println("编码结果：" + ecdData); // 打印String的编码结果

        HashMap<String, Character> dcdMap = tree.getDcdMap();
        String rst = HuffmanTree.decode(dcdMap,ecdData);
        System.out.println("译码结果： "+rst);
        System.out.println("压缩率："+HuffmanTree.compRate(ecdData, rst));

        // IO
        IOManager.writeTree(tree);
        HuffmanTree newT = IOManager.readTree();
        assert newT != null;
        System.out.println(newT.getEcdData());
        IOManager.writeFile(IOManager.TEXT_FILE,rst);
        IOManager.writeFile(IOManager.CODE_PRINT,tree.getEcdData());
        IOManager.writeFile(IOManager.CODE_FILE,tree.getEcdData());
    }
}
