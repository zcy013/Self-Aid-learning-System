package drager;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.logging.Handler;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ChangeTPassword extends JFrame{
	private JLabel[] labels;
	private JPasswordField[] inputs;
	private JButton confirm;
	private JButton cancle;
	
	private GridBagLayout layout;
	GridBagConstraints constraints;
	
	
	public ChangeTPassword(){
		super("更改密码");
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		setLayout(layout);
		
		labels = new JLabel[3];
		inputs = new JPasswordField[3];
		labels[0] = new JLabel("请输入原密码：",SwingConstants.LEFT);
		labels[1] = new JLabel("请输入新密码：",SwingConstants.LEFT);
		labels[2] = new JLabel("请确认新密码：",SwingConstants.LEFT);
		
		Handler handler = new Handler();
		for(int i = 0; i < 3; i++){
			inputs[i] = new JPasswordField(16);
			inputs[i].addActionListener(handler);
		}
		confirm = new JButton("确定");
		confirm.addActionListener(handler);
		cancle = new JButton("取消");
		cancle.addActionListener(handler);
		addComponent(labels[0], 0, 0, 2, 1);
		addComponent(inputs[0], 0, 1, 2, 1);
		addComponent(labels[1], 0, 2, 2, 1);
		addComponent(inputs[1], 0, 3, 2, 1);
		addComponent(labels[2], 0, 4, 2, 1);
		addComponent(inputs[2], 0, 5, 2, 1);
		constraints.fill = GridBagConstraints.NONE;
		addComponent(confirm, 0, 6, 1, 1);
		addComponent(cancle, 1, 6, 1, 1);
		
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
				dispose();//关闭窗口
				//在这里添加返回之前窗口的代码-------------------------------------------------------------------------------------------
				return;
			}
			
			String oldPassword = String.valueOf(inputs[0].getPassword());
			String newPassword1 = String.valueOf(inputs[1].getPassword());
			String newPassword2 = String.valueOf(inputs[2].getPassword());
			if(newPassword1.equals(newPassword2)){
				if(newPassword1.length() == 0){
					JOptionPane.showMessageDialog(null, "请输入密码！");
					return;
				}
				if(newPassword1.length() > 16){
					JOptionPane.showMessageDialog(null, "密码长度不能超过16！");
					inputs[1].setText("");
					inputs[2].setText("");
					return;
				}
				if(Login.loginOK("admin", oldPassword)){
					if(Login.changePassword(newPassword1)){
						JOptionPane.showMessageDialog(null, "修改密码成功！请重新登录");
						dispose();
						new HelloPage();
						return;
					}
					JOptionPane.showMessageDialog(null, "change错误！");
					return;
				}
				JOptionPane.showMessageDialog(null, "原密码不正确！");
				return;
			}
			else 
				JOptionPane.showMessageDialog(null, "两次输入的密码不一致！");
				inputs[1].setText("");
				inputs[2].setText("");
		}
		
	}
}
