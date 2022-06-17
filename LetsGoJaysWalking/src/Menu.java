
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;




public class Menu implements MouseMotionListener,MouseListener,KeyListener{
	private Rectangle playButton;
	private Rectangle helpButton;
	private Rectangle helpBackButton;
	private Rectangle quitButton;
	private Rectangle gameOverQuitButton;
	private Rectangle chickButton;
	private Rectangle pandaButton;
	private Rectangle catButton;
	private Rectangle penguinButton;
	private Rectangle monkeyButton;
	private String name;
	
	private BufferedImage map;
	private BufferedImage gameover;
	BufferedImage image[];
	private Color playButtonColor;
	private Color helpButtonColor;
	private Color helpBackButtonColor;
	private Color quitButtonColor;
	private Color gameOverQuitButtonColor;
	private Color character1Color;
	private Color character2Color;
	private Color character3Color;
	private Color character4Color;
	private Color character5Color;
	private Font font;
	private Font font2;
	private Font font3;
	private Font font4;
private Font animalFont;
	
	Menu(){
		
		playButton= new Rectangle(500, 250, 119, 55);
		helpButton= new Rectangle(500, 350, 110, 55);
		quitButton= new Rectangle(500, 450, 110, 55);
		helpBackButton= new Rectangle(500, 400, 135, 55);
		chickButton = new Rectangle(120,150,110,55);
		 pandaButton= new Rectangle(320,150,110,55);;
		 catButton = new Rectangle(520,150,110,55);
	 penguinButton = new Rectangle(720,150,110,55);
	 monkeyButton = new Rectangle(920,150,110,55);
		gameOverQuitButton=new Rectangle(500, 450, 110, 55);
		image= new BufferedImage[10];
		loadMap();
		loadGameOver();
	}
	
