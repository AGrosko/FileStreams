import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class randProductFile {

    Product prod;
    RandomAccessFile randFile;

    int numRecords;

    public int getNumRecords() {
        return numRecords;
    }

    public void newProduct(String ID, String Name, String Description, double Price) {

        prod = new Product(ID, Name, Description, Price);

    }

    public void newFile(String Name) {

        try {
            randFile = new RandomAccessFile(Name, "rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        numRecords = 0;

    }

    public void closeFile() {
        try {

            randFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void openFile(String fileName) throws FileNotFoundException {
        randFile = new RandomAccessFile(fileName, "r");

    }

    public void saveProduct()  {


        try {

            randFile.write(String.format("%35s", prod.getName()).getBytes(StandardCharsets.UTF_8));

            randFile.write(String.format("%75s", prod.getDescription()).getBytes(StandardCharsets.UTF_8));
            randFile.write(String.format("%6s", prod.getID()).getBytes(StandardCharsets.UTF_8));
            randFile.writeDouble(prod.getPrice());

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        numRecords++;
    }

    public Product readProduct() throws EOFException{

        byte[] nameBytes = new byte[35];
        byte[] descBytes = new byte[75];
        byte[] idBytes = new byte[6];

        try {
            randFile.read(nameBytes);

            String tempName =  new String(nameBytes,StandardCharsets.UTF_8);

            randFile.read(descBytes);

            String tempDesc = new String(descBytes,StandardCharsets.UTF_8);

            randFile.read(idBytes);
            String tempID = new String(idBytes, StandardCharsets.UTF_8);

            double tempPrice = randFile.readDouble();
            return new Product(tempID.trim(), tempName.trim(), tempDesc.trim(), tempPrice);


        }
        catch (EOFException e){throw new EOFException();}
        catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public ArrayList<Product> readAllProds(){
        ArrayList<Product> toReturn = new ArrayList<Product>();


        while (true){
            try{
                  toReturn.add(this.readProduct());

            }
            catch (EOFException e){
                break;
            }

        }

        return toReturn;
    }

}





