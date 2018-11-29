import java.util.*;
import java.io.*;
import java.lang.*;


class Main
{
    public Main()
    {
        //constructor for Main class
    }
    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);

        String reserved = input.nextLine();
        //System.out.println(reversed_words);

        String input_text = input.nextLine();
        //System.out.println(input_text);

        input.close();

        Analyzer A = new Analyzer(input_text,reserved);
        System.out.print("1a: ");
        for(String s:A._1a())
        {
            System.out.print(s+" ");
        }

        System.out.print("\n1b: ");
        for(String s:A._1b())
        {
            System.out.print(s+" ");
        }

        System.out.print("\n1c: ");
        for(String s:A._1c())
        {
            System.out.print(s+" ");
        }

        System.out.print("\n1d: ");
        for(String s:A._1d())
        {
            System.out.print(s+" ");
        }

        System.out.print("\n2a:\n");
        for(String s:A._2a())
        {
            System.out.print(s+" \n");
        }

        System.out.print("3a: ");
        for(String s:A._3a())
        {
            System.out.print(s+" ");
        }

        System.out.print("\n3b: ");
        for(String s:A._3b())
        {
            System.out.print(s+" ");
        }
        System.out.println("");
    }
}

class Analyzer
{
    private ArrayList<String> reserved_words = new ArrayList<String>();
    public void setreserved_words(String reserved)
    {
        String[] arr = reserved.split(" "); 
        for (int i =0;i<arr.length;i++)
        {
            reserved_words.add(arr[i]);
        }
    }
    public ArrayList<String> getreserved_words()
    {
        return reserved_words;
    }

    private ArrayList<String> input_text_arr = new ArrayList<String>(); 
    public void setinput_text_arr(String input_text)
    {
        String str = input_text;
        str = str.replace(".", "");
        str = str.replace(",", "");
        str = str.replace(";", "");
        str = str.replace("_","");
        str = str.replace("—","");
        str = str.replace(":", "");
        str = str.replace("\"", "");
        str = str.replace("\''", "");
        str = str.replace("-", "");
        str = str.replace("  "," "); 
        str = str.toLowerCase();   //. , ; : ‘ “ -
        //System.out.println(str);
        String[] arr = str.split(" ");
        for(int i =0;i<arr.length;i++)
        {
            input_text_arr.add(arr[i]);
        }
        //System.out.println(input_text_arr);
    }
    public ArrayList<String> getinput_text_arr()
    {
        return input_text_arr;
    }

    public Analyzer(String input_text,String reserved)
    {
        //constructor for Analyzer class
        setinput_text_arr(input_text);
        setreserved_words(reserved);
    }
    

    public ArrayList<String> _1a()
    {
        ArrayList<String> solution = this.getinput_text_arr();
        Set<String> myset = new LinkedHashSet<String>(solution);
        solution = new ArrayList<String>(myset);
        //System.out.println("unique words are: "+solution);
        return solution;
    }

    public ArrayList<String> _1b()
    {
        ArrayList<String> solution = this._1a();
        Collections.sort(solution);
        return solution;
    }

    public ArrayList<String> _1c() 
    {
        ArrayList<String> solution = this._1b();
        Collections.sort(solution,new _1C());
        return solution;
    }

    public ArrayList<String> _1d()
    {
        ArrayList<String> solution1 = this.getinput_text_arr();
        ArrayList<String> solution = this._1a();
        // _1D d = new _1D();
        // d.freq(solution);
        _1D d = new _1D();
        d.arr = solution1;
        Collections.sort(solution,d);
        return solution;
    }

    public ArrayList<String> _2a()
    {
        ArrayList<String> solution = this.getinput_text_arr();
        ArrayList<String> alist = this._1b();
        ArrayList<String> startings = new ArrayList<String>();
        ArrayList<String> all_startings = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();
        for(String s:alist)
        {
            String S = Character.toString(s.charAt(0));
            startings.add(S);
        }
        for(String s:solution)
        {
            String S = Character.toString(s.charAt(0));
            all_startings.add(S);
        }
        Set<String> myset = new LinkedHashSet<String>(startings);
        startings = new ArrayList<String>(myset);
        for(int i =0;i<startings.size();i++)
        {
            int f1 = Collections.frequency(all_startings, startings.get(i));
            result.add(startings.get(i)+" "+Integer.toString(f1));
        }
        return result;
    }

    
    public ArrayList<String> _3a()
    {
        ArrayList<String> solution = this._1a();
        ArrayList<String> rev = this.getreserved_words();
        solution.retainAll(rev);
        return solution;
    }

    public ArrayList<String> _3b()
    {
        ArrayList<String> solution = this._3a();
        ArrayList<String> solution1 = this.getinput_text_arr();
        _3B b = new _3B();
        b.arr = solution1;
        Collections.sort(solution,b);
        return solution;   
    }
    
}

class _3B implements Comparator<String>
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

class _1D implements Comparator<String>
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

class _1C implements Comparator<String>
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
