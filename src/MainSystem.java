import java.io.*;
import java.util.*;

/**
 * Created by Dawidek on 2018-04-03.
 */
public class MainSystem {

    static String fileName = null;
    static Library library = new Library();
    static Scanner scanner;
    static boolean running = true;

    public static void main (String[] args){

        while (running) {

                System.out.println("\nEnter 0 for loading the Library." +
                        "\nEnter 1 for save and quit" +
                        "\nEnter 2 for list all the Books in the Library." +
                        "\nEnter 3 for add Book to the Library." +
                        "\nEnter 4 for checking the information of the Book with the Book name" +
                        "\nEnter 5 for searching the Books with date range" +
                        "\nEnter 6 for searching the Books with the type of the Book" +
                        "\nEnter 7 for searching the Books with the actor of the Book"
                );

            try{
                scanner = new Scanner(System.in);
                int answer = scanner.nextInt();
                scanner.nextLine();

                switch (answer) {
                    case 0:
                        System.out.println("Enter the filename to load: ");
                        loadScript(scanner.next());
                        break;
                    case 1:
                        saveAndQuit();
                        break;
                    case 2:
                        System.out.println(library.toString());
                        break;
                    case 3:
                        addBook();
                        break;
                    case 4:
                        getBookInfo();
                        break;
                    case 5:
                        getBooksWithDateRange();
                        break;
                    case 6:
                        getBooksWithType();
                        break;
                    case 7:
                        getBooksWithActorName();
                        break;
                }
            } catch (InputMismatchException ime){
                System.out.println("You have to enter only 1-7");
                ime.printStackTrace();
            }
        }
        System.exit(0);
    }


    public static void addBook() {
        String title;
        PersonName directorName = new PersonName("", "");
        String personNameAndSurname = "";
        String actorFullName = "";
        String type;
        int issueYear;
        List<PersonName> actorsNames = new ArrayList<>();
        Tuple<String, String> directorTupleFullName = new Tuple<>("","");

        System.out.println("\nEnter title: ");
        title = scanner.nextLine();
        while(!title.matches("[A-Za-z0-9 -]{1,20}")){
            System.out.println("\nEnter title with max 20 chars and only letters and numbers: ");
            title = scanner.nextLine();
        }

        int wordsOfDirector = -1;
        int timesOfTypeDirector = 0;
        while(wordsOfDirector!=2) {
            if(timesOfTypeDirector==0){
                System.out.println("\nEnter director Name and Surname: ");
                personNameAndSurname = scanner.nextLine();
                wordsOfDirector = HelperLibrary.countWordsUsingSplit(personNameAndSurname);
                timesOfTypeDirector++;
            }
            else{
                System.out.println("\nEnter ONLY Name and Surname: ");
                personNameAndSurname = scanner.nextLine();
                wordsOfDirector = HelperLibrary.countWordsUsingSplit(personNameAndSurname);
            }
        }

        String[] parts = personNameAndSurname.split("\\s+");
        directorName.setName(parts[0]);
        directorName.setSurname(parts[1]);

        List<String> possibleTypes = Arrays.asList("horror", "commedy", "drama");

        System.out.println("\nEnter type: ");
        type = scanner.nextLine();
        while(!possibleTypes.contains(type)) {
            System.out.println("Enter only the type like: " + HelperLibrary.listToString(possibleTypes));
            type = scanner.nextLine();
        }


        System.out.println("\nEnter issue Year: ");
        int nowYear = Calendar.getInstance().get(Calendar.YEAR);
        int downRangeYear = nowYear - 100;

        issueYear = scanner.nextInt();
        while(issueYear<downRangeYear || issueYear>nowYear){
            System.out.println("Enter valid year of range " + downRangeYear + "-" + nowYear);
            issueYear = scanner.nextInt();
        }

        int actorsNumber = -1;
        int timesOfTypeActorNumber = 0;
        while(actorsNumber < 0 || actorsNumber > 5) {
            if (timesOfTypeActorNumber == 0){
                System.out.println("\nEnter actors number(min.0 - max.5): ");
                actorsNumber = scanner.nextInt();
                scanner.nextLine();
                timesOfTypeActorNumber ++;
            }
            else {
                System.out.println("\nYou entered invalid amount of actors - it has to be in range 0-5. Enter actors number: ");
                actorsNumber = scanner.nextInt();
                scanner.nextLine();
            }

        }

        for (int i = 1; i <= actorsNumber; i++) {
            int wordsOfActorFullName = -1;
            int timesOfTypeActorFullName = 0;
            while (wordsOfActorFullName != 2) {
                    if (timesOfTypeActorFullName == 0) {
                        System.out.println("\nEnter name and surname of the " + i + " actor: ");
                        actorFullName = scanner.nextLine();
                        wordsOfActorFullName = HelperLibrary.countWordsUsingSplit(actorFullName);
                        timesOfTypeActorFullName++;

                    } else {
                        System.out.println("\nEnter ONLY name and surname of the " + i + " actor: ");
                        actorFullName = scanner.nextLine();
                        wordsOfActorFullName = HelperLibrary.countWordsUsingSplit(actorFullName);
                    }
                }
            String[] parts2 = actorFullName.split("\\s+");
            String actName = parts2[0];
            String actSurname = parts2[1];

            PersonName personName = new PersonName(actName,actSurname);
            actorsNames.add(personName);
        }

        Book book = new Book.BookBuilder(title, directorName, type, issueYear, actorsNames).build();
        library.addBook(book);

    }

    private static void saveAndQuit() {
        System.out.println("Enter the filename: ");
        fileName = scanner.next() + ".ser";
        running = false;

        FileOutputStream fos = null;
        ObjectOutputStream ous = null;

        try {
            fos = new FileOutputStream(fileName);
            ous = new ObjectOutputStream(fos);
            ous.writeObject(library);
            fos.close();
            ous.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadScript(String name) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        File file = new File(name + ".ser");
        if(file.exists()) {
            try {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                library = (Library) ois.readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The file does not exists!");
        }
    }

    private static void getBookInfo() {
        System.out.println("Type the name of book you are looking for: ");
        String answer = scanner.nextLine();
        library.getParticularBook(answer);
    }

    private static void getBooksWithDateRange() {
        System.out.println("Type the date year range:");
        System.out.println("From: ");
        int from = scanner.nextInt();
        System.out.println("To: ");
        int to = scanner.nextInt();
        library.getBooksDataOnRange(from, to);
    }

    private static void getBooksWithType() {
        System.out.println("Type the type of the books you are looking for: ");
        String type = scanner.nextLine();
        library.getBooksDataOnType(type);
    }

    private static void getBooksWithActorName() {
        System.out.println("Type the actor of the books you are looking for: ");
        String actor = scanner.nextLine();
        library.getBooksDataOnActorName(actor);
    }

}
