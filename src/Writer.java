import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Рустам on 21.10.2015.
 */
public class Writer {
    private static long headerPosition = 0;
    Reader reader = new Reader(path);
    private static String path = "text.txt";
    private static RandomAccessFile randomAccessFile;
    private static long headerLength = 0;
//
//
//    public void writeTo(String str) throws IOException {
//        //write to file
//        randomAccessFile = new RandomAccessFile(path, "rw");
//       // if(checkForValid(str) == null){
//            Reader reader = new Reader(path);
//            ArrayList<String> arrayList = reader.getMeta();
//            String tempHeader = reader.read(Reader.headerPosition);
//            randomAccessFile.seek(Reader.headerPosition);
//            randomAccessFile.write(str.getBytes());
//
//            //randomAccessFile.seek(Reader.headerPosition + str.length());
//            tempHeader = newHeader(tempHeader);
//            randomAccessFile.write(tempHeader.getBytes());
//
//            randomAccessFile.close();
//      //  }
//    }


    public void writeTo(String[] record) throws IOException {
        //write to file
        randomAccessFile = new RandomAccessFile(path, "rw");
       // if(checkForValid(str) == null){
        int numOfBuckets = Integer.parseInt(reader.arrayListMeta.get(0));
        int buckedId =
                (int)Support.hash(
                        Support.toBinary(
                                Long.parseLong(record[0])
                        )
                )%numOfBuckets;

        String tempHeader = reader.read(Reader.headerPosition);
        randomAccessFile.seek(Reader.headerPosition);

        String str = record[0] + "$" + record[1] + "$"+ record[2] + "$" + record[2]+"\n";
        randomAccessFile.write(str.getBytes());
//
        //randomAccessFile.seek(Reader.headerPosition + str.length());
            tempHeader = newHeader(tempHeader,
                    randomAccessFile.getFilePointer() + str.length());
            randomAccessFile.write(tempHeader.getBytes());
//
            randomAccessFile.close();
//      //  }
    }

    private String checkForValid(String str) {

        return null;
    }

    //here we should change
    private String newHeader(String tempHeader, long recordPosition) {

        tempHeader = tempHeader + "$" + recordPosition;
        headerLength = tempHeader.length();
        return tempHeader;
    }


    public static String hasSuchId(String str) {

        String temp = "";
//        temp = str.substring(0, 19);
        int i  = 0;
        String line = "";
        Scanner in = null;
        try {
            in = new Scanner(new File("text.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(in.hasNext())
        {
            line = in.nextLine();
            if(line.indexOf(temp) != -1){
                //System.out.println(line.indexOf(temp));
                return line;
            }
        }
        in.close();

        return null;
    }



    public static void changeFromIndex(int index){

        try {
            randomAccessFile = new RandomAccessFile(path, "rw");
            randomAccessFile.seek(index);
            randomAccessFile.write("d".getBytes());
            randomAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
