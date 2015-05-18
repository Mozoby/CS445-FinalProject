/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs445.mesh;

/**
 *  This Enum exists to determine between
 *  cube types for the purposes of drawing 
 *  and texture.
 * 
 *  I've opted for explicit Texture Y Indexes
 *  over the implicit ordering of the enum
 *  for ease of changing the enum if need be.
 * 
 * @author Bryan Thornbury
 */
public enum CubeType {
    Grass(0),
    Dirt(1),
    Sand(2),
    Water(3),
    Rock(4),
    Bedrock(5);
    
    private int textureYIndex;
    
    private CubeType(int textureYIndex) {
        this.textureYIndex = textureYIndex;
    }
    
    public int getTextureYIndex(){
        return this.textureYIndex;
    }
}
