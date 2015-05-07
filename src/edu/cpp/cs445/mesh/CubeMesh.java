 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs445.mesh;

import edu.cpp.cs445.Coordinate3D;
import static org.lwjgl.opengl.GL11.glColor3f;

/**
 *
 * @author BryanThornbury
 */
public class CubeMesh extends BaseMesh implements ITransformable{
    
    private Coordinate3D scale;
    private Coordinate3D translation;
    private Float rotationAngle;
    private Coordinate3D rotationPivot;
    
    private Coordinate3D[] colors;
    
    //lBoF : left bottom front (x y z)
    // r : right
    // t : top
    // f : front
    // bo : bottom
    // ba : back
    private static Coordinate3D[] computeVertices(Coordinate3D lbof, float l, float h, float d){
        Coordinate3D[] vertices= new Coordinate3D[8];
        
        /*
         4---5
        /|  /|
       2---3 |
       | 6-|-7
       |/  |/ 
       0---1
        
        */
        
        vertices[0] = lbof;
        vertices[1] = new Coordinate3D(lbof.getX() + l, lbof.getY(), lbof.getZ());
        
        vertices[2] = new Coordinate3D(lbof.getX(), lbof.getY() + h, lbof.getZ());
        vertices[3] = new Coordinate3D(lbof.getX() + l, lbof.getY() + h, lbof.getZ());
        
        vertices[4] = new Coordinate3D(lbof.getX(), lbof.getY() + h, lbof.getZ() + d);
        vertices[5] = new Coordinate3D(lbof.getX() + l, lbof.getY() + h, lbof.getZ() + d);
        
        vertices[6] = new Coordinate3D(lbof.getX(), lbof.getY(), lbof.getZ() + d);
        vertices[7] = new Coordinate3D(lbof.getX() + l, lbof.getY(), lbof.getZ() + d);
        
        return vertices;
    }
    
    public CubeMesh(Coordinate3D leftBottomFront, float length, float height, float depth){
        this(computeVertices(leftBottomFront, length, height, depth));
    }
    
    public CubeMesh(Coordinate3D[] vertices){
        super(vertices, MeshType.Quad);
        
        //1 for each face
      
        colors = new Coordinate3D[8];
        for(int i = 0; i < 8; i++){
            float r = (i & 4);
            float g = (i & 2);
            float b = (i & 1);
            
            colors[i] = new Coordinate3D(r, g, b);
        }
    }
    
    
    
    @Override
    public void draw(){
        //Custom cube draw algo
        //Condenses 24 vertices of 4 different quads into only 8
        int c = 1;
        
        for(int b = 0; b < 8; b+=2){
            
            glColor3f(colors[c].getX(), colors[c].getY(), colors[c].getZ());
            vertList.get(b).draw();
            vertList.get(b + 1).draw();
            vertList.get((b + 3) % vertList.size()).draw();
            vertList.get((b + 2) % vertList.size()).draw();
            c++;
        }
        
        //left and rigth
        for(int b = 0; b < 2; b++){
            glColor3f(colors[c].getX(), colors[c].getY(), colors[c].getZ());
            vertList.get(b).draw();
            vertList.get(b + 2).draw();
            vertList.get(b + 4).draw();
            vertList.get(b + 6).draw();
            c++;
        }
    }

    @Override
    public void setRotation(float angle, Coordinate3D pivot) {
        this.rotationAngle = angle;
        this.rotationPivot = pivot;
    }

    @Override
    public void setTranslation(Coordinate3D translation) {
        this.translation = translation;
    }

    @Override
    public void setScale(Coordinate3D scale) {
        this.scale = scale;
    }

    @Override
    public Float getRotationAngle() {
        return this.rotationAngle;
    }

    @Override
    public Coordinate3D getRotationPivot() {
        return this.rotationPivot;    
    }

    @Override
    public Coordinate3D getTranslation() {
        return this.getTranslation();
    }

    @Override
    public Coordinate3D getScale() {
        return this.scale;
   }
    
}
