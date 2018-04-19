package fr.leaxs.Mahjong;

public class Tuile 
{
    private final Type_Tuile type;

    public Tuile(Type_Tuile type) 
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
        return type.toString();
    }    
}
