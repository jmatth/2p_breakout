
public interface Commons {
	public static final int Screen_Width = 800;
	public static final int Screen_Height = 400;
	
	//paddle dimensions
	public static final int Paddle_Width = 10;
	public static final int Paddle_Height = 60;
	
	//bricks per row
	public static final int Bricks_Per_Row = 10;
	
	//rows of bricks
	public static final int Brick_Rows = 10;
	
	//space between bricks
	public static final int Brick_Separation = 4;
	
	//width of a brick
	public static final int Brick_Width = 8;
	
	//height of a brick
	public static final int Brick_Height =
			(Screen_Height - (Brick_Rows - 1) * Brick_Separation) / Brick_Rows;
	
	//ball radius. may not be needed.
	public static final int Ball_Radius = 10;
	
	//offset of bricks from the left. might be wrong because i'm an idiot.
	public static final int Brick_Offset = 342;
	
	public static final int Start_Lives = 3;
	
	//limit framerate
	public int Frame_Delay = 15;
	
	public final double Ball_Velocity = 6;
	
	//paddle speed with key controls
	public static final int Paddle_Velocity = 10;
}
