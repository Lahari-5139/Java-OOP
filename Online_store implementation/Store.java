import java.util.*;
import java.io.*;

class Store
{
    public  Store()
    {

    }
    ArrayList<Item> myitems = new ArrayList<Item>();
    ArrayList<String> cositem_name = new ArrayList<String>();
    ArrayList<Float> cositem_quan = new ArrayList<Float>();
    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        Store mystore = new Store();
        while(true)
        {
            String[] itemarr; 
            String item = input.nextLine();
            itemarr = item.split(" ");
            if (itemarr[0].equals("End"))
            {
                break;
            }
            if (itemarr.length == 2)
            {
                float z = Float.parseFloat(itemarr[1]);
                Item I = new Perishables(itemarr[0],z);
                mystore.myitems.add(I);
                //System.out.println("Parishable");
            }
            if (itemarr.length == 3)
            {
                float z = Float.parseFloat(itemarr[1]);
                float k = Float.parseFloat(itemarr[2]);
                Item I = new Non_Perishables(itemarr[0],z,k);
                mystore.myitems.add(I);
                //System.out.println("Non-Parishable");
            }
        }

        while(true)
        {
            String itemname = input.next();
            if (itemname.equals("Checkout"))
            {
                break;
            }    
            mystore.cositem_name.add(itemname);
            float itemquan = input.nextFloat();
            mystore.cositem_quan.add(itemquan);
        }
        Cart mycart = new Cart(mystore.myitems,mystore.cositem_name,mystore.cositem_quan);
        Biller mybiller = new Biller();
        mybiller.addperishables(mystore.cositem_name, mycart.perish_items, mystore.myitems);
        mybiller.addnonperishables(mystore.cositem_name, mycart.nonperish_items, mystore.myitems);
        for (int i =0;i<(mystore.cositem_name.size());i++)
        {
            System.out.print(mystore.cositem_name.get(i)+" ");//+"%.1f",mystore.cositem_quan.get(i) + " " +"%.1f",mybiller.costs.get(i)+"\n");
            System.out.printf("%.1f ",mystore.cositem_quan.get(i));
            System.out.printf("%.1f\n",mybiller.costs.get(i));
        }
        System.out.println("Shipping " + mybiller.packer_shipping(mycart.perish_items, mycart.nonperish_items, mystore.myitems));
        System.out.println("Total " + mybiller.gettotal());
    }
}

class Cart
{
    public Cart(ArrayList<Item> myitems,ArrayList<String> cositem_name,ArrayList<Float> cositem_quan)
    {
        classify(myitems,cositem_name,cositem_quan);
    }

    ArrayList<String> perish_items = new ArrayList<String>();
    ArrayList<String> nonperish_items = new ArrayList<String>();
    public void classify(ArrayList<Item> myitems,ArrayList<String> cositem_name,ArrayList<Float> cositem_quan)
    {
        for (int i=0;i<cositem_name.size();i++)
        {
            for (Item _item:myitems)
            {
                if(cositem_name.get(i).equals(_item.getname()))
                {
                    if(_item.gettype().equals("Perishable"))
                    {
                        perish_items.add(cositem_name.get(i));
                        _item.setunits(cositem_quan.get(i));
                    }
                    if(_item.gettype().equals("NonPerishable"))
                    {
                        nonperish_items.add(cositem_name.get(i));
                        _item.setunits(cositem_quan.get(i));
                    }
                    
                }
            }
        }
        //System.out.println(perish_items);
        //System.out.println(nonperish_items);
    }
}

class Biller
{
    static float total_cost=0;
    //public Biller(ArrayList<String>cositem_name,ArrayList<String> perish_items,ArrayList<String> nonperish_items,ArrayList<Item> myitems)
    //{
        //this.addperishables(cositeam_name,perish_items, myitems);
        //this.addnonperishables(cositem_name,nonperish_items, myitems);
        //this.packer_shipping(perish_items, nonperish_items, myitems);
    //}
    public Biller()
    {

    }
    ArrayList<Float> costs = new ArrayList<Float>();
    public void addperishables(ArrayList<String> cositem_name,ArrayList<String> perish_items,ArrayList<Item> myitems)
    {
        int k = cositem_name.size();
        for (int i =0 ;i <k;i++)
        {
            float z = 0;
            costs.add(i,z);
        }
        for(String s:perish_items)
        {
            for(Item I:myitems)
            {
                if(s.equals(I.getname()))
                {
                    float cost = I.getprice()*I.getunits();
                    total_cost = total_cost+cost;
                    costs.set(cositem_name.indexOf(s),cost);
                    //cost=costs.get(cositem_name.indexOf(s));
                   // System.out.println(I.getname()+" "+I.getunits()+" "+ cost);
                }
            }
        }
    }

