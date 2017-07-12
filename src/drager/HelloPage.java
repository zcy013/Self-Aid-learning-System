package drager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class HelloPage extends JFrame {

	public static Color myGray = new Color(200, 230, 255);
	public static Color myBlue = new Color(93, 155, 249);
	private JTextField userAccount;
	private JPasswordField userPassword;
	private JButton login, register, forget;
	private JLabel text1;
	private JLabel text2;
	private JPanel panel1;
	private JPanel panel2;

	private GridBagLayout layout;
	GridBagConstraints constraints;

	public HelloPage() {
		super("Java自助学习系统");
		getContentPane().setBackground(myBlue);// 设置背景颜色

		// ImageIcon title = new
		// ImageIcon(GUIDemo.class.getResource("user.png"));
		// setIconImage(title.getImage());设置左上角图标的两种方法
		/*
		 * setIconImage(getToolkit().getImage(HelloPage.class.getResource(
		 * "user.png")));
		 * 
		 * logo = new
		 * ImageIcon(HelloPage.class.getResource("user.png")).getImage();
		 */
		// panel1 = new PortraitPanel();

		userAccount = new JTextField(8);
		userAccount.addKeyListener(new KeyListener() {// 限制只能输入数字

			private int flag = 1;// 对输入不做限制

			@Override
			public void keyTyped(KeyEvent e) {
				if (userAccount.getText().length() == 0) {
					int keyChar = e.getKeyChar();
					if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) {
						flag = 0;// 只能输入数字
					} else
						flag = 1;
				} else if (flag == 0) {
					int keyChar = e.getKeyChar();
					if (keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9 || keyChar == KeyEvent.VK_TAB) {

					}

					else
						e.consume();
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		userAccount.setBorder(new EmptyBorder(0, 0, 0, 0));
		userAccount.setBackground(myGray);
		userPassword = new JPasswordField(8);
		userPassword.setBorder(new EmptyBorder(0, 0, 0, 0));
		userPassword.setBackground(myGray);
		ButtonHandler handler = new ButtonHandler();
		userPassword.addActionListener(handler);
		userAccount.addActionListener(handler);

		login = new JButton("登录");
		login.setBorderPainted(false);
		login.setBackground(myGray);

		login.addActionListener(handler);

		text1 = new JLabel("ID:", SwingConstants.CENTER);
		text2 = new JLabel("Password:", SwingConstants.CENTER);

		register = new JButton("注册");
		register.setContentAreaFilled(false);
		register.setBorderPainted(false);
		register.addActionListener(handler);
		forget = new JButton("找回密码");
		forget.setContentAreaFilled(false);
		forget.setBorderPainted(false);
		forget.addActionListener(handler);

		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		setLayout(layout);
		setBackground(myBlue);
		addComponent(text1, 0, 0, 1, 1);
		addComponent(text2, 0, 1, 1, 1);
		addComponent(userAccount, 1, 0, 1, 1);
		addComponent(userPassword, 1, 1, 1, 1);
		addComponent(login, 0, 2, 3, 1);
		addComponent(register, 2, 0, 1, 1);
		addComponent(forget, 2, 1, 1, 1);

		/*
		 * panel2 = new textPanel();
		 * 
		 * add(panel1); add(panel2);
		 */
		setVisible(true);
		setMinimumSize(new Dimension(400, 300));
		// setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);// 居中
	}

	/*
	 * class PortraitPanel extends JPanel{ public void paintComponent(Graphics
	 * g){ super.paintComponent(g); setBackground(myBlue); //Graphics2D g2=
	 * (Graphics2D)g; int width = Math.min(getWidth(), getHeight());
	 * g.drawImage(logo, (getWidth()-width)/2, (getHeight()-width)/2, width,
	 * width, this); } }
	 */
	/*
	 * class textPanel extends JPanel{ private GridBagLayout layout;
	 * GridBagConstraints constraints; public void paintComponent(Graphics g){
	 * super.paintComponent(g); layout = new GridBagLayout(); constraints = new
	 * GridBagConstraints(); constraints.weightx = 0; constraints.weighty = 0;
	 * constraints.fill = GridBagConstraints.HORIZONTAL; setLayout(layout);
	 * setBackground(myBlue); addComponent(text1, 0, 0, 1, 1);
	 * addComponent(text2, 0, 1, 1, 1); addComponent(userAccount, 1, 0, 1, 1);
	 * addComponent(userPassword, 1, 1, 1, 1); addComponent(login, 0, 2, 3, 1);
	 * addComponent(register, 2, 0, 1, 1); addComponent(forget, 2, 1, 1, 1);
	 * 
	 * 
	 * } private void addComponent(Component component, int column, int row, int
	 * width, int height){ constraints.gridx = column; constraints.gridy = row;
	 * constraints.gridheight = height; constraints.gridwidth = width;
	 * layout.setConstraints(component, constraints); add(component); } }
	 */

	private void addComponent(Component component, int column, int row, int width, int height) {
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridheight = height;
		constraints.gridwidth = width;
		layout.setConstraints(component, constraints);
		add(component);
	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == login || e.getSource() == userPassword || e.getSource() == userAccount) {
				String account = userAccount.getText();
				String password = String.valueOf(userPassword.getPassword());
				if (Login.loginOK(account, password)) {
					dispose();// 关闭当前窗口
					Login.nextPage();

				}
			} else if (e.getSource() == register) {
				dispose();
				new SignIn();
			} else if (e.getSource() == forget) {
				dispose();
				new FindPassword();
			}

		}

	}

}