import java.util.*;
import java.io.*;

public class Teller
{
    private int no_of_cashiers;
    static int time;
    static int num=0 ;
    private ArrayList<cashier> cash_arr = new ArrayList<cashier>();
    public Teller()
    {
    }

    public void updatetime(int t)
    {
        time = t;
    }

    public void setnum()
    {
        num = 0;
    }

    public void setnoofcashiers(ArrayList<request> requests)
    {   
        int z;
        int l = requests.size();
        
        for (request R:requests)
        {
            //System.out.println((requests.get(i)).getid());
            z = R.getid();
            if (z != -1)
            {
                R.setreqid();
                //cashreq.add(R);
                num = num+1;
            }
            if (z == -1)
            {
                break;
            }
        }
        if (num%3 == 0)
        {
            no_of_cashiers = (num/3);
        }
        else
        {
            no_of_cashiers = ((num/3)+1);
    
        }
    }

    public int getnoofcashiers()
    {
        return no_of_cashiers;
    }

    public void casharraystack(ArrayList<request> requests,int num,int no_of_cashiers)
    {
        for (int i = 0; i < num; i ++)
        {
            this.cash_arr.get(i%no_of_cashiers).req_stack.push(requests.get(i));
        }
    }

    public void setcasharray(int no_of_cashiers)
    {
        for (int i=0;i< no_of_cashiers;i++)
        {
            cashier c = new cashier(i+1);
            cash_arr.add(c);
        }
    }

    public void out(ArrayList<request> requests)
    {
        this.setnoofcashiers(requests);
        this.setcasharray(no_of_cashiers);
        this.casharraystack(requests,num,no_of_cashiers);
        for (int i =0;i<no_of_cashiers;i++)
        {
            cash_arr.get(i).cash_func(this);
        }
    } 

    public static void main(String args[])
    {
        Teller T = new Teller();
        Scanner in = new Scanner(System.in);
       // System.out.println("input is:" + type);
        ArrayList<String> reqs = new ArrayList<String>();
       // ArrayList<Integer> ids = new ArrayList<Integer>();
        //ArrayList<Integer> amounts = new ArrayList<Integer>();
        //List<List<Integer>> idamt = new ArrayList<List<Integer>>();
        ArrayList<request> requests = new ArrayList<request>();
        while(true)
        {
            String type = in.next();

            if(type.equals("D"))
            {
                reqs.add(type);
                int id = in.nextInt();
                //ids.add(id);
                int amount = in.nextInt();
                //amounts.add(amount);
                request R = new request(type,id,amount);
                requests.add(R);
            }   

            if(type.equals("W"))
            {
                reqs.add(type);
                int id = in.nextInt();
               // ids.add(id);
                int amount = in.nextInt();
               // amounts.add(amount);
                request R = new request(type,id,amount);
                requests.add(R);
            }   

            if(type.equals("Q"))
            {
                reqs.add(type);
                int id = in.nextInt();
                //ids.add(id);
                int amount = 0;
                request R = new request(type,id,amount);
                requests.add(R);
            }

            if(type.equals("B"))
            {
                reqs.add(type);
                int id = -1;
                int amount = 0;
                request R = new request(type,id,amount);
                requests.add(R);
            }

            if(type.equals("E"))
            {
                int id = -1;
                int amount = 0;
                request R = new request(type,id,amount);
                requests.add(R);
                //System.out.println("YAyy");
                break;            
            }
        }
        while(requests.isEmpty() == false)
        {
            T.out(requests);
            for(int i =0;i<num+1; i++)
            {
                requests.remove(0);
            }
            T.setnum();
        }
        
       // T.out(requests);
    }
}

class request
{
    private int req_amount;
    private int cos_id;
    private String req_type;
    private int req_time;
    private int req_id;
    static int a=1;

    public void setid(int id)
    {
        cos_id = id;
    }

    public void setreqid()
    {
        req_id = a;
        a = a+1;
    }
    public int getreqid()
    {
        return req_id;
    }

    public int getid()
    {
        return this.cos_id;
    }

    public void setamount(int amount)
    {
        req_amount = amount;
    }

    public int getamount()
    {
        if (req_type.equals("W"))
        {
            return req_amount*-1;
        }
        else
        {
            return req_amount;
        }  

    }

    public void settype(String type)
    {
        req_type = type;
    }

    public String gettype()
    {
        return req_type;
    }

    public void settime(String type)
    {
        if (type.equals("D"))
        {
            req_time = 5;
        }
        if (type.equals("W"))
        {
            req_time = 10;
        }
        if (type.equals("Q"))
        {
            req_time = 2;
        }
    }

    public int gettime()
    {
        return req_time;
    }

   /* public request(String type,int id)
    {
        setid(id);
        settype(type);
        //String t = this.gettype();
    }*/

    public request(String type, int id, int amount)
    {
        setid(id);
        settype(type);
        setamount(amount);
        settime(type);
        //req_num = req_num+1;
    }

    
}

class cashier
{
   // private int no_of_cashiers;
    private int cashier_no ;
    static int num = 0;
  //Stack<request> req_stack;
    Stack<request> req_stack = new Stack<request>();
    static int totaltime;
   
    //ArrayList<request> cashreq = new ArrayList<request>();
   

    public void push_req(request R)
    {
        this.req_stack.push(R);        
    }

    public void setcashno(int n)
    {
        cashier_no = n;
    }

    public int getcashno()
    {
        return cashier_no;
    }

    public void cash_func(Teller t)
    {
        int totalamount = 0;
        int totaltime = t.time;
        System.out.println("Cashier " + cashier_no);
        while (this.req_stack.empty() == false)
        {
            request R = req_stack.peek();
            totaltime = totaltime + R.gettime();
            t.updatetime(totaltime);
            totalamount = totalamount + R.getamount();
            System.out.println("Request "+R.getreqid()+" "+totaltime);
            req_stack.pop();
        }
        System.out.println("Total "+totalamount);
    }

    public cashier(int n)
    {
        setcashno(n);
    }
}

