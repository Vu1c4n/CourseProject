import java.io.*;

public class IOManager {
    public static final String CODE_FILE = "files/CodeFile";
    public static final String CODE_PRINT = "files/CodePrint";
    public static final String HFM_TREE = "files/hfmTree";
    public static final String TO_BE_TRAN = "files/ToBeTran";
    public static final String TEXT_FILE = "files/TextFile";

    public static void writeTree(HuffmanTree t){
        try{
            ObjectOutputStream opt = new ObjectOutputStream(new FileOutputStream(HFM_TREE));
            opt.writeObject(t);
            opt.close();
            System.out.println("hfmTree写入成功");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public static HuffmanTree readTree(){
        try{
            ObjectInputStream ipt = new ObjectInputStream(new FileInputStream(HFM_TREE));
            HuffmanTree tree = (HuffmanTree) ipt.readObject();
            ipt.close();
            return tree;
        } catch(Exception e){
            return null;
        }
    }

    public static void writeFile(String path,String content){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(content);
            writer.close();
            System.out.println("文件写入成功");
        } catch(Exception e){
            return;
        }
    }

}
