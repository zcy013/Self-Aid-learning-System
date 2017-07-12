package Qiang;

import java.awt.Image;

import javax.swing.ImageIcon;

public class scale {
	public ImageIcon icon;
	public Image slideImage;
	
	public static ImageIcon scaledImage(ImageIcon a,int width,int height){
		ImageIcon icon=null;
		double rateicon=(double)a.getIconWidth()/(double)a.getIconHeight();
		double ratepanel=(double)width/(double)height;
		//width of icon is limit.
		if(rateicon>ratepanel){
			double nowIheight=(double)width/(double)a.getIconWidth()*(double)a.getIconHeight();
			icon=new ImageIcon(a.getImage().getScaledInstance(width, (int)nowIheight, Image.SCALE_SMOOTH));
		}
		//height of icon is limit.
		else{
			double nowIwidth=(double)height/(double)a.getIconHeight()*(double)a.getIconWidth();
			icon=new ImageIcon(a.getImage().getScaledInstance((int)nowIwidth, height, Image.SCALE_SMOOTH));
		}
		return icon;
	}
	
	
}
