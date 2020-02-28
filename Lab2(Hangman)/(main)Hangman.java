package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman implements IHangman {

    private String Reader;
    private static char c;
    private static char[] dashes;
    public static String[] words=null;
    private static String chosen;
    private static String afterdash;
    private int count=0;
    private static int trial=3;
    private int flag=0;
    private int lo=0;
    private int clean=0;
    private int rightGuess=0;
    private int winning=0;

    public static void main(String[] args) throws Exception {
        Hangman hang= new Hangman();
        hang.setDictionary(words);
        while(trial !=0){
            afterdash=hang.guess(c);
            System.out.println(afterdash);
            if(hang.winning==1){
                System.out.println("You Won");
                break;
            }
        }
    }


    public String[] read(){
        List<String> itemList;
        itemList = new ArrayList<String>();
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("names.txt"))) {
            while((Reader=br.readLine())!=null){
                itemList.add(Reader);
            }
        }catch (IOException e){
            e.getMessage();
        }
        String[] itemsArray = new String[itemList.size()];
        itemsArray=itemList.toArray(itemsArray);
        return itemsArray;
    }

    @Override
    public void setDictionary(String[] words) {
        this.words = read();
    }
    public boolean checkBuggy(String bug){
        if(bug==null){
            return true;}
        else if(bug.isEmpty()){
            return true;}
        else{
            return false;
        }
    }

    @Override
    public String selectRandomSecretWord() {
        Random rand = new Random();
        int j = rand.nextInt(words.length);
        chosen = words[j];
        System.out.println(chosen);
        if(checkBuggy(chosen)==true){
            System.out.println("Empty Word,,,Selecting new word for you");
            chosen=selectRandomSecretWord();
            return chosen;
        }else{
            return chosen;
        }
    }

    @Override
    public String guess(Character c) throws Exception {
        while(flag==0){
            chosen=selectRandomSecretWord();
            flag=1;}
        char[] ch=new char[chosen.length()];
        for(int o=0;o<ch.length;o++){
            ch[o]=chosen.charAt(o);
        }
        if(lo==0){
        dashes = new char[ch.length];
        lo=1;
        }
        while(clean==0){
        for(int i=0; i<ch.length;i++){
            dashes[i]='-';
        }
            for(int i=0; i<ch.length;i++){
                System.out.print(dashes[i]);
            }
        clean=1;
    }
        Scanner scan=new Scanner(System.in);
        c=scan.next().charAt(0);
        if (c == null){
            return null;
        }else{
        for(int k=0;k<ch.length;k++){
            if(ch[k]==c.toLowerCase(c)){
                dashes[k]=c.toLowerCase(c);
                count++;
                rightGuess++;
            }
            if(ch[k]==c.toUpperCase(c)){
                dashes[k]=c.toUpperCase(c);
                count++;
                rightGuess++;
            }
        }
        if(rightGuess==ch.length){
            winning=1;
        }
        setMaxWrongGuesses(trial);
        afterdash=String.valueOf(dashes);
        return afterdash;}
    }

    @Override
    public void setMaxWrongGuesses(Integer max) {
        if(count ==0){
            trial--;
            System.out.println("Wrong Guess");
        }
        if(trial==0){
            System.out.println("You Lose");
        }
    }
}
