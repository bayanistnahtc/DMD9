import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Рустам on 21.10.2015.
 */
public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader("text.txt");
       // reader.ParsMeta();
       //System.out.println(Reader.headerPosition);
        Writer writer = new Writer();
        long temp = 4;
        Student student = new Student(64, "Rinat", "en@smail.com", "Mayasmi");
       // System.out.println(Support.hash(Support.toBinary(temp))%4);

//        student.insertToTable();
        Searcher searcher = new Searcher();
        searcher.search(64);


    }
}
