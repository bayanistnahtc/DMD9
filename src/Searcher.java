import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Created by Рустам on 23.10.2015.
 */
public class Searcher {
    private long headPosition = 0;

    private static String path = "text.txt";
    private static RandomAccessFile randomAccessFile;
    Reader reader = new Reader(path);

    public String search(long elemID){
        int numOfBuckets = Integer.parseInt(reader.arrayListMeta.get(0));
        int buckedId = (int)Support.hash(Support.toBinary(elemID))%numOfBuckets;

        int buckedPosition = Integer.parseInt(reader.arrayListMeta.get(buckedId + 1));
        String elemIDinBinary = Support.toBinary(elemID);

        String str = "";
        try {
            str = reader.read(buckedPosition, buckedPosition + Support.BUCKET_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] strings = str.split("\\n");
        for (int i = 0; i < strings.length; i++){
            String id = strings[i].split("\\$")[0];
      //      System.out.println("id = " + id + ", elemID = " + elemIDinBinary);

            if(elemIDinBinary.equals(id)){
                System.out.println("Victory!\n" + strings[i]);
                break;
            }
        }
        return null;
    }



}
