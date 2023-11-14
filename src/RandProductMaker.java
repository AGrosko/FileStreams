import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;


public class RandProductMaker extends JFrame {

    randProductFile prodFile;

    JPanel mainPnl;

        JPanel filePnl;
            JTextField fileFld;
            JButton createBtn;


        JPanel dataPnl;
            JTextField nameFld;
            JTextField idFld;
            JTextField descFld;
            JSpinner priceSpn;
            JTextField countFld;
                int count;
        JPanel controlPnl;
            JButton addBtn;
            JButton quitBtn;
            JButton closeBtn;



    public RandProductMaker(){
        mainPnl = new JPanel(new GridLayout(3,1));

        createFilePnl();
        createdataPnl();
        createControlPnl();

        mainPnl.add(filePnl);
        mainPnl.add(dataPnl);
        mainPnl.add(controlPnl);


        addListeners();



        this.add(mainPnl);
    }

    private void createControlPnl(){

        controlPnl = new JPanel(new GridLayout(1,3));

        addBtn = new JButton("Add Item");
        quitBtn = new JButton("Quit");
        closeBtn = new JButton("Close File");

        controlPnl.add(addBtn,BorderLayout.WEST);
        controlPnl.add(closeBtn,BorderLayout.CENTER);
        controlPnl.add(quitBtn,BorderLayout.EAST);

        closeBtn.setVisible(false);
        addBtn.setVisible(false);



    }

    private void createFilePnl(){

        filePnl = new JPanel(new GridLayout(1,2));

        fileFld = new JTextField();
        fileFld.setBorder(new TitledBorder("File Name"));
        createBtn = new JButton("Create File");

        filePnl.add(fileFld);
        filePnl.add(createBtn);




    }

    private void createdataPnl(){

        dataPnl = new JPanel(new GridLayout(2,3));
        nameFld = new JTextField();
        nameFld.setBorder(new TitledBorder("Product Name"));
        idFld = new JTextField();
        idFld.setBorder(new TitledBorder("Product ID"));
        descFld = new JTextField();
        descFld.setBorder(new TitledBorder("Product Description"));
        priceSpn = new JSpinner(new SpinnerNumberModel(1,0.1,1000,0.01));
        priceSpn.setBorder(new TitledBorder("Product Price"));
        countFld = new JTextField();
        countFld.setBorder(new TitledBorder("Number of Products"));

        dataPnl.add(nameFld);
        dataPnl.add(idFld);
        dataPnl.add(descFld);
        dataPnl.add(priceSpn);
        dataPnl.add(countFld);

    }

    public void addListeners(){

        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!fileFld.getText().isEmpty()){
                    prodFile = new randProductFile();
                    prodFile.newFile(fileFld.getText());

                    closeBtn.setVisible(true);
                    addBtn.setVisible(true);
                    count = 0;
                    countFld.setText(Integer.toString(count));

                }
                else {JOptionPane.showMessageDialog(mainPnl,"Please Enter a File Name");}
            }
        });

        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prodFile.closeFile();
                JOptionPane.showMessageDialog(mainPnl, "File Closed");
            }
        });

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(
                        (!nameFld.getText().isEmpty()) &&
                                (!descFld.getText().isEmpty())&&
                                (!idFld.getText().isEmpty())


                ){
                    prodFile.newProduct(idFld.getText(),nameFld.getText(),descFld.getText(), (Double) priceSpn.getValue());
                    JOptionPane.showMessageDialog(mainPnl,nameFld.getText() + " added to file");
                    prodFile.saveProduct();
                    count++;
                    countFld.setText(Integer.toString(count));
                    idFld.setText("");
                    nameFld.setText("");
                    descFld.setText("");
                    priceSpn.setValue(0);
                }
            }
        });

    }



}
