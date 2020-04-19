package GUI;
import POP.PopMail;
import POP.PopResult;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class OpenMail extends JFrame implements ActionListener {
    public JScrollPane jsp;
    //主界面
    public JPanel mailPanel;
    //分为头部和邮件主体部分
    public JPanel jp1,jp2;
    //头部包含的信息
    public JPanel titlePane, senderPane, receiverPane, appendixPane, datePane;
    public JLabel titleLabel, senderLabel, receiverLabel, appendixLabel,dateLabel;
    //邮件主体
    public JEditorPane mailBody;

    public PopMail popMail;
    public JFrame jf1;
    public String path;

    //构造方法
    public OpenMail(PopMail popMail) throws Exception{
        this.jf1 = this;
        this.popMail = popMail;
        init();
    }

    public void init() throws Exception{
//        int width = 700;
//        int height = 70;
        this.setLayout(new BorderLayout(1,1));
        mailPanel = new JPanel();
        mailPanel.setLayout(new BorderLayout(2,1));
        this.add(mailPanel,BorderLayout.CENTER);

        jsp = new JScrollPane(mailPanel);
        this.add(jsp);

        jp1 = new JPanel();
        jp2 = new JPanel();
//        jp2.setSize();
        mailPanel.add(jp1,BorderLayout.NORTH);
        mailPanel.add(jp2,BorderLayout.CENTER);
        jp1.setLayout(new GridLayout(5,1,1,1));
        jp2.setLayout(new BorderLayout(1, 1));

        titlePane = new JPanel();
        titleLabel = new JLabel("主题：" + popMail.getSubject());
        titleLabel.setFont(new Font("楷体", Font.BOLD, 30));
        titlePane.add(titleLabel);
        titlePane.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
        jp1.add(titlePane);

        senderPane = new JPanel();
        senderPane.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
        senderLabel = new JLabel("发件人：" + popMail.getFrom());
        senderLabel.setFont(new Font("楷体",Font.BOLD,20));
        senderPane.add(senderLabel);
        jp1.add(senderPane);

        receiverPane = new JPanel();
        receiverPane.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
        receiverLabel = new JLabel("收件人：" + popMail.getTo());
        receiverLabel.setFont(new Font("楷体",Font.BOLD,20));
        receiverPane.add(receiverLabel);
        jp1.add(receiverPane);

        //TODO：判断是否含有附件
        Boolean hasAnnex = false;//popMail.hasAnnex();
        //附件信息
        appendixPane = new JPanel();
        appendixPane.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
        //TODO：+popMail.get附件()
        String annexName = "";
        if(hasAnnex){
//            annexName = popMail.getAnnexName();
        }
        appendixLabel = new JLabel("附件：" + annexName);
        appendixLabel.setFont(new Font("楷体",Font.BOLD,20));
        appendixPane.add(appendixLabel);
        jp1.add(appendixPane);
        //是否出现下载按钮
        if(hasAnnex){
            JButton downLoadBtn = new JButton("下载");
            appendixPane.add(downLoadBtn);
            //点击下载附件
            downLoadBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //弹出选择文件框
                    JFileChooser jfc = new JFileChooser();
                    jfc.setDialogTitle("下载");
                    jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int flag = jfc.showSaveDialog(jf1);
                    if (flag == JFileChooser.APPROVE_OPTION) {
                        File file = jfc.getSelectedFile();
                        path = file.getPath();
                    } else {
                        return;
                    }
                    //TODO:下载
                    try{
                        //popMail.downloadAnnex();
                        JOptionPane.showMessageDialog(null, "下载附件成功！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "下载附件失败！", "提示消息", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
        }


        datePane = new JPanel();
        datePane.setLayout(new FlowLayout(FlowLayout.RIGHT, 2, 2));
        dateLabel = new JLabel("2020-4-15");
        datePane.add(dateLabel);
        jp1.add(datePane);

        mailBody = new JEditorPane();
        mailBody.setEditable(false);
        mailBody.setText(popMail.getText());
        jp2.add(mailBody,BorderLayout.CENTER);


//        this.add(mailPanel);
        this.setTitle("Mail");
        this.setSize(750,700);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        ImageIcon i = new ImageIcon(getClass().getResource("mail3.png"));
        this.setIconImage(i.getImage());
        //只关闭当前窗体
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
