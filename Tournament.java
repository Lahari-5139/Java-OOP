import java.util.*;
import java.io.*;
import java.lang.Math;

class Tournament
{
    public Tournament()
    {}
    public void round(ArrayList<Integer> order,int n,List<List<Integer>> matrix)
    {
       // int size;
        //size = order.size();
        int z = (int)((Math.log(n))/(Math.log(2)));
        //System.out.println(z);
        for(int j = 0;j<z;j++)
        {
            //ArrayList<Integer> roundarr = new ArrayList<Integer>(n/(int)(Math.pow(2,j)));
            for (int i = 0;i < n/(Math.pow(2,j+1)) ; i++)
            {
                    int p1 = order.get((0));
                    int p2 = order.get((1));
                    match mymatch = new match(p1,p2);
                    int w = mymatch.winner(matrix);
                   // System.out.println(w);
                    order.add(w);
                    order.remove(0);
                    order.remove(0);
            }
            System.out.print("Round " + (j+1)+" Winners: ");
            for (int i :order)
            {
                System.out.print(i+" ");
            }
            System.out.println("");
        }
        
        
       // size = round.size();
        //System.out.println(size);
    }

    public static void main(String args[])
    {
        Tournament T = new Tournament(); 
        Scanner teams = new Scanner(System.in);
        int n = teams.nextInt();
        
        //int[][] matrix = new int[n][n];
        // ArrayList<Integer>[][] matrix = new ArrayList<Integer>[n][n];
        List<List<Integer>> matrix = new ArrayList<List<Integer>>(n);
        for(int i = 0; i < n; i++)  {
            matrix.add(new ArrayList<Integer>());
        }
        for (int i = 0; i<n ; i++)
        {
             
            for (int j=0;j<n;j++ )
            {
                int k = teams.nextInt();
                matrix.get(j).add(k);
                // matrix[0][0] = new ArrayList(); // add another ArrayList object to [0,0]
                //matrix[i][j].add(k); // add object to that ArrayList
                //System.out.println(matrix[i][j]);
            }
        }
        //System.out.println(matrix);
       // System.out.println(matrix.get(1).get(0));
        ArrayList<Integer> order = new ArrayList<Integer>(n);
        for (int k =0;k<n;k++)
        {
            int a;
            a = teams.nextInt();
            order.add(a);
        }
        int size = order.size();
       // System.out.println(order);
       // match mymatch = new match(0,1);
        //System.out.println(mymatch.winner(matrix));
        T.round(order,n,matrix);
        //T.round(order,matrix);
    }    
} 

class Team
{
    private String name;
    
    public Team()
    {}
    
    public Team(String iname)
    {
        setname(iname);
    }
    
    public void setname(String myname)
    {
        name = myname;
    }
    
    public String getname()
    {
        return name;
    }
}

class match
{
    private int player1;
    private int player2;
    
    public void setplayer1(int player1)
    {
        this.player1 = player1;
    }

    public void setplayer2(int player2)
    {
        this.player2 = player2;
    }

    public int getplayer1()
    {
        return player1;
    }

    public int getplayer2()
    {
        return player2;
    }

    public match(int player1, int player2)
    {
        setplayer1(player1);
        setplayer2(player2);
    }

    public int winner(List<List<Integer>> matrix) 
    {
        int k = 0;
        k = matrix.get(player1-1).get(player2-1);
        if (k == 1)
        {
            return player1;
        }
        if (k == -1)
        {
            return player2;
        }
        if (k == 0)
        {
         return player1;
        }
        return 0;
    }
}