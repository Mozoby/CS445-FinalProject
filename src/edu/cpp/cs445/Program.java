package edu.cpp.cs445;


import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


import static org.lwjgl.opengl.GL11.*;

/**
 * Course:      CS 445 Computer Graphics
 * Professor:  Tony Diaz
 * Assignment: Final Program
 * 
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
        glOrtho(-WIDTH/2 , WIDTH/2, -HEIGHT/2, HEIGHT/2, 100, -100);
        glMatrixMode(GL_MODELVIEW);
         
       
         
    }
    
    public static void loop(){
        System.out.println("loop");
        Cube c = new Cube(new Coordinate3D(-50,-50,-10), 100, 100, 100);
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT);
            
            //Do Stuff
            glLoadIdentity();
            glRotatef(33f,0f,0f,1f);
            glRotatef(10f, 1,0,0);
            
            glBegin(GL_QUADS);
            //glColor3f(1,1,1);
            c.draw();
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
       
        setup();
        loop();

        Display.destroy();
        System.exit(0);
    }
    
}
