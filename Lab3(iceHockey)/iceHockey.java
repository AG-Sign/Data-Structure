package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class iceHockey implements IPlayersFinder {
    public static int count=0;
    public static int Xmax,Xmin,Ymax,Ymin;
    public static int Xfinal;
    public static int Yfinal;
    public static char[][] letters;

    public static void main(String[] args) {}

    public char[][] stringToCharArray(String [] word){
        char[][] char2D = new char[word.length][];

        for (int i = 0; i < word.length; i++) {
            char2D[i] = word[i].toCharArray();
        }
        return char2D;
    }

    public void check(int x,int y,int team){
        char c = (char)(team +'0');
        if(y+1<letters[x].length && letters[x][y+1]==c){
            count++;
            if(Xmax < y+1){
            Xmax=y+1;}
            letters[x][y+1]=0;
            check(x,y+1,team);
        }
        if(y-1>=0 && letters[x][y-1]==c){
            count++;
            if(Xmin > y-1){
            Xmin=y-1;}
            letters[x][y-1]=0;
            check(x,y-1,team);
        }
        if(x+1<letters.length && letters[x+1][y]==c){
            count++;
            if(Ymax < x+1){
            Ymax=x+1;}
            letters[x+1][y]=0;
            check(x+1,y,team);
        }
        if(x-1>=0 && letters[x-1][y]==c){
            count++;
            if(Ymin > x-1){
            Ymin=x-1;}
            letters[x-1][y]=0;
            check(x-1,y,team);
        }
    }

    @Override
    public Point[] findPlayers(String[] photo, int team, int threshold) {
        if(photo == null || photo.length==0 || photo[0]==""){
            return null;
        }else{
        ArrayList<Point> p = new ArrayList<Point>();
        letters=stringToCharArray(photo);
        char c = (char)(team +'0');
        int i,j;
        int l=0;
        for(i=0;i<letters.length;i++){
            for(j=0;j<letters[i].length;j++){
                if(letters[i][j]==c){
                    count++;
                    Xmax=j;Xmin=j;
                    Ymax=i;Ymin=i;
                    letters[i][j]=0;
                    check(i,j,team);
                    count*=4;
                }
                if(count >= threshold){
                    Xfinal= Xmax+Xmin+1;
                    Yfinal= Ymax+Ymin+1;
                    p.add(new Point(Xfinal,Yfinal));
                }
                count=0;
            }
        }
        Collections.sort(p, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare((int)(o1.getX()), (int)(o2.getX()));
            }
        });
        Point[] Arr = new Point[p.size()];
        p.toArray(Arr);
        return Arr;
    }}
}
