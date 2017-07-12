package Qiang;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import drager.StudentPage;
import javax.swing.ImageIcon;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.CardLayout;

public class studyFace extends JFrame {

	private JPanel contentPane;

	private int currentPage = 1;// 当前页数
	private int totalPage = 1;// 章总页数
	private int currentChapter = 1;// 当前章节
	private boolean submited;

	private ImageIcon icon;// ppt图片icon
	private JLabel PPTlabel;// ppt图片
	private JButton prePage;// 上一页
	private JButton nextPage;// 下一页
	private JLabel pageShowlabel;// 页数
	private JTextField pagetext;// 页数显示
	private boolean flag = false;// 鼠标未进入PPT区域为false

	private int stuID;// 学号
	private ResultSet rSet;
	private JScrollPane scrollPane;
	private JTextArea QuestiontextArea;// 题干
	private JScrollPane blankPane;
	private JTextArea blankArea;
	private JPanel questPanel;
	private JPanel choice;// 选择题GUI组
	private JRadioButton rdbtnA;
	private JRadioButton rdbtnB;
	private JRadioButton rdbtnC;
	private JRadioButton rdbtnD;
	private ButtonGroup ButGroup;// 按钮组
	private JButton prePro;// 上一题
	private JButton nextPro;// 下一题
	private int currentQuestion = 1;// 当前问题
	private int totalQuestion = 1;// 问题总数
	private int finishedQuestion = 0;// 完成问题
	private JTextField protext;// 页数提示
	private String proProperty;// 题目属性（选择/填空）

	private CardLayout cardLayout;// 2种题型切换卡片类型
	private JPanel cardPanel;// 卡片组件
	private JLabel userLabelLabel;// 当前用户
	private JPanel outBlankpane;// 答题卡片
	private JButton save;// 保存按钮

	private JLabel panel;// 填空答题框标识
	private JPanel panel_button;// 按钮卡片
	private JLabel ShowChoice;// 选择题标识

	private JPanel title;// 顶部组件
	private JButton submit;// 提交按钮

	private Font buttonFont;// 按钮字体
	private JPanel bottomPanel;// 底部组件
	private JButton exit;// 退出
	private JButton back;// 返回
	private JPanel exitPanel;// 退出和返回组件

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// studyFace frame = new studyFace(2, 11510233);// 章节，学号
	// frame.setVisible(true);
	// } catch (Exception eMain) {
	// eMain.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public studyFace(int chapterID, int id) throws SQLException {
		currentChapter = chapterID;
		stuID = id;
		buttonFont = new Font("微软雅黑", Font.PLAIN, 16);
		submited = find.num2done(currentChapter, stuID);

