import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    JPanel root;
    JLabel userNameLabel,passWordLabel;
    JTextField userTextField,passWordTextField;
    JButton enterButton,closeButton;

    MainWindow() {
        root = new JPanel();      //定义面板容器
        setContentPane(root);
        setLayout(null);         //设置面板为绝对布局

        // 模块1-输入字符串编码

        // 模块2-输入权值编码


        //用户名标签
        userNameLabel = new JLabel("文本信息");
        userNameLabel.setBounds(52, 33, 54, 15);
        root.add(userNameLabel);

        //用户名文本框
        userTextField = new JTextField(12);
        userTextField.setBounds(116, 30, 139, 21);
        root.add(userTextField);



        // 登录按钮
        enterButton = new JButton("编码");          //定义按钮对象
        enterButton.setBounds(64, 116, 69, 23);
        root.add(enterButton);

        // 编码按钮
        closeButton = new JButton("译码");
        closeButton.setBounds(174, 116, 69, 23);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取文本
                String s = MainWindow.this.userTextField.getText();
                if(s.equals("")){ return; }
                System.out.println("输入信息：" + s);

                IOManager.writeFile(IOManager.TO_BE_TRAN,s); // 写入文件

                HuffmanTree tree = new HuffmanTree(s); // 创建树
                IOManager.writeTree(tree); // 写入树
                IOManager.writeFile(IOManager.CODE_PRINT,tree.getEcdData()); // 写入编码文件

                String codePrint = IOManager.readFile(IOManager.CODE_PRINT);
                assert codePrint != null;
                String output = HuffmanTree.decode(tree.getDcdMap(),codePrint);
                System.out.println(output);

                IOManager.writeFile(IOManager.TEXT_FILE,output);

                new Msg(MainWindow.this).setVisible(true);
            }
        });
        root.add(closeButton);

        //设置窗口风格
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 300, 340, 256);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
