import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;
import java.util.Random;


public class Bonus {
	
	private int x, y, dx, radius;
	private MainProject sp;
	private boolean Start;
	static AudioClip comperror;
	URL url;
	
	public Bonus(){
		x = 600;
		y = 200;
		radius = 10;
		dx = -2;
	}
	
	
	
	public int getDx() {
		return dx;
	}



	public void setDx(int dx) {
		this.dx = dx;
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
		int Radius = b.getRadius();
		int BallY = b.getY();
		int BallX = b.getX();
		
		int a = y - BallY;
		int bb = x - BallX;
		double c = Math.sqrt((double)a*a + (double)bb*bb);
		if (c < Radius + radius){
			Bonus(b);
		}
		
	}


	private void Bonus(Ball b) {
		Boolean gm = sp.isGameover();
		if (!gm){
			sp.setScore(sp.getScore() + 100);
			y = 1000;
			comperror.play();
		}
		
	}
	
	public void paint(Graphics g){
		g.setColor(Color.MAGENTA);
		g.fillOval(x, y, radius*2, radius*2);
		
	}

}
