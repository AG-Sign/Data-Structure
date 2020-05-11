package eg.edu.alexu.csd.datastructure.mailServer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Sort implements ISort{
private String so;
	
    public String getSo() {
		return so;
	}
	public void setSo(String so) {
		this.so = so;
	}
	// QUICK SORT METHODS
    // QUICK SORT METHODS
    // QUICK SORT METHODS
    // QUICK SORT METHODS
	static int swapSubject(ILinkedList list, int low, int high) {
		String temp=(String)list.get(high);
		String[] last=temp.split("===");
        String pivot = last[4];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
    		String temp2=(String)list.get(j);
    		String[] last2=temp2.split("===");
    		String hihi=last2[4];
            if (pivot.compareToIgnoreCase(hihi)>=0) {
                i++;
                String temp3 = (String) list.get(i);
                list.set(i,(String)list.get(j));
                list.set(j,temp3);
            }
        }
        String temp4 = (String) list.get(i+1);
        list.set(i+1,(String)list.get(high));
        list.set(high,temp4);
        return i + 1;
    }
	static int swapFrom(ILinkedList list, int low, int high) {
		String temp=(String)list.get(high);
		String[] last=temp.split("===");
        String pivot = last[2];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
    		String temp2=(String)list.get(j);
    		String[] last2=temp2.split("===");
    		String hihi=last2[2];
            if (pivot.compareToIgnoreCase(hihi)>=0) {
                i++;
                String temp3 = (String) list.get(i);
                list.set(i,(String)list.get(j));
                list.set(j,temp3);
            }
        }
        String temp4 = (String) list.get(i+1);
        list.set(i+1,(String)list.get(high));
        list.set(high,temp4);
        return i + 1;
    }
	static int swapTo(ILinkedList list, int low, int high) {
		String temp=(String)list.get(high);
		String[] last=temp.split("===");
        String pivot = last[3];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
    		String temp2=(String)list.get(j);
    		String[] last2=temp2.split("===");
    		String hihi=last2[3];
            if (pivot.compareToIgnoreCase(hihi)>=0) {
                i++;
                String temp3 = (String) list.get(i);
                list.set(i,(String)list.get(j));
                list.set(j,temp3);
            }
        }
        String temp4 = (String) list.get(i+1);
        list.set(i+1,(String)list.get(high));
        list.set(high,temp4);
        return i + 1;
    }
    static void change(ILinkedList list) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        for(int i=0;i<list.size();i++){
    		String temp=(String)list.get(i);
    		String[] last2=temp.split("===");
    		String hihi=last2[1];
            list.set(i,last2[0]+"==="+format.parse(hihi)+"==="+last2[2]+"==="+last2[3]+"==="+last2[4]+"==="+last2[5]);
        }
    }
    static void changeBack(ILinkedList list) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat dat =new SimpleDateFormat("yyyy/MM/dd");
        for(int i=0;i<list.size();i++){
    		String temp=(String)list.get(i);
    		String[] last2=temp.split("===");
    		Date hihi=format.parse(last2[1]);
            list.set(i,last2[0]+"==="+dat.format(hihi)+"==="+last2[2]+"==="+last2[3]+"==="+last2[4]+"==="+last2[5]);
        }
    }

    static int flag=0;
    static int swapDate(ILinkedList list, int low, int high) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Stacks st=new Stacks();
        if(flag==0){
        //change(list);
        flag=1;
        }
        int i = (low - 1);
		String temp=(String)list.get(high);
		String[] last=temp.split("===");
		//st.push((Object)last[1]);
        Date pivot =  format.parse(last[1]);
        for (int j = low; j <= high - 1; j++) {
    		String temp2=(String)list.get(j);
    		String[] last2=temp2.split("===");
            Date yo=format.parse(last2[1]);
            if (pivot.compareTo(yo)>=0) {
                i++;
                String temp3 = (String) list.get(i);
                list.set(i,(String)list.get(j));
                list.set(j,temp3);
            }
        }
        String temp4 = (String) list.get(i+1);
        list.set(i+1,(String)list.get(high));
        list.set(high,temp4);
        return i + 1;
    }

    
  //8albn msh 7t7tagha ya 3m yasser bs ana saybha e7tyaty brdoxDD
    static SLL pick(ILinkedList list,String type){
        SLL aux = new SLL();
        int j=0;
        if(type.equalsIgnoreCase("date"))
            j++;
        if(type.equalsIgnoreCase("from") ||type.equalsIgnoreCase("sender") )
            j=j+2;
        if(type.equalsIgnoreCase("to") || type.equalsIgnoreCase("reciever"))
            j=j+3;
        if(type.equalsIgnoreCase("subject"))
            j=j+4;
        for(int i=0;i<list.size();i++){
            String temp =(String)list.get(i);
            String[] stT=temp.split("===");
            aux.add(stT[j]);
        }
        SLL aux2=new SLL();
        if(type.equalsIgnoreCase("date")){
            for(int i=0;i<aux.size();i++){
                String temp =(String)aux.get(i);
                String[] stT=temp.split(" ");
                aux2.add(stT[0]);
            }
            return aux2;
        }else {
            return aux;

        }
    }

    static void removeTime(ILinkedList list){
        for(int i=0;i<list.size();i++){
            String temp =(String)list.get(i);
            String[] dash=temp.split("===");
            String[] space=dash[1].split(" ");
            list.set(i,dash[0]+"==="+space[0]+"==="+dash[2]+"==="+dash[3]+"==="+dash[4]+"==="+dash[5]);
        }
    }
    
     static void quick(ILinkedList list,String type) throws ParseException {
        Stacks stack = new Stacks();
        removeTime(list);
        SLL aux=new SLL();
        //aux=pick(list,type);
        int l=0;
        int tr=0;
        int h=list.size() - 1;
        stack.push(l);
        stack.push(h);
        int p=0;
        while (!stack.isEmpty()) {
            h = (int) stack.pop();
            l = (int) stack.pop();
            if(type.equalsIgnoreCase("subject")) {
                 p = swapSubject(list, l, h);
            }else if(type.equalsIgnoreCase("date")){
                 p=swapDate(list,l,h);
                 tr=1;
            }else if(type.equalsIgnoreCase("from") ||type.equalsIgnoreCase("sender")) {
            	p=swapFrom(list,l,h);
            }else if(type.equalsIgnoreCase("to") || type.equalsIgnoreCase("reciever")) {
            	p=swapTo(list,l,h);
            }
            if (p - 1 > l) {
                stack.push(l);
                stack.push(p - 1);
            }

            if (p + 1 < h) {
                stack.push(p + 1);
                stack.push(h);
            }
        }
        if(tr==1){
        changeBack(list);
            }
    }

}