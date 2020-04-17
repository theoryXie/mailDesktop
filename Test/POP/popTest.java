package POP;

import SMTP.MailBody;
import org.junit.Test;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.util.regex.*;

public class popTest {

    String fromPattern = "\\(CST\\)From: (.*)To";
    String toPattern = "To: (.*)MIME-Version";
    String subjectPattern = "Subject: =\\?utf-8\\?b\\?(.*)\\?=";
    String textPattern = "Content-Transfer-Encoding: base64(.*)--a";

    //测试pop3
    @Test
    public void testPop(){
        String res = "Received: from whu.edu.cn (unknown [202.114.64.89])\tby newmx43.qq.com (NewMx) with SMTP id \tfor <827041735@qq.com>; Thu, 16 Apr 2020 10:12:23 +0800X-QQ-FEAT: qbGGU7ggPtCGmewK3hTKdPr/wChRmsWyN+tzR06O2s6stK00789Z+YbcBnfTh\tu5UhF7YsYD3lW/QX1GMJwAXsUtLVVqv3X4W5qDAFQHl06xX+C8q1MVhQFUef8M38yqS29GX\trS2W+qD9WTOWeQBtUNXjjRKI1txry+lCQYV9IR+NlOQaX9bW5FlDHntXRKDUYJqVpRE0+Rf\tMM3kMy2AyJueYnziYR13iKxjgHf7ZrCDUTYBzuezllIau92uG7jURjnFvuinGALNLVe8/Rp\tcIfVdOttGM7NzUX-QQ-MAILINFO: NbUxUzKtP+7YXrl9LOzRFYHg6LEOdnqUBxKTF8Ft9YpYQw7nFFOpROkA4\tZD2mgYRJorTwRJVxJEHgGVCElC/eLhovui5ty6KOC1VkhANjUy5i85yJy9yfn4ucOHOTo/+\teP47mB7RBuayYMgHgPjU4Ar22buw/GNcdaVcxVyonyuGX-QQ-mid: mxszc19t1587003142t1432121yX-QQ-ORGSender: 2017302580167@whu.edu.cnX-QQ-XMAILINFO: MSLmTS2n5AhqePn27IC/WiL8VV53jfz6xZqqYBWaif8j1Ko0vpN2jZ0Rj8Wcte\t 1p8vFkE0NU4+rjjIK5kQaM4PL6wJ1JoBj9xO8Lad2238L72/bH5kE5xxAITBn9wz6f62N9OD6QeN\t s7LyeEE1Q7J6dHr5GMU8F4774AviAfi+mnVf8vDQcpPCPrfRpQl9xXmq/n/JPcIhqLubNE64Jf6H\t +l+PIrZKZWmqkeuBlVEMH2Ir/BbUsJHIfiSY7qeXFLKu2rOqho9T/PEtBVMbGGjY15BXEhQ72Qgr\t PN9rEGKjYXV+BAElOneemegooHTSBgBubE1CpBaQaz1hBbcTszclBHzfNnmcLuLuRD06VgSpnHBO\t dg09z1vmM+hqD5OgRGoGQN1mzkFD4Fh3UEa1qAxYERllUpbFJysnmMFf8sFIDEZRLjJw0y+LE6zl\t eMmaLY9kNQMgJRV6O6vMqTZX3sHGd7fCu1B81fdp0DHMyqxxhsuRBRDvClAuvYdoXFypreryq2J5\t kF2ehg1FUxIDQ+dQBpgckykjbvmsx7YMbQygyoN/nZoVjcbrjsi88BRcF2oloHw/QyN+LbxE/VFv\t AZnSYxbxuMsDdZugDwITvwd51WNTb2c4+24J0n+EShC78StPvLmqsZKTBJAJ1BC2pO+QYw5kfoNY\t 6BbPmJtNZgO9Sqt30PObDyQJTOL4twjIYXFQ==DKIM-Signature: v=1; a=rsa-sha256; c=relaxed/relaxed;\td=whu.edu.cn; s=dkim; h=Received:From:To:MIME-Version:Subject:\tContent-Type:Date:Message-Id; bh=tNFIHndlg8Ept9tNbU43csJcWlE/Jwo\tyAkB+6zsaxSs=; b=EhbuL0GtczSYRFeEZx5vJakUTqCxYq6jNDqDyNqciqh/305\ttBBY+H2H2VUQqG8I8btdVGvVcsgP3j/EPvif3gtLg2X6/jmaH2FQIFwa9JWaeWB0\tQD9WxbZ31nbWhb757NljKTuJn3kkD8QzAe4NumDrKxT8GGB7V2tmU2uXaGwc=Received: from whu.edu.cn (unknown [118.250.122.68])\tby email2 (Coremail) with SMTP id AgBjCgAXyEwFv5devnoBAQ--.9885S2;\tThu, 16 Apr 2020 10:12:22 +0800 (CST)From: 2017302580167@whu.edu.cnTo: [827041735@qq.com, whuxcsy@163.com]MIME-Version: 1.0Subject: =?utf-8?b?5L2g5aW9?=Content-Type:multipart/mixed;boundary=\"a\"Date: 2020-04-16 10:12:23X-CM-TRANSID:AgBjCgAXyEwFv5devnoBAQ--.9885S2Message-Id:<5E97BF06.0092A4.20677@whu.edu.cn>X-Coremail-Antispam: 1UD129KBjDUn29KB7ZKAUJUUUUU529EdanIXcx71UUUUU7v73\tVFW2AGmfu7bjvjm3AaLaJ3UjIYCTnIWjp_UUUrP7k0a2IF6F4UM7kC6x804xWl14x267AK\txVWUJVW8JwAFc2x0x2IEx4CE42xK8VAvwI8IcIk0rVWrJVCq3wAFIxvE14AKwVWUJVWUGw\tA2jI8I6cxK6x804I0_KFvE3s1l8cAvFVAK0II2c7xJM28CjxkF64kEwVA0rcxSw2x7M28E\tF7xvwVC0I7IYx2IY67AKxVW7JVWDJwA2z4x0Y4vE2Ix0cI8IcVCY1x0267AKxVWxJVW8Jr\t1l84ACjcxK6I8E87Iv67AKxVWxJr0_GcWl84ACjcxK6I8E87Iv6xkF7I0E14v26rxl6s0D\tM2vYz4IE04k24VAvwVAKI4IrM2vYz4IE4I80cI0F6IAvxc0EwIxC4wAS0I0E0xvYzxvE52\tx082IY62kv0487Mc02F40EFcxC0VAKzVAqx4xG6I80ewAqx4xG6xAqzxv2648Iw2C25wAv\twIk0rcx26280x24lYx0E2Ix0cI8IcVAFwI0_JF0_Jw1lYx0Ex4A2jsIE14v26r4j6F4UMc\tvjeVCFs4IE7xkEbVWUJVW8JwACjcxG0xvEwIxGrwACY4xI67k04243AVAKzVAKj4xxM4xv\tF2IE5I8CrVAEw40kM4xvF2IEFcIF4cxKeVAYzIxK87IYawAKzVAC0xCFj2AI6cx7M4kE6x\t8GjcxK67AEwI8IwI0ExsIj0wAKzVCY07xG64k0F24lc7CjxVAKzI0EY4vE52x082I5MxkI\tecxEwVAFwVW8JwCF04k20xvY0x0EwIxGrwCFx2IqxVCFs4IE7xkEbVWUJVW8JwC20s026c\t02F40E14v26r106r1rMI8I3I0E7480Y4vE14v26r1j6r18MI8E67AF67kF1VAFwI0_Jrv_\tJF1lIxkGc2Ij64vIr41lIxAIcVC0I7IYx2IY67AKxVWUJVWUCwCI42IY6xIIjxv20xvEc7\tCjxVAFwI0_Jr0_Gr1lIxAIcVCF04k26cxKx2IYs7xG6rWUJVWrZr1UMIIF0xvEx4A2jsIE\t14v26r1j6r4UMIIF0xvEx4A2jsIEc7CjxVAFwI0_Gr0_Gr1UMVCEFcxC0VAYjxAxZFUvcS\tsGvfC2KfnxnUUI43ZEXa7IU53gA7UUUUU==X-CM-SenderInfo: ysqrljaqsvmiqrwxq4lkxovvfxof0/--aContent-Type: text/html; charset=\"utf-8\"Content-Transfer-Encoding: base645L2g5aW96LCi5biF5a6H--a";
        System.out.println(regixPattern(fromPattern, res));
        System.out.println(regixPattern(toPattern, res));
        System.out.println(regixPattern(subjectPattern, res));
        System.out.println(regixPattern(textPattern, res));
    }

    private String regixPattern(String pattern, String res) {
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(res);
        if (m.find()){
            return m.group(1);
        }
        else
            return "NO MATCH";
    }

    @Test
    public void test(){
        ReceiveController rec = new ReceiveController();
        PopMailServer ps = new PopMailServer("pop.qq.com","110");
        MailBody mb2 = new MailBody();
        mb2.setSendUser("827041735@qq.com");
        mb2.setSendUserPwd("---");
        PopResult result = rec.receiveMail(ps,mb2);
        for(PopMail popMail:result.getMails()){
            System.out.println(popMail);
        }
    }
}
