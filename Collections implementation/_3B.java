import java.util.*;
import java.io.*;
import java.lang.*;

public class _3B implements Comparator<String>
{
    ArrayList<String> arr = new ArrayList<String>(); 
    public int compare(String s1, String s2)
    {
        int f1 = Collections.frequency(this.arr, s1);
        int f2 = Collections.frequency(this.arr, s2);
        if(f1>f2)
        {
            return -1;
        }
        if(f2>f1)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

}