    public void addnonperishables(ArrayList<String> cositem_name,ArrayList<String> nonperish_items,ArrayList<Item> myitems)
    {
        for(String s:nonperish_items)
        {
            for(Item I:myitems)
            {
                if(s.equals(I.getname()))
                {
                    float cost = I.getprice()*I.getunits();
                    total_cost = total_cost+cost;
                    costs.set(cositem_name.indexOf(s),cost);
                    //cost=costs.get(cositem_name.indexOf(s));
                    //System.out.println(I.getname()+" "+I.getunits()+" "+ cost);
                }
            }
        }
    }

    public float packer_shipping(ArrayList<String> perish_items,ArrayList<String> nonperish_items,ArrayList<Item> myitems)
    {
        Packer mypacker = new Packer(perish_items, nonperish_items, myitems);
        //System.out.println("Shipping " + mypacker.shipping_cost);
        total_cost=total_cost+mypacker.shipping_cost;
        return mypacker.shipping_cost;
    }

    public float gettotal()
    {
        return total_cost;
    }
}

class Packer
{
    static float shipping_cost=0;
    
    public Packer(ArrayList<String> perish_items,ArrayList<String> nonperish_items,ArrayList<Item> myitems)
    {
        this.shipping_perish(perish_items, myitems);
        this.shipping_nonperish(nonperish_items, myitems);
    }

    public void shipping_perish(ArrayList<String> perish_items,ArrayList<Item> myitems)
    {
        float totalweight = 0;
        for(String s:perish_items)
        {
            for(Item I:myitems)
            {
                if(s.equals(I.getname()))
                {
                    totalweight = totalweight+I.getunits();
                }
            }
        }
        if(totalweight <= 5)
        {
            shipping_cost=shipping_cost+0;
        }
        if(totalweight > 5)
        {
            shipping_cost = shipping_cost+5*(totalweight-5);
        }
    }

    public void shipping_nonperish(ArrayList<String> nonperish_items,ArrayList<Item> myitems)
    {
        float totalvolume =0;
        for(String s:nonperish_items)
        {
            for(Item I:myitems)
            {
                if(s.equals(I.getname()))
                {
                    totalvolume= totalvolume+ I.getvolume()*I.getunits();
                    //System.out.println(totalvolume); 
                }
            }
        }
        if(totalvolume <= 4)
        {
            shipping_cost = shipping_cost+0;
        }
        if(totalvolume > 4)
        {
            shipping_cost = shipping_cost+(totalvolume-4)*2;
        }
    }
}

class Item
{
    private String name;
    private float price;
    private String type;
    private float units;
    //private float volume;

    //private float volume;
    
    public void setvolume(float ivolume)
    {
       // volume = ivolume;
    }
    public float getvolume()
    {
        //return volume;
        return 0;
    }

    public void setname(String iname)
    {
        name = iname;
    }
    public String getname()
    {
        return name;
    }

    public void setprice(float iprice)
    {
        price = iprice;
    }
    public float getprice()
    {
        return price;
    }

    public void setunits(float iunits)
    {
        units = iunits;
    }
    public float getunits()
    {
        return units;
    }

    public void settype(String itype)
    {
        type = itype;
    }
    public String gettype()
    {
        return type;
    }
    public Item()
    {

    }
}

class Perishables extends Item
{   
    private float weight;
    public void setwt(float iweight)
    {
        weight = iweight;
    }
    public float getwt()
    {
        return weight;
    }

    public Perishables(String iname, float iprice)
    {
        super();
        this.setname(iname);
        this.setprice(iprice);
        this.settype("Perishable");
    }

}

class Non_Perishables extends Item
{   
    private float volume;
    
    public void setvolume(float ivolume)
    {
        volume = ivolume;
    }
    public float getvolume()
    {
        return volume;
    }
    public Non_Perishables(String iname, float iprice,float ivolume)
    {
        super();
        this.setname(iname);
        this.setprice(iprice);
        this.setvolume(ivolume);
        this.settype("NonPerishable");
    }

}
