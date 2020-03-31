package GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import Util.MailUtil;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class receiveUI extends JFrame implements ActionListener {

    public JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP);
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

    public static void main (String[] args){
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                //初始化主题
                //设置字体
                InitGlobalFont(new Font("微软雅黑", Font.PLAIN, 13));

                //设置边框样式
                //普通不透明
                BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
                //设置主题
                try {
                    org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //隐藏设置按钮
                UIManager.put("RootPane.setupButtonVisible", false);
                // 开启/关闭窗口在不活动时的半透明效果
                // 设置此开关量为false即表示关闭之，BeautyEye LNF中默认是true
                BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
                // 设置BeantuEye外观下JTabbedPane的左缩进
                // 改变InsetsUIResource参数的值即可实现
                UIManager.put("TabbedPane.tabAreaInsets", new javax.swing.plaf.InsetsUIResource(3, 20, 2, 20));
                // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                new receiveUI();
            }
        } );
    }

    //字体设置
    public static void InitGlobalFont(Font font){
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

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
        portText.setText("100");

        rcvrLabel = new JLabel("收件人邮箱：");
        rcvrLabel.setBounds(40,100,100,25);
        rcvPanel.add(rcvrLabel);
        rcvrText = new JTextField();
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
        rcvButton.addActionListener(this);
        rcvPanel.add(rcvButton);

        closeButton = new JButton("关闭");
        closeButton.setBounds(330,570,70,25);
        closeButton.addActionListener(this);
        rcvPanel.add(closeButton);


        this.add(jtp);
        jtp.add("收邮件", rcvPanel);
        this.setTitle("邮箱");
        this.setSize(500, 700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon i = new ImageIcon(getClass().getResource("mail3.png"));
        this.setIconImage(i.getImage());

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

        Object key = e.getSource();
        if(key.equals(rcvButton)){
            //接收
        }
    }


    //
}

