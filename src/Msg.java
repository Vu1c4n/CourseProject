import javax.swing.*;
import java.awt.*;

public class Msg extends JDialog {
    Msg(MainWindow f){
        super(f,"译码结果",true);
        Container c = getContentPane();
        String content = IOManager.readFile(IOManager.TEXT_FILE);
        c.add(new Label("Decode Result:    " + content));
        setBounds(240,240,300,100);
    }
}
