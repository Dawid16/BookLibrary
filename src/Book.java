import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawidek on 2018-04-03.
 */

public class Book implements Serializable {

    private String title;
    private PersonName directorName;
    private String type;
    private int issueYear;
    private List<PersonName> actorsNames;
    private Tuple<String, String> directorFullName;

    public Book(BookBuilder builder){
        this.title = builder.title;
        this.directorName = builder.directorName;
        this.type = builder.type;
        this.issueYear = builder.issueYear;
        this.actorsNames = builder.actorsNames;
        this.directorFullName = builder.directorFullName;
    }

    public Book(){
        title = null;
        directorName = null;
        type = null;
        issueYear = 0;
        actorsNames = null;
    }

    public String getTitle(){return title;}

    public PersonName getDirectorName(){return directorName;}

    public String getType(){return type;}

    public int getIssueYear(){return issueYear;}

    public List<PersonName> getActorsNames(){return actorsNames;}

    public String getActors(){
        String actors= "\n";
        for(int i = 0; i<actorsNames.size(); i++){
            actors = actorsNames.get(i).getName() + " " + actorsNames.get(i).getSurname() + "\n" + actors;
        }
        return actors;
    }

    public List<String> getActorFullName(){
        List<String> actors = new ArrayList<>();

        for(int i = 0; i<actorsNames.size(); i++){
            String actorFullName = actorsNames.get(i).getName() + " " + actorsNames.get(i).getSurname();
            actors.add(actorFullName);
        }
        return actors;
    }

    @Override
    public String toString(){
        return  "\nTitle: " + getTitle() +
                "\nDirector: " + directorName.getName() + " " + directorName.getSurname() +
                "\nType: " + getType() +
                "\nIssueYear: " + getIssueYear() +
                "\nActors: " + "\n" + getActors() +
                "\n";
    }

    public static class BookBuilder{
        // required parameters
        private String title;
        private PersonName directorName;
        private String type;
        private int issueYear;
        List<PersonName> actorsNames;
        // optional parameters
        private Tuple<String, String> directorFullName;

        public BookBuilder(String title, PersonName directorName, String type, int issueYear, List<PersonName> actorsNames){
            this.title=title;
            this.directorName=directorName;
            this.type=type;
            this.issueYear=issueYear;
            this.actorsNames=actorsNames;
        }

        public BookBuilder setDirectorFullName(Tuple directorFullName) {
            this.directorFullName = directorFullName;
            return this;
        }

        public Book build(){
            return new Book(this);
        }

    }
















    /*
    public Book(String title, PersonName directorName, String type, int issueYear, List<PersonName> actorsNames,
                Tuple<String, String> directorFullName){
        this.title = title;
        this.directorName = directorName;
        this.type = type;
        this.issueYear = issueYear;
        this.directorFullName = directorFullName;
        this.actorsNames = actorsNames;
    } */



}
