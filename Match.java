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
        k = matrix.get(player1).get(player2);
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