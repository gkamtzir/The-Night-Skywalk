import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;
import java.util.Random;


public class GravDown {
	
	private int x, y, radius;
	private double dx;
	private MainProject sp;
	private boolean Start;
	static AudioClip comperror;
	URL url;
	
	
	public double getDx() {
		return dx;
	}



	public void setDx(double dx) {
		this.dx = dx;
	}



	public GravDown(){
		x = 1500;
		y = 400;
		radius = 10;
		dx = -2;
	}
	
	
	
	public void update(MainProject sp, Ball b){
		try{
			url = sp.getDocumentBase();
		}catch (Exception e){
			
		}
		comperror = sp.getAudioClip(url, "Music/computer error.au");
		Start = sp.isStart();
		this.sp = sp;
		if (Start){
			x += dx;
		}
		if (x - radius + dx< 0){
			Random r = new Random();
			x = 800 + r.nextInt(800);
			y = r.nextInt(500);
		}
		
		CheckForPick(b);
		
	}
	
	private void CheckForPick(Ball b){
		int Radius = b.getRadius();
		int BallY = b.getY();
		int BallX = b.getX();
		
		int a = y - BallY;
		int bb = x - BallX;
		double c = Math.sqrt((double)a*a + (double)bb*bb);
		if (c < Radius + radius){
			PerformAction(b);
		}
	
	}
	
	
	private void PerformAction(Ball b) {
		
		b.setGravity(b.getGravity() + 1);
		y = 1000;
		comperror.play();
		
	}



	public void paint(Graphics g){
		g.setColor(Color.green);
		g.fillOval(x, y, radius*2, radius*2);
		
	}
	
}
