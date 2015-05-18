/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cpp.cs445.mesh;

import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Bryan Thornbury
 */
public interface IDrawable {
    public void draw();
    public void setTexture(Texture texture, int textureCubeSize);
}
