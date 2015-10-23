import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Рустам on 21.10.2015.
 */
public class Reader {

    public static long headerPosition = 0;
    /**
     * it is file name and location
     */
    private String path;
    private RandomAccessFile randomAccessFile;
    public  ArrayList<String> arrayListMeta;

    public Reader(String path){
        this.path = path;
        arrayListMeta = getMeta();
    }

    /**
     * read by position
     * @param pointer
     * @return
     * @throws IOException
     */
    public String read(long pointer) throws IOException {
        randomAccessFile = new RandomAccessFile(path, "r");
        String res = "";
        //read by byte
        randomAccessFile.seek(pointer);
        int b = randomAccessFile.read();
        while (b != -1){
            res += (char)b;
            b = randomAccessFile.read();
        }
        randomAccessFile.close();
        return res;
    }

    public String read(long pointerStart, long pointerEnd ) throws IOException {
        randomAccessFile = new RandomAccessFile(path, "r");
        String res = "";
        //read by byte
        randomAccessFile.seek(pointerStart);

        int b = randomAccessFile.read();
        while (b != -1 || randomAccessFile.getFilePointer() == pointerEnd){
            res += (char)b;
            b = randomAccessFile.read();
        }
        randomAccessFile.close();
       //System.out.println(res);
        return res;
    }

    /**
     * read from end to this word
     * @param to
     * @return
     * @throws IOException
     */
    private String readFromEnd(String to)throws IOException{
        to = new StringBuilder(to).reverse().toString();
        char[]temp = to.toCharArray();
        int j = 0;
        randomAccessFile = new RandomAccessFile(path, "r");
        String res = "";
        //read by byte
        long fileLength = randomAccessFile.length();

        int b = randomAccessFile.read();
        for(int i = 1; i < fileLength; i++){
            randomAccessFile.seek(fileLength - i);
            b = randomAccessFile.read();
            if(b == temp[j]){
                headerPosition = (fileLength - i);
                j++;
                if(j == temp.length){
                    res += (char)b;
                    break;
                }
            }
            res += (char)b;
        }

        randomAccessFile.close();
        return res;
    }


    /**
     * parse the meta and give an Array, where
     * the first is number of buckets,
     * the second is the first bucket position
     * the third is a position in bucked
     * @return
     */
    public ArrayList<String> getMeta(){
        ArrayList<String> metaData = new ArrayList<>();
        String meta =  "";
        try {
            meta = this.readMeta();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!meta.equals("")){
            char[] header = "HEADER".toCharArray();
            char[] metaArray = meta.toCharArray();
            boolean flag = false;
            int j = 0;
            for (int i = 0; i < header.length; i++) {
                if(header[i] == metaArray[i]){
                    j++;
                    if(j == header.length){
                        flag = true;
                    }
                }
            }


            if(flag){
                String temp = new String(metaArray);
                char[]newMetaArray = temp.substring(header.length+1, temp.length()).toCharArray();
                temp = new String(newMetaArray);
                //System.out.println(temp);
                String[] splitMeta = temp.split("\\$");

                for (int i = 0; i < splitMeta.length; i++) {
                    metaData.add(splitMeta[i]);
                }
            }
        }
    return metaData;
    }

    private String readMeta() throws IOException{
        String res = "";
        res = this.readFromEnd("HEADER");
        res = new StringBuilder(res).reverse().toString();
        return res;
    }

    public String has(String Id) {

        String temp = "";
//        temp = str.substring(0, 19);
        int i  = 0;
        String line = "";
        Scanner in = null;
        try {
            in = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(in.hasNext())
        {
            line = in.nextLine();
            if(line.indexOf(temp) != -1){
               // System.out.println(line.indexOf(temp));
                return line;
            }
        }
        in.close();

        return null;
    }

}
