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