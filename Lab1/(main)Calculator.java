package com.company;


public class Calculator implements ICalculator {

    public static void main(String[] args) {}
    @Override
    public int add(int x,int y){
        return x+y;
    }
    @Override
    public float divide(int x,int y)throws RuntimeException{
        if(x==0){
            return 0;
        }
        if(y==0){
            throw new RuntimeException("Division by Zero is not possible");
        }else{
            return (float)x/y;
        }
    }

}
