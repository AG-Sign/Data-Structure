package eg.edu.alexu.csd.datastructure.mailServer;

public class PriorityQ implements IPriorityQueue {
	public class Node2{
        private Object element;
        private int access;
        private Node2 next;

        public Node2(Object obj, Node2 n){
            element=obj;
            next=n;
        }

        public Node2() {}

        public int getAccess() {
            return access;
        }

        public void setAccess(int acc) {
            access = acc;
        }

        public Object getElement() {
            return element;
        }

        public void setElement(Object obj) {
            element = obj;
        }

        public Node2 getNext() {
            return next;
        }

        public void setNext(Node2 newNext) {
            next = newNext;
        }
    }
    private int size=0;
    private Node2 head;
    private Node2 rear;



    @Override
    public void insert(Object item, int key) {
        Node2 node=new Node2();
        node.setElement(item);
        node.setAccess(key);
        if(size==0){
            head=node;
        }else if(key<head.getAccess()){
            node.next=head;
            head=node;
        }else {
            Node2 n=head;
            Node2 trace=head;
            int flag=0;
            int first=0;
                while (n.next != null) {
                    if (key > n.getAccess()) {
                        if(first==1){
                            trace=trace.next;
                        }
                        n = n.next;
                        first=1;
                    } else {
                        trace.next=node;
                        node.next=n;
                        flag=1;
                        break;
                    }
                }
                if(size>1 && n.next==null ){
            if (key > n.getAccess()) {
                n.next = node;
            } else {
                trace.next=node;
                node.next=n;
                flag=1;
            }}
                if(flag==0){
                   n.next=node;
                }
        }
        size++;
    }

    @Override
    public Object removeMin() {
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
    public Object min() {
        if(isEmpty())
            throw new RuntimeException("Empty Queue");
        return head.getElement();
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
    public void show(){
        Node2 node=head;
        while (node!=null){
            System.out.println(node.getElement());
            node=node.getNext();
        }
    }

}
