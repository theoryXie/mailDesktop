package POP;

import java.util.List;

public class PopMail {
    //发件人
    String from;
    //收件人
    String to;
    //主题
    String subject;
    //正文
    String text;
    //附件名
    List<String> fileNames;

    public PopMail(String from, String to, String subject, String text, List<String> fileNames) {
    	this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.fileNames = fileNames;
    }



	public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

	public List<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}

	@Override
	public String toString() {
		return "PopMail [from=" + from + ", to=" + to + ", subject=" + subject + ", text=" + text + ", fileNames="
				+ fileNames + "]";
	}

    

   
}
