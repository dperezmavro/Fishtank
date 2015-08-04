import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.Vector;

public class Aquarium extends JFrame implements Runnable{

	private static final long serialVersionUID = 1L;


	protected Image memoryImage ,
					aquariumImage ,
					explosion ;
	protected Graphics memoryGraphics ;
	protected Image[] fishimg = new Image[2] ;
	protected Thread thread ;
	protected MediaTracker tracker ;
	protected int numberFish = 25, sleepTime = 100 ;
	protected Vector <Fish>  fishes = new  Vector <Fish> (numberFish);
	protected FishChecker fishChecker ;
	private boolean boom = false ;

	
	public static void main(String[] args){

		new Aquarium();		
	}
	
	public Aquarium(){

		this.setTitle("The Aquarium");
		this.addMouseListener(new WindowListenerClass());
		
		tracker = new MediaTracker(this);

		fishimg[0] = Toolkit.getDefaultToolkit().getImage("images/fish1.png");
		tracker.addImage(fishimg[0], 0);

		fishimg[1] = Toolkit.getDefaultToolkit().getImage("images/fish2.gif");
		tracker.addImage(fishimg[1], 0);

		aquariumImage = Toolkit.getDefaultToolkit().getImage("images/tank.jpg");
		tracker.addImage(aquariumImage,0);

		explosion = Toolkit.getDefaultToolkit().getImage("images/explosion.png");
		tracker.addImage(explosion, 0);
		
		
		try{
			tracker.waitForID(0);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}

		this.setSize(aquariumImage.getWidth(this), aquariumImage.getHeight(this));
		this.setResizable(false);
		this.setVisible(true);

		memoryImage = createImage(this.getSize().width , this.getSize().height);
		memoryGraphics = memoryImage.getGraphics();

		thread = new Thread(this);
		thread.start();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void explodeFish(){
		
		boom = true ;
		repaint();
	}
	
	public void paint(Graphics g){

		memoryGraphics.drawImage(aquariumImage, 0, 0, this);

		for(int i = 0 ; i < fishes.size() ; i++){

			fishes.elementAt(i).drawFishImage(memoryGraphics);
		}
		
		if(boom == true ){
			memoryGraphics.drawImage(explosion, FishChecker.fishx, FishChecker.fishy, this);
			boom = false ;
		}
				
		g.drawImage(memoryImage, 0,0,this);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		Rectangle edges = new Rectangle(
				0 + getInsets().left,
				0 + getInsets().top,
				getSize().width - (getInsets().left + getInsets().right),
				getSize().height - (getInsets().top + getInsets().bottom));

		for (int i = 0 ; i < numberFish ; i++){
			 
			fishes.add(new Fish(fishimg[0] , fishimg[1] ,edges , this) );

			try{
				Thread.sleep(20);
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
		
		fishChecker = new FishChecker(fishes);

		fishChecker.setCurrentClick(0,0 );
		Thread t = new Thread (fishChecker);
		t.start();
		
		while(true){

			for(int j = 0 ; j < fishes.size() ; j ++){
				fishes.elementAt(j).swim();
			}

			try{
				Thread.sleep(sleepTime);
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}

			this.repaint();
		}//end while 
	}
	
	public JFrame returnSelf(){
		
		return this ;
	}
	
	class WindowListenerClass implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			
			int x = arg0.getX();
			int y = arg0.getY();
			
			fishChecker.setCurrentClick(x, y);
			if (fishChecker.checkFish()){
				explodeFish();
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
