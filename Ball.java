import acm.graphics.*;
import java.awt.Color;
public class Ball extends GImage implements Commons{
	
	double Vx = 0;
	double Vy = 0;
	Color type;
	boolean revx = true;
	boolean revy = true;
	
	public Ball(String name, Color initial){
		super(name);
		type = initial;
		resetState();
		
		
	}
	
	public void resetState(){
		if(type==Color.BLUE){
			setLocation(25, Commons.Screen_Height/2-getHeight()/2);
		}else{
			setLocation(Commons.Screen_Width-45, Commons.Screen_Height/2-getHeight()/2);
		}
	}
	
	public void initVelocity(double x, double y){
		Vx = x;
		Vy = y;
	}
	
	public void move(){
		move(Vx, Vy);
	}
	
	public void paddleCollisionRel(double offset){
		Vy = Vy += offset;
		if(Math.abs(Vy)>Math.abs(Vx)*2){
			if(Vy<0){
				Vy = -Math.abs(Vx);
			}else{
				Vy = Math.abs(Vx);
			}
		}
	}
	
	public void paddleCollisionCalc(double offset){
		Vy += offset;
		if(Math.abs(Vy) >= Commons.Ball_Velocity){
			if(Vy<0){
				Vy = -(Commons.Ball_Velocity-.5);
			}else{
				Vy = Commons.Ball_Velocity-.5;
			}
		}
		
		if(Vx<0){
			Vx = -Math.sqrt(Math.pow(Commons.Ball_Velocity, 2)-Math.pow(Vy, 2));
		}else{
			Vx = Math.sqrt(Math.pow(Commons.Ball_Velocity, 2)-Math.pow(Vy, 2));
		}
	}
	
	public void setXvel(double x){
		Vx = x;
	}

	public void setYvel(double y){
		Vy = y;
	}
	
	public void reverseYvel(){
		if (revy) {
			this.Vy = -this.Vy;
		}
	}
	
	public void reverseXvel(){
		if (revx) {
			this.Vx = -this.Vx;
		}
	}
	
	
}
