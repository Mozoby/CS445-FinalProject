/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs445.voxel;

import edu.cpp.cs445.Coordinate3D;
import edu.cpp.cs445.mesh.CubeMesh;
import edu.cpp.cs445.noise.SimplexNoise;

/**
 *
 * @author mozoby
 */
public class VoxelChunk {
    
    private final int CHUNK_SIZE=30;
    private final double PERSISTENCE = .5;
    private final float CUBE_SIZE = .25f;
    
    //private SimplexNoise noise;
    private CubeMesh blocks[][][];
    private SimplexNoise noise;
    
    public VoxelChunk(){
        long seed = System.currentTimeMillis();
        noise = new SimplexNoise(32, PERSISTENCE, (int) seed);
        setupBlocks();
    }
    
    private void setupBlocks(){
        //This method populates each of the blocks;
        blocks = new CubeMesh[CHUNK_SIZE][CHUNK_SIZE][CHUNK_SIZE];
        
        for (int x=0; x < CHUNK_SIZE; ++x){
            for(int z=0; z< CHUNK_SIZE; ++z){

                double height = noise.getNoise(x , z ) + 1;
                
                setupColumn(x,z,height);
            }
        }
    }
    
    private void setupColumn(int i, int k, double height){
        float x  = CUBE_SIZE * i;
        float z  = CUBE_SIZE * k;
                    
        if(height >= CHUNK_SIZE){
            height = CHUNK_SIZE - 1;
        }
        
        
        for(int j = 0; j <= (height/CUBE_SIZE); j += 1){
            float y = CUBE_SIZE * j;
            blocks[i][j][k] = new CubeMesh(new Coordinate3D(x,y,z),CUBE_SIZE);
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
