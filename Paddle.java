import java.awt.Color;
import java.awt.event.KeyEvent;
import acm.graphics.*;

public class Paddle extends GRect implements Commons{
	int pid;
	double dy = 0;
	
	public Paddle(double width, double height, int player) {
		super(width, height);
		pid = player;
		resetState();
		if(pid==1){
			setColor(Color.BLUE);
		}else{
			setColor(Color.RED);
		}
	}
	
	public void move(int y){
		setLocation(getX(), y);
		
		if(getY()+getHeight()>Screen_Height){
			setLocation(getX(), Screen_Height-getHeight());		
		}else if(getY()<0){
			this.setLocation(getX(), getHeight()/2);
		}
	}
	
	public void keyMove(){
		
		if(getY()+getHeight()>Commons.Screen_Height){
			if(dy<0){	
				move(0, dy);
			}
		}else if(getY()<0){
			if(dy>0){
				move(0, dy);
			}
		}else{
			move(0, dy);
		}
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(pid == 1){
			if(key == KeyEvent.VK_W){
				dy = -Commons.Paddle_Velocity;
			}else if(key == KeyEvent.VK_S){
				dy = Commons.Paddle_Velocity;
			}
			
		}else{
			if(key == KeyEvent.VK_UP){
				dy = -Commons.Paddle_Velocity;
			}else if(key == KeyEvent.VK_DOWN){
				dy = Commons.Paddle_Velocity;
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if(pid == 1){
			if(key == KeyEvent.VK_W || key == KeyEvent.VK_S){
				dy = 0;
			}
			
			
		}else{
			if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN){
				dy = 0;
			}
		}
		
	}
	
	
	public void resetState(){
		if(pid==1){
			this.setLocation(10, Screen_Height/2-(Paddle_Height/2));
		}else{
			this.setLocation(Screen_Width-20, Screen_Height/2-(Paddle_Height/2));
		}
	}
}
