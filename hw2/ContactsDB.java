import java.io.*;
import java.util.Scanner;

/**
 * Manages a simple contacts database with the following features:
 *  - Save a contact
 *  - Search for a contact
 *  - Print all contacts out to the screen
 *  - Quit
 */
public class ContactsDB {
    static final String CONTACTS_DB = "contacts.txt";

    public static void main(String[] args) throws IOException {
        Scanner userIn = new Scanner(System.in);
        char cmd = ' ';

        while (true) {
            System.out.print("(F)ind Contact\n(S)ave Contact\n(P)rint Contacts\n(Q)uit\n |\n  `--> $ ");
            cmd = userIn.nextLine().charAt(0);

            switch (cmd) {
                case 'F': case 'f':
                    search4Contact( readContactsFile() );
                    break;
                case 'S': case 's':
                    saveContact( appendContactsFile() );
                    break;
                case 'P': case 'p':
                    printContacts( readContactsFile() );
                    break;
                case 'Q': case 'q':
                    System.exit(0);
                    break;

                default:
                    System.out.printf("ERROR:  INVALID OPTION!%n%n");
            }
        }
    }

    /**
     * Prompts user for name and number, then appends each on its own line to contacts.txt
     * @param pw Reference to a PrintWriter object [ typically from appendContactsFile() ]
     */
    private static void saveContact(PrintWriter pw) {
        String name = new String("");
        String number = new String("");

        Scanner userIn = new Scanner(System.in);
        System.out.println("Enter new contact's name: ");
        name = userIn.nextLine();
        System.out.println("Enter new contact's number: ");
        number = userIn.nextLine();

        pw.print(name + "\n" + number + "\n");
        pw.close();
    }

    /**
     * Prompts user for a name, then searches for a contact by name, printing that contact's phone numbear or a not found message.
     * @param s Reference to a Scanner object [ typically from readContactsFile() ]
     */
    private static void search4Contact(Scanner s) {
        String name = new String("");

        Scanner userIn = new Scanner(System.in);
        System.out.println("Enter name to search for: ");
        name = userIn.nextLine();

        while ( s.hasNextLine() ) {

            if ( s.nextLine().compareTo(name) == 0 ) {
                System.out.println( "Number: " + s.nextLine() ); // assumes pairs of lines
                System.out.println("");
                s.close();
                return;
            }
        }
        System.out.println("ERROR:  CONTACT NOT FOUND!");
        s.close();
    }

    /**
     * Prints all stored contacts in a tabular format
     * @param s Reference to a Scanner object [ typically from readContactsFile() ]
     */
    private static void printContacts(Scanner s) {
        if ( s.hasNextLine()) {
            System.out.println("+======================+======================+");
            System.out.println(":.        NAME        .:.       NUMBER       .:");
            System.out.println("+======================+======================+");

            do {
                System.out.printf( "| %-20s ", s.nextLine() );
                System.out.printf( ": %-20s |%n", s.nextLine() ); // assumes data is a pair of lines
                System.out.println("+----------------------+----------------------+");
            }
            while ( s.hasNextLine() );
        }
        else {
            System.out.println("ERROR: NO CONTACTS in DATABASE!");
        }
        System.out.println("");
        s.close();
    }

    /**
     * Utility function for reading from the contacts.txt file, creating file if needed
     * @return Scanner object attached to the contacts.txt file
     */
    private static Scanner readContactsFile() throws IOException {
        File f = new File(CONTACTS_DB);

        if ( ! f.exists() ) {
            f.createNewFile();
        }
        return new Scanner(f);
    }

    /**
     * Utility function for appending to the contacts.txt file
     * @return PrintWriter object attached to the contacts.txt file
     */
    private static PrintWriter appendContactsFile() throws IOException {
        FileWriter fw = new FileWriter(CONTACTS_DB, true);
        return new PrintWriter(fw);
    }
}
