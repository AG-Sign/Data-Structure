package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Stacks st=new Stacks();
        while(true) {
            System.out.println("1.Push");
            System.out.println("2.Pop");
            System.out.println("3.Peek");
            System.out.println("4.Get Size");
            System.out.println("5.Check if Empty");
            int k = scan.nextInt();
            if (k == 1) {
                System.out.println("Enter the Element to be pushed : ");
                String l = scan.next();
                st.push(l);
                System.out.println("Element Pushed ^^");
            } else if (k == 2) {
                try {
                    String l = st.pop().toString();
                    System.out.println("Element " + l + " was popped from the stack");
                }catch (Exception e){
                    System.out.println("The stack is empty , Nothing to be popped");
                }
            } else if (k == 3) {
                try {
                    String l = st.peek().toString();
                    System.out.println("Element " + l + " is the top element of the stack");
                }catch (Exception e){
                    System.out.println("The stack is empty , Nothing at the top");
                }
            } else if (k == 4) {
                System.out.println("The size of the stack is " + st.size());
            } else if (k == 5) {
                if (st.isEmpty()) {
                    System.out.println("The stack is Empty");
                } else {
                    System.out.println("The stack is not Empty");
                }
            }
        }
    }
}
