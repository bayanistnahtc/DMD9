/**
 * Created by Рустам on 23.10.2015.
 */
public class Support {
    public static int currentBucket = 0;
    public static final int BUCKET_SIZE = 2048;


    public static long hash(String str)
    {
        long hash = 2_139_062_143;
        char[] tempStr = str.toCharArray();
        for(int i = 0; i < tempStr.length; i++){
            hash = 37 * hash + tempStr[i];
        }
        return Math.abs(hash);

    }



    public static String toBinary(long id){
        String binID = ""+id;
        binID = ""+ Support.hash(binID);
        binID = Long.toString(id, 2);
//        for (int i = 0; i < 33; i++){
//            if(binID.length() < 33){
//                binID = "0" + binID;
//            }
//        }
     //   System.out.println(binID);
        return binID;
    }
}