		try {
			// 初始化检查学生做题情况
			rSet = DBUtility
					.executeQuery("SELECT * from chapter" + currentChapter + "_Answers where StudentID=" + stuID);
			if (rSet.next() == false) {
				DBUtility.closeConnection();
				boolean flag = DBUtility.executeUpdate(
						"INSERT INTO chapter" + currentChapter + "_Answers (`StudentID`) VALUES ('" + stuID + "')");
				boolean flag2 = DBUtility.executeUpdate(
						"INSERT INTO chapter" + currentChapter + "_Scores (`StudentID`) VALUES ('" + stuID + "')");
				DBUtility.closeConnection();
			}
			DBUtility.closeConnection();
			rSet = DBUtility
					.executeQuery("SELECT * from chapter" + currentChapter + "_Answers where StudentID=" + stuID);

			setBounds(100, 10, 1200, 850);
			contentPane = new JPanel();
			setContentPane(contentPane);
			this.setMinimumSize(new Dimension(1200, 1000));
			contentPane.setLayout(new BorderLayout());

			// 标题
			title = new JPanel(new BorderLayout());
			JLabel lblJava = new JLabel(String.format("Java自助学习系统：第%s章", Num2Han.fomatInteger(currentChapter)));
			lblJava.setHorizontalAlignment(SwingConstants.CENTER);
			// lblJava.setFont(new Font("微软雅黑", Font.PLAIN, 22));
			title.add(lblJava, BorderLayout.CENTER);

			submit = new JButton("提交当前章节所有回答");
			submit.addActionListener(new submitHandler());
			// submit.setFont(buttonFont);

			title.add(submit, BorderLayout.EAST);
			contentPane.add(title, BorderLayout.NORTH);

			bottomPanel = new JPanel();
			contentPane.add(bottomPanel, BorderLayout.SOUTH);
			bottomPanel.setLayout(new BorderLayout());

			// contentPane.add(lblJava, BorderLayout.NORTH);

			userLabelLabel = new JLabel("当前用户：" + stuID);
			bottomPanel.add(userLabelLabel, BorderLayout.WEST);
			// userLabelLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));

			exitPanel = new JPanel();
			bottomPanel.add(exitPanel, BorderLayout.EAST);

			back = new JButton("返回");
			// back.setFont(buttonFont);
			exitPanel.add(back);
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int act = JOptionPane.NO_OPTION;
					act = JOptionPane.showConfirmDialog(contentPane, "关闭当前学习窗口，返回章节选择？", "返回",
							JOptionPane.YES_NO_OPTION);
					if (act == JOptionPane.YES_OPTION) {
						try {
							if (proProperty.equals("blank")) {
								if (!blankArea.getText().equals(rSet.getString("Answer" + currentQuestion))
										&& !(rSet.getString("Answer" + currentQuestion) == null
												& blankArea.getText().equals(""))) {
									int temp = JOptionPane.showConfirmDialog(contentPane, "已经修改，是否保存？", "确认",
											JOptionPane.YES_NO_OPTION);
									if (temp == JOptionPane.YES_OPTION) {
										String ans = blankArea.getText();
										boolean flag = DBUtility
												.executeUpdate("UPDATE chapter" + currentChapter + "_Answers SET Answer"
														+ currentQuestion + "='" + ans + "' WHERE StudentID=" + stuID);
										DBUtility.closeConnection();
										rSet = DBUtility.executeQuery("SELECT * from chapter" + currentChapter
												+ "_Answers where StudentID=" + stuID);
										rSet.next();
									}
								}

							}

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						DBUtility.closeConnection();
						dispose();
						StudentPage.showPage(String.valueOf(stuID));
					}

				}
			});

			exit = new JButton("退出");
			// exit.setFont(buttonFont);
			exitPanel.add(exit);
			exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int act = JOptionPane.NO_OPTION;
					act = JOptionPane.showConfirmDialog(contentPane, "关闭学习系统？", "退出", JOptionPane.YES_NO_OPTION);
					if (act == JOptionPane.YES_OPTION) {
						try {
							if (proProperty.equals("blank")) {
								if (!blankArea.getText().equals(rSet.getString("Answer" + currentQuestion))
										&& !(rSet.getString("Answer" + currentQuestion) == null
												& blankArea.getText().equals(""))) {
									int temp = JOptionPane.showConfirmDialog(contentPane, "已经修改，是否保存？", "确认",
											JOptionPane.YES_NO_OPTION);
									if (temp == JOptionPane.YES_OPTION) {
										String ans = blankArea.getText();
										boolean flag = DBUtility
												.executeUpdate("UPDATE chapter" + currentChapter + "_Answers SET Answer"
														+ currentQuestion + "='" + ans + "' WHERE StudentID=" + stuID);
										DBUtility.closeConnection();
										rSet = DBUtility.executeQuery("SELECT * from chapter" + currentChapter
												+ "_Answers where StudentID=" + stuID);
										rSet.next();
									}
								}

							}

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						DBUtility.closeConnection();
						dispose();
					}

				}
			});

			JSplitPane splitPane_1 = new JSplitPane();
			splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
			contentPane.add(splitPane_1, BorderLayout.CENTER);

			JSplitPane splitPane = new JSplitPane();
			splitPane_1.setLeftComponent(splitPane);
			splitPane.setDividerLocation(800);

			// 问题区

			questPanel = new JPanel();
			splitPane.setRightComponent(questPanel);
			questPanel.setLayout(new BoxLayout(questPanel, BoxLayout.Y_AXIS));

			JLabel quest = new JLabel("\u8BF7\u56DE\u7B54\u4E0B\u5217\u95EE\u9898\uFF1A");
			quest.setAlignmentX(Component.CENTER_ALIGNMENT);
			questPanel.add(quest);
			// quest.setFont(new Font("微软雅黑", Font.PLAIN, 16));

			scrollPane = new JScrollPane() {
				public Dimension getPreferredSize() {
					return questPanel.getSize();
				};
			};

			questPanel.add(scrollPane);

			QuestiontextArea = new JTextArea();
			QuestiontextArea.setLineWrap(true);
			scrollPane.setViewportView(QuestiontextArea);
			QuestiontextArea.setEditable(false);
			// QuestiontextArea.setFont(new Font("微软雅黑", Font.PLAIN, 17));
			// 初始化题干和问题属性
			try {
				QuestiontextArea.setText(currentQuestion + "." + find.num2question(currentChapter, currentQuestion));
				proProperty = find.num2questIdentity(currentChapter, currentQuestion);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			// 初始化问题总数
			try {
				totalQuestion = find.num2questNum(currentChapter);
			} catch (NumberFormatException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			cardPanel = new JPanel();
			questPanel.add(cardPanel);
			cardLayout = new CardLayout();
			cardPanel.setLayout(cardLayout);

			choice = new JPanel();
			// choice.setMinimumSize(new Dimension(350, 500));
			cardPanel.add(choice, "choice");

			// 选项ABCD
			ButGroup = new ButtonGroup();
			choice.setLayout(new BorderLayout(0, 0));

			panel_button = new JPanel();
			choice.add(panel_button, BorderLayout.SOUTH);
			rdbtnA = new JRadioButton("A");
			panel_button.add(rdbtnA);
			// rdbtnA.setFont(buttonFont);
			rdbtnA.addActionListener(new choiceHandler());
			ButGroup.add(rdbtnA);

			rdbtnB = new JRadioButton("B");
			panel_button.add(rdbtnB);
			// rdbtnB.setFont(buttonFont);
			rdbtnB.addActionListener(new choiceHandler());
			ButGroup.add(rdbtnB);

			rdbtnC = new JRadioButton("C");
			panel_button.add(rdbtnC);
			// rdbtnC.setFont(buttonFont);
			rdbtnC.addActionListener(new choiceHandler());
			ButGroup.add(rdbtnC);

			rdbtnD = new JRadioButton("D");
			panel_button.add(rdbtnD);
			// rdbtnD.setFont(buttonFont);
			rdbtnD.addActionListener(new choiceHandler());
			ButGroup.add(rdbtnD);
			if(submited==true){
				rdbtnA.setEnabled(false);
				rdbtnB.setEnabled(false);
				rdbtnC.setEnabled(false);
				rdbtnD.setEnabled(false);
			}

			ShowChoice = new JLabel("选择题：");
			ShowChoice.setVerticalAlignment(SwingConstants.BOTTOM);
			// ShowChoice.setFont(new Font("微软雅黑", Font.PLAIN, 17));
			choice.add(ShowChoice, BorderLayout.CENTER);

			outBlankpane = new JPanel();
			cardPanel.add(outBlankpane, "blank");
			outBlankpane.setMinimumSize(new Dimension(350, 500));
			outBlankpane.setLayout(new BorderLayout());

			blankPane = new JScrollPane();
			outBlankpane.add(blankPane, BorderLayout.CENTER);
			blankArea = new JTextArea();
			if(submited==true){
				blankArea.setEditable(false);
			}
			// blankArea.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			blankPane.setViewportView(blankArea);

			panel = new JLabel("答题框：");
			// panel.setFont(new Font("微软雅黑", Font.PLAIN, 17));
			outBlankpane.add(panel, BorderLayout.NORTH);

			save = new JButton("保存输入");
			if (submited == true) {
				save.setEnabled(false);
			}
			save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent enter) {
					try {
						String ans = blankArea.getText();
						boolean flag = DBUtility.executeUpdate("UPDATE chapter" + currentChapter + "_Answers SET Answer"
								+ currentQuestion + "='" + ans + "' WHERE StudentID=" + stuID);
						DBUtility.closeConnection();
						rSet = DBUtility.executeQuery(
								"SELECT * from chapter" + currentChapter + "_Answers where StudentID=" + stuID);
						rSet.next();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
			// save.setFont(buttonFont);
			outBlankpane.add(save, BorderLayout.SOUTH);

			if (proProperty.equals("blank")) {
				cardLayout.show(cardPanel, "blank");
			} else if (proProperty.equals("choice")) {
				cardLayout.show(cardPanel, "choice");
			}
			// 读取历史做题数据
			try {
				rSet.next();
				String answer = rSet.getString("Answer" + currentQuestion);
				if (answer == null) {
					rdbtnA.setSelected(false);
					rdbtnB.setSelected(false);
					rdbtnC.setSelected(false);
					rdbtnD.setSelected(false);
					blankArea.setText("");
				} else if (answer != null) {
					if (answer.equals("A")) {
						rdbtnA.setSelected(true);
					} else if (answer.equals("B")) {
						rdbtnB.setSelected(true);
					} else if (answer.equals("C")) {
						rdbtnC.setSelected(true);
					} else if (answer.equals("D")) {
						rdbtnD.setSelected(true);
					} else {
						blankArea.setText(answer);
					}
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			JPanel indexPro = new JPanel();
			questPanel.add(indexPro);

			JLabel label = new JLabel("\u9898\uFF1A");
			// label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
			indexPro.add(label);

			protext = new JTextField();
			// protext.setFont(new Font("微软雅黑", Font.PLAIN, 16));
			indexPro.add(protext);
			protext.setColumns(5);
			protext.setText(currentQuestion + "/" + totalQuestion);
			// 鼠标全选页数
			protext.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					protext.selectAll();
				}
			});
			// 输入题号响应
			protext.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String in = protext.getText();
					try {
						int temp = Integer.parseInt(in);
						if (temp < 1 || temp > totalQuestion) {
							throw new NumberFormatException();
						}
						if (temp == 1) {
							prePro.setEnabled(false);
							nextPro.setEnabled(true);
						} else if (temp == totalQuestion) {
							prePro.setEnabled(true);
							nextPro.setEnabled(false);
						} else {
							prePro.setEnabled(true);
							nextPro.setEnabled(true);
						}
						QuestiontextArea.setText(temp + "." + find.num2question(currentChapter, temp));
						proProperty = find.num2questIdentity(currentChapter, temp);
						currentQuestion = temp;
						if (proProperty.equals("blank")) {
							cardLayout.show(cardPanel, "blank");
						} else if (proProperty.equals("choice")) {
							cardLayout.show(cardPanel, "choice");
						}
						String answer = rSet.getString("Answer" + currentQuestion);
						if (answer == null) {
							rdbtnA.setSelected(false);
							rdbtnB.setSelected(false);
							rdbtnC.setSelected(false);
							rdbtnD.setSelected(false);
							blankArea.setText("");
						} else if (answer != null) {
							if (answer.equals("A")) {
								rdbtnA.setSelected(true);
							} else if (answer.equals("B")) {
								rdbtnB.setSelected(true);
							} else if (answer.equals("C")) {
								rdbtnC.setSelected(true);
							} else if (answer.equals("D")) {
								rdbtnD.setSelected(true);
							} else {
								blankArea.setText(answer);
							}
						}
						protext.setText(currentQuestion + "/" + totalQuestion);

					} catch (NumberFormatException eNum) {
						JOptionPane.showMessageDialog(contentPane, String.format("找不到输入的题号“%s”", in));
						protext.setText(currentQuestion + "/" + totalQuestion);
					} catch (SQLException eSQL) {
						JOptionPane.showMessageDialog(contentPane, String.format("找不到输入的题号“%s”", in));
						protext.setText(currentQuestion + "/" + totalQuestion);
					}

				}
			});

			// 上一题
			prePro = new JButton("\u4E0A\u4E00\u9898");
			// prePro.setFont(buttonFont);
			indexPro.add(prePro);
			prePro.addActionListener(new questButtonHandler());
			prePro.setEnabled(false);

			// 下一题
			nextPro = new JButton("\u4E0B\u4E00\u9898");
			// nextPro.setFont(buttonFont);
			indexPro.add(nextPro);
			nextPro.addActionListener(new questButtonHandler());

			// PPT部分
			JPanel slidePanel = new JPanel();
			splitPane.setLeftComponent(slidePanel);
			slidePanel.setLayout(new BorderLayout());

			// 上下页按钮
			JPanel pagePanel = new JPanel();
			slidePanel.add(pagePanel, BorderLayout.SOUTH);

			// ppt图片
			PPTlabel = new JLabel("");
			PPTlabel.addMouseListener(new PageListener());
			PPTlabel.addMouseWheelListener(new PageListener());

			PPTlabel.setHorizontalAlignment(SwingConstants.CENTER);
			slidePanel.add(PPTlabel, BorderLayout.CENTER);
			PPTlabel.setMinimumSize(new Dimension(800, 600));// 最小窗口大小

			// 初始化加载第一页图片
			try {
				totalPage = Integer.valueOf(find.num2Chapter(currentChapter));
				String path = find.num2URL(2, currentPage);
				icon = new ImageIcon(path); // 相对路径

			} catch (SQLException eSQL) {
				eSQL.printStackTrace();
			}
			// ppt图片自适应控件大小
			this.setVisible(true);
			icon = scale.scaledImage(icon, PPTlabel.getWidth(), PPTlabel.getHeight());
			PPTlabel.setIcon(icon);

			// ppt页数标签
			pageShowlabel = new JLabel("\u9875\uFF1A");
			// pageShowlabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
			pagePanel.add(pageShowlabel);
			// ppt页数输入
			pagetext = new JTextField(currentPage + "/" + totalPage);
			// pagetext.setFont(new Font("微软雅黑", Font.PLAIN, 16));
			pagePanel.add(pagetext);
			pagetext.setColumns(4);

			pagetext.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String in = pagetext.getText();
					try {
						int temp = Integer.parseInt(in);
						if (temp < 1 || temp > totalPage) {
							throw new NumberFormatException();
						}
						String path = find.num2URL(currentChapter, temp);
						icon = new ImageIcon(path); // 相对路径
						icon = scale.scaledImage(icon, PPTlabel.getWidth(), PPTlabel.getHeight());
						PPTlabel.setIcon(icon);
						currentPage = temp;
						if (currentPage == 1) {
							prePage.setEnabled(false);
							nextPage.setEnabled(true);
						} else if (currentPage == totalPage) {
							prePage.setEnabled(true);
							nextPage.setEnabled(false);
						} else {
							prePage.setEnabled(true);
							nextPage.setEnabled(true);
						}
						pagetext.setText(currentPage + "/" + totalPage);

					} catch (NumberFormatException eNum) {
						JOptionPane.showMessageDialog(contentPane, String.format("找不到输入的页码“%s”", in));
						pagetext.setText(currentPage + "/" + totalPage);
					} catch (SQLException eSQL) {
						JOptionPane.showMessageDialog(contentPane, String.format("找不到输入的页码“%s”", in));
						pagetext.setText(currentPage + "/" + totalPage);
					}
				}
			});

			// 上一页
			prePage = new JButton("\u4E0A\u4E00\u9875");
			// prePage.setFont(buttonFont);
			pagePanel.add(prePage);
			prePage.addActionListener(new pageButtonHandler());
			prePage.setEnabled(false);
			// 下一页
			nextPage = new JButton("\u4E0B\u4E00\u9875");
			// nextPage.setFont(buttonFont);
			pagePanel.add(nextPage);
			nextPage.addActionListener(new pageButtonHandler());

			JPanel readingPanel = new JPanel();
			splitPane_1.setRightComponent(readingPanel);
			readingPanel.setLayout(new BorderLayout(0, 0));

			JLabel lblFurtherReading = new JLabel("Further Reading: ");
			// lblFurtherReading.setFont(new Font("微软雅黑", Font.PLAIN, 16));
			readingPanel.add(lblFurtherReading, BorderLayout.NORTH);

			JTextArea reading = new JTextArea();
			reading.setEditable(false);
			reading.setLineWrap(true);
			// reading.setFont(new Font("微软雅黑", Font.PLAIN, 18));
			try {
				lblFurtherReading.setText("Further Reading:        " + find.num2title(currentChapter));
				reading.setText(find.num2txt(currentChapter));
			} catch (SQLException e1) {
				reading.setText("此章没有拓展阅读。");
			}
			reading.setCaretPosition(0);
			JScrollPane scrolpane = new JScrollPane();
			scrolpane.setViewportView(reading);
			readingPanel.add(scrolpane, BorderLayout.CENTER);

			// 鼠标全选页数
			pagetext.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					pagetext.selectAll();
				}
			});

			// 窗口大小变换事件
			slidePanel.addComponentListener(new ComponentAdapter() {
				@Override
				public void componentResized(ComponentEvent e) {
					icon = scale.scaledImage(icon, PPTlabel.getWidth(), PPTlabel.getHeight());
					PPTlabel.setIcon(icon);
				}
			});
		} finally {
			DBUtility.closeConnection();
		}
	}// 构造函数结束

	// 上下页事件处理
	private class pageButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {

			if (event.getSource() == prePage) {
				currentPage--;
				pagetext.setText(currentPage + "/" + totalPage);

			} else if (event.getSource() == nextPage) {
				currentPage++;
				pagetext.setText(currentPage + "/" + totalPage);
			}

			if (currentPage >= 1 && currentPage <= totalPage) {
				try {
					String path = find.num2URL(currentChapter, currentPage);

					icon = new ImageIcon(path); // 相对路径
					// 图片自适应控件大小
					icon = scale.scaledImage(icon, PPTlabel.getWidth(), PPTlabel.getHeight());
					PPTlabel.setIcon(icon);
					if (currentPage == 1) {
						prePage.setEnabled(false);
						nextPage.setEnabled(true);
					} else if (currentPage == totalPage) {
						prePage.setEnabled(true);
						nextPage.setEnabled(false);
					} else {
						prePage.setEnabled(true);
						nextPage.setEnabled(true);
					}
				} catch (SQLException eSQL) {
					eSQL.printStackTrace();
				}
			} else {
				if (currentPage < 1) {
					currentPage++;
					pagetext.setText(currentPage + "/" + totalPage);
					// JOptionPane.showMessageDialog(null, "已经是第一张");
				}

				if (currentPage > totalPage) {
					currentPage--;
					pagetext.setText(currentPage + "/" + totalPage);
					// JOptionPane.showMessageDialog(null, "已经是最后一张");
				}
			}

		}

	}

	// 滚轮翻页事件处理
	private class PageListener implements MouseWheelListener, MouseListener {

		@Override
		public void mouseEntered(MouseEvent e) {
			flag = true;
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent ew) {
			if (flag == true) {
				if (ew.getWheelRotation() == -1) {
					currentPage--;
					pagetext.setText(currentPage + "/" + totalPage);
				}
				if (ew.getWheelRotation() == 1) {
					currentPage++;
					pagetext.setText(currentPage + "/" + totalPage);
				}
			}

			if (currentPage >= 1 && currentPage <= totalPage) {
				try {
					String path = find.num2URL(currentChapter, currentPage);
					icon = new ImageIcon(path); // 相对路径
					// 图片自适应控件大小
					icon = scale.scaledImage(icon, PPTlabel.getWidth(), PPTlabel.getHeight());
					PPTlabel.setIcon(icon);
					if (currentPage == 1) {
						prePage.setEnabled(false);
						nextPage.setEnabled(true);
					} else if (currentPage == totalPage) {
						prePage.setEnabled(true);
						nextPage.setEnabled(false);
					} else {
						prePage.setEnabled(true);
						nextPage.setEnabled(true);
					}
				} catch (SQLException eSQL) {
					// TODO Auto-generated catch block
					eSQL.printStackTrace();
				}
			} else {
				if (currentPage < 1) {
					currentPage++;
					pagetext.setText(currentPage + "/" + totalPage);
					JOptionPane.showMessageDialog(PPTlabel, "已经是第一张");
				}

				if (currentPage > totalPage) {
					currentPage--;
					pagetext.setText(currentPage + "/" + totalPage);
					JOptionPane.showMessageDialog(PPTlabel, "已经是最后一张");
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			flag = false;
			// System.out.println("out");
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	// 上下题按钮响应
	private class questButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			int act = JOptionPane.NO_OPTION;
			// JOptionPane.CANCEL_OPTION为取消无动作
			// JOptionPane.NO_OPTION为正常上下题
			// JOptionPane.YES_OPTION为弹出确认保存

			try {
				if (proProperty.equals("blank")) {
					if (!blankArea.getText().equals(rSet.getString("Answer" + currentQuestion))
							&& !(rSet.getString("Answer" + currentQuestion) == null & blankArea.getText().equals(""))) {
						act = JOptionPane.showConfirmDialog(contentPane, "已经修改，是否保存？", "确认",
								JOptionPane.YES_NO_CANCEL_OPTION);

					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (act == JOptionPane.NO_OPTION || act == JOptionPane.YES_OPTION) {// 正常上下题弹or出确认保存
				if (act == JOptionPane.YES_OPTION) {
					// 保存题目
					try {
						String ans = blankArea.getText();
						boolean flag = DBUtility.executeUpdate("UPDATE chapter" + currentChapter + "_Answers SET Answer"
								+ currentQuestion + "='" + ans + "' WHERE StudentID=" + stuID);
						DBUtility.closeConnection();
						rSet = DBUtility.executeQuery(
								"SELECT * from chapter" + currentChapter + "_Answers where StudentID=" + stuID);
						rSet.next();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				if (event.getSource() == prePro) {
					currentQuestion--;
					protext.setText(currentQuestion + "/" + totalQuestion);

				} else if (event.getSource() == nextPro) {
					currentQuestion++;
					protext.setText(currentQuestion + "/" + totalQuestion);
				}

				if (currentQuestion >= 1 && currentQuestion <= totalQuestion) {
					try {
						QuestiontextArea
								.setText(currentQuestion + "." + find.num2question(currentChapter, currentQuestion));
						proProperty = find.num2questIdentity(currentChapter, currentQuestion);
						if (currentQuestion == 1) {
							prePro.setEnabled(false);
							nextPro.setEnabled(true);
						} else if (currentQuestion == totalQuestion) {
							prePro.setEnabled(true);
							nextPro.setEnabled(false);
						} else {
							prePro.setEnabled(true);
							nextPro.setEnabled(true);
						}

						if (proProperty.equals("blank")) {
							cardLayout.show(cardPanel, "blank");
						} else if (proProperty.equals("choice")) {
							cardLayout.show(cardPanel, "choice");
						}
						String answer = rSet.getString("Answer" + currentQuestion);
						if (answer == null) {
							ButGroup.remove(rdbtnA);
							ButGroup.remove(rdbtnB);
							ButGroup.remove(rdbtnC);
							ButGroup.remove(rdbtnD);
							rdbtnA.setSelected(false);
							rdbtnB.setSelected(false);
							rdbtnC.setSelected(false);
							rdbtnD.setSelected(false);
							ButGroup.add(rdbtnA);
							ButGroup.add(rdbtnB);
							ButGroup.add(rdbtnC);
							ButGroup.add(rdbtnD);
							blankArea.setText("");
						} else if (answer != null) {
							if (answer.equals("A")) {
								rdbtnA.setSelected(true);
							} else if (answer.equals("B")) {
								rdbtnB.setSelected(true);
							} else if (answer.equals("C")) {
								rdbtnC.setSelected(true);
							} else if (answer.equals("D")) {
								rdbtnD.setSelected(true);
							} else {
								blankArea.setText(answer);
							}
						}
					} catch (SQLException eSQL) {
						eSQL.printStackTrace();
					}
				} else {
					if (currentQuestion < 1) {
						currentQuestion++;
						protext.setText(currentQuestion + "/" + totalQuestion);
						// JOptionPane.showMessageDialog(null, "已经是第一题");
					}

					if (currentQuestion > totalQuestion) {
						currentQuestion--;
						protext.setText(currentQuestion + "/" + totalQuestion);
						// JOptionPane.showMessageDialog(null, "已经是最后一题");
					}
				}
			} else if (act == JOptionPane.CANCEL_OPTION) {
				// 然而什么都没发生
			}
		}
	}

	// 选项ABCD事件响应
	private class choiceHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent choiceE) {
			try {
				if (rdbtnA.isSelected()) {
					boolean flag = DBUtility.executeUpdate("UPDATE chapter" + currentChapter + "_Answers SET Answer"
							+ currentQuestion + "='A' WHERE StudentID=" + stuID);
					DBUtility.closeConnection();
					rSet = DBUtility.executeQuery(
							"SELECT * from chapter" + currentChapter + "_Answers where StudentID=" + stuID);
					rSet.next();
				} else if (rdbtnB.isSelected()) {
					boolean flag = DBUtility.executeUpdate("UPDATE chapter" + currentChapter + "_Answers SET Answer"
							+ currentQuestion + "='B' WHERE StudentID=" + stuID);
					DBUtility.closeConnection();
					rSet = DBUtility.executeQuery(
							"SELECT * from chapter" + currentChapter + "_Answers where StudentID=" + stuID);
					rSet.next();
				} else if (rdbtnC.isSelected()) {
					boolean flag = DBUtility.executeUpdate("UPDATE chapter" + currentChapter + "_Answers SET Answer"
							+ currentQuestion + "='C' WHERE StudentID=" + stuID);
					DBUtility.closeConnection();
					rSet = DBUtility.executeQuery(
							"SELECT * from chapter" + currentChapter + "_Answers where StudentID=" + stuID);
					rSet.next();
				} else if (rdbtnD.isSelected()) {
					boolean flag = DBUtility.executeUpdate("UPDATE chapter" + currentChapter + "_Answers SET Answer"
							+ currentQuestion + "='D' WHERE StudentID=" + stuID);
					DBUtility.closeConnection();
					rSet = DBUtility.executeQuery(
							"SELECT * from chapter" + currentChapter + "_Answers where StudentID=" + stuID);
					rSet.next();
				} else {
					boolean flag = DBUtility.executeUpdate("UPDATE chapter" + currentChapter + "_Answers SET Answer"
							+ currentQuestion + "=NULL WHERE StudentID=" + stuID);
					DBUtility.closeConnection();
					rSet = DBUtility.executeQuery(
							"SELECT * from chapter" + currentChapter + "_Answers where StudentID=" + stuID);
					rSet.next();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	// 提交按钮响应
	private class submitHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				if (find.num2done(currentChapter, stuID)) {
					JOptionPane.showMessageDialog(contentPane, "你已经提交过当前章节答案，不可再次提交。");
				} else {

					int act = JOptionPane.NO_OPTION;
					// 如果界面为填空题，自动保存答案
					if (proProperty.equals("blank")) {
						String ans = blankArea.getText();
						boolean flag = DBUtility.executeUpdate("UPDATE chapter" + currentChapter + "_Answers SET Answer"
								+ currentQuestion + "='" + ans + "' WHERE StudentID=" + stuID);
						DBUtility.closeConnection();
						rSet = DBUtility.executeQuery(
								"SELECT * from chapter" + currentChapter + "_Answers where StudentID=" + stuID);
						rSet.next();
					}

					act = JOptionPane.showConfirmDialog(contentPane, "确定提交所有回答？提交后将不能修改，系统将自动批改选择题。", "确认",
							JOptionPane.YES_NO_OPTION);
					if (act == JOptionPane.YES_OPTION) {
						boolean finished = counter.checkFinished(stuID, currentChapter);
						if (!finished) {
							act = JOptionPane.showConfirmDialog(contentPane, "还未完成所有题目，确定提交所有回答？提交后将不能修改，系统将自动批改选择题。",
									"确认", JOptionPane.YES_NO_OPTION);
						}
						if (act == JOptionPane.YES_OPTION) {
							counter.giveScore(stuID, currentChapter);
							rdbtnA.setEnabled(false);
							rdbtnB.setEnabled(false);
							rdbtnC.setEnabled(false);
							rdbtnD.setEnabled(false);
							blankArea.setEditable(false);
							save.setEnabled(false);
							
						}

					}
				}
			} catch (NumberFormatException eN) {
				eN.printStackTrace();
			} catch (SQLException eSQL) {
				eSQL.printStackTrace();
			}

		}

	}

}
