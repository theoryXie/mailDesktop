package GUI;
import sun.plugin2.message.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
    public Message message;

    //构造方法
    public OpenMail() throws Exception{
//        this.message = message;
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
        titleLabel = new JLabel("主题：");
        titleLabel.setFont(new Font("楷体", Font.BOLD, 30));
        titlePane.add(titleLabel);
        titlePane.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
        jp1.add(titlePane);

        senderPane = new JPanel();
        senderPane.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
        senderLabel = new JLabel("发件人：");
        senderLabel.setFont(new Font("楷体",Font.BOLD,20));
        senderPane.add(senderLabel);
        jp1.add(senderPane);

        receiverPane = new JPanel();
        receiverPane.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
        receiverLabel = new JLabel("收件人：");
        receiverLabel.setFont(new Font("楷体",Font.BOLD,20));
        receiverPane.add(receiverLabel);
        jp1.add(receiverPane);

        appendixPane = new JPanel();
        appendixPane.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));
        appendixLabel = new JLabel("附件：");
        appendixLabel.setFont(new Font("楷体",Font.BOLD,20));
        appendixPane.add(appendixLabel);
        jp1.add(appendixPane);

        datePane = new JPanel();
        datePane.setLayout(new FlowLayout(FlowLayout.RIGHT, 2, 2));
        dateLabel = new JLabel("2020-4-15");
        datePane.add(dateLabel);
        jp1.add(datePane);

        mailBody = new JEditorPane();
        mailBody.setText("Save on simple and effective skincare solutions...\n" +
                "\n" +
                "FREE WORLDWIDE DELIVERY*\n" +
                "\n" +
                "HQ Hair <https://links.n.hqhair.com/ls/click?upn=zuM0yI1Vk7D2VmtUyJRvRsN5ISvjiIOS-2F2" +
                "sq24nrvOFU5BkXPpQ-2FUlnfthCxRmZ8uDn9pEpDOTtUS8cpGx1i-2BAIHd2S2jWOwEGEA7Um2Aza-2FNBKi6tW" +
                "nK2u4-2F-2Fql44yz0N5vXFgnUlgoqAyP4fug4-2FZvlfdiPUlIUYvP8Rsyd0Ji5G3k85mI2mnaNPeVTI8Hc1SE" +
                "Sgj4uSUipXXS5443scu8AQtKZpdLXn-2FaLv28WlQhyjDHRQT3ijvdeGspi8Qijh8dz72d0yyY3BQTR4hwrJpuVl" +
                "D84XoAp5mUqP1c2iq5TxjT3fi0HU-2Bc8AuovBzTztmb_DOMMMy2XVTAiz2UaIAPh6gBt9z-2FJxQN-2FIS3p4yKh" +
                "hmeDyV6iUiUZLXfGZBAO05xpPZ3lXj3ZXzkpMjmHTBlhNqXGdrT4fflbupvqBP9V3D0kr7QPoOm9iOyTEG88lbZ3T" +
                "2u8ZLFJCl0uNV-2FJdO0DKgzahlniu-2BSvQ4HuqFdn53euKDfbRSEuoPMkuDgpqtMOPUL4Gwu0VdY36R9H9R5kV2" +
                "ufX5nbOzxSEbHbmhl7T2xPALRf5u-2Fi-2BzQLDtqUfQlqdro7z-2B7qdVG1ImDV8QCR0iuEfabihNk21z7ixqanmg" +
                "iQ-2BKsaEQcafxNGpvcHglgmYH2JwDJ5bA3I-2F7YvBakJtoCBqLuVAmc6F-2Fj0PhDWYMktkDbtpzf5DDvcMiVhAJ-" +
                "2FJty2yCEBUWm3JSqX-2FnJfgYlW7hTNyU6IwPBAKi2zxweWP-2Bgk-2BkXgEPSKpBAZTDUhSjBLwyogb6cLyd2R6xV" +
                "AFQP9RYrs129Vkx3Vv-2FIjY52tyF-2FkyuzKsHf3Z6u-2BtOz82xXjgXjqa6JGZT-2F5nc26sbLfjyy3B6RLQozW-2" +
                "FrRrbPG7Ft0NDomnkGP7o8iiiGG33jXyPbZnSlac3rNk1AoXXJmrRMK-2BNT3CAwcuGdxDwO5cFIecUGbf6RgRIzTFf" +
                "U1GiJVdheGf4xQ74O053pPC7cstmMPXlV87-2FEyvYDWFnUBP9Xov6FjuRQoJ5NT-2Ffpsa5wgi16zHWWhJhdM82O3FC" +
                "wr9JeXnznpJTPbNv0opRiiQ-3D>\n" +
                "\n" +
                "HOME <https://links.n.hqhair.com/ls/click?upn=zuM0yI1Vk7D2VmtUyJRvRv8XZ6dXAhSl9QcRhLH9AGSnY" +
                "1iMr-2BUpwqIRziCRVEnJLJB-2BR4vRD04z8YCDvul1Hbohk989o0dKRWFcoiRln068P-2BIZ09cSdRYXL4EoRfeyDc" +
                "K0WpGR6V9gMsg0icY9dnV2PfSldpz8WIRnZ2D76YTbDrEnUvWIIfdAdRlQrOW3932jyA1A5KcAO24GDls1L5O3-2FWnT" +
                "FhLxEZ3Op644tyQKHbO-2BXjR229n8zcrKSp-2Beq5qPDXMVGwZRCVioOl72d-2FU5GOIaWDwehMUGRsJSgULzzvfVE" +
                "G2Ysdn2QxNF2pMN4TcM_DOMMMy2XVTAiz2UaIAPh6gBt9z-2FJxQN-2FIS3p4yKhhmeDyV6iUiUZLXfGZBAO05xpPZ3l" +
                "Xj3ZXzkpMjmHTBlhNqXGdrT4fflbupvqBP9V3D0kr7QPoOm9iOyTEG88lbZ3T2u8ZLFJCl0uNV-2FJdO0DKgzahlniu-2BSvQ" +
                "4HuqFdn53euKDfbRSEuoPMkuDgpqtMOPUL4Gwu0VdY36R9H9R5kV2ufX5nbOzxSEbHbmhl7T2xPALRf5u-2Fi-2BzQLDtqUfQ" +
                "lqdro7z-2B7qdVG1ImDV8QCR0iuEfabihNk21z7ixqanmgiQ-2BKsaEQcafxNGpvcHglgmYH2JwDJ5bA3I-2F7YvBakJtoCBq" +
                "LuVAmc6F-2Fj0PhDWYMktkDbtpzf5DDvcMiVhAJ-2FJty2yCEBUWm3JSqX-2FnJfgYlW7hTNyU6IwPBAKi2zxweWP-2Bgk-2Bk" +
                "XgEPSKpBAZTDUhSjBLwyogb6cLyd2R6xVAFQP9RYrs129Vkx3Vv-2FIjY52tyF-2FkyuzKsHf3Z6u-2BtOz82xXjgXjqa6JGZT" +
                "-2F5nc26sbLfjyy3B6RLQozW-2FrRrbPG5WoJOKM1meP-2B9605R1BZOLVehN98HVS-2FzD2Tyox49PV0-2Fvf6qM2iXe1-2FoO" +
                "jxxQTOPkQVmp28loRdJifrGuP2ZlJdUivGyi5K88OIkuoE-2BDR0K7XMzexmHJWdn-2BpM7afjfG7NBQKJnT6SRm73shrVCDR5c" +
                "5rkG8xSE06B8SCRRyhHs1RSFQ6Ywx39jtv4m1TK4-3D>");
        jp2.add(mailBody,BorderLayout.CENTER);


//        this.add(mailPanel);
        this.setTitle("Mail");
        this.setSize(750,700);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ImageIcon i = new ImageIcon(getClass().getResource("mail3.png"));
        this.setIconImage(i.getImage());


    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
