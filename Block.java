import java.awt.Color;

import acm.graphics.*;
public class Block extends GRect{
	
	boolean destroyed;
	boolean type;
	
	public Block(int x, int y, int width, int length, boolean type){
		super(x, y, width, length);
		destroyed = false;
		
		if(type){
			this.setColor(Color.RED);
		}else{
			this.setColor(Color.BLUE);
		}
		
	}
	
	public boolean isDestroyed(){
		return destroyed;
	}

	public void setDestroyed(boolean destroyed){
		this.destroyed = destroyed;
	}
}
