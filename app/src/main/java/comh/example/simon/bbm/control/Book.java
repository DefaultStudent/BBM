package comh.example.simon.bbm.control;

/**
 * Created by 96495 on 2017/5/29.
 */

public class Book {
    private String id;
    private String name;
    private String type;
    private String author;
    private String publish;
    private String price;

    public Book(String id,  String name, String type, String author, String publish, String price){
        this.id = id;
        this.name = name;
        this.type = type;
        this.author = author;
        this.publish = publish;
        this.price = price;
    }

    public String getId(){
        return  id;
    }
    public String getName(){
        return name;
    }
    public String getType(){
        return type;
    }
    public String getAuthor(){
        return  author;
    }
    public String getPublish(){
        return publish;
    }
    public String getPrice(){
        return price;
    }
}
