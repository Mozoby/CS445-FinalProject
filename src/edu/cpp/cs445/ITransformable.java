/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs445;

/**
 * Interface to standardize transformation accesses, and internal data model.
 * @author Bryan Thornbury
 */
public interface ITransformable {
     
    //Set the Rotation Angle and Pivot Point
    public void setRotation(float angle, Coordinate3D pivot);
    
    //Set the Translation Direction
    public void setTranslation(Coordinate3D translation);
    
    //Set both x and y scale factors
    public void setScale(Coordinate3D scale);
    
    //Return only the rotaiton angle
    public Float getRotationAngle();
    
    //Return Rotation Pivot
    public Coordinate3D getRotationPivot();
    
    //Return Translation Direction
    public Coordinate3D getTranslation();
    
    //Return Scale (x and y)
    public Coordinate3D getScale();
}
