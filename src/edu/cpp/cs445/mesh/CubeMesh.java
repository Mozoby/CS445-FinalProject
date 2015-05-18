 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs445.mesh;

import edu.cpp.cs445.Coordinate2D;
import edu.cpp.cs445.Coordinate3D;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author BryanThornbury
 */
public class CubeMesh extends BaseMesh implements ITransformable{
    
    private static int[] textureMap;
    
    static {
        // 0 - Front
        // 1 - Top
        // 2 - Back
        // 3 - Bottom
        // 4 - Left
        // 5 - Right
        textureMap = new int[6];
        
        textureMap[0] = 0;
        textureMap[1] = 1;
        textureMap[2] = 0;
        textureMap[3] = 2;
        textureMap[4] = 0;
        textureMap[5] = 0;
    }
    
    private Texture texture;
    private int textureCubeSize; // T
    private Coordinate2D[][] textureVertices;
    
    private CubeType type;
    
    private Coordinate3D scale;
    private Coordinate3D translation;
    private Float rotationAngle;
    private Coordinate3D rotationPivot;
    
    private Coordinate3D[] colors;
    
    //The Texture map holds the indice
    // in the texture array for each
    // face of the cube.
    
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
    
    
    
    public CubeMesh(Coordinate3D leftBottomFront, float length, float height, float depth, CubeType type){
        this(computeVertices(leftBottomFront, length, height, depth), type);
    }
    
    public CubeMesh(Coordinate3D leftBottomFront, float size, CubeType type){
        this(computeVertices(leftBottomFront, size, size, size), type);
    }
    
    public CubeMesh(Coordinate3D[] vertices, CubeType type){
        super(vertices, MeshType.Quad);
        
        this.type = type;
        
        //1 for each face
      
        colors = new Coordinate3D[8];
        for(int i = 0; i < 8; i++){
            float r = (i & 4);
            float g = (i & 2);
            float b = (i & 1);
            
            colors[i] = new Coordinate3D(r, g, b);
        }
    }
    
    
    private void computeTextureVertices(){
        int T = this.textureCubeSize;
        int y = this.type.getTextureYIndex();
        
        int bY = T * y;
        int tY = bY + T;
        
        float width = texture.getTextureWidth();
        float height = texture.getTextureHeight();
        
        //In same order as textureMap
        this.textureVertices = new Coordinate2D[6][4];
        
        for(int i = 0; i < 4; ++i){
            int lX = textureMap[i] * T;
            int rX = lX + T;
            
            textureVertices[i][0] = new Coordinate2D(lX / width, bY / height);
            textureVertices[i][1] = new Coordinate2D(rX / width, bY / height);
            textureVertices[i][2] = new Coordinate2D(lX / width, tY / height);
            textureVertices[i][3] = new Coordinate2D(rX / width, tY / height);
        }
        
        for(int i = 4; i < 6; ++i){
            int lX = textureMap[i] * T;
            int rX = lX + T;
            
            
            textureVertices[i][0] = new Coordinate2D(lX / width, tY / height);
            textureVertices[i][1] = new Coordinate2D(lX / width, bY / height);
            textureVertices[i][2] = new Coordinate2D(rX / width, bY / height);
            textureVertices[i][3] = new Coordinate2D(rX / width, tY / height);
        }
    }
    
    @Override
    public void draw(){
        //Custom cube draw algo
        //Condenses 24 vertices of 4 different quads into only 8
        int c = 1;
        this.texture.bind();
        for(int b = 0; b < 8; b+=2){
            
            //glColor3f(colors[c].getX(), colors[c].getY(), colors[c].getZ());
            
            glTexCoord2f(textureVertices[c-1][0].getX(), textureVertices[c-1][0].getY());
            vertList.get(b).draw();
            
            glTexCoord2f(textureVertices[c-1][1].getX(), textureVertices[c-1][1].getY());
            vertList.get(b + 1).draw();
            
            glTexCoord2f(textureVertices[c-1][2].getX(), textureVertices[c-1][2].getY());
            vertList.get((b + 3) % vertList.size()).draw();
            
            glTexCoord2f(textureVertices[c-1][3].getX(), textureVertices[c-1][3].getY());
            vertList.get((b + 2) % vertList.size()).draw();
            c++;
        }
        
        //left and rigth
        for(int b = 0; b < 2; b++){
            //glColor3f(colors[c].getX(), colors[c].getY(), colors[c].getZ());
            
            glTexCoord2f(textureVertices[c-1][0].getX(), textureVertices[c-1][0].getY());
            vertList.get(b).draw();
            
            glTexCoord2f(textureVertices[c-1][1].getX(), textureVertices[c-1][1].getY());
            vertList.get(b + 2).draw();
            
            glTexCoord2f(textureVertices[c-1][2].getX(), textureVertices[c-1][2].getY());
            vertList.get(b + 4).draw();
            
            glTexCoord2f(textureVertices[c-1][3].getX(), textureVertices[c-1][3].getY());
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

    @Override
    public void setTexture(Texture texture, int textureCubeSize) {
        this.texture = texture;
        this.textureCubeSize = textureCubeSize;
        computeTextureVertices();
    }
    
    
}
