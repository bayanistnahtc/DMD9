import java.io.IOException;

/**
 * Created by Рустам on 18.10.2015.
 */


public class Student {
    private static int idAutoIncrement = 1;
    private long id;
    private String name;
    private String email;
    private String address;
    public long hashID;


    public Student(long id, String name, String email, String address) throws IllegalStateException{
        if(id > 0){
            this.id = id;
        }else {
            throw new IllegalStateException("id must be bigger than zero!");
        }

        this.name = name;
        this.email = email;
        this.address = address;
        idAutoIncrement++;

    }

    public Student(String name, String email, String address) {
        this.id = idAutoIncrement;
        this.name = name;
        this.email = email;
        this.address = address;
        idAutoIncrement++;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void insertToTable(){
        String binID = Support.toBinary(getId());
        hashID = Support.hash(binID);
        String[]record = new String[4];
        record[0] = binID;
        record[1] = name;
        record[2] = email;
        record[3] = address;

        try {
            Writer writer = new Writer();
            writer.writeTo(record);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void deleteFromTable(int id){
        String binID = Support.toBinary(getId());
        Writer writer = new Writer();
       // writer.

//        if(ReaderAndWriter.has(binID) != null){
//
//        }
        return;
    }

}
