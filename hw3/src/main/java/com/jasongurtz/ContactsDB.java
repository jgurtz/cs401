package com.jasongurtz;

import java.io.*;
import java.util.Scanner;

/**
 * A simple contacts database
 * <p>
 * This program supports the following features:
 *  - Save a contact to the database
 *  - Search for a contact and print their phone number
 *  - Print all contacts in the database
 *  - Delete a contact
 *  - Quit
 * </p>
 *
 * {@link LinkedList}
 * {@link Node}
 *
 * @author Jason Gurtz-Cayls
 */
public class ContactsDB {
    static final String CONTACTS_DB = "contacts.txt";

    public static void main(String[] args) throws IOException {
        Scanner userIn = new Scanner(System.in);
        char cmd = ' ';

        LinkedList contacts = new LinkedList();
        loadContacts(contacts, readContactsFile());

        while (true) {
            System.out.print("(D)elete Contact\n(F)ind Contact\n(S)ave Contact\n(P)rint Contacts\n(Q)uit\n |\n  `--> $ ");
            cmd = userIn.nextLine().charAt(0);

            switch (cmd) {
                case 'D': case 'd':
                    deleteContact(contacts);
                    break;
                case 'F': case 'f':
                    search4Contact(contacts);
                    break;
                case 'P': case 'p':
                    contacts.printAll();
                    break;
                case 'Q': case 'q':
                    saveContactDB( contacts, writeContactsFile() );
                    System.exit(0);
                    break;
                case 'S': case 's':
                    saveContact(contacts);
                    break;

                default:
                    System.out.printf("ERROR:  INVALID OPTION!%n%n");
            }
        }
    }

    /**
     * Deletes a contact from linked list
     * <p>
     * Prompts the user to enter a name and then searches for that name in the linked list. Deletes the contact if found.
     * </p>
     * @param cLst Reference to a contacts LinkedList object
     * @author Jason Gurtz-Cayla
     */
    private static void deleteContact(LinkedList cLst) {
        String name = new String("");

        Scanner userIn = new Scanner(System.in);
        System.out.println("Enter name of contact to delete: ");
        name = userIn.nextLine();

        Node contact = cLst.findByName(name);

        if ( contact != null ) {
            System.out.println("\nDeleting this contact:\n");
            System.out.println( "Name: " + contact.getName() );
            System.out.println( "Number: " + contact.getNumber() );
            System.out.println("");

            cLst.removeNode(contact);
        }
        else {
            System.out.println("ERROR:  CONTACT NOT FOUND!\n");
        }
    }
    
    /**
     * Saves contacts from linked list, overwriting contacts.txt
     * @param cLst Reference to a contacts LinkedList object
     * @param pw PrintWriter object
     * @author Jason Gurtz-Cayla
     */
    private static void saveContactDB(LinkedList cLst, PrintWriter pw) {
        if (cLst.getFirst() != null) {
            Node cur = cLst.getFirst();
            do {
                pw.print( cur.getName() + '\n' + cur.getNumber() + '\n' );
                cur = cur.getNext();
            }
            while ( cur != null );
        }
        pw.close();
    }

    /**
     * Loads contacts from file into the linked list
     * @param cLst Reference to a contacts LinkedList object
     * @param s Scanner object
     * @author Jason Gurtz-Cayla
     */
    private static void loadContacts(LinkedList cLst, Scanner s) {
        String name = new String("");
        String number = new String("");

        if ( s.hasNextLine() ) {

            do {
                name = s.nextLine();
                number = ( s.hasNextLine() ) ? s.nextLine() : "";
                cLst.appendNode(name, number);
            }
            while ( s.hasNextLine() );
        }
        s.close();
    }

    /**
     * Prompts user for name and number, then adds the data as a new node on end of the linked list
     * @param cLst Reference to a contacts LinkedList object
     * @author Jason Gurtz-Cayla
     */
    private static void saveContact(LinkedList cLst) {
        String name = new String("");
        String number = new String("");

        Scanner userIn = new Scanner(System.in);
        System.out.println("Enter new contact's name: ");
        name = userIn.nextLine();
        System.out.println("Enter new contact's number: ");
        number = userIn.nextLine();

        cLst.appendNode(name, number);
    }

