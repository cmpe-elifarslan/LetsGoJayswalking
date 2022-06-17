

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

import java.awt.event.KeyEvent;


public class Car {
	private Rectangle car;
	public int speed;
	private int x,y,w,h;
	private BufferedImage image;

	
	Car(int x,int y,int w,int h,int speed){
		this.setX(x);
		this.y=y;
		this.w=w;
		this.h=h;
		this.speed=speed;	
		setCarType();
		car=new Rectangle(x,y,w,h);
	}
	
	public void setCarType(){
		Random rand= new Random();
		int temp= rand.nextInt(4);
		if(speed>0){
			if(temp==1 || temp==3)
				changeSprite("Images/redright.png");
			else 
				changeSprite("Images/blueright.png");
		}
		else{
			if(temp==1  || temp==3)
				changeSprite("Images/blueleft.png");
			else 
				changeSprite("Images/redleft.png");
		}
	}
	
	public void changeSprite(String sprite){
		try {
			image= ImageIO.read(getClass().getResourceAsStream(sprite));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void render(Graphics g){
		Graphics2D g2d= (Graphics2D)g;
		if(this.speed>0)
		g2d.drawImage(image, car.x-25, car.y, null);
		else 
			g2d.drawImage(image, car.x, car.y, null);
		
		carAction();
		
		if(isOut()){
			if(car.x>1200)
				car.x=-40; 
			else if(car.x<1200)
				car.x=Play.WIDTH;
		}
	}
	public void carAction(){
			car.x=car.x+speed;
	}
	public boolean isOut(){
		return car.getMinX()>=Play.WIDTH||car.getMaxX()<=0;
	}
	public Rectangle getCar() {
		return car;
	}
	public void setCar(Rectangle car) {
		this.car = car;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

}
