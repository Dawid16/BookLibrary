/**
 * Created by Dawidek on 2018-04-12.
 */
public class PersonName {

    private String name;
    private String surname;

    public PersonName(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
}
