package GUI;

import SMTP.MailBody;
import SMTP.MailServer;
import SMTP.SendController;
import Util.MailUtil;
import javafx.scene.control.ComboBox;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class MainGUI extends JFrame implements ActionListener {


    //发送邮件界面
    public JPanel sendPanel;
    public JLabel sendLabel;
    //SMTP服务
    public JLabel serviceLabel;
    public JTextField serviceText;
    //端口
    public JLabel portLabel;
    public JTextField portText;
    //发送人
    public JLabel senderLabel;
    public JTextField senderText;
    //密码
    public JLabel passwordLabel;
    public JPasswordField passwordText;
    //接收人
    public JLabel receiverLabel;
    public JTextField receiverText;
    //标题
    public JLabel titleLabel;
    public JTextField titleText;
    //上传附件按钮
    public JButton uploadButton;
    public JLabel fileLable;
    //清除文件按钮
    public JButton deleteFile;
    //传文件进度
    public JProgressBar progressBar;

    //正文 滚动面板
    public JLabel textLabel;
    public JTextArea textArea;
    public JScrollPane scrollPane;
    //发送按钮
    public JButton sendButton;
    //存草稿
    public JButton saveButton;




    public static void main ( final String[] args ){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // 初始化字体
                InitGlobalFont(new Font("微软雅黑", Font.PLAIN, 13));
                // 改变窗口边框样式定义
                // 系统默认样式 osLookAndFeelDecorated
                // 强立体半透明 translucencyAppleLike
                // 弱立体感半透明 translucencySmallShadow
                // 普通不透明 generalNoTranslucencyShadow
                BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;

                // 设置主题BeautyEye
                try {
                    org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // 隐藏“设置”按钮
                UIManager.put("RootPane.setupButtonVisible", false);
                // 开启/关闭窗口在不活动时的半透明效果
                // 设置此开关量为false即表示关闭之，BeautyEye LNF中默认是true
                BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
                // 设置BeautyEye外观下JTabbedPane的左缩进
                // 改变InsetsUIResource参数的值即可实现
                UIManager.put("TabbedPane.tabAreaInsets", new javax.swing.plaf.InsetsUIResource(3, 20, 2, 20));
                // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new MainGUI();
            }
        });
    }

    public MainGUI() {
        init();

    }

    private void init(){

        sendPanel = new JPanel(new BorderLayout());
        sendPanel.setLayout(null);


        sendLabel = new JLabel("发送邮件");

        //第一列坐标
        int x1 = 30;
        int y1 = 10;
        int width1 = 100;
        int height1 = 25;
        //第二列坐标
        int x2 = 140;
        int y2 = 10;
        int width2 = 300;
        int height2 = 25;

        serviceLabel = new JLabel("SMTP服务器");
        serviceLabel.setBounds(x1,y1,width1,height1);
        sendPanel.add(serviceLabel);

        serviceText = new JTextField();
        serviceText.setBounds(x2,y2,width2,height2);
        sendPanel.add(serviceText);

        portLabel = new JLabel("端口");
        y1 = y1 + 40;
        portLabel.setBounds(x1,y1,width1,height1);
        sendPanel.add(portLabel);

        portText = new JTextField();
        y2 = y2 + 40;
        portText.setBounds(x2,y2,width2,height2);
        sendPanel.add(portText);

        senderLabel = new JLabel("发件人邮箱");
        y1 = y1 + 40;
        senderLabel.setBounds(x1,y1,width1,height1);
        sendPanel.add(senderLabel);

        senderText = new JTextField();
        y2 = y2 + 40;
        senderText.setBounds(x2,y2,width2,height2);
        sendPanel.add(senderText);

        passwordLabel = new JLabel("授权码");
        y1 = y1 + 40;
        passwordLabel.setBounds(x1,y1,width1,height1);
        sendPanel.add(passwordLabel);

        passwordText = new JPasswordField();
        y2 = y2 + 40;
        passwordText.setBounds(x2,y2,width2,height2);
        sendPanel.add(passwordText);

        receiverLabel = new JLabel("收件人邮箱");
        y1 = y1 + 40;
        receiverLabel.setBounds(x1,y1,width1,height1);
        sendPanel.add(receiverLabel);

        receiverText = new JTextField();
        y2 = y2 + 40;
        receiverText.setBounds(x2,y2,width2,height2);
        sendPanel.add(receiverText);

        titleLabel = new JLabel("邮件标题");
        y1 = y1 + 40;
        titleLabel.setBounds(x1,y1,width1,height1);
        sendPanel.add(titleLabel);

        titleText = new JTextField();
        y2 = y2 + 40;
        titleText.setBounds(x2,y2,width2,height2);
        sendPanel.add(titleText);

        uploadButton = new JButton("上传文件");
        y1 = y1 + 40;
        uploadButton.setBounds(20,y1,80,height1);
        sendPanel.add(uploadButton);

        fileLable = new JLabel();
        y2 = y2 + 40;
        fileLable.setBounds(x2,y2,width2,height2);
        fileLable.setText("文件名");
        sendPanel.add(fileLable);

        deleteFile = new JButton("清除文件");
        y1 = y1 + 40;
        deleteFile.setBounds(20,y1,80,height1);
        sendPanel.add(deleteFile);
        deleteFile.setEnabled(false);

        progressBar = new JProgressBar();
        y2 = y2 + 40;
        progressBar.setBounds(x2,y2+5,width2,height2-5);
        //进度条中间显示的百分数
        progressBar.setStringPainted(true);
        sendPanel.add(progressBar);



        textLabel = new JLabel("正文");
        y1 = y1 + 50;
        textLabel.setBounds(x1,y1,width1,height1);
        sendPanel.add(textLabel);

        textArea = new JTextArea();
        //自动换行
        textArea.setLineWrap(true);
        //创建滚动面板, 指定滚动显示的视图组件(textArea), 垂直滚动条一直显示, 水平滚动条从不显示
        scrollPane = new JScrollPane(textArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        y2 = y2 + 50;
        scrollPane.setBounds(x2-50,y2,width2+70,250);
        sendPanel.add(scrollPane);

        sendButton = new JButton("发送");
        y1 = y1+270;
        sendButton.setBounds(x1,y1,width1,height1);
        sendPanel.add(sendButton);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SendMail();

            }
        });

        saveButton = new JButton("存草稿");
        saveButton.setBounds(x1+120,y1,width1,height1);
        sendPanel.add(saveButton);


        this.add(sendPanel);
        this.setTitle("邮箱");
        this.setSize(500, 700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        ImageIcon icon = new ImageIcon(getClass().getResource("mail.png"));
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    // font
    private static void InitGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

    //发送邮件
    private void SendMail(){
        String smtpUrl = serviceText.getText();
        String port = portText.getText();
        String sender = senderText.getText();
        String pwd = new String(passwordText.getPassword());

        String receiver = receiverText.getText();
        List<String> recs = new ArrayList<>();
        recs.add(receiver);
        //TODO 暂时只发送给一个地址
        String subject = titleText.getText();
        String mainText = textArea.getText();
        String[] addresses = {sender,receiver};

        if(MailUtil.checkMailFormat(addresses))
        {
            MailBody mailBody = new MailBody(sender,pwd,recs,subject,null,mainText,null);
            MailServer mailServer = new MailServer(smtpUrl,port);
            SendController sendController = new SendController();
            String res = sendController.sendMail(mailServer,mailBody);
            if(res.equals("发送成功")){
                JOptionPane.showMessageDialog(null, "发送成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "发送失败", "错误", JOptionPane.ERROR_MESSAGE);
            }

        }
        else {
            //提示地址错误
            JOptionPane.showMessageDialog(null, "邮箱地址格式出错", "错误", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
