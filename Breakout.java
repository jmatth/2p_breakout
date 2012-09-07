import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.*;
import acm.graphics.*;
import acm.program.*;
import java.awt.event.KeyAdapter;
public class Breakout extends GraphicsProgram implements Commons{
	
	Ball ball1, ball2;
	Paddle player1;
	Paddle player2;
	Block[] blocks;
	Timer timer;
	boolean ingame = true;
	boolean dead = false;
	int lives = 3;
	boolean hit1, hit2;
	GImage happy = new GImage("../happyball.png");
	boolean win = false;
	
	 
	public void CharlieSheen(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_Y){
			for(int i = 0; i<100; i++){
				blocks[i].setFilled(false);
				remove(blocks[i]);
			}
		}
	}
	
	 public class keyInput extends KeyAdapter{
		 
		 public void keyPressed(KeyEvent e){
			player1.keyPressed(e);
			player2.keyPressed(e);
			CharlieSheen(e);
		 }
		 
		 public void keyReleased(KeyEvent e){
			player1.keyReleased(e);
			player2.keyReleased(e);
		 }
	 }
	
	public void checkWallBounce(){
		if(ball1.getY()<0){
			ball1.reverseYvel();
			ball1.setLocation(ball1.getX(), 1);
		}else if(ball1.getY()+ball1.getHeight()>Commons.Screen_Height){
			ball1.reverseYvel();
			ball1.setLocation(ball1.getX(), Commons.Screen_Height-ball1.getHeight()-1);
		}
		
		if(ball2.getY()<0){
			ball2.reverseYvel();
			ball2.setLocation(ball2.getX(), 1);
		}else if(ball2.getY()+ball2.getHeight()>Commons.Screen_Height){
			ball2.reverseYvel();
			ball2.setLocation(ball2.getX(), Commons.Screen_Height-ball2.getHeight()-1);
		}
	}

	public void gameInit(){
		
		setSize(Commons.Screen_Width, Commons.Screen_Height);
		
		Random random = new Random();
		player1 = new Paddle(Paddle_Width, Paddle_Height, 1);
		player2 = new Paddle(Paddle_Width, Paddle_Height, 2);
		add(player1);
		add(player2);
		
		
		
		blocks = new Block[100];
		
		for(int i=0;i<100;i+=10){
			for(int j = 0; j<10; j++){
				
				blocks[i+j] = new Block(Brick_Offset+(j*(Brick_Width+Brick_Separation)), (i/10)*(Brick_Height+Brick_Separation), Brick_Width, Brick_Height, random.nextBoolean());
				blocks[i+j].setFilled(true);
				add(blocks[i+j]);
			}
		}
		ball1 = new Ball("../deadball.png", Color.BLUE);
		ball2 = new Ball("../deadball.png", Color.RED);
		ball1.setImage("../ball.png");
		ball2.setImage("../ball.png");
		add(ball1);
		add(ball2);
		ball1.sendToFront();
		ball2.sendToFront();
		
	}
	
	public void movePaddles(){
		player1.keyMove();
		player2.keyMove();
	}
	
	public void moveballz(){
		ball1.move();
		ball2.move();
	}
	
	public void randomizeVelocities(){
		Random generator = new Random();
		if(ball1.type==Color.BLUE){
		ball1.setYvel(2+5*generator.nextDouble());
		ball1.setXvel(Math.abs(2+5*generator.nextDouble()));
		}else{
			ball1.setYvel(2+5*generator.nextDouble());
			ball1.setXvel(-Math.abs((2+5*generator.nextDouble())));
		}
		
		if(ball2.type==Color.BLUE){
		ball2.setYvel(2+5*generator.nextDouble());
		ball2.setXvel(Math.abs(2+5*generator.nextDouble()));
		}else{
			ball2.setYvel(2+5*generator.nextDouble());
			ball2.setXvel(-Math.abs(2+5*generator.nextDouble()));
		}
	}
	
	public void checkJustBlocks(GRect hit, Ball ball){
		
		if(hit!=null && hit!=player1 && hit!= player2){
			if (hit.getFillColor()==ball.type) {
				hit.setFilled(false);
				remove(hit);
				ball.reverseYvel();
				ball.revy = false;
			}
		}
		
	}
	
	public void checkPaddlesBlocks(GRect hit, Ball ball, Paddle player){
		
		/**
		if(hit == player){
			double off = (ball.getY()+ball.getHeight()/2) - (player.getY()+player.getHeight()/2);
			ball.paddleCollisionRel(off/10);
			ball.type = player.getColor();
			ball.reverseXvel();
			ball.revx = false;
			freeBall(ball, hit);
		*/
		
		if(hit == player){
			double off = (ball.getY()+ball.getHeight()/2) - (player.getY()+player.getHeight()/2);
			ball.paddleCollisionCalc(off/6);
			ball.type = player.getColor();
			ball.reverseXvel();
			ball.revx = false;
			freeBall(ball, player);
			
		}else if(hit!=null && hit != player1 && hit != player2){
			if(hit.getFillColor()==ball.type){
				hit.setFilled(false);
				remove(hit);
				ball.reverseXvel();
				ball.revx = false;
				
				
			}
		}
		
	}

	public void assignConstVel(){
		ball1.setYvel(2);
		ball2.setYvel(2);
		ball1.setXvel(Math.sqrt(Math.pow(Commons.Ball_Velocity, 2)-4));
		ball2.setXvel(-Math.sqrt(Math.pow(Commons.Ball_Velocity, 2)-4));
	}
	
	public GRect checkPoint(double x, double y){
		if(getElementAt(x, y)==ball1 || getElementAt(x,y)==ball2){
			return null;
		}else{
			return (GRect) getElementAt(x, y);
		}
	}
	
	public void checkCollision(){
		
//		GRect b1right = checkPoint(
//				ball1.getX()+ball1.getWidth()+1,
//				ball1.getY()+ball1.getHeight()/2);
//		GRect b1left = checkPoint(
//				ball1.getX()-1,
//				ball1.getY()+ball1.getHeight()/2);
//		GRect b1top = checkPoint(
//				ball1.getX()+ball1.getWidth()/2,
//				ball1.getY()-1);
//		GRect b1bottom = checkPoint(
//				ball1.getX()+ball1.getWidth()/2,
//				ball1.getY()+ball1.getHeight()+1);
		
		GRect b1TopRight = checkPoint(
				ball1.getX()+ball1.getWidth()-8, ball1.getY()-1);
		GRect b1TopLeft = checkPoint(
				ball1.getX()+8, ball1.getY()-1);
		GRect b1RightTop = checkPoint(
				ball1.getX()+ball1.getWidth()+1, ball1.getY()+5);
		GRect b1LeftTop = checkPoint(
				ball1.getX()-1, ball1.getY()+5);
		GRect b1BottomRight = checkPoint(
				ball1.getX()+ball1.getWidth()-8, ball1.getY()+ball1.getHeight()+1);
		GRect b1BottomLeft = checkPoint(
				ball1.getX()+8, ball1.getY()+ball1.getHeight()+1);
		GRect b1RightBottom = checkPoint(
				ball1.getX()+ball1.getWidth()+1, ball1.getY()+ball1.getHeight()-8);
		GRect b1LeftBottom = checkPoint(
				ball1.getX()-1, ball1.getY()+ball1.getHeight()-8);
		
		
//		GRect b2right = checkPoint(
//				ball2.getX()+ball2.getWidth()+1,
//				ball2.getY()+ball2.getHeight()/2);
//		GRect b2left = checkPoint(
//				ball2.getX()-1,
//				ball2.getY()+ball2.getHeight()/2);
//		GRect b2top = checkPoint(
//				ball2.getX()+ball2.getWidth()/2,
//				ball2.getY()-1);
//		GRect b2bottom = checkPoint(
//				ball2.getX()+ball2.getWidth()/2,
//				ball2.getY()+ball2.getHeight()+1);
		
		GRect b2TopRight = checkPoint(
				ball2.getX()+ball2.getWidth()-8, ball2.getY()-1);
		GRect b2TopLeft = checkPoint(
				ball2.getX()+8, ball2.getY()-1);
		GRect b2RightTop = checkPoint(
				ball2.getX()+ball2.getWidth()+1, ball2.getY()+5);
		GRect b2LeftTop = checkPoint(
				ball2.getX()-1, ball2.getY()+5);
		GRect b2BottomRight = checkPoint(
				ball2.getX()+ball2.getWidth()-8, ball2.getY()+ball2.getHeight()+1);
		GRect b2BottomLeft = checkPoint(
				ball2.getX()+8, ball2.getY()+ball2.getHeight()+1);
		GRect b2RightBottom = checkPoint(
				ball2.getX()+ball2.getWidth()+1, ball2.getY()+ball2.getHeight()-8);
		GRect b2LeftBottom = checkPoint(
				ball2.getX()-1, ball2.getY()+ball2.getHeight()-8);
		
		ball1.revx = true;
		ball1.revy = true;
		checkPaddlesBlocks(b1RightTop, ball1, player2);
		
		checkPaddlesBlocks(b1RightBottom, ball1, player2);
		
		checkPaddlesBlocks(b1LeftTop, ball1, player1);
		
		checkPaddlesBlocks(b1LeftBottom, ball1, player1);
		
		//checkPaddlesBlocks(b1right, ball1, player2);
		
		//checkPaddlesBlocks(b1left, ball1, player1);
		
//		checkJustBlocks(b1top, ball1);
		
//		checkJustBlocks(b1bottom, ball1);
		
		checkJustBlocks(b1TopRight, ball1);
		
		checkJustBlocks(b1TopLeft, ball1);
		
		checkJustBlocks(b1BottomRight, ball1);
		
		checkJustBlocks(b1BottomLeft, ball1);
		
		ball2.revx = true;
		ball2.revy = true;
		checkPaddlesBlocks(b2RightTop, ball2, player2);
			
		checkPaddlesBlocks(b2RightBottom, ball2, player2);
			
		checkPaddlesBlocks(b2LeftTop, ball2, player1);
			
		checkPaddlesBlocks(b2LeftBottom, ball2, player1);
		
		//checkPaddlesBlocks(b2right, ball2, player2);
			
		//checkPaddlesBlocks(b2left, ball2, player1);
			
//		checkJustBlocks(b2top, ball2);
			
//		checkJustBlocks(b2bottom, ball2);
			
		
		checkJustBlocks(b2TopRight, ball2);
		
		checkJustBlocks(b2TopLeft, ball2);
		
		checkJustBlocks(b2BottomRight, ball2);
		
		checkJustBlocks(b2BottomLeft, ball2);
			
		
	}

	public void freeBall(Ball ball, GRect stuck){
		while(ball.getX()+ball.getWidth()>=stuck.getX() && ball.getX()<=stuck.getX()+stuck.getWidth()){
			pause(Commons.Frame_Delay);
			movePaddles();
			checkWallBounce();
			ball.move();
		}
	}
	
	public void checkDead(){
		if(ball1.getX()+ball1.getWidth()>Commons.Screen_Width || ball1.getX()<0){
			dead = true;
		}
		
		if(ball2.getX()+ball2.getWidth()>Commons.Screen_Width || ball2.getX()<0){
			dead = true;
		}
	}
	
	public boolean checkWin(){
		boolean won = true;
		for(int i = 0; i<100; i++){
			if(blocks[i].isFilled()){
			won = false;
			break;
			}
		}
		return won;
	}
	

	public void checkSideBounce(){
		if(ball1.getX()<0){
			ball1.reverseXvel();
			ball1.setLocation(1, ball1.getY());
		}else if(ball1.getX()+ball1.getWidth()>Commons.Screen_Width){
			ball1.reverseXvel();
			ball1.setLocation(Commons.Screen_Width-ball1.getWidth()-1, ball1.getY());
		}
		
		if(ball2.getX()<0){
			ball2.reverseXvel();
			ball2.setLocation(1, ball2.getY());
		}else if(ball2.getX()+ball2.getWidth()>Commons.Screen_Width){
			ball2.reverseXvel();
			ball2.setLocation(Commons.Screen_Width-ball2.getWidth()-1, ball2.getY());
		}
	}
	
	public void runGame(){
		
		
		while (true) {
			assignConstVel();
			pause(3000);
			dead=false;
			while (!dead && !win) {
				pause(Commons.Frame_Delay);
				movePaddles();
				moveballz();
				checkWallBounce();
				checkCollision();
				checkDead();
				win = checkWin();
			}
			
			if (!win) {
				ball1.setImage("../deadball.png");
				ball2.setImage("../deadball.png");
				lives--;
				pause(3000);
				ball1.type = Color.BLUE;
				ball2.type = Color.RED;
				ball1.setImage("../ball.png");
				ball2.setImage("../ball.png");
				ball1.resetState();
				ball2.resetState();
				player1.resetState();
				player2.resetState();
			}else{
				remove(player1);
				remove(player2);
				happy.setLocation(Commons.Screen_Width/2-happy.getWidth()/2, 0);
				add(happy);
				while(true){
					pause(5);
					moveballz();
					checkWallBounce();
					checkSideBounce();
				}
			}
		}
	}

	
	public void run(){
		addKeyListeners(new keyInput());
		gameInit();
		runGame();
	}
}