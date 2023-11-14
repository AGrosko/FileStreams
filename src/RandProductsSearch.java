import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class RandProductsSearch extends JFrame {

    randProductFile prodFile;
    ArrayList<Product> prodList;
    JPanel mainPnl;
        JPanel filePnl;
            JFileChooser chooser;
            JButton openBtn;

        JPanel searchPnl;
            JTextField searchTF;
            JButton searchBtn;
        JPanel dispPnl;
            JTextArea dispTA;



    RandProductsSearch(){
        mainPnl = new JPanel(new BorderLayout());
        this.add(mainPnl);

        createFilePnl();
        mainPnl.add(filePnl,BorderLayout.NORTH);
        createSearchPnl();
        mainPnl.add(searchPnl,BorderLayout.SOUTH);
        createDispPnl();
        mainPnl.add(dispPnl,BorderLayout.CENTER);

    }
    public void createDispPnl(){
       dispPnl = new JPanel();
       dispTA = new JTextArea(30,30);
       dispPnl.add(dispTA);
       dispTA.setBorder(new TitledBorder("Display"));
    }
    public void createSearchPnl(){
        searchPnl = new JPanel();
        searchBtn = new JButton("Search");
        searchTF = new JTextField(30);
        searchPnl.add(searchTF);
        searchPnl.add(searchBtn);
        searchTF.setBorder(new TitledBorder("Search Term"));

        searchBtn.setVisible(false);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                for(Product entry : prodList){

                    if(entry.getName().contains(searchTF.getText())){

                        dispTA.append(entry.toCSVDataRecord() + "\n");
                    }
                }

                }

        });
    }

    public void createFilePnl(){
        File workingDirectory = new File(System.getProperty("user.dir"));
        filePnl = new JPanel();
        chooser = new JFileChooser();
        openBtn = new JButton("Open File");
        chooser.setCurrentDirectory(workingDirectory);

        filePnl.add(chooser);
        filePnl.add(openBtn);

        openBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prodFile = new randProductFile();
                try {
                    prodFile.openFile(chooser.getSelectedFile().getName());
                    prodList = prodFile.readAllProds();

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                searchBtn.setVisible(true);


            }
        });
    }

}
