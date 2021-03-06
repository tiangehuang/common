package tmp.j2d;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.yuan.common.swing.ImageTool;

public class ImageFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public ImageFrame(String title){
		super();
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //必须,否则内存泄露
	}
	
	/**
	 * 设置窗口图标, 可以是png,gif等
	 * @param iconFile String
	 */ 
	public void setIcon(String iconFile){
		try {
			super.setIconImage(ImageIO.read(new File(iconFile)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fullScreen(){
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice(); 
		if ( gd.isFullScreenSupported() ){
			gd.setFullScreenWindow(this); 
		}
		if (gd.isDisplayChangeSupported()) {
//		     gd.setDisplayMode(new DisplayMode(800, 600, DisplayMode.BIT_DEPTH_MULTI, 60));
		 }
	}
	
	private void center(){
		double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (screenWidth - this.getWidth()) / 2, (int) (screenHeight - this.getHeight()) / 2); 
		
//		new Robot().createScreenCapture(new Rectangle(0, 0, (int)screenWidth, (int)screenHeight));
	}
	
	private void renderToScreen(){
//		fullScreen();
		this.pack();
		center();
		this.setVisible(true);
	}
	
	public void showImage(BufferedImage bufferedImage){
		this.getContentPane().add(new ImagePanel(bufferedImage));
		renderToScreen();
	}
	
	public void showPanel(JPanel panel){
		this.getContentPane().add(panel);
		renderToScreen();
	}
	
	public void showComponent(Component comp){
		this.getContentPane().add(comp);
		renderToScreen();
	}
	
	public static void main(String args[])throws Exception{
		ImageFrame imageFrame = new ImageFrame("Java2D演示");
		imageFrame.showImage(ImageTool.captureScreen());
	}

}

class ImagePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private BufferedImage bufferedImage;
	
	public ImagePanel(BufferedImage bufferedImage){
		this.bufferedImage = bufferedImage;
		
		//设置组件首选大小
		this.setPreferredSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(bufferedImage, 0, 0, null);
	}
	
}
