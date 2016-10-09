import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;


public class MainProject extends Applet implements Runnable, KeyListener, MouseMotionListener, MouseListener{
	
	private static final long serialVersionUID = 1L;
	private Image i;
	private Graphics doubleG;
	Ball b;
	Platforms p[] = new Platforms[8];
	GravDown gd[] = new GravDown[2];
	private int score;
	private boolean gameover= false;
	private Image city;
	URL url;
	private double CityX = 0;
	private double CityDx = -1;
	Bonus bo[] = new Bonus[3];
	private boolean MouseIn = false;
	private boolean start = false, starte = false;
	
	
	public boolean isStart() {
		return start;
	}


	public void setStart(boolean start) {
		this.start = start;
	}


	public boolean isGameover() {
		return gameover;
	}


	public void setGameover(boolean gameover) {
		this.gameover = gameover;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public void init(){
		
		setSize(800,600);
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		
		try{
			
			url = getDocumentBase();
			
		}catch (Exception e){
			
			
			
		}
		
		city = getImage(url, "Images/For the game.png");
		
	}
	
	
	public void start(){
		
		score = 0;
		b = new Ball(350, 200);
		
		for (int i = 0; i<p.length; i++){
			p[i] = new Platforms(i*150, 400);
		}
		
		for (int i = 0; i<gd.length; i++){
			gd[i] = new GravDown();	
		}
		
		for (int i = 0; i<bo.length; i++){
			bo[i]= new Bonus();
		}
		
		Thread thread = new Thread(this);
		thread.start();
		
	}


	@Override
	public void run() {
		while(true){
			
			gameover = b.getGameOver();
			
			if (gameover == true){
				
				CityDx = 0;
				b.setDx(0);
				
				for (int i = 0; i<p.length; i++){
					p[i].setDx(0);
				}
				
				for (int i = 0; i<gd.length; i++){
					gd[i].setDx(0);
				}
			
				for (int i = 0; i<bo.length; i++){
					bo[i].setDx(0);
				}
				
			}else {
				
				if (start){
					
					score ++;
					CityX += CityDx;
				
				}
				
			}
			
			b.update(this);
			
			for (int i = 0; i<p.length; i++){
				p[i].update(this, b);
			}
			
			for (int i = 0; i<gd.length; i++){
				gd[i].update(this, b);
			}
			
			for (int i = 0; i<bo.length; i++){
				bo[i].update(this, b);
			}
			
			repaint();
			try {
				
				Thread.sleep(17);
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
	}
	
	
	
	public void update(Graphics g){
		
		if (i ==null){
			
			i = createImage(this.getWidth(), this.getHeight());
			doubleG = i.getGraphics();
			
		}
		
		doubleG.setColor(getBackground())	;
		doubleG.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		doubleG.setColor(getForeground());
		paint(doubleG);
		
		g.drawImage(i, 0, 0, this);
		
	}
	
	public void paint(Graphics g){
		
		if (CityX == -getWidth()){
			CityX = -3;
		}
		
		g.drawImage(city, (int)CityX, 0, this);
		g.drawImage(city, (int)CityX + getWidth() -5, 0, this);
		b.paint(g);
		
		for (int i =0; i<p.length; i++){
			p[i].paint(g);
		}
		
		for (int i =0; i<gd.length; i++){
			gd[i].paint(g);
		}
		
		for (int i = 0; i<bo.length; i++){
			bo[i].paint(g);
		}
		
		String sc = Integer.toString(score);
		Font f = new Font("Arial", Font.BOLD, 65);
		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString(sc, 650, 90);
		
		if (gameover == true){
			if (MouseIn){
				g.setColor(Color.ORANGE);
				g.drawString("Game Over", 250, 100);
				g.drawString("Your Score:", 250, 200);
				g.drawString(sc, 350, 300);
				g.setColor(Color.RED);
				g.drawString("Play again?", 250, 400);
			}else {
				g.setColor(Color.ORANGE);
				g.drawString("Game Over", 250, 100);
				g.drawString("Your Score:", 250, 200);
				g.setColor(Color.GREEN);
				g.drawString(sc, 350, 300);
				g.setColor(Color.ORANGE);
				g.drawString("Play again?", 250, 400);
			}
			
		}
		
		 if (!start){
			if (!starte){
				g.setColor(Color.WHITE);
				g.drawString("Start", 350, 300);
			}else {
				g.setColor(Color.RED);
				g.drawString("Start", 350, 300);
			
			}
		}
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		
		switch(arg0.getKeyCode()){
		case KeyEvent.VK_LEFT:
			b.LeftMove();

			break;
		case KeyEvent.VK_RIGHT:
			b.RightMove();

			break;
		}	
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent arg0) {
		
		if (arg0.getX() > 350 && arg0.getX() < 500){
			if (arg0.getY() > 250 && arg0.getY() < 300){
				MouseIn = true;
			}
		}
		if (arg0.getX() < 350 || arg0.getX() > 500){
			MouseIn = false;
		}
		if (arg0.getY() < 250 || arg0.getY() > 300){
			MouseIn = false;
		}
		
		if (arg0.getX() > 350 && arg0.getX() < 500){
			if (arg0.getY() > 250 && arg0.getY() < 300){
				starte = true;
			}
		}	
		
		if (arg0.getX() < 350 || arg0.getX() > 500){
				starte = false;
		}
		
		if (arg0.getY() < 250 || arg0.getY() > 300){
				starte = false;
		}	
	}
	
	


	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		if (MouseIn){
			b = null;
			score = 0;
			b = new Ball(350, 200);
			for (int i = 0; i<p.length; i++){
				p[i] = new Platforms(i*150, 400);
			}
			for (int i = 0; i<gd.length; i++){
				gd[i] = new GravDown();
				
			}
			for (int i = 0; i<bo.length; i++){
				bo[i]= new Bonus();
			}
			CityDx = -1;
			MouseIn = false; 
			
		}
		
		if (starte){
			start = true;
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}


	


