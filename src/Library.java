import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Dawidek on 2018-04-03.
 */
public class Library implements Serializable{

    private List<Book> allBooks;

    public Library(){
        allBooks = new ArrayList<Book>();
    }

    public void addBook(Book book){
        allBooks.add(book);
    }

    @Override
    public String toString(){
        String total = "\n";

        /*
        for(int i=0; i<allBooks.size(); i++){
            Book b = allBooks.get(i);
            total = total + b.toString();
        }*/

        Iterator<Book> iterator = allBooks.iterator();
        while(iterator.hasNext()) {
            Book b = iterator.next();
            total = total + b.toString();
        }
        return total;
    }

    public void getBooksWithPredicate(Predicate<Book> predicate, String errorMessage) {
        String bookDetails = "";
        Iterator<Book> iterator = allBooks.iterator();
        while(iterator.hasNext()) {
            Book b = iterator.next();
            if(predicate.test(b)){
                bookDetails = b.toString();
            }
        }

        if(bookDetails.isEmpty()){
            System.out.println(errorMessage);
        }
        System.out.println(bookDetails);
    }

    public void getParticularBook(String nameOfBook){
        getBooksWithPredicate(b -> b.getTitle().equalsIgnoreCase(nameOfBook), "");
    }

    public void getBooksDataOnRange(int from, int to){
        getBooksWithPredicate(b -> b.getIssueYear() >= from && b.getIssueYear() <= to, "No books in range of: " + from + "-" + to);
    }

    public void getBooksDataOnType(String type){
        getBooksWithPredicate(b -> b.getType().equalsIgnoreCase(type), "No books of type: " + type);
    }

    public void getBooksDataOnActorName(String actorName){
        getBooksWithPredicate(b -> b.getActorFullName().contains(actorName), "No books with actor:"   + actorName);
    }



    /*
    public void getParticularBook(String nameOfBook){
        String bookDetails = "";
        Iterator<Book> iterator = allBooks.iterator();
        while(iterator.hasNext()) {
            Book b = iterator.next();
            if(b.getTitle().equalsIgnoreCase(nameOfBook)){
                bookDetails = b.toString();
            }
        }
        System.out.println(bookDetails);
    }
    */
/*
    public void getBooksDataOnRange(int from, int to){
        String bookDetails = "";
        Iterator<Book> iterator = allBooks.iterator();
        while(iterator.hasNext()) {
            Book b = iterator.next();
            if(b.getIssueYear() >= from && b.getIssueYear() <= to){
                bookDetails = b.toString();
            }
        }

        if(bookDetails.isEmpty()){
            System.out.println("No books in range of: " + from + "-" + to);
        }
        System.out.println(bookDetails);
    } */

/*
    public void getBooksDataOnType(String type){
        String bookDetails = "";
        Iterator<Book> iterator = allBooks.iterator();
        while(iterator.hasNext()) {
            Book b = iterator.next();
            if(b.getType().equalsIgnoreCase(type)){
                bookDetails = b.toString();
            }
        }

        if(bookDetails.isEmpty()){
            System.out.println("No books of type: " + type);
        }
        System.out.println(bookDetails);
    }
    */
/*
    public void getBooksDataOnActorName(String actorName){
        String bookDetails = "";
        Iterator<Book> iterator = allBooks.iterator();
        while(iterator.hasNext()) {
            Book b = iterator.next();
            if(b.getActorFullName().contains(actorName)){
                bookDetails = b.toString();
            }
        }
        if(bookDetails.isEmpty()){
            System.out.println("No books of type: " + actorName);
        }
        System.out.println(bookDetails);
    }
    */




}
