package eg.edu.alexu.csd.datastructure.mailServer;

import java.util.EmptyStackException;

public class Stacks implements IStack{
	public class Node2{
        private Object element;
        private Node2 next;

        public Node2(Object obj,Node2 n){
            element=obj;
            next=n;
        }

        public Node2() {}

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
    private Node2 top;
    private int size;

    /**
     * Removes the element at the top of stack
     * @return
     *        Return the removable element and exception if it is empty
     */
    @Override
    public Object pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        Object trial = top.getElement();
        top=top.getNext();
        size--;
        return trial;
    }

    /**
     * Get the value of the element at the top of the stack without
     * removing it
     * @return
     *         Return that value and exception if the stack is empty
     */
    @Override
    public Object peek() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return top.getElement();
    }

    /**
     * insert an element at the top of the stack
     * @param
     *         element
     */
    @Override
    public void push(Object element) {
        Node2 node = new Node2(element,top);
        top=node;
        size++;

    }

    /**
     * check if the stack is empty or not
     *
     * @return
     *        True if it is empty and false otherwise
     */
    @Override
    public boolean isEmpty() {
        if(top==null)
            return true;
        return false;
    }

    /**
     * get the size of the stack
     * @return
     *        Integer value of the size
     */
    @Override
    public int size() {
        return size;
    }

}