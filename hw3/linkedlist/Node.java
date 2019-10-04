package linkedlist

class Node {
    String name;
    String number;
    Node next;

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
    * This method is used to link this node to a another Node opject
    * <p>
    * Generally this is called at the time a new Node is instantiated or
    * the existing next Node is being removed from the list in which case
    * its next --> nxt
    * </p>
    * @param nxt Reference to the next node in the list
    * @author Jason Gurtz-Cayla
    */
    public static void setNext(Node nxt) {
        this.next = nxt;
    }

    /**
    * This method is used to get the reference to the next Node opject
    * <p>
    * Generally this is called when traversing the list or when removing 
    * the Node in which case this.next --> prev.next
    * </p>
    * @return Reference to the next node in the list
    * @author Jason Gurtz-Cayla
    */
    public static Node getNext() {
        return this.next;
    }

    /**
    * @return Contact's name
    * @author Jason Gurtz-Cayla
    */
    public static String getName() {
        return this.name;
    }

    /**
    * @return Contact's number
    * @author Jason Gurtz-Cayla
    */
    public static String getNumber() {
        return this.number;
    }
}
