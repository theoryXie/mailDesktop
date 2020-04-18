package GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import java.util.List;

import POP.PopMail;
import POP.PopMailServer;
import POP.PopResult;
import POP.ReceiveController;
import SMTP.MailBody;
import Util.MailUtil;
import com.sun.org.apache.bcel.internal.generic.POP;

public class receiveUI extends JFrame implements ActionListener {

   // public JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP);
    public JPanel rcvPanel;
    //接收邮件列表
    public JList rcvMailList;
    //邮件显示容器
    public JScrollPane mailPane;
    //标签
    public JLabel popLabel, portLabel, rcvrLabel, pswdLabel;
    //输入框
    public JTextField popText, portText, rcvrText;
    public JPasswordField pswdText;
    //接收、关闭按钮
    public JButton rcvButton, closeButton;

    public static DefaultListModel mailData = new DefaultListModel();

    //收件人
    public String sendUser;
    public Boolean sendUserIsOk2 = true;

    //接受返回的结果
    PopResult popResult;



    //构造函数
    public receiveUI(){
        init();
    }

    private void init(){
        rcvPanel = new JPanel();
        rcvPanel.setLayout(null);

        popLabel = new JLabel("POP服务：");
        popLabel.setBounds(40,20,100,25);
        rcvPanel.add(popLabel);
        popText = new JTextField();
        popText.setBounds(120,20,340,25);
        rcvPanel.add(popText);
        //默认值
        popText.setText("pop.qq.com");

        portLabel = new JLabel("端口：");
        portLabel.setBounds(40,60,100,25);
        rcvPanel.add(portLabel);
        portText = new JTextField();
        portText.setBounds(120,60,340,25);
        rcvPanel.add(portText);
        //默认为100
        portText.setText("110");

        rcvrLabel = new JLabel("收件人邮箱：");
        rcvrLabel.setBounds(40,100,100,25);
        rcvPanel.add(rcvrLabel);
        rcvrText = new JTextField("1244535094@qq.com");
        rcvrText.setBounds(120,100,340,25);
        rcvPanel.add(rcvrText);
        //检查邮箱格式是否正确
        checkMail();
        rcvrText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                checkMail();
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        pswdLabel = new JLabel("授权码：");
        pswdLabel.setBounds(40,140,100,25);
        rcvPanel.add(pswdLabel);
        pswdText = new JPasswordField();
        pswdText.setBounds(120,140,340,25);
        rcvPanel.add(pswdText);

        // 邮件列表
        rcvMailList = new JList(mailData);
        this.rcvMailList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // 双击显示邮件
				if (e.getClickCount() == 2) {
					// 获取下标
					int num = rcvMailList.getSelectedIndex();
					if(!mailData.isEmpty()){
					    int length = popResult.getAllNum();
					    try {
					        OpenMail openMail = new OpenMail();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "打开邮件失败！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        }
                    };
//					if (MailReciveController.message != null && MailReciveController.message.length > 0) {
//						int length = MailReciveController.message.length;
//						// 显示面板
//						try {
//							ShowMail sm = new ShowMail(MailReciveController.message[length - 1 - num]);
//						} catch (Exception e1) {
//							e1.printStackTrace();
//							JOptionPane.showMessageDialog(null, "打开邮件失败！", "提示消息", JOptionPane.WARNING_MESSAGE);
//						}
//					}

				}
            }
        });

        rcvMailList.setSelectedIndex(0);
        mailPane = new JScrollPane(rcvMailList);
        mailPane.setBorder(new TitledBorder("Email 列表"));
        mailPane.setBounds(50,180,400,360);
        rcvPanel.add(mailPane);

        rcvButton = new JButton("接收");
        rcvButton.setBounds(100,570,70,25);
        //点击接收按钮
        rcvButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //开启一个线程，避免阻塞整个界面
                Thread t1= new Thread() {
                    public void run() {
                        System.out.print("点击成功");
                        ReceiveMail();
                    }
                };
                t1.start();
            }
        });
        rcvPanel.add(rcvButton);

        closeButton = new JButton("关闭");
        closeButton.setBounds(330,570,70,25);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
//                    System.exit(0);
                    new OpenMail();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        rcvPanel.add(closeButton);


//        this.add(jtp);
//        jtp.add("收邮件", rcvPanel);
        this.add(rcvPanel);
        this.setTitle("邮箱");
        this.setSize(500, 700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon i = new ImageIcon(getClass().getResource("mail3.png"));
        this.setIconImage(i.getImage());

    }

    private void ReceiveMail(){
        String popUrl = popText.getText();
        String port = portText.getText();
        String receiver = rcvrText.getText();
        String psw = new String(pswdText.getPassword());

        //验证参数不为空
        if(!("".equals(popUrl) || "".equals(port) || "".equals(psw))) {
            MailBody mailBody2 = new MailBody();
            mailBody2.setSendUser(receiver);
            mailBody2.setSendUserPwd(psw);
            PopMailServer popMailServer = new PopMailServer(popUrl,port);
            ReceiveController receiveController = new ReceiveController();
            popResult = receiveController.receiveMail(popMailServer,mailBody2);
            JOptionPane.showMessageDialog(null,popResult.getMessage(),"提示",JOptionPane.INFORMATION_MESSAGE);
            //如果接受邮件成功
            if("读取成功！".equals(popResult.getMessage())){
                List<PopMail> popMails = popResult.getMails();
                for(int i = popResult.getAllNum();i>0;i--){
                    PopMail popMail = popMails.get(i - 1);
                    mailData.addElement(popMail.getSubject());
                }
            }
        }else{
            //弹框报错
            JOptionPane.showMessageDialog(null,"请将信息填写完整","错误",JOptionPane.ERROR_MESSAGE);
        }

    }

    protected void checkMail() {
        // 收件人
        sendUser = rcvrText.getText().trim();
        if (!"".equals(sendUser)) {
            String[] s = new String[1];
            s[0] = sendUser;
            if (MailUtil.checkMailFormat(s)) {
                sendUserIsOk2 = true;
            } else { //邮箱格式不正确
                sendUserIsOk2 = false;
                JOptionPane.showMessageDialog(null, "收件人邮箱格式不正确！请检查！", "提示消息", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

//        Object key = e.getSource();
//        if(key.equals(rcvButton)){
//            //接收
//        }
    }


    //
}

