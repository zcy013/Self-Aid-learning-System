package drager;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SignIn extends JFrame{
	private JLabel[] labels;
	private JTextField name;
	private JTextField studentID;
	private JPasswordField[] passwords;
	private JButton confirm;
	private JButton cancle;
	
	private GridBagLayout layout;
	GridBagConstraints constraints;
	
	public SignIn(){
		super("新用户注册");
		getContentPane().setBackground(HelloPage.myBlue);
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		setLayout(layout);
		
		labels = new JLabel[4];
		passwords = new JPasswordField[2];
		name = new JTextField(10);
		name.setBackground(HelloPage.myGray);
		name.setBorder(new EmptyBorder(0, 0, 0, 0));
		studentID = new JTextField(11);
		studentID.setBackground(HelloPage.myGray);
		studentID.setBorder(new EmptyBorder(0, 0, 0, 0));
		labels[0] = new JLabel("请输入学号：",SwingConstants.LEFT);
		labels[1] = new JLabel("请输入姓名：",SwingConstants.LEFT);
		labels[2] = new JLabel("请设置密码：",SwingConstants.LEFT);
		labels[3] = new JLabel("再次确认密码：",SwingConstants.LEFT);
		
		Handler handler = new Handler();
		passwords[0] = new JPasswordField(16);
		passwords[1] = new JPasswordField(16);
		passwords[0].setBackground(HelloPage.myGray);
		passwords[1].setBackground(HelloPage.myGray);
		passwords[0].setBorder(new EmptyBorder(0, 0, 0, 0));
		passwords[1].setBorder(new EmptyBorder(0, 0, 0, 0));
		passwords[0].addActionListener(handler);
		passwords[1].addActionListener(handler);
		
		
		
		confirm = new JButton("确定");
		confirm.addActionListener(handler);
		confirm.setBackground(HelloPage.myGray);
		
		cancle = new JButton("取消");
		cancle.addActionListener(handler);
		cancle.setBackground(HelloPage.myGray);
		addComponent(labels[0], 0, 0, 2, 1);
		addComponent(studentID, 0, 1, 2, 1);
		addComponent(labels[1], 0, 2, 2, 1);
		addComponent(name, 0, 3, 2, 1);
		addComponent(labels[2], 0, 4, 2, 1);
		addComponent(passwords[0], 0, 5, 2, 1);
		addComponent(labels[3], 0, 6, 2, 1);
		addComponent(passwords[1], 0, 7, 2, 1);
		constraints.fill = GridBagConstraints.NONE;
		addComponent(confirm, 0, 8, 1, 1);
		addComponent(cancle, 1, 8, 1, 1);
		
		setVisible(true);
		setSize(300, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	private void addComponent(Component component, int column, int row, int width, int height){
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridheight = height;
		constraints.gridwidth = width;
		layout.setConstraints(component, constraints);
		add(component);
	}
	
	private class Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == cancle){
				dispose();
				new HelloPage();
				return;
			}
						
			String password1 = String.valueOf(passwords[0].getPassword());
			String password2 = String.valueOf(passwords[1].getPassword());
			String studentName = name.getText();
			int id = Integer.parseInt(studentID.getText());
			
			if(studentName.equals("")||studentName.length() > 10){
				if(studentName.equals("")){
					JOptionPane.showMessageDialog(null, "请输入姓名！");
					return;
				}
				else 
					JOptionPane.showMessageDialog(null, "姓名不能超过10个字符！");
				return;
			}
			if(id > 11510937 || id <11510000){//判断学号是否合法
				JOptionPane.showMessageDialog(null, "学号不存在！");
				return;
			}
			try {
				ResultSet rs = DBUtility.executeQuery("SELECT Activation FROM students WHERE StudentID = " + id);
				if(rs.next()){
					if(rs.getString(1).equals("yes")){
						JOptionPane.showMessageDialog(null, "该账号已注册，请直接登录！");
						DBUtility.closeConnection();
						return;
					}
				JOptionPane.showMessageDialog(null, "该账号已录入但未激活，请点击找回密码！");
				DBUtility.closeConnection();
				return;
				}
				DBUtility.closeConnection();
			} catch (Exception e2) {
				e2.printStackTrace();
				DBUtility.closeConnection();
			}
			
			
			if(password1.equals(password2)){
				if(password1.length() == 0){
					JOptionPane.showMessageDialog(null, "请输入密码！");
					return;
				}
				if(password1.length() > 16){
					JOptionPane.showMessageDialog(null, "密码长度不能超过16！");
					passwords[1].setText("");
					passwords[2].setText("");
					return;
				}
				int vcode = (int)(Math.random()*900000 + 100000);//生成6位数验证码
				DBUtility.executeUpdate("DELETE FROM students WHERE StudentID = " + id);//删除记录
				DBUtility.executeUpdate("INSERT INTO students (StudentID, StudentName, StudentPassword, Vcode) "
				+ "VALUES(" + id + ",\"" + studentName + "\",\"" + password1 + "\"," + vcode + ")");
				
				String toEmail = id + "@mail.sustc.edu.cn";
				SSLEmail.sendCode(toEmail, vcode);//发送验证码
				
				int getVcode = Integer.parseInt(JOptionPane.showInputDialog("已发送验证码到你的邮箱\n请输入验证码："));
				while(getVcode != vcode){
					getVcode = Integer.parseInt(JOptionPane.showInputDialog("验证码错误\n请重新输入验证码："));
				}
					DBUtility.executeUpdate("UPDATE students SET Activation = " + "\"yes\""  + " WHERE StudentID = " + id);
					JOptionPane.showMessageDialog(null, "注册成功！请登录");
					dispose();
					new HelloPage();
					return;
				
				
			}
			else 
				JOptionPane.showMessageDialog(null, "两次输入的密码不一致！");
			passwords[1].setText("");
			passwords[2].setText("");
		}
		
	}

}