    /**
     * Prompts user for a name, then searches for a contact by name, printing that contact's phone numbear or a not found message.
     * @param cLst Reference to a contacts LinkedList object
     */
    private static void search4Contact(LinkedList cLst) {
        String name = new String("");

        Scanner userIn = new Scanner(System.in);
        System.out.println("Enter name to search for: ");
        name = userIn.nextLine();

        Node contact = cLst.findByName(name);

        if ( contact != null ) {
            System.out.println( "Number: " + contact.getNumber() ); // assumes pairs of lines
            System.out.println("");
        }
        else {
            System.out.println("ERROR:  CONTACT NOT FOUND!\n");
        }
    }

    /**
     * Utility function for reading from the contacts.txt file, creating file if needed
     * @throws java.io.IOException Some issue with reading from contacts.txt file
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
     * Utility function for writing out (overwriting) the contacts.txt file
     * @throws java.io.IOException Some issue with writing to contacts.txt file
     * @return PrintWriter object attached to the contacts.txt file
     */
    private static PrintWriter writeContactsFile() throws IOException {
        FileWriter fw = new FileWriter(CONTACTS_DB, false);
        return new PrintWriter(fw);
    }
}

/**
 * This is an implementation of a doubly-linked list
 * <p>
 * Getters for the first and last Nodes are provided
 * as well as some specific methods for a Contacts DB
 * </p>
 *
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
    private void setLast(Node nod) {
        this.last = nod;
    }

    /**
     * This method removes a contact noda from the list
     * @param nod Reference to the node to be removed
     * @author Jason Gurtz-Cayla
     */
    public void removeNode(Node nod) {
        Node prevNode = nod.getPrev();
        Node nextNode = nod.getNext();

        if (nod.getPrev() == null) {
            //first in the list...
            this.setFirst(nextNode);

            if ( nextNode != null) {
                nextNode.setPrev(null);
            }
        }

        if (nod.getNext() == null) {
            //last in the list...
            this.setLast(prevNode);

            if (prevNode != null) {
                prevNode.setNext(null);
            }
        }

        if (nod.getPrev() != null && nod.getNext() != null) {
            //middle of list...
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);
        }
        nod.setPrev(null);
        nod.setNext(null);
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
            Node currLast = this.getLast();
            currLast.setNext(newNode); // link back
            newNode.setPrev(currLast); // link forward
        }
        this.setLast(newNode);
    }

    /**
     * This method appends a new contact Node to the end of the list
     * <p>
     * The arguments are used to instantiate a new Node object which
     * is then linked to the current last Node object in the list
     * </p>
     * @param nam Contact's name
     * @return Reference to the found node
     * @author Jason Gurtz-Cayla
     */
    public Node findByName(String nam) {
        if (this.getFirst() == null) {
            return null;
        }
        else {
            Node cur = this.getFirst();
            do {
                if (0 == nam.compareToIgnoreCase( cur.getName() )) {
                    return cur;
                }
                else {
                    cur = cur.getNext();
                }
            }
            while ( cur != null );
        }
        // nothing found
        return null;
    }

    /**
     * Prints all nodes in a tabular format
     * @author Jason Gurtz-Cayla
     */
    public void printAll() {
        if (this.getFirst() == null) {
            System.out.println("ERROR: NO CONTACTS in DATABASE!");
        }
        else {
            System.out.println("+======================+======================+");
            System.out.println(":.        NAME        .:.       NUMBER       .:");
            System.out.println("+======================+======================+");

            Node cur = this.getFirst();
            do {
                System.out.printf( "| %-20s ", cur.getName() );
                System.out.printf( ": %-20s |%n", cur.getNumber() );
                System.out.println("+----------------------+----------------------+");
                cur = cur.getNext();
            }
            while ( cur != null );
        }
        System.out.println("");
    }
}

/**
 * This is a node for storing the data of one contact in a doubly-linked list
 *
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
    public Node(String nam, String num) {
        this.name = nam;
        this.number = num;
        this.prev = null;
        this.next = null;
    }

    /**
    * This method is used to link this node to the previous Node opject
    * <p>
    * Generally this is called at the time a new Node is instantiated or
    * the existing previous Node is being removed from the list in which case
    * its prev gets assigned to prv in this method call
    * </p>
    * @param prv Reference to the previous node in the list
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
    public Node getPrev() {
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
    * Getter for the contact's name
    * @return Contact's name
    * @author Jason Gurtz-Cayla
    */
    public String getName() {
        return this.name;
    }

    /**
    * Getter for the contact's number
    * @return Contact's number
    * @author Jason Gurtz-Cayla
    */
    public String getNumber() {
        return this.number;
    }
}
