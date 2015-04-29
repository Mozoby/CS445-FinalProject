/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs445;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bryan Thornbury
 */
public abstract class BaseMesh implements IDrawable{
    
    public enum MeshType {
        Triangle(3),
        Quad(4);
        
        private int verticeCount;
        
        private MeshType(int verticeCount){
            this.verticeCount = verticeCount;
        }
        
        public int getVerticeCount(){
            return verticeCount;
        }
    };
    
    protected MeshType type;
    
    protected ArrayList<Coordinate3D> vertList;
    
    public BaseMesh(Coordinate3D[] vertices, MeshType type){
        this.type = type;
        
        this.vertList = new ArrayList<Coordinate3D>(vertices.length);
        for(Coordinate3D c : vertices){
            this.vertList.add(c);
        }
    }
    
    public BaseMesh(List<Coordinate3D> vertices, MeshType type){
        this.type = type;
        
        this.vertList = new ArrayList<Coordinate3D>(vertices.size());
        for(Coordinate3D c : vertices){
            this.vertList.add(c);
        }
    }
    
    public BaseMesh(MeshType type){
        this.type = type;
        
        this.vertList = new ArrayList<Coordinate3D>(type.getVerticeCount()*6);
    }
    
    public void draw(){
        for(Coordinate3D d : this.vertList){
            d.draw();
        }
    }
}
