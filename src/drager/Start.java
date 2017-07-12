package drager;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class Start {

	public static void main(String[] args) {

//		JFrame.setDefaultLookAndFeelDecorated(true);
//		JDialog.setDefaultLookAndFeelDecorated(true);
//		try {
//			UIManager.setLookAndFeel(new org.jvnet.substance.skin.SubstanceModerateLookAndFeel());
//		} catch (UnsupportedLookAndFeeladminE115xception e) {
//			e.printStackTrace();
//		}
		FontUIResource f = new FontUIResource("Î¢ÈíÑÅºÚ", Font.PLAIN, 18);
		Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, f);
			}
		}

		 UIManager.put("TabbedPane.font", new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
//		 UIManager.put("Label.font", new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
//		 UIManager.put("Button.font", new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
//		 UIManager.put("RadioButton.font", new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
//		 UIManager.put("ComboBox.font", new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
//		 UIManager.put("TextField.font", new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
//		 UIManager.put("PasswordField.font", new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
//		 UIManager.put("List.font", new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
//		 UIManager.put("TextArea.font", new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
		 UIManager.put("Table.font", new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
//		 UIManager.put("OptionPane.font", new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
//		 UIManager.put("Menu.font", new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));
//		 UIManager.put("MenuItem.font", new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 18));

		// Font fnt = new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16);
		// FontUIResource fontRes = new FontUIResource(fnt);
		// for(Enumeration keys = UIManager.getDefaults().keys();
		// keys.hasMoreElements();){
		// Object key = keys.nextElement();
		// Object value = UIManager.get(key);
		// if(value instanceof FontUIResource)
		// UIManager.put(key, fontRes);
		// }
		new HelloPage();
	}

}
