import java.util.*;
import java.io.*;
import java.lang.*;

public class _1D implements Comparator<String>
{
    ArrayList<String> arr = new ArrayList<String>();
    // public void freq(ArrayList<String> arr)
    // {
    //     HashMap<String, Integer> hmap =   new HashMap<String, Integer>(); 
    //     ArrayList<Integer> freqs = new ArrayList<Integer>();
    //     System.out.println("d part array is: "+arr);
    //     // Traverse through the given array 
    //     for (String s:arr) 
    //     { 
    //         System.out.println(s);
    //         //Map.Entry m = hmap.entrySet(); 
    //         int c;
    //         if(hmap.get(s) == null)
    //         {
    //             c = 0;
    //         }
    //         else
    //         {
    //             c = hmap.get(s);
    //         }
    //         System.out.println(c);
    //         // If this is first occurrence of element  
    //         if (hmap.get(s) == null) 
    //         {
    //             hmap.put(s,1);
    //         }
    //         // If elements already exists in hash map 
    //         else 
    //         {
    //             hmap.put(s,c+1);
    //         }       
    //     } 

    //     for(Map.Entry m: hmap.entrySet())
    //     {
    //         freqs.add(m.getvalue());
    //     }
         // Print result 
        // for (Map.Entry m:hmap.entrySet())
        // {
        //     System.out.println("Frequency of " + m.getKey() +  " is " + m.getValue()); 
        // } 
             
    public int compare(String s1, String s2)
    {
        int f1 = Collections.frequency(this.arr, s1);
        int f2 = Collections.frequency(this.arr, s2);
        if(f1>f2)
        {
            return 1;
        }
        if(f2>f1)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }

}