/*package com.asu.edu.daos;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CaptchaImage {

    public static void main(String[] args) {
    	int rlength =(int)((Math.random()*100)%3); 
    	//random part of the length
    	int length = ((7 + rlength)); 
    	//total length= 7+random length
    	char c[]=new char[length];
 
    	for (int i = 0; i < length; i++) {
    		int rbase=(int)((Math.random()*100)+1);
    		//System.out.println(rbase);
    		int baseChar = rbase % 62;
    		int charCap = 0;
    		if (baseChar < 26) {
    			charCap = 65 + baseChar;
    		}
    		else if (baseChar < 52){
    			charCap = 97 + (baseChar - 26);
    		}
    		else {
    			charCap = 48 + (baseChar - 52);
    		}
    		
    		c[i]=((char)charCap);
    		
    	}
    	
    	//converting a array of characters into a string
        String text =new String(c);
        
        //creating the image in the java.awt.img class
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        int rfont=(int)(((Math.random()*100)%11)+1); 
        Font font= new Font("Arial", Font.TRUETYPE_FONT, 20);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(text);
        int height = fm.getHeight();
        g2d.dispose();
        
        //Just to track data flow, I have placed random print statements.
System.out.println(text);
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        switch(rfont){
        case 1: g2d.setColor(Color.DARK_GRAY);
        break;
        case 2: g2d.setColor(Color.CYAN);
        break;
        case 3: g2d.setColor(Color.GREEN);
        break;
        case 4: g2d.setColor(Color.LIGHT_GRAY);
        break;
        case 5: g2d.setColor(Color.BLACK);
        break;
        case 6: g2d.setColor(Color.LIGHT_GRAY);
        break;
        case 7: g2d.setColor(Color.MAGENTA);
        break;
        case 8: g2d.setColor(Color.ORANGE);
        break;
        case 9: g2d.setColor(Color.PINK);
        break;
        case 10: g2d.setColor(Color.YELLOW);
        break;
        }
        
        g2d.drawString(text, 0, fm.getAscent());
        g2d.dispose();
        System.out.println(text);
//      Just to track data flow, I have placed random print statements.
        try {
        	
            ImageIO.write(img, "png", new File("Captcha.png"));
            System.out.println(text);
        } catch (IOException ex) {
            ex.printStackTrace();
            //System.out.println("Exception!");
        }
        System.out.println(text);
//      Just to track data flow, I have placed random print statements.
    }

}
*/