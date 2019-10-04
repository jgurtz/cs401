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

/**
* This is an implementation of a circular singley-linked list
* <p>
* Getters for the first and last Nodes are provided
* as well as some specific methods for a Contacts DB
* </p>
* @author Jason Gurtz-Cayla
*/
class LinkedList {
    private Node first;
    private Node last;

    /**
    * This method is used to get the reference to the first Node opject
    * <p>
    * As a singley-linked list, this will be the entry point for searches
    * and ad-hoc inserting of new Nodes
    * </p>
    * @return Reference to the first node in the list
    * @author Jason Gurtz-Cayla
    */
    public Node getFirst() {
        return this.first;
    }

    /**
    * This method is used to set the reference to the first Node opject in the list
    * @param nod Reference to the node to be first in the list
    * @author Jason Gurtz-Cayla
    */
    public void setFirst(Node nod) {
        this.first = nod;
    }

    /**
    * This method is used to get the reference to the last Node opject
    * <p>
    * As a singley-linked list, this will be the entry point for
    * appending new Nodes
    * </p>
    * @return Reference to the last node in the list
    * @author Jason Gurtz-Cayla
    */
    public Node getLast() {
        return this.last;
    }

    /**
    * This method is used to set the reference to the last Node opject
    * <p>
    * This will be used primarily when appending a new Node to the list
    * </p>
    * @param nod Reference to the node to be at the end the list
    * @author Jason Gurtz-Cayla
    */
    private void getLast(Node nod) {
        this.last = nod;
    }

    /**
    * This method removes a contact noda from the liste
    * @param nod Reference to the node to be removed
    * @author Jason Gurtz-Cayla
    */
    public Node removeNode(Node nod) {
    }

    /**
    * This method appends a new contact Node to the end of the list
    * <p>
    * The arguments are used to instantiate a new Node object which
    * is then linked to the current last Node object in the list
    * </p>
    * @param nam Contact's name
    * @param num Contacts telephone number
    * @author Jason Gurtz-Cayla
    */
    public void appendNode(String nam, String num) {
        Node newNode = new Node(nam, num);

        if (this.getFirst() == null) {
            this.setFirst(newNode);
        }
        else {
            Node currLast = this.getLast()
            currLast.setNext(newNode);
        }
        this.setLast(newNode);
        newNode.setNext( this.getFirst() );
    }

    public void printList(void) {
    }
}

/**
* This is a node for storing the data of one contact in a doubly-linked list
* @author Jason Gurtz-Cayla
*/
class Node {
    private String name;
    private String number;
    private Node prev;
    private Node next;

    /**
    * @param nam Contact's name
    * @param num Contacts telephone number
    * @author Jason Gurtz-Cayla
    */
    public Node(String nam, String rnNum) {
        this.name = nam;
        this.number = num;
    }

    /**
    * This method is used to link this node to the previous Node opject
    * <p>
    * Generally this is called at the time a new Node is instantiated or
    * the existing next Node is being removed from the list in which case
    * its next get assigned to nxt in this method call
    * </p>
    * @param nxt Reference to the next node in the list
    * @author Jason Gurtz-Cayla
    */
    public void setPrev(Node prv) {
        this.prev = prv;
    }

    /**
    * This method is used to link this node to the next Node opject
    * <p>
    * Generally this is called at the time a new Node is instantiated or
    * the existing next Node is being removed from the list in which case
    * its next get assigned to nxt in this method call
    * </p>
    * @param nxt Reference to the next node in the list
    * @author Jason Gurtz-Cayla
    */
    public void setNext(Node nxt) {
        this.next = nxt;
    }

    /**
    * This method is used to get the reference to the previous Node opject
    * <p>
    * Generally this is called when traversing the list or when removing 
    * the Node in which case this.next gets assign to prev.next
    * </p>
    * @return Reference to the previous node in the list
    * @author Jason Gurtz-Cayla
    */
    public Node getNext() {
        return this.prev;
    }

    /**
    * This method is used to get the reference to the next Node opject
    * <p>
    * Generally this is called when traversing the list or when removing 
    * the Node in which case this.next gets assign to prev.next
    * </p>
    * @return Reference to the next node in the list
    * @author Jason Gurtz-Cayla
    */
    public Node getNext() {
        return this.next;
    }

    /**
    * @return Contact's name
    * @author Jason Gurtz-Cayla
    */
    public String getName() {
        return this.name;
    }

    /**
    * @return Contact's number
    * @author Jason Gurtz-Cayla
    */
    public String getNumber() {
        return this.number;
    }
}
