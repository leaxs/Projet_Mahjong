package fr.leaxs.Mahjong;

public class Tuile 
{
    private final int type;

    public Tuile(int type) 
    {
        this.type = type;
    }

    public boolean equals(Tuile tuile)
    {
        return this.type == tuile.type;
    }

    @Override
    public String toString() 
    {
        return "Type de la tuile : "+type;
    }    

    public int getType() {
        return type;
    }
    
    
}
