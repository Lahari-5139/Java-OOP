import java.util.*;
import java.io.*;
import java.lang.*;

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
