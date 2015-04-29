
package edu.cpp.cs445;

import static org.lwjgl.opengl.GL11.glVertex3f;

/**
 * Stores 3 floats that represent a point in 3d space.
 * It is also used to store other 3-vector values such
 * as color.
 * 
 * @author Bryan Thornbury
 */
public class Coordinate3D implements IDrawable{
    private float x;
    private float y;
    private float z;
    
    public Coordinate3D(){}
    public Coordinate3D(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY() {
        return y;
    }
    
    public float getZ() {
        return z;
    }
    
    public void setX(float x){
        this.x = x;
    }
    
    public void setY(float y){
        this.y = y;
    }
    
    public void setZ(float z){
        this.z = z;
    }

    @Override
    public void draw() {
        glVertex3f(x,y,z);
    }
}
