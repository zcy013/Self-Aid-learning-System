package drager;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ChangeName extends JFrame{
	private String studentID;
	private JLabel note;
	private JTextField newName;
	private JButton confirm, cancle;
	private GridBagLayout layout;
	GridBagConstraints constraints;
	
	public ChangeName(String studentID){
		super("修改姓名");
		this.studentID = studentID;
		getContentPane().setBackground(HelloPage.myBlue);
		layout = new GridBagLayout();
		constraints = new GridBagConstraints();
		constraints.weightx = 0;
		constraints.weighty = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		setLayout(layout);
		
		note = new JLabel("请输入新姓名：", SwingConstants.LEFT);
		newName = new JTextField(10);
		newName.setBorder(new EmptyBorder(0, 0, 0, 0));
		newName.setBackground(HelloPage.myGray);
		confirm = new JButton("确定");
		confirm.setBackground(HelloPage.myGray);
		cancle = new JButton("取消");
		cancle.setBackground(HelloPage.myGray);
		Handler handler = new Handler();
		newName.addActionListener(handler);
		confirm.addActionListener(handler);
		cancle.addActionListener(handler);
		
		addComponent(note, 0, 0, 2, 1);
		addComponent(newName, 0, 1, 2, 1);
		addComponent(confirm, 0, 2, 1, 1);
		addComponent(cancle, 0, 2, 1, 1);
		
		setSize(250,200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	private class Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == cancle){
				dispose();
				StudentPage.showPage(studentID);
				return;
			}
			if(e.getSource() == confirm || e.getSource() == newName){
				if(newName.getText().equals("")){
					JOptionPane.showMessageDialog(null, "请输入姓名！");
					return;
				}
				if(newName.getText().length() > 10){
					JOptionPane.showMessageDialog(null, "不能超过10个字符！");
					return;
				}
				else
					if(Login.changeName(newName.getText())){
						JOptionPane.showMessageDialog(null, "修改成功！");						
						dispose();
						StudentPage.showPage(studentID);
						return;
					}
			}
		} 
		
	}
	private void addComponent(Component component, int column, int row, int width, int height){
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridheight = height;
		constraints.gridwidth = width;
		layout.setConstraints(component, constraints);
		add(component);
	}

}
