import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame= new JFrame("LET'S GO JAYSWALKING");
		Play game= new Play();	
		frame.add(game);
		frame.setSize(1200,650);
      		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	} 

}
