import java.util.Vector;


public class FishChecker implements Runnable{
	
	private Vector <Fish> fishes ;
	private int xpos , ypos ;
	static int fishx ,fishy;
	
	public FishChecker(Vector <Fish> fi){
		
		fishes = fi ;
	}
	
	public void setCurrentClick(int x , int y ){
		
		xpos = x ;
		ypos = y ;
	}
	
	@Override
	public void run() {
		checkFish();
	}

	public boolean checkFish(){
		
		for(int i = (fishes.size() -1) ; i >= 0 ; i--){
			
			if(xpos > fishes.get(i).getStartX() &&
				ypos > fishes.get(i).getStartY() &&
				xpos < fishes.get(i).getEndX() &&
				ypos < fishes.get(i).getEndY()){
									
					
					fishx = fishes.get(i).getStartX();
					fishy = fishes.get(i).getStartY();
					
					fishes.remove(i);
					//this break is to remove just one fish, if we remove it, all fish in point x,y will get removed 
					return true ;
			}
		}
		
		return false ;
	}
}
