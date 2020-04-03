package com.company;

import java.util.*;

/**
 * This class Helps the compiler to perform
 * multiple operations in a numeric expression
 * preserving the priority of each operator and parentheses
 */

public class Evaluate implements IExpressionEvaluator {

    /**
     * Takes a String from the user expressed in infix
     * and converts it to postfix expression for the compiler to use
     * @param
     *         expression String ( infix )
     * @return
     *         Postfix String
     */

    @Override

    public String infixToPostfix(String expression) {
        int brack=0;
        if(expression.equals("")){
            throw new IllegalArgumentException("Please enter valid form.");
        }
        expression=expression.replace("("," ( ").replace(")"," ) ");
        expression=expression.replace("+"," + ").replace("-"," - ").replace("*"," * ").replace("/"," / ");
        String[] ch=expression.split(" ");
        int flag2=0;
        for(int i=0;i<ch.length;i++){
            if(ch[i].equals("")){
                flag2++;
            }
        }
        final List<String> list = new ArrayList<String>();
        Collections.addAll(list,ch);
        for(int i=1;i<=flag2;i++){
            list.remove("");
        }
        ch=list.toArray(new String[list.size()]);
        if(ch.length==1)
            return expression;
        Stacks stack = new Stacks();
        StringBuilder result= new StringBuilder();
        int flag=0;
        if( (ch[0].equals("+") || ch[0].equals("/") || ch[0].equals("*")) || (ch[ch.length - 1].equals("+") || ch[ch.length - 1].equals("-") || ch[ch.length - 1].equals("/") || ch[ch.length - 1].equals("*")) ){
            throw new IllegalArgumentException("Can't begin or end with an operator");
        }
       for(int i=0;i<ch.length;i++){
            if( (ch[i].equals("+") || ch[i].equals("-") || ch[i].equals("/") || ch[i].equals("*")) && (ch[i + 1].equals("+") || ch[i + 1].equals("/") || ch[i + 1].equals("*")) ){
                throw new IllegalArgumentException("Can't have two operators in touch");
            }
            if(!ch[i].equals("+") && !ch[i].equals("-") && !ch[i].equals("*") && !ch[i].equals("/") && !ch[i].equals("(") && !ch[i].equals(")")){
                if((i+1<ch.length)&&(ch[i+1].equals("("))){
                    throw new IllegalArgumentException("Can't have a number and a bracket in touch");
                }else {
                    result.append(ch[i]).append(" ");
                }
            }else if(flag==0 && ch[0].equals("-")){
                result.append('0').append(" ");
                result.append(ch[i + 1]).append(" ");
                result.append(ch[i]).append(" ");
                i++;
                flag=1;
            }
            else if ( (i-1>-1) &&(ch[i - 1].equals("+") || ch[i - 1].equals("-") || ch[i - 1].equals("/") || ch[i - 1].equals("*")) && ch[i].equals("-")){
                result.append('0').append(" ");
                result.append(ch[i + 1]).append(" ");
                result.append(ch[i]).append(" ");
                i++;
            }else if (ch[i].equals("+") || ch[i].equals("-") || ch[i].equals("/") || ch[i].equals("*")){
                if(stack.isEmpty()){
                    stack.push(ch[i]);
                }else{
                    while (true){
                        if(stack.isEmpty()){
                            stack.push(ch[i]);
                            break;
                        }else{
                    String top=stack.peek().toString();
                    if(getPrio(top)<getPrio(ch[i])){
                        stack.push(ch[i]);
                        break;
                    }else{
                        result.append(stack.pop()).append(" ");
                    }}}
                }
            }else if(ch[i].equals("(")){
                brack++;
                if(ch[i+1].equals(")")){
                    throw new IllegalArgumentException("Can't have two brackets with empty values");
                }else{
                stack.push(ch[i]);}
            }else if(ch[i].equals(")")){
                brack--;
                if((i+1<ch.length)&&(ch[i+1].equals("(")))
                    throw new IllegalArgumentException("Can't have open and close bracket in touch");
                if((i+1<ch.length)&&(!ch[i+1].equals("+") && !ch[i+1].equals("-") && !ch[i+1].equals("*") && !ch[i+1].equals("/")&& !ch[i+1].equals("(")&& !ch[i+1].equals(")"))){
                    throw new IllegalArgumentException("Can't have a bracket and a number in touch");
                }else {
                    while (stack.peek().toString().charAt(0) != '(') {
                        result.append(stack.pop()).append(" ");
                    }
                    stack.pop();
                }
            }
        }
       if(brack!=0){
           throw new IllegalArgumentException("Can't have an open bracket without a closing one or vise versa");
       }

        while (!stack.isEmpty()){
            result.append(stack.pop()).append(" ");
        }
        return result.toString();
    }

    /**
     * takes in a String which is an operator
     * and return the priority according to precedence table
     * @param
     *         c ( String )
     * @return
     *         Integer flag
     */
    public int getPrio(String c){
        int prio=0;
        if(c.equals("+") || c.equals("-")){
            prio=1;
        }else if(c.equals("*") || c.equals("/")){
            prio=2;
        }
        return prio;
    }

    /**
     * Calculates the value of the input postfix expression
     * which was found in earlier methods
     * @param
     *         expression String ( Postfix )
     * @return
     *         Integer numeric value
     */

    @Override
    public int evaluate(String expression) {
        float result=0;
        String[] ch= expression.split(" ");
        Stacks stack = new Stacks();
        if(ch.length==1){
            stack.push(ch[0]);
            result= Float.parseFloat(stack.pop().toString());
            return (int)Math.round(result);
        }
        for(int i=0;i<ch.length;i++) {
            if(Character.isLetter(ch[i].charAt(0))){
                throw new IllegalArgumentException("Can't contain letters ,, Only numbers");
            }
            if (!ch[i].equals("+") && !ch[i].equals("-") && !ch[i].equals("*") && !ch[i].equals("/")) {
                //Float.parseFloat(String.valueOf(ch[i]));
                stack.push(ch[i]);
            }else if (ch[i].equals("+")){
                Float second= Float.parseFloat(stack.pop().toString());
                Float first=  Float.parseFloat(stack.pop().toString());
                stack.push(first+second);
            }else if (ch[i].equals("-")){
                Float second= Float.parseFloat(stack.pop().toString());
                Float first=  Float.parseFloat(stack.pop().toString());
                stack.push(first-second);
            }else if (ch[i].equals("*")){
                Float second= Float.parseFloat(stack.pop().toString());
                Float first=  Float.parseFloat(stack.pop().toString());
                stack.push(first*second);
            }else if (ch[i].equals("/")){
                float second= Float.parseFloat(stack.pop().toString());
                float first=  Float.parseFloat(stack.pop().toString());
                if(second==0){
                    throw new IllegalArgumentException("Can't divide by zero");
                }else{
                stack.push(first/second);}
            }
        }
        result= (float) stack.pop();
        return (int)Math.round(result);
    }
}
