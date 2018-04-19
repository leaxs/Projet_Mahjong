package fr.leaxs.Mahjong;

class SelectionnedTuile 
{
    private int x;
    private int y;
    private int z;
    private Tuile tuile;

    public SelectionnedTuile(int x, int y, int z, Tuile tuile) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.tuile = tuile;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Tuile getTuile() {
        return tuile;
    }
    
}
