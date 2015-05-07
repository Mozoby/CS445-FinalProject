/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs445.voxel;

/**
 *
 * @author mozoby
 */
public class VoxelChunk {
    
    private final int CHUNK_SIZE=30;
    
    //private SimplexNoise noise;
    private VoxelBlock blocks[][][];
    
    public VoxelChunk(){
        //noise = new SimplexNoise();
        setupBlocks();
    }
    
    private void setupBlocks(){
        //This method populates each of the blocks;
        
        for (int x=0; x < CHUNK_SIZE; ++x){
            for(int z=0; z< CHUNK_SIZE; ++z){
                
                //Sim
                
            }
        }
    }
}
