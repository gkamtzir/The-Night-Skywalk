import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;


public class Platforms {
	
	private int x, y, width, height;
	private double dx;
	Image plat;
	URL url;
	static MainProject sp;
	private boolean Start;
	static AudioClip bounce;
	
	public Platforms(int x, int y){
		this.x = x;
		this.y = y;
		width = 120;
		height = 50;
		dx = -2;
	}
	
	
	public double getDx() {
		return dx;
	}


	public void setDx(double dx) {
		this.dx = dx;
	}


	public void update(MainProject sp, Ball b){
		Start = sp.isStart();
		try{
			url = sp.getDocumentBase();
		}catch (Exception e){
			
		}
		plat = sp.getImage(url, "Images/Platforms.png");
		bounce = sp.getAudioClip(url, "Music/bounce.au");
		if (Start){
			x += dx;
		}
		checkForCollision(b);
		if (x + width + dx< 0){
			Random r = new Random();
			x = sp.getWidth() + r.nextInt(400);
			y = 200 + r.nextInt(200);
		}
		
		this.sp = sp;
		
	}
	
	
	
	private void checkForCollision(Ball b) {
		int BallX = b.getX();
		int BallY = b.getY();
		int BallRadius = b.getRadius();
		
		if (BallY + BallRadius > y -15 && BallY + BallRadius < y + height -15){
			if (BallX > x && BallX < x + width){
				double Dy = b.getGameDy();
				b.setY(y - BallRadius);
				b.setDy(-Dy);
				bounce.play();
				
			}
			
		}
		if (BallY - BallRadius > y && BallY - BallRadius < y + height){
			if (BallX > x && BallX < x + width){
				double Dy = b.getGameDy();
				b.setY(y + BallRadius + height);
				b.setDy(Dy);
				
			}
		}
		
	}


	public void paint(Graphics g){
		g.drawImage(plat, x, y, sp);
		
	}
	
}
