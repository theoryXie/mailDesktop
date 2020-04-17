package Util;

import POP.PopMail;
import SMTP.DeliveredState;
import SMTP.MailBody;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author     : xsy
 * @description: 公用邮件工具类
 * @date       : 2020/2/29
 */
public class MailUtil {

    /**
     *
     * 验证邮箱地址格式是否正确
     *
     * @author  xsy
     * @param  mails -- 邮件地址
     * @return 如果邮件地址有一个不符合要求，则返回false，否则为true
     */
    public static boolean checkMailFormat(String[] mails){
        if (mails == null) {
            return false;
        }
        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        // 遍历数组
        for (String mail : mails) {
            if (!regex.matcher(mail).matches()) {
                return false;
            }
        }
        return true;
    }

    public static PopMail decodePop(String mailString) throws IOException {
        String fromPattern = "\\(CST\\)From: (.*)To";
        String toPattern = "To: (.*)MIME-Version";
        String subjectPattern = "Subject: =\\?utf-8\\?b\\?(.*)\\?=";
        String textPattern = "Content-Transfer-Encoding: base64(.*)--a";
        String from =  regixPattern(fromPattern, mailString);
        String to = regixPattern(toPattern, mailString);
        final BASE64Decoder decoder = new BASE64Decoder();
        String subject = new String(decoder.decodeBuffer(regixPattern(subjectPattern, mailString)), StandardCharsets.UTF_8);
        String text = new String(decoder.decodeBuffer(regixPattern(textPattern, mailString)), StandardCharsets.UTF_8);
        return new PopMail(from,to,subject,text);
    }

    private static String regixPattern(String pattern, String res) {
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(res);
        if (m.find()){
            return m.group(1);
        }
        else
            return "NO MATCH";
    }



    /**
     *
     * 验证邮箱用户邮箱和密码是否正确
     *
     * @author  csy
     * @param  user -- 用户名
     * @param  pwd  -- 密码
     */
    public static boolean checkAccount(String user, String pwd){
        //TODO 验证用户名和密码

        return false;
    }

    /**
     *
     * 获取文件路径
     *
     * @author  csy
     * @param  files -- 多个文件
     * @param filesPath -- 文件路径的list
     */
    public static void getFilesPath(File[] files,List<String> filesPath){
        for(int i = 0;i < files.length;i++){
            if(files[i].exists() && files[i].isFile()){
                filesPath.add(files[i].getAbsolutePath());
            }
        }
    }

    /**
     *
     * 获取文件路径
     *
     * @author  csy
     * @param  files -- 多个文件
     * @return 多个文件名字组成的字符串
     */
    public static String getFilesName(File[] files){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i < files.length;i++){
            if(files[i].exists() && files[i].isFile()){
                stringBuilder.append(files[i].getName() + ";");
            }
        }
        return stringBuilder.toString();
    }


    /**
     * 获取文件的byte[]数组
     *
     * @author  xsy
     * @param  filePath -- 文件路径
     * @return 文件byte数组
     */
    public static byte[] getContent(String filePath) throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length
                && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file "
                    + file.getName());
        }
        fi.close();
        return buffer;
    }
}
