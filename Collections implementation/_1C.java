import java.util.*;
import java.io.*;
import java.lang.*;

public class _1C implements Comparator<String>
{
    public int compare(String s1,String s2)
    {
        int n1 = s1.length();
        int n2 = s2.length();
        if(n1>n2)
        {
            return 1;
        }
        if(n2>n1)
        {
            return -1;
        }
        else
        {
            return 0;
        }
        
    }
}