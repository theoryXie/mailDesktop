package Util;

import POP.PopMail;
import SMTP.DeliveredState;
import SMTP.MailBody;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omg.CORBA.Environment;

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
        String fromPattern = "From: (.*)To: \\[";
        String toPattern = "To: (.*)MIME-Version";
        String subjectPattern = "Subject: =\\?utf-8\\?b\\?(.*)\\?=";
        String textPattern = "charset=\"utf-8\"Content-Transfer-Encoding: base64([^-]*)(--a)?";
		String filePattern = "name=(.*)";
		String filePattern_2 = "name=\"(.*)\"(.*)";
		
		//==============================解析除了附件以外的信息=======================================
        String from =  regixPattern(fromPattern, mailString);
        String to = regixPattern(toPattern, mailString);
        final BASE64Decoder decoder = new BASE64Decoder();
        String subject = new String(decoder.decodeBuffer(regixPattern(subjectPattern, mailString)), StandardCharsets.UTF_8);
        String text = new String(decoder.decodeBuffer(regixPattern(textPattern, mailString).replace("--a","")), StandardCharsets.UTF_8);
        
        //==============================解析附件base64码==========================================
        Matcher m = regixFilePattern(filePattern, mailString);
        List<String> fileNames = new ArrayList<String>();
        if(null!=m) {
			String[] splits = (m.group(0)).split("--aContent-Transfer-Encoding:base64Content-type:application/octet-stream;");
			String dirPath = System.getProperty("user.dir")+"\\Files\\";//文件保存的目录
			File dirFile = new File(dirPath);
			if(!dirFile.exists()) {
				dirFile.mkdirs();
			}
			for (String string : splits) {
				String fileName = regixFilePattern(filePattern_2, string).group(1);
				String base64 = regixFilePattern(filePattern_2, string).group(2);
				String path = dirPath+"\\"+fileName;//文件存储路径
				decryptByBase64(base64, path);
				fileNames.add(fileName);
			}
        }
        
        
        return new PopMail(from,to,subject,text,fileNames);
    }

    private static String regixPattern(String pattern, String res) {
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(res);
        if (m.find()){
            return m.group(1);
        }
        else
            return "";
    }

    
    private static Matcher regixFilePattern(String pattern, String res) {
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(res);
        if (m.find()){
            return m;
        }
        else
            return null;
    }
    
    
    /**
     * 把base64转化为文件.
     *
     * @param base64   base64
     * @param filePath 目标文件路径
     * @return boolean isTrue
     */
    public static Boolean decryptByBase64(String base64, String filePath) {
        try {
            Files.write(Paths.get(filePath),
                    Base64.decode(base64.substring(base64.indexOf(",") + 1)), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Boolean.TRUE;
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
    
    //获取文件路径
    public static void getFilesPath(File[] files,List<String> filesPath){
	    for(int i = 0;i < files.length;i++){
	    	if(files[i].exists() && files[i].isFile()) {
	    		filesPath.add(files[i].getAbsolutePath());
	    	}
	    }
    }

}
