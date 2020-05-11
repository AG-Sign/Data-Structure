package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Search implements IFilter{
	
	public String getSr() {
		return sr;
	}
	public void setSr(String sr) {
		this.sr = sr;
	}
	private String sr;
    //BINARY SEARCH METHOD
    //BINARY SEARCH METHOD
    //BINARY SEARCH METHOD
    //BINARY SEARCH METHOD

       /*static int binarySearch(ILinkedList list, int x) {
            int l = 0, r = list.size() - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if ((int)list.get(m) == x)
                    return m;
                if ((int)list.get(m) < x)
                    l = m + 1;
                else
                    r = m - 1;
            }
            return -1;
        }*/
    static int[] binary(ILinkedList list, String x) {
    	int index=0;
    		if(x.contains("/")) {
    			index=1;
    		}else if(x.contains("@")) {
    			index=2;
    		}else {
    			index=4;
    		}
        Stacks stack=new Stacks();
        List<Integer> li = new ArrayList<Integer>();
        int l = 0, r = list.size() - 1;
        stack.push(l);
        stack.push(r);
        int mid=0;
        int p=0;
        int flag=0;
        int ret=0;
            while (l <= r) {
            	if(ret==1)
            		break;
                r = (int) stack.pop();
                l = (int) stack.pop();
                mid = l + (r - l) / 2;
        		String temp=(String)list.get(mid);
        		String[] last=temp.split("===");
                String hihi = last[index];
                if (x.compareTo(hihi) == 0) {
                    li.add(p, mid);
                    p++;
                    flag=1;
                }
                if(flag==1) {
                	int t=mid-1,u=mid+1;
                	while(t>=l) {
                		String temp2=(String)list.get(t);
                		String[] last2=temp2.split("===");
                        String hihi2 = last2[index];
                		if(x.compareTo(hihi2) == 0) {
                            li.add(p, t);
                            p++;
                		}else
                			break;
                		t--;
                	}
                	while(u<=r) {
                		String temp2=(String)list.get(u);
                		String[] last2=temp2.split("===");
                        String hihi2 = last2[index];
                		if(x.compareTo(hihi2) == 0) {
                            li.add(p, u);
                            p++;
                		}else
                			break;
                		u++;
                	}
                	ret=1;
                }else { 
                if (x.compareToIgnoreCase(hihi) < 0){
                    r = mid - 1;
                stack.push(l);
                stack.push(r);
            }else{
                l = mid + 1;
                stack.push(l);
                stack.push(r);
            }
                }
        }
            if(li.size()==0)
                li.add(-1);
        int[] arr=new int[li.size()];
        for(int i=0;i<li.size();i++){
            arr[i]=li.get(i);
        }
        System.out.println("The searched is "+x);
            return arr;

    }
    
    
    //8albn msh 7t7tagha ya 3m yasser bs ana saybha e7tyaty xDD
    static String[] getPathFromSelected(ILinkedList list,String x){
        List<String> haha = new ArrayList<String>();
        for(int i=0;i<list.size();i++){
            String temp =(String)list.get(i);
            String[] stT=temp.split("===");
            for(int j=0;j<stT.length;j++){
                if(x.compareToIgnoreCase(stT[j])==0){
                    if(j==0)
                        haha.add(stT[0]);
                    else
                        haha.add(stT[0]);

                    break;
                }
            }
        }
        String[] path=new String[haha.size()];
        path=haha.toArray(path);
        return path;
    }
    public String searchAttachments(SLL attachments,String attachmentName) {
    	String attachmentSearch=attachmentName.trim().toLowerCase();
    	System.out.println(attachmentName);
    	for(int i=0;i<attachments.size();i++) {
    		File newFile=new File((String)attachments.get(i));
    		System.out.println(newFile.getName());
    		if(newFile.getName().trim().toLowerCase().substring(0,newFile.getName().indexOf('.')).equals(attachmentSearch)) {
    			return (String)attachments.get(i);
    		}
    	}
    	return null;
    }
}