package edu.cpp.cs445;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


import static org.lwjgl.opengl.GL11.*;

/**
 * Course:      CS 445 Computer Graphics
 * Professor:  Tony Diaz
 * Assignment: Final FProgram
 * 
 * This Program Reads in Polygon definitions from "./coordinates.txt"
 * and draws those polygons.
 * 
 * @author Bryan Thornbury
 * @author 
 * @author
 */
public class Program {
    
    private static final Integer FPS = 10;
    private static final Integer WIDTH = 640;
    private static final Integer HEIGHT = 480;
    
    
    
    //Simply set up the opengl viewport and Display
    public static void setup(){
         
        
         try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
         
         
        glMatrixMode(GL_PROJECTION);
        glOrtho(0 , WIDTH, 0, HEIGHT, 1, -1);
        glMatrixMode(GL_MODELVIEW);
         
       
         
    }
    
    public static void loop(){
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT);
            
            //Do Stuff
            Display.update();
            
            //Sync w/ FPS value
            Display.sync(FPS);
            
            //Listen for escape key to exit
            while (Keyboard.next()) {
                if (Keyboard.getEventKeyState()) {
                    if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
                        System.exit(0);
                    }
                }
            }
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        setup();
        loop();

        Display.destroy();
        System.exit(0);
    }
    
}
