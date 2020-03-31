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
    public JPanel jp1,jp2;
    public JList jl;
    public JScrollPane jsp;
    public JLabel jlb1, jlb2, jlb3, jlb4;
    public JTextField tt1, tt2, tt3;
    public JPasswordField jpf;
    public JButton button1, button2, button3, button4;

    public static DefaultListModel mailData = new DefaultListModel();

    public MailUtil mu = new MailUtil();

    //收件人
    public String sendUser;
    public Boolean sendUserIsOk2 = true;
    //默认设置



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
        jp2 = new JPanel();
        jp2.setLayout(null);

        jlb1 = new JLabel("POP服务：");
        jlb1.setBounds(40,20,100,25);
        jp2.add(jlb1);
        tt1 = new JTextField();
        tt1.setBounds(120,20,340,25);
        jp2.add(tt1);
        //默认值
        tt1.setText("pop.qq.com");

        jlb2 = new JLabel("端口：");
        jlb2.setBounds(40,60,100,25);
        jp2.add(jlb2);
        tt2 = new JTextField();
        tt2.setBounds(120,60,340,25);
        jp2.add(tt2);
        //默认为100
        tt2.setText("100");

        jlb3 = new JLabel("收件人邮箱：");
        jlb3.setBounds(40,100,100,25);
        jp2.add(jlb3);
        tt3 = new JTextField();
        tt3.setBounds(120,100,340,25);
        jp2.add(tt3);
        //检查邮箱格式是否正确
        checkMail();
        tt3.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                checkMail();
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        jlb4 = new JLabel("授权码：");
        jlb4.setBounds(40,140,100,25);
        jp2.add(jlb4);
        jpf = new JPasswordField();
        jpf.setBounds(120,140,340,25);
        jp2.add(jpf);

        // 邮件列表
        jl = new JList(mailData);
        this.jl.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // 双击显示邮件
//				if (e.getClickCount() == 2) {
//					// 获取下标
//					int num = jl.getSelectedIndex();
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
//
//				}
            }
        });

        jl.setSelectedIndex(0);
        jsp = new JScrollPane(jl);
        jsp.setBorder(new TitledBorder("Email 列表"));
        jsp.setBounds(50,180,400,360);
        jp2.add(jsp);

        button1 = new JButton("接收");
        button1.setBounds(100,570,70,25);
        button1.addActionListener(this);
        jp2.add(button1);

//		button2 = new JButton("重置");
//		button2.setBounds(155,630,70,25);
//		button2.addActionListener(this);
//		jp2.add(button2);
//
//		button3 = new JButton("中止");
//		button3.setBounds(255,570,70,25);
//		button3.addActionListener(this);
//		button3.setEnabled(false);
//		jp2.add(button3);

        button4 = new JButton("关闭");
        button4.setBounds(330,570,70,25);
        button4.addActionListener(this);
        jp2.add(button4);


        this.add(jtp);
        jtp.add("收邮件",jp2);
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
        sendUser = tt3.getText().trim();
        if (!"".equals(sendUser)) {
            String[] s = new String[1];
            s[0] = sendUser;
            if (!mu.checkMailFormat(s)) {
                sendUserIsOk2 = false;
                JOptionPane.showMessageDialog(null, "收件人邮箱格式不正确！请检查！", "提示消息", JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                sendUserIsOk2 = true;
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object key = e.getSource();
        if(key.equals(button1)){
            //接收
        }
    }


    //
}

