package eg.edu.alexu.csd.datastructure.mailServer;

public class LinkedBasedQ implements IQueue{
	public class Node{
        private Object element;
        private Node next;

        public Node(Object obj,Node n){
            element=obj;
            next=n;
        }

        public Node() {}

        public Object getElement() {
            return element;
        }

        public void setElement(Object obj) {
            element = obj;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node newNext) {
            next = newNext;
        }
    }
    private Node head;
    private Node rear;
    private int size;



    public void show(){
        Node node=head;
        for(int i=0;i<size;i++){
            System.out.println(node.getElement());
            node=node.getNext();
        }
    }


    @Override
    public void enqueue(Object item) {
        Node node=new Node();
        node.setElement(item);
        if(size==0){
            head=node;
        }else {
            rear.setNext(node);
        }
        rear=node;
        size++;
    }

    @Override
    public Object dequeue() {
        if(isEmpty())
            throw new RuntimeException("Empty Queue");

        Object temp=head.getElement();
        head=head.getNext();
        size--;
        if(isEmpty())
            rear=null;
        return temp;

    }

    @Override
    public boolean isEmpty() {
        if(size==0)
            return true;

        return false;
    }

    @Override
    public int size() {
        return size;
    }

}