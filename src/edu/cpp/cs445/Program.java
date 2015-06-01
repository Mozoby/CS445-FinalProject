package edu.cpp.cs445;


import org.lwjgl.BufferUtils;
import java.nio.FloatBuffer; 
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
    
    private FPCameraController fp;
    private DisplayMode displayMode;
    
    private FloatBuffer lightPos;
    private FloatBuffer wLight; 
    
    
    //Simply set up the opengl viewport and Display
    public void start(){
        
         try {
            createWindow();
            initGL();
        } catch (Exception e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
         
         
        fp = new FPCameraController(0f,0f,-5f);
        
        try {
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
    
    private void initLighting(){
        lightPos = BufferUtils.createFloatBuffer(4);
        lightPos.put(0.0f).put(0.0f).put(0.0f).put(1.0f).flip();
        
        wLight = BufferUtils.createFloatBuffer(4);
        wLight.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip(); 
    }
    
    private void initGL(){
        glEnable(GL_TEXTURE_2D);
        
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
            
        initLighting(); 
        glLight(GL_LIGHT0, GL_POSITION, lightPos);
        glLight(GL_LIGHT0, GL_SPECULAR, wLight);
        glLight(GL_LIGHT0, GL_DIFFUSE, wLight);
        glLight(GL_LIGHT0, GL_AMBIENT, wLight); 
        
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0); 
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