	public void render(Graphics g){
		Graphics2D g2d= (Graphics2D)g;
		if(Play.state==Play.STATE.MENU){
			font= new Font("Century Gothic",Font.BOLD,50);
			g2d.setFont(font);
			g2d.drawImage(map, 0, 0, null);
			animalFont=new Font("Century Gothic",Font.BOLD,20);
			
			g2d.setFont(animalFont);
			g2d.setColor(Color.BLACK);
			
			g2d.drawString("WELCOME TO THE GAME, PLEASE CHOOSE A CHARACTER", 340, 80);
			g2d.draw(chickButton);
			g2d.setColor(character1Color);
			g2d.drawString("CHICK", chickButton.x+20, chickButton.y+30); 
			
			g2d.setColor(character2Color);
			g2d.draw(pandaButton);
			g2d.drawString("PANDA", pandaButton.x+20, pandaButton.y+30);

			g2d.setColor(character3Color);
			g2d.draw(catButton);
			g2d.drawString("CAT", catButton.x+30, catButton.y+30);
			
			g2d.setColor(character4Color);
			g2d.draw(penguinButton);
			g2d.drawString("PENGUIN", penguinButton.x+10, penguinButton.y+30);
			
			g2d.setColor(character5Color);
			g2d.draw(monkeyButton);
			g2d.drawString("MONKEY", monkeyButton.x+15, monkeyButton.y+30);
			
			g2d.setFont(font);
			g2d.setColor(playButtonColor);		
			g2d.draw(playButton);
			g2d.drawString("PLAY", playButton.x, playButton.y+47);

			g2d.setColor(helpButtonColor);
			g2d.draw(helpButton);
			g2d.drawString("HELP", helpButton.x, helpButton.y+47);

			g2d.setColor(quitButtonColor);
			g2d.draw(quitButton);
			g2d.drawString("QUIT", quitButton.x, quitButton.y+47);
		}
		if(Play.state==Play.STATE.HELP){
			g2d.drawImage(map, 0, 0, null);
			
			g2d.setColor(Color.BLACK);
			font2= new Font("Century Gothic",Font.BOLD,25);
			g2d.setFont(font2);
			g2d.drawString("  The aim of the game is to reach the highest score by collecting stars and cross all roads.", 30, helpButton.y-170);
			g2d.drawString("Use the arrow keys or W-A-S-D keys to move the character.", 100, helpButton.y-100);
			
			
			g2d.setColor(helpBackButtonColor);
			g2d.draw(helpBackButton);
			g2d.setFont(font);
			g2d.drawString("BACK", helpBackButton.x, helpBackButton.y+47);
		}		
		if(Play.state==Play.STATE.GAMEOVER) {
			g2d.drawImage(gameover, 0, 0, null);
			g2d.setColor(Color.RED);
			font3= new Font("Century Gothic",Font.BOLD,100);
			g2d.setFont(font4);
			g2d.setColor(Color.BLACK);
			g2d.drawString("TOTAL SCORE:"+Integer.toString(Play.score),400,50);
			g2d.setColor(Color.RED);
			g2d.setFont(font3);
			if(Play.score==2000 && Character.getCharacter().getCenterY()<150)
			g2d.drawString("  YOU WIN!", 250, 280);
			else
			g2d.drawString("GAME OVER." , 250, 280);
			font4= new Font("Century Gothic",Font.BOLD,50);
			g2d.setFont(font4);
			g2d.setColor(Color.BLACK);
			g2d.drawString("NEXT TIME USE THE CROSSWALK!", 170, 330);
			g2d.setColor(quitButtonColor);
			g2d.draw(gameOverQuitButton);
			g2d.drawString("QUIT", gameOverQuitButton.x, gameOverQuitButton.y+47);		
		}
	}
	public void loadMap(){
		try {
		map= ImageIO.read(getClass().getResourceAsStream("Images/newmenuscreen.jpeg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void loadGameOver() {
		try {
		gameover= ImageIO.read(getClass().getResourceAsStream("Images/gameover.jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	public boolean isInsideRect(Rectangle button,int x,int y){
		return ( x >= button.getMinX() && x <= button.getMaxX() ) && ( y >= button.getMinY() && y <= button.getMaxY());
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int mouseX=e.getX();
		int mouseY=e.getY();
		if(isInsideRect(playButton,mouseX,mouseY) && Play.state==Play.STATE.MENU)
		{
			Play.state=Play.STATE.GAME;
		}
		else if(isInsideRect(helpButton,mouseX,mouseY)&&Play.state==Play.STATE.MENU)
		{
			Play.state=Play.STATE.HELP;
		}
		else if(isInsideRect(quitButton, mouseX, mouseY)&&Play.state==Play.STATE.MENU)
		{
			System.exit(1);
		}
		else if(isInsideRect(gameOverQuitButton,mouseX,mouseY) && Play.state==Play.STATE.GAMEOVER)
		{
			System.exit(1);
		}
		
		if(Play.state==Play.STATE.HELP&&isInsideRect(helpBackButton, mouseX, mouseY)){
			Play.state=Play.STATE.MENU;
		}
		if(isInsideRect(pandaButton,mouseX,mouseY) && Play.state==Play.STATE.MENU) {
			Play.character.characterName="panda";
		}
		else if(isInsideRect(chickButton,mouseX,mouseY) && Play.state==Play.STATE.MENU) {
			Play.character.characterName="chick";
		}
		else if(isInsideRect(penguinButton,mouseX,mouseY) && Play.state==Play.STATE.MENU) {
			Play.character.characterName="penguin";
		}
		else if(isInsideRect(monkeyButton,mouseX,mouseY) && Play.state==Play.STATE.MENU) {
			Play.character.characterName="monkey";
		}
		else if(isInsideRect(catButton,mouseX,mouseY) && Play.state==Play.STATE.MENU) {
			Play.character.characterName="cat";
		
		}
	} 

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int mouseX=e.getX();
		int mouseY=e.getY();
		if(isInsideRect(chickButton,mouseX,mouseY)){
			character1Color=Color.YELLOW;
		}
		else{
			character1Color=Color.BLACK;
		}
		if(isInsideRect(pandaButton,mouseX,mouseY)){
			character2Color=Color.CYAN;
		}
		else{
			character2Color=Color.BLACK;
		}
		if(isInsideRect(catButton,mouseX,mouseY)){
			character3Color=Color.ORANGE;
		}
		else{
			character3Color=Color.BLACK;
		}
		if(isInsideRect(penguinButton,mouseX,mouseY)){
			character4Color=Color.PINK;
		}
		else{
			character4Color=Color.BLACK;
		}
		if(isInsideRect(monkeyButton,mouseX,mouseY)){
			character5Color=Color.BLUE;
		}
		else{
			character5Color=Color.BLACK;
		}
		if(isInsideRect(playButton,mouseX,mouseY)){
			playButtonColor=Color.GREEN;
		}
		else{
			playButtonColor=Color.BLACK;
		}
		if(isInsideRect(helpButton, mouseX, mouseY)){
			helpButtonColor=Color.ORANGE;
		}
		else{
			helpButtonColor=Color.BLACK;
		}
		if(isInsideRect(quitButton, mouseX, mouseY)){
			quitButtonColor=Color.RED;
		}
		else{
			quitButtonColor=Color.BLACK;
		}
		if(isInsideRect(helpBackButton, mouseX, mouseY)){
			helpBackButtonColor=Color.RED;
		}
		else{
			helpBackButtonColor=Color.BLACK;
		}
		if(isInsideRect(gameOverQuitButton,mouseX,mouseY)){
			gameOverQuitButtonColor=Color.RED;
		}
		else{
			gameOverQuitButtonColor=Color.BLACK;}
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keycode=e.getKeyCode();
		if(keycode == KeyEvent.VK_ESCAPE)
		    {
			 	System.exit(1);
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
	
}
