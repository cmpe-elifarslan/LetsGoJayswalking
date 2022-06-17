
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Character extends JPanel implements KeyListener{
	private int x,y,w,h;
	private static Rectangle character;
	private BufferedImage image;
	private String sprite;
	public static String characterName=null;

	Character(int x,int y,int w,int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		character=new Rectangle(x,y,w,h);
		setPicture("Images/panda.png");

	} 
	

	public void render(Graphics g){
		Graphics2D g2d=(Graphics2D)g;
		if(this.characterName.equalsIgnoreCase("cat"))
			setPicture("Images/cat.png");
		else if(this.characterName.equalsIgnoreCase("chick"))
			setPicture("Images/chick.png");
		else if(this.characterName.equalsIgnoreCase("monkey"))
			setPicture("Images/monkey.png");
		if(this.characterName.equalsIgnoreCase("penguin"))
			setPicture("Images/penguin.png");
		if(this.characterName.equalsIgnoreCase("panda"))
			setPicture("Images/panda.png");
		g2d.drawImage(image,character.x,character.y,null);
	} 
	public void mover(int speed){
	character.x=character.x+speed;
	}
	public void stopMove(){
		mover(0);
	}
	public int roundTo(int number){
		return number + (number%20);
	}
	
	public void setPicture(String sprite){
		this.sprite=sprite;
		try {
			image= ImageIO.read(getClass().getResourceAsStream(sprite));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public boolean state(){
		return Play.state==Play.STATE.GAME;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(state()){
			int key=e.getKeyCode();
			if (key==KeyEvent.VK_RIGHT || key==KeyEvent.VK_D) {
				if(character.getMaxX()+1*Play.GRID<Play.WIDTH){
					character.x=character.x+1*Play.GRID;
					character.setLocation(character.x, character.y);			
				}
				
			}
			else if(key==KeyEvent.VK_LEFT || key==KeyEvent.VK_A) {
				if(character.getMaxX()-1*Play.GRID>0 ){
					character.x=character.x-1*Play.GRID;
					character.setLocation(character.x, character.y);
				}
				}
			else if(key==KeyEvent.VK_UP || key==KeyEvent.VK_W) {
				if(character.getMaxY()-1*Play.GRID>0)
					character.y=character.y-1*Play.GRID;
				if(character.x%20==0)
					character.setLocation(character.x, character.y);
				else{
					character.setLocation(roundTo(character.x), character.y);
				}
				

			}
			else if(key==KeyEvent.VK_DOWN || key==KeyEvent.VK_S) {
				if(character.getMaxY()+1*Play.GRID<Play.HEIGHT)
					character.y=character.y+1*Play.GRID;
				if(character.x%20==0)
					character.setLocation(character.x, character.y);
				else{
					character.setLocation(roundTo(character.x), character.y);
				}
				

			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static Rectangle getCharacter() {
		return character;
	}
	public void setCharacter(Rectangle character) {
		this.character = character;
	}
	
	
}
