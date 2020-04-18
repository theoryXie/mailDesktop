package POP;

import SMTP.MailBody;
import org.junit.Test;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.util.regex.*;

public class popTest {

    String fromPattern = "From: (.*)To";
    String toPattern = "To: (.*)MIME-Version";
    String subjectPattern = "Subject: =\\?utf-8\\?b\\?(.*)\\?=";
    String textPattern = "Content-Transfer-Encoding: base64(.*)(--a)*";

    //测试pop3
    @Test
    public void testPop(){
        String res = "Received: from whu.edu.cn (unknown [202.114.64.89])\tby newmx44.qq.com (NewMx) with SMTP id \tfor <827041735@qq.com>; Sat, 18 Apr 2020 21:12:45 +0800X-QQ-FEAT: 7sdUmzD4owccZBqEJemXazfcp/k+j0PYQ6S1A8Lz3Yc5eLazlZJ36Q5I1jlqR\tr5P9JNyNE0S0L9hg6FjA9+/fUr2iv3vt4Y/Mf6hbPw6OLFpGdAQ3tgEBsO4wl7/EGIWZ6rR\tQIvZ3zquOQaWkW+EH0U7Z5LXBrHUkBrm+SO9qdVfZGufMSvtUzP+3VeWtXf6CKEe59r9Wit\tG3Xl5sVNutsPfKGFxhtYcy1+XcpgeD8rrZmBksAhggsjBrN+g9cXTltYIyCgXC7TW1kuqto\t7DmunmB64Dvv44X-QQ-MAILINFO: NL3WKUOj1eeIuTEcSU8gbPDeo3+oMSuDOF1J5rJqtZbMU+R5ZlOlRrOUl\tOXtEODPEclfTCOJYEsOq69E7qIed6Ak1n6HMwGXx0IgsdPj1squLN5et6PK/j20GzpO2Fpf\tUVH0MLn9GqJjl12DTdVyRnTkEV48Q/9vMDAtcEHqACxPX-QQ-mid: mxsza74t1587215564t7ahyunmkX-QQ-ORGSender: 2017302580167@whu.edu.cnX-QQ-XMAILINFO: NcczmF6PuHANw5Ggg9BmQbP6E0FxyOrXmXn/9CQwj/MmrLOXnJL28Hd9VmuKj2\t jMfGEEIesmnQTqrUPZcwYSmP9rVHXclFnN59yHykDsYBQiX230no6OIkkxRXVXhv0YRbzU1xP53O\t +4tPD16YJWKfnqxtPaVSaZ9l9DzP7zy2VE42PPOiV/k7qPkvxFQpzoJW03B0sxJPZroTqr6MA1BU\t 1OKjS3ojCfaHR34E0y4FzGaYqBeBswceITCKzC4Fjubrn5KhU1qUsxRD+dBkJSqxPrAMmEXLrIt4\t iyBNOcwllHT0wHG6iOWOSybYZ3FVCsybeSegwpwJ2HbbQXxTpyP6aev/bP9QZ8yygFrwpRyxjl2m\t Bh5qfFq8OL9bCRasecVvGIsWczQZ0PhFxl0vQAEMSuBTjO4CSlDCcmx1qy2vcBdvp9maY7W8yOfS\t AMiSvyRUNIQ64nXRb4Z33PWaaUvTqlwaLMfO4shHmSsGGru4NK7XzmLktP6e8sMMoWyMCCAxAA5U\t KW0AbmsrZLU7RJObadkSdUE26r7/i6CJu0ikDIzflCn3NKJvitdNd95eXb24ogxMj8YhPw99mO0u\t dejZmaIaX8CCc3AQ2GTMP8dXpR4bKoCvruDzPo5/wWNgGwSszHOd8axAo4MVghG1PFb0EhUwNg2n\t /lSA/l/2ZGN1giBaKZ+cz7qP2VCr9jZYBPVQ==DKIM-Signature: v=1; a=rsa-sha256; c=relaxed/relaxed;\td=whu.edu.cn; s=dkim; h=Received:From:To:MIME-Version:Subject:\tContent-Type:Date:Message-Id; bh=uahDU6eRspVbsdWbX/NP7ZO8ad0S2ug\tuLIhkcLhIfgg=; b=VuxyQb7Oyep6gaRJ2iTYVXs6GT/488FL2MY+F+LBZNDdsXT\tRfAMDcheBagceq4xySbeNyFByVNVNdH3rb3Ofb0QJT4BWfj1W48iwi5DEeGOu3VN\tamrvni9gQJutEBPUVXEv9Gto4o57a8WiOhe2ofc4UsucT2t4QCZVzBxbc3Js=Received: from whu.edu.cn (unknown [118.250.30.28])\tby email2 (Coremail) with SMTP id AgBjCgDXiE3J_Jpe_DERAQ--.60539S2;\tSat, 18 Apr 2020 21:12:43 +0800 (CST)From: 2017302580167@whu.edu.cnTo: [827041735@qq.com]MIME-Version: 1.0Subject: =?utf-8?b?5L2g5aW9?=Content-Type:multipart/mixed;boundary=\"a\"Date: 2020-04-18 09:12:46X-CM-TRANSID:AgBjCgDXiE3J_Jpe_DERAQ--.60539S2Message-Id:<5E9AFCCB.00DE4E.20677@whu.edu.cn>X-Coremail-Antispam: 1UD129KBjDUn29KB7ZKAUJUUUUU529EdanIXcx71UUUUU7v73\tVFW2AGmfu7bjvjm3AaLaJ3UjIYCTnIWjp_UUUrk7k0a2IF6F4UM7kC6x804xWl14x267AK\txVWUJVW8JwAFc2x0x2IEx4CE42xK8VAvwI8IcIk0rVWrJVCq3wAFIxvE14AKwVWUJVWUGw\tA2jI8I6cxK6x804I0_KFvE3s1l8cAvFVAK0II2c7xJM28CjxkF64kEwVA0rcxSw2x7M28E\tF7xvwVC0I7IYx2IY67AKxVW7JVWDJwA2z4x0Y4vE2Ix0cI8IcVCY1x0267AKxVW8JVWxJw\tA2z4x0Y4vEx4A2jsIE14v26rxl6s0DM28EF7xvwVC2z280aVCY1x0267AKxVW0oVCq3wAa\tc4AC62xK8xCEY4vEwIxC4wAac4AC6xC2jxv24VCSYI8q64vIFxWle2I262IYc4CY6c8Ij2\t8IcVAaY2xG8wAqx4xG64xvF2IEw4CE5I8CrVC2j2Wl5I8CrVCF54CYxVAG04kSxc1lY4va\tj48v8VA2jI8xMcIj6xIIjxv20xvE14v26r1j6r18McIj6I8E87Iv67AKxVWUJVW8JwAm72\tCE4IkC6x0Yz7v_Jr0_Gr1lF7xvr2IY64vIr41lFcxC0VAYjxAxZF0Ew4CEw7xC0wACY4xI\t6c02F40Ez4kGawACY4xI64xv4xAvw2CEb4CIw280cs4lw4CEF2IF47xS0VAv8wAKzVCjr7\txvwVAFz4v204v26I0v724lw4CEc2x0rVAKj4xxMxkF7I0Ew4C26cxK6c8Ij28IcwCY02Av\tz4vE14v_Xr4l42xK82IYc2Ij64vIr41l4I8I3I0E4IkC6x0Yz7v_Jr0_Gr1lx2IqxVAqx4\txG67AKxVWUGVWUWwC20s026x8GjcxK67AKxVWUJVWUGwC2zVAF1VAY17CE14v26r1Y6r17\tMIIYrxkI7VAKI48JMIIF0xvE2Ix0cI8IcVAFwI0_Jr0_JF4lIxAIcVC0I7IYx2IY6xkF7I\t0E14v26r1j6r4UMIIF0xvE42xK8VAvwI8IcIk0rVWrZr1j6s0DMIIF0xvEx4A2jsIE14v2\t6r1j6r4UMIIF0xvEx4A2jsIEc7CjxVAFwI0_Jr0_Gr1l6VACY4xI67k04243AbIYCTnIWI\tevJa73UjIFyTuYvjxUqRnQUUUUUX-CM-SenderInfo: ysqrljaqsvmiqrwxq4lkxovvfxof0/--aContent-Type: text/html; charset=\"utf-8\"Content-Transfer-Encoding: base645L2g5aW96LCi5biF5a6H77yM5L2g6L+Y5aW95ZCX77yM5oiR6ams5LiK5p2l5om+5L2g--a";
        //String res = "Received: from smtp.qq.com ([118.250.30.28])\tby newxmesmtplogicsvrszc5.qq.com (NewEsmtp) with SMTP\tid 9238F656; Sat, 18 Apr 2020 20:36:35 +0800X-QQ-mid: xmsmtpt1587213395tyg75z6uqX-QQ-XMAILINFO: N8kmgMm8RQgCpeEht/zKf2Ytu9cXdqLKz0xE4IdKfFiiXJMNnJ0zGi+T93RqPh\t dsiQz8jkBXT3rh2w/vLo7oleIe5PYrJDsKGyg+qE0PQfduOWXAkdmIVj72yJGSQeGmNnyAHPXsrD\t 8lwq2i2OVRAPAxfN9jHZoqYNhajcrnQbw88zpOIJxVODovJ5+LnQ8FZs6dscc24vYKytYfk8v1xB\t OXWhQy7FKKyB9USmqrUIuVpspK6gmeAtjuZcb7EgyGKK4TxtRjaB1jeWYZ0PDqMTlO12M2uOIUhI\t levTa4yc+fRt3S+jgZWzI6KxJMsnHvZapIigAPsIx+nuBlmRe2AZyVFdaxaluBeuo1yUYhzkogUY\t E3Dq/pKZRZigqmWgVG08+ZQdwTV+qj41funwLa5MgMD+USMlvOhynmKznN++kS8R5NdR/ftNzEOa\t 59UPf4COmbcizV8T6sMICPrl76D8Hxd9rp9ygIfo3CjE8kblZZ5db32v+6skw8qEd7O6V+p2NCmZ\t w7fNcv57rKoKCDISU5FJVsK6anhqvovJADmjyrvrecBoCb9rBAWNTmM2PT6D30UMV13woQ25M/Yd\t 0bo0Y/afRDqlKh5/uQQhErg1GBlBgn6goCVj6UBKdEyFP+HAeOXeWYSQ1goaDlnDozVQo=From: 827041735@qq.comTo: [827041735@qq.com]MIME-Version: 1.0Subject: =?utf-8?b?5L2g5aW977yM5oiR5Lus55qE6YKu5Lu257O757uf57uI5LqO5a6M5oiQ5LqG?=Content-Type:multipart/mixed;boundary=\"a\"Date: 2020-04-18 08:36:39--aContent-Type: text/html; charset=\"utf-8\"Content-Transfer-Encoding: base6457uP6L+H5b6I6ZW/5pe26Ze055qE5Yqq5Yqb77yM5oiR5Lus55qEbWFpbERlc2t0b3Dnu4jkuo7lrozmiJDkuoY=";
        System.out.println(regixPattern(fromPattern, res));
        System.out.println(regixPattern(toPattern, res));
        System.out.println(regixPattern(subjectPattern, res));
        System.out.println(regixPattern(textPattern, res).replace("--a",""));
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
        mb2.setSendUserPwd("izaalzwhtryvbdbb");
        PopResult result = rec.receiveMail(ps,mb2);
        for(PopMail popMail:result.getMails()){
            System.out.println(popMail);
        }
    }
}
