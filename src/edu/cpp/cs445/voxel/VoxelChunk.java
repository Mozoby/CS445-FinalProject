/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs445.voxel;

import edu.cpp.cs445.Coordinate3D;
import edu.cpp.cs445.mesh.CubeMesh;
import edu.cpp.cs445.mesh.CubeType;
import edu.cpp.cs445.noise.SimplexNoise;
import java.io.IOException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author mozoby
 */
public class VoxelChunk {
    
    private final int CHUNK_SIZE=30;
    private final double PERSISTENCE = .25;
    private final float CUBE_SIZE = .1f;
    
    private Texture terrainTexture;
    
    //private SimplexNoise noise;
    private CubeMesh blocks[][][];
    private SimplexNoise noise;
    
    public VoxelChunk(){
        long seed = System.currentTimeMillis();
        noise = new SimplexNoise(32, PERSISTENCE, (int) seed);
        
        try{
            terrainTexture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("resources/terrain.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        
        setupBlocks();
    }
    
    private void setupBlocks(){
        //This method populates each of the blocks;
        blocks = new CubeMesh[CHUNK_SIZE][CHUNK_SIZE][CHUNK_SIZE];
        
        for (int x=0; x < CHUNK_SIZE; ++x){
            for(int z=0; z< CHUNK_SIZE; ++z){
                double height = noise.getNoise(x, z) + 1;
                
                setupColumn(x,z,height);
            }
        }
    }
    
    private CubeType getType(int cubeHeight){
        
        switch(cubeHeight){
            case 0: case 1:
                return CubeType.Bedrock;
            
            case 2: case 3:
                return CubeType.Rock;
            
            case 4: case 5: 
                return CubeType.Sand;
            
            default:
                return CubeType.Dirt;
        }
    }
    
    private void setupColumn(int i, int k, double height){
        float x  = CUBE_SIZE * i;
        float z  = CUBE_SIZE * k;
                    
        if(height >= CHUNK_SIZE){
            height = CHUNK_SIZE - 1;
        }
        
        int maxJ = (int) (height/CUBE_SIZE);
        for(int j = 0; j <= maxJ; j += 1){
            float y = CUBE_SIZE * j;
            
            CubeType type;
            if(j == maxJ){
                type = CubeType.Grass;
            }else{
                type = getType(j);
            }
            
            blocks[i][j][k] = new CubeMesh(new Coordinate3D(x,y,z),CUBE_SIZE, type);
            blocks[i][j][k].setTexture(terrainTexture, 64);
        }
        
    }
    
    public void draw(){
        for (int x=0; x < CHUNK_SIZE; ++x){
            for(int y=0; y< CHUNK_SIZE; ++y){
                for(int z = 0; z < CHUNK_SIZE;++z){
                    if(blocks[x][y][z] != null){
                        blocks[x][y][z].draw();
                    }
                }            
            }
        }
    }
}
