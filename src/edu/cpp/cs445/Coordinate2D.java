
package edu.cpp.cs445;

/**
 * Stores 2 floats that represent a point in 2d space.
 * @author Bryan Thornbury
 */
public class Coordinate2D {
    private float x;
    private float y;
    
    public Coordinate2D(){}
    
    public Coordinate2D(Coordinate2D coord){
        this.x = coord.x;
        this.y = coord.y;
    }
    public Coordinate2D(float x, float y){
        this.x = x;
        this.y = y;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public void setX(float x){
        this.x = x;
    }
    
    public void setY(float y){
        this.y = y;
    }
}
