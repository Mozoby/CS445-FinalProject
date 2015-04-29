package edu.cpp.cs445;


import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU; 

import static org.lwjgl.opengl.GL11.*;

/**
 * Course:      CS 445 Computer Graphics
 * Professor:  Tony Diaz
 * Assignment: Final Program
 * 
 * 
 * @author Bryan Thornbury
 * @author Renita Priya
 * @author
 */
public class Program {
    
     private static final Integer FPS = 10;
    // private static final Integer WIDTH = 640;
    // private static final Integer HEIGHT = 480;
    
    private FPCameraController fp = new FPCameraController(0f,0f,0f);
    private DisplayMode displayMode;
    
    
    //Simply set up the opengl viewport and Display
    public void start(){
         
         try {
            createWindow();
            initGL();
            fp.gameLoop(); 
        } catch (Exception e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
         
 
         
    }
    
    private void createWindow() throws Exception{
        Display.setFullscreen(false);
        DisplayMode d[] = Display.getAvailableDisplayModes();
        for (int i = 0; i < d.length; i++) {
            if (d[i].getWidth() == 640 && d[i].getHeight() == 480 && d[i].getBitsPerPixel() == 32) {
                displayMode = d[i];
                break;
            }
        }
        Display.setDisplayMode(displayMode);
        Display.setTitle("My Graphics Window!");
        Display.create();

    }
    
    private void initGL(){
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        
        GLU.gluPerspective(100.0f, (float)displayMode.getWidth()/(float)displayMode.getHeight(), 0.1f, 300.0f);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        // Enable depth test
        glEnable(GL_DEPTH_TEST);
        // Accept fragment if it closer to the camera than the former one
        glDepthFunc(GL_LESS);
            
    }
public static void loop(){
    System.out.println("loop");
     while (!Display.isCloseRequested()) {
         glClear(GL_COLOR_BUFFER_BIT);

         //Do Stuff
         glBegin(GL_QUADS);
         glEnd();
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
        Program ourProject = new Program();
        ourProject.start(); 
    }
    
}
