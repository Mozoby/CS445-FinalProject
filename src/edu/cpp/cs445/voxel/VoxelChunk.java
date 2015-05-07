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
    private final double PERSISTENCE = 1.1;
    
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
                
                double height = noise.getNoise(x, z);
                
                setupColumn(x,z,height);
            }
        }
    }
    
    private void setupColumn(int x, int z, double height){
        if(height >= CHUNK_SIZE){
            height = CHUNK_SIZE - 1;
        }
        
        if (height < 1){
            height = 1;
        }
        
        for(int y = 0; y < height; ++y){
            blocks[x][y][z] = new CubeMesh(new Coordinate3D(x,y,z),1);
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
