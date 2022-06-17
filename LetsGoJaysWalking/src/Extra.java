import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;
public class Extra {
	
private Rectangle extra;
private BufferedImage image;
private String type;
private int x;
private int y;
private int width;
private int height;
public boolean intersect=false;

Extra(int x,int y,int w,int h,String type){
	this.setX(x);
	this.setY(y);
	this.width=w;
	this.height=h;
	this.type=type;
	
	extra=new Rectangle(x,y,width,height);
	setImage();
}

public void undraw() {
	if(!intersect) {
	this.setX(0);
	this.setY(0);
	this.width=70;
	this.height=100;}
}
public void setImage() {
	if(this.type.equalsIgnoreCase("hearth"))
		changeSprite("Images/hearth.png");
	else if(this.type.equalsIgnoreCase("star"))
		changeSprite("Images/star.png");
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
	g2d.drawImage(image, extra.x, extra.y, null);
}
public Rectangle getExtra() {
	return extra;
}
public void setExtra(Rectangle extra) {
	this.extra=extra;
}

public int getX() {
	return x;
}

public void setX(int x) {
	this.x = x;
}

public int getY() {
	return y;
}

public void setY(int y) {
	this.y = y;
}
}
