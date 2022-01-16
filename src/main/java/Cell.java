import java.util.Objects;

public class Cell extends Main{
    private final int x, y;
    int  numLiveNeighbor;
    private Boolean isLive;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.isLive = false;
        this.numLiveNeighbor = 0;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public Boolean getLive() {
        return isLive;
    }

    public int getNumLiveNeighbor() {
        return numLiveNeighbor;
    }

    public void setLive(Boolean live) {
        isLive = live;
    }

    public void setNumLiveNeighbor(int c){
        numLiveNeighbor = c;
    }
}
