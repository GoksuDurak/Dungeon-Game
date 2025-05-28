public class Vertex {
    private int x;
    private int y;

    public Vertex(int x,int y) {
        this.x = x;
        this.y = y;
    }
    public int getVertexX()
    {
        return x;
    }
    public int getVertexY()
    {
        return y;
    }
    public void setVertexX(int x)
    {
        this.x = x;
    }
    public void setVertexY(int y)
    {
        this.y = y;
    }
    public boolean equals(Vertex point) {
        if (this.x == point.x && this.y == point.y) {
            return true;
        } else {
            return false;
        }
    }
}
