import javax.swing.*;
import java.awt.*;

public class makerViewer {

    public static void main(String[] args) {
    RandProductMaker frame = new RandProductMaker();

        frame.setTitle("Product Maker");
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (dimension.width)/2+ (dimension.width)/4;
    int height  = (dimension.height)/2 + (dimension.height)/4;

    int x = (dimension.width - width) / 2;

    int y = (dimension.height - height) / 2;
        frame.setLocation(x,y);
        frame.setSize(width,height);
        System.out.println(dimension.width);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

}}
