import java.awt.*;
import java.util.Random;

import javax.swing.*;

public class Fish {

	protected Image lookingRight , lookingLeft;
	protected Rectangle container ;
	protected JFrame frame ;
	protected Point location , velocity ;
	protected Random random ;
	
	
	public Fish(Image r ,Image l, Rectangle cont , Component fr){
		
		random = new Random(System.currentTimeMillis());
		lookingRight = r ;
		lookingLeft = l ;
		container = cont ;
		frame = (JFrame) fr ;	
		
		location = new Point(100
	            + (Math.abs(random.nextInt()) % 300),
	            100 + (Math.abs(100 + random.nextInt()) % 100));
		
        velocity = new Point(random.nextInt() % 8, random.nextInt() % 8);
    }
	
	public void swim(){
		
		   if(random.nextInt() % 7 <= 1){

		        velocity.x += random.nextInt() % 4;

		        velocity.x = Math.min(velocity.x, 8);
		        velocity.x = Math.max(velocity.x, -8);

		        velocity.y += random.nextInt() % 4;

		        velocity.y = Math.min(velocity.y, 8);
		        velocity.y = Math.max(velocity.y, -8);
		    }
		   
		   location.x += velocity.x ;
		   location.y += velocity.y ;
		   
		   
		    if (location.x < container.x) {
		        location.x = container.x;
		        velocity.x = -velocity.x;
		    }

		    if ((location.x + lookingLeft.getWidth(frame))
		            > (container.x + container.width)){
		            location.x = container.x + container.width - lookingLeft.getWidth(frame);
		            velocity.x = -velocity.x;
		        }

		   if (location.y < container.y){
		        location.y = container.y;
		        velocity.y = -velocity.y;
		    }

		    if ((location.y + lookingLeft.getHeight(frame))
		        > (container.y + container.height)){
		        location.y = container.y + container.height - lookingLeft.getHeight(frame);
		        velocity.y = -velocity.y;
		    }
		}
	
	public void drawFishImage(Graphics g){
		
		if(velocity.x < 0 ){
			
			g.drawImage(lookingLeft, location.x, location.y , frame);
		}else{
			g.drawImage(lookingRight, location.x, location.y , frame);
		}
		
	}	
	
	public int getStartX(){
		
		return location.x ;
	}
	
	public int getEndX(){
		
		return (location.x + lookingRight.getWidth(frame)) ;
	}
	
	public int getStartY(){
		
		return location.y ;
	}
	
	public int getEndY(){
		
		return (location.y + lookingRight.getHeight(frame)) ;
	}
}