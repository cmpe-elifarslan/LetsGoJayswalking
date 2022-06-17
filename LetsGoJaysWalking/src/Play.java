


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Play extends JPanel implements Runnable{
	public static int GRID=20;
	public static int ERRORY=10;
	public static int ERRORX=16;
	public static int WIDTH=1200+ERRORX;
	public static int HEIGHT=650-ERRORY;
	public enum STATE{
		MENU,
		GAME,
		HELP,
		GAMEOVER
	};
	public static STATE state=STATE.MENU;
	
	private Menu menu;
	private BufferedImage image;
	public static Character character;
	private Extra extra1[];
	private Extra extra2[];
	private Car cars1[];
	private Car cars2[];
	private Car car3[];
	private Car cars4[];
	private Car cars5[];
	private Car cars6[];
	private Car cars7[];
	private Car cars8[];
	
	private int deaths=0;
	public static int score=0;
	private int health=3;
	Play(){
		character= new Character(550,HEIGHT-70,25,25);
		menu= new Menu();
		cars1= new Car[5];
		cars2= new Car[5];
		car3= new Car[4];
		cars4=new Car[6];
		cars5=new Car[4];
		cars6=new Car[5];
		cars7=new Car[6];
		cars8=new Car[5];
		extra1=new Extra[20];
		extra2=new Extra[2];
		loadMap();
		playmusic(1);
		initializeGame();
		start();
		this.addKeyListener(menu);
		this.addMouseListener(menu);
		this.addMouseMotionListener(menu);
		this.addKeyListener(character);
		setFocusable(true);
	}

	
	public void initializeGame(){
		Random a= new Random();
		int randomx,randomy;
		//create car with x,y,w,h and speed
		for(int i = 0; i<extra1.length;i++) 
		{
			randomx=a.nextInt(4);
			randomy=a.nextInt(4);
				extra1[i]= new Extra(5+randomx*320,150+randomy*100,25,25,"star");
			
			}
		
			for(int i = 0; i<extra2.length;i++)
			{
				randomx=a.nextInt(4);
				randomy=a.nextInt(4);
				extra2[i]= new Extra(30+randomx*330,150+randomy*115,25,25,"hearth");
				}
		for(int i=0;i<cars1.length;i++){  
			
			cars1[i]= new Car(0+i*270,HEIGHT-140,40,40,2);
			}
		for(int i=0;i<cars2.length;i++){
			cars2[i]= new Car(0+i*200,HEIGHT-195,40,40,-2);
		}
		for(int i=0;i<car3.length;i++){
			car3[i]= new Car(0+i*250,HEIGHT-250,40,40,2);
		}
		for(int i=0;i<cars4.length;i++){
			cars4[i]= new Car(0+i*220,HEIGHT-305,40,40,-2);
		}
		for(int i=0;i<cars5.length;i++){
			cars5[i]= new Car(0+i*185,HEIGHT-435,40,40,2);
		}
		for(int i=0;i<cars6.length;i++){
			cars6[i]= new Car(0+i*230,HEIGHT-490,40,40,-3);
		}
		for(int i=0;i<cars7.length;i++){
			cars7[i]= new Car(0+i*220,HEIGHT-545,40,40,2);
		}
		for(int i=0;i<cars8.length;i++){
			cars8[i]= new Car(0+i*220,HEIGHT-595,40,40,-2);
		}
		
	}
	
	

	public void controlCrash(){
		
		for(Extra extra : extra1)
			if(!extra.intersect){
			if(character.getCharacter().getBounds().intersects(extra.getExtra().getBounds())) {
				score=score+100;
				extra.intersect=true;
			extra.undraw();
			playpointup();
			}}
		
		for(Extra extra : extra2)
			if(!extra.intersect) {	
			if(character.getCharacter().getBounds().intersects(extra.getExtra().getBounds())) {
			++health;
			extra.intersect=true;
			extra.undraw();
			playhealthup();
			}}
		for(Car car:cars1){
			if(character.getCharacter().getBounds().intersects(car.getCar().getBounds())){
				health--;
				end();
				reset();
				playcrash();
			}
		}
		for(Car car:cars2){
			if(character.getCharacter().getBounds().intersects(car.getCar().getBounds())){
				health--;
				end();
				reset();
				playcrash();
				}
		}
		for(Car car:car3){
			if(character.getCharacter().getBounds().intersects(car.getCar().getBounds())){
				health--;
				end();
				reset();
				playcrash();
			}
		}
		for(Car car:cars4){
			if(character.getCharacter().getBounds().intersects(car.getCar().getBounds())){
				health--;
				end();
				reset();
				playcrash();
				}
		}
		for(Car car:cars5){
			if(character.getCharacter().getBounds().intersects(car.getCar().getBounds())){
				health--;
				end();
				reset();
				playcrash();
			}
		}
		for(Car car:cars6){
			if(character.getCharacter().getBounds().intersects(car.getCar().getBounds())){
				health--;
				end();
				reset();
				playcrash();
			}
		}
		for(Car car:cars7){
			if(character.getCharacter().getBounds().intersects(car.getCar().getBounds())){
				health--;
				end();
				reset();
				playcrash();
			}
		}
		for(Car car:cars8){
			if(character.getCharacter().getBounds().intersects(car.getCar().getBounds())){
				health--;
				end();
				reset();
				playcrash();
			}
		}
	}
	public void end() {
		if(health==0 || (score==2000 && character.getCharacter().getCenterY()<HEIGHT-600) ) {
			state=STATE.GAMEOVER;
			}
	
	}
	
	public void loadMap(){
		try {
			image= ImageIO.read(getClass().getResourceAsStream("Images/newmap2.jpeg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	public void score(){
		if(character.getCharacter().getCenterY()<HEIGHT-600){
			state=STATE.GAMEOVER;		}
	}
	public void showInfo(Graphics g){
		Graphics2D g2d= (Graphics2D)g;
		g.setColor(Color.WHITE);
		g2d.setFont(new Font("Arial", Font.PLAIN, 25));
		g2d.drawString("Deaths:"+Integer.toString(deaths), 285, 35);
		g2d.drawString("Score:"+Integer.toString(score), 410,35);
		g2d.drawString("Lives:"+Integer.toString(health), 560, 35);
		g2d.drawString("Character:"+character.characterName, 700 , 35);	
	}
	public void reset(){
		deaths++;
		character.getCharacter().x=550;
		character.getCharacter().y=HEIGHT-70;
	}
	public void AntiAliasing(Graphics g){
		Graphics2D g2d= (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	public void renderGame(Graphics g){
		g.drawImage(image, 0, 0, null);
		character.render(g);
		for(Extra extra:extra1)
			if(!extra.intersect)
			extra.render(g);
		for(Extra extra:extra2)
			if(!extra.intersect)
				extra.render(g);		
		for(Car car: cars1)
			car.render(g);
		for(Car car: cars2)
			car.render(g);
		for(Car car: car3)
			car.render(g);
		for(Car car: cars4)
			car.render(g);
		for(Car car: cars5)
			car.render(g);
		for(Car car: cars6)
			car.render(g);
		for(Car car: cars7)
			car.render(g);
		for(Car car: cars8)
			car.render(g);
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		AntiAliasing(g);
		if(state==STATE.MENU||state==STATE.HELP|| state==STATE.GAMEOVER){
			menu.render(g);
		}
		else if(state==STATE.GAME){
		renderGame(g);
		score();
		showInfo(g);
		controlCrash();
		
		
		
		}
	}

    public void start() {   			
        Thread thread = new Thread(this);
        thread.start();
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	
	}
	
Sound sound = new Sound();
	
	public void playmusic(int i) {
		if(state==STATE.GAME) {
			stopMusic(1);
		}
		sound.setFile(1);
		sound.playSound();
        sound.loop();   
	}
	
	public void stopMusic(int i) {
		sound.setFile(i);
		sound.stop();
		
	}
	
	public void playcrash() {
		sound.setFile(0);
		sound.playSound();
	}
	
	public void playhealthup() {
		sound.setFile(2);
		sound.playSound();
	}
	
	public void playpointup() {
		sound.setFile(3);
		sound.playSound();
	}

}
