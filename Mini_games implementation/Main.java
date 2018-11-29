import java.util.*;
import java.io.*;

class Main
{
    ArrayList<GameConsole> mygames = new ArrayList<GameConsole>(); 
    public static void main(String args[])
    {
        Main M = new Main();
        Scanner input = new Scanner(System.in);
        //String input_name = input.next();
        while(true)
        {
            String input_array = input.nextLine();
            String[] inarr;
            inarr = input_array.split(" ");
            String input_name = inarr[0];
            if(input_name.equals("End"))
            {
                 break;
            }
            int input_num = Integer.parseInt(inarr[1]);
            //int input_num = input.nextInt();
            GameConsole mygame = new GameConsole(input_name,input_num, input);
            M.mygames.add(mygame);
            //input_name = input.next();    
        }
        for(GameConsole G : M.mygames)
        {
            G.call_game();
        }
        input.close();
    }
}

class GameConsole
{
    private String game_name;
    private int game_num;
    ArrayList<String> dirlist = new ArrayList<String>();

    public GameConsole(String input_name,int input_num, Scanner inputdir)
    {
        setgame_name(input_name);
        setgame_num(input_num);
        directions(game_num, inputdir);
        //call_game(game_num);
    }

    public void setgame_name(String input_name )
    {
        game_name = input_name;
    }
    public String getgame_name()
    {
        return game_name;
    }

    public void setgame_num(int input_num)
    {
        game_num = input_num;
    }
    public int getgame_num()
    {
        return game_num;
    }

    public void directions(int game_num, Scanner inputdir)
    {
        String dir = inputdir.nextLine();
        //System.out.println("Input directions are: " + dir);
        String[] dirarray = dir.split(" "); 
        for (int i =0; i <game_num;i++)
        {
            dirlist.add(dirarray[i]);
        }
        //System.out.println(dirlist);
          
    }

    public void call_game()
    {
        if(this.game_name.equals("RandomWalk"))
        {
            Game mygame1 = new Randomwalk(dirlist);
        }
        if(this.game_name.equals("FlightSimple"))
        {
            Game mygame1 = new Flightsimple(dirlist);
        }
    }
    
}

class Game
{
    // public void directions(int game_num)
    // {
    //     String dir = inputdir.nextLine();
    //     //System.out.println("Input directions are: " + dir);
    //     String[] dirarray = dir.split(" ");; 
    //     for (int i =0; i <game_num;i++)
    //     {
    //         dirlist.add(dirarray[i]);
    //     }
    //     //System.out.println(dirlist);
          
    // }

    public void up()
    {
    }

    public void down()
    {

    }

    public void left()
    {

    }

    public void right()
    {

    }

    public Game()
    {
        //directions(game_num);
    }
     
}

class Randomwalk extends Game
{
    private int loc_row;
    private int loc_column;
    
    public void setloc_row(int n)
    {
        this.loc_row = n;
    }
    
    public int getloc_row()
    {
        return this.loc_row;
    }
    public void setloc_column(int n)
    {
        this.loc_column = n;
    }
    
    public int getloc_column()
    {
        return this.loc_column;
    }
    
    public Randomwalk(ArrayList<String> dirlist)
    {
        super();
        this.setloc_row(10);
        this.setloc_column(10);
        this.work(dirlist);
    }

    public void work(ArrayList<String> dirlist)
    {
        int i;
        for(i=0;i<dirlist.size();i++)
        {
            if(dirlist.get(i).equals("L"))
            {
                if(loc_column < 0)
                {
                    //System.out.println("column "+ loc_column);
                    continue;
                }
                else
                {
                   // System.out.println("column "+loc_column);
                    this.left();
                }
            }

            if(dirlist.get(i).equals("R"))
            {
                if(loc_column >19 )
                {
                    //System.out.println("column"+loc_column);
                    System.out.println("RandomWalk: Lost");
                    break;
                }
                else
                {
                    //System.out.println("Column"+loc_column);
                    this.right();
                }
            }

            if(dirlist.get(i).equals("U"))
            {
                if(loc_row >19)
                {
                    //System.out.println("row"+loc_row);
                    System.out.println("RandomWalk: Lost");
                    break;
                }
                else
                {
                    //System.out.println("Row" +loc_row);
                    this.up();
                }
            }

            if(dirlist.get(i).equals("D"))
            {
                if(loc_row <0)
                {
                    //System.out.println("Row" + loc_row);
                    System.out.println("RandomWalk: Won");
                    break;
                }
                else
                {
                    //System.out.println("Row "+loc_row);
                    this.down();
                }
            }
            
        }
        if(i == dirlist.size())
            {
                //System.out.println(i);
                System.out.println("RandomWalk: Game Over");
            }
    }

    @Override
    public void up()
    {
        loc_row = loc_row+1;
    }
    @Override
    public void down()
    {
        loc_row = loc_row -1;
    }
    @Override
    public void left()
    {
        loc_column = loc_column -1;
    }
    @Override
    public void right()
    {
        loc_column = loc_column+1;
    }

    

}

class Flightsimple extends Game
{
    private int speed;
    private int altitude;

    public void setspeed(int n)
    {
        speed = n;
        //System.out.println("setspeed to "+ speed);
    }
    public int getspeed()
    {
        return speed;
    }

    public void setaltitude(int n)
    {
        altitude = n;
        //System.out.println("setaltitude to "+altitude);
    }
    public int getaltitude()
    {
        return altitude;
    } 
    public Flightsimple(ArrayList<String> dirlist)
    {
        super();
        setaltitude(0);
        setspeed(0);
        work(dirlist);
    }

    @Override
    public void up()
    {
       altitude = altitude+1;
       //System.out.println("up is called,altitude is "+ altitude);    
    }
    @Override
    public void down()
    {
        altitude = altitude -1;
        //System.out.println("down is called,altitude is "+ altitude);
    }
    @Override
    public void left()
    {
        speed = speed -1;
        //System.out.println("Left is called,speed is "+speed);
    }
    @Override
    public void right()
    {
        speed = speed +1;
        //System.out.println("right is called,speed is "+speed);
    }

    public void work(ArrayList dirlist)
    {
        int i;
        for( i=0;i<dirlist.size();i++)
        {
            if(dirlist.get(i).equals("L"))
            {
                //System.out.println("L is called:");
                if(altitude == 0)
                {
                    if(speed <= 0)
                    {
                        System.out.println("FlightSimple: Lost");
                        break;
                    }
                    else
                    {
                        this.left();
                    }    
                }
                else
                {
                    if(speed == 0)
                    {
                        continue;
                    }
                    else
                    {
                        this.left();
                    }
                    
                }
                
                
            }

            if(dirlist.get(i).equals("R"))
            {
                //System.out.println("R is called:");
                this.right();
            }

            if(dirlist.get(i).equals("U"))
            {
                //System.out.println("U is called:");
                if(altitude == 0)
                {
                    if(speed > 2)
                    {
                        this.up();
                    }
                    else
                    {
                        continue;
                    }
                }
                else
                {
                    this.up();
                }
            }

            if(dirlist.get(i).equals("D"))
            {
                //System.out.println("D is called:");
                if(altitude == 0)
                {
                    System.out.println("FlightSimple: Lost");
                    break;
                }
                else
                {
                    this.down();
                }
            }
            if(speed == 0 && altitude == 0)
            {
                System.out.println("FlightSimple: Won");
                break;
            }
        }
        if (i == dirlist.size())
        {
            System.out.println("FlightSimple: Game Over");
        }
    }

}