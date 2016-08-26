package util;


import java.lang.reflect.Field;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import base.Log;
import base.Parameter;

public class EmailSender {
	
	public static String SMTP = "smtp";
	public static String SUBJECT = "subject";
	public static String FROM = "from";
	public static String SENDER_NAME = "senderName";
	public static String SET_TO = "setTo";
	public static String COPY_TO = "copyTo";
	public static String USER_NAME = "userName";
	public static String PASSWORD = "password";
	public static String FILE_PATH = "filePath";	
	private MimeMessage mimeMsg; 		//MIME�ʼ�����   
	private Session session; 			//�ʼ��Ự����   
	private Properties props;			//ϵͳ����    
	private Multipart mp; 				//Multipart����,�ʼ�����,����,���������ݾ���ӵ����к�������MimeMessage����   
	private String smtp = null;			//������SMTP��������ַ
	private String subject = null;		//�ʼ�����
	private String from = null;			//�����������ַ
	private String senderName = null;	//��������ʾ����
	private String setTo = null;		//�ռ��������ַ�������ַʱ��Ӣ�Ķ��ŷָ�
	private String copyTo = null;		//�����������ַ�������ַʱ��Ӣ�Ķ��ŷָ�
	private String userName = null;		//�����������û���
	private String password = null;		//��������������
	private String filePath = null;		//������·��

	/** 
	* Constructor 
	* @param parameters �ʼ����͵����ò���
	*/  
	public EmailSender(Parameter parameters){   
		init(parameters);		
	}
	
	/** 
     * ����sendOut��������ʼ�����
     * @throws MessagingException 
     */  
    public boolean send(String text, String encoding){  
    	boolean needCopyto = true;	   
		setSmtpHost();   		//����smtp�����ַ
	    createMimeMessage();  	//�����Ự
        setNeedAuth(true); 		//������֤  
        if(!setSubject()) return false;  	//��������
        if(!setBody(text, encoding))return false;  		//��������
        
        if(!(filePath==null || filePath.equals(""))){		//���ø���
        	if(!addFileAffix()) return false;   
        }
        
        if(!setTo()) return false; 	//�����ռ���
        
        if(copyTo==null || copyTo.equals("")){	//���ó�����
        	needCopyto = false;	        	
        }else{
        	if(!setCopyTo()) return false; 
        }
        
        if(!setFrom()) return false;  	//���÷�����

        if(!sendOut(needCopyto)) return false;  //���÷��ͺ���       
        
        return true;             
    } 
    
    @SuppressWarnings("null")
	protected void init(Parameter parameters){
		//����Ƿ�ȱ�ٷ����ʼ��ı�Ҫ����
		String[] argNames= {"smtp", "from", "setTo", "userName", "password"};
		for(String name:argNames){
			String value = parameters.get(name);
			if(value==null || value.equals("")){
				System.out.println("ȱ�ٷ����ʼ��ı�Ҫ����:"+name);
			}
		}
		//ͨ�������ʼ�������б�
		for(String name :parameters.asMap().keySet()){
			String value = parameters.get(name);
			if(value!=null || !value.equals("")){
				try {
					Field field = this.getClass().getDeclaredField(name);
					field.set(this, value);				
				} catch (NoSuchFieldException e) {
					System.out.println("EmailSender���������²�������"+name);
				} catch (SecurityException e) {
					System.out.println("��ʼ��EmailSender �����쳣!");
				} catch (IllegalArgumentException e) {
					System.out.println("EmailSender ���²���������ֵ���Ͳ���ȷ��"+name);
				} catch (IllegalAccessException e) {
					System.out.println("��ʼ��EmailSender �����쳣!");
				}			   
			}
		}	
	}
	
	/** 
     * �����ʼ����ͷ����� 
     * @param hostName String  
     */  
  	protected void setSmtpHost() {   
  		Log.info("����ϵͳ���ԣ�mail.smtp.host = "+smtp);   
        if(props == null)  
            props = System.getProperties(); //���ϵͳ���Զ���    
        props.put("mail.smtp.host",smtp); //����SMTP����   
    }
  	
  	 /** 
     * ����MIME�ʼ�����   
     * @return 
     */  
  	protected boolean createMimeMessage()   
    {   
        try {   
        	Log.info("׼����ȡ�ʼ��Ự����");   
            session = Session.getDefaultInstance(props,null); //����ʼ��Ự����   
        }   
        catch(Exception e){   
        	System.out.println("��ȡ�ʼ��Ự����ʱ��������");
            return false;   
        }   
      
        Log.info("׼������MIME�ʼ�����");   
        try {   
            mimeMsg = new MimeMessage(session); //����MIME�ʼ�����   
            mp = new MimeMultipart("related");   
          
            return true;   
        } catch(Exception e){   
        	System.out.println("����MIME�ʼ�����ʧ�ܣ�"); 
            return false;   
        }   
    }
   
    /** 
     * ����SMTP�Ƿ���Ҫ��֤ 
     * @param need 
     */  
  	protected void setNeedAuth(boolean need) {   
  		Log.info("����smtp�����֤��mail.smtp.auth = "+need);   
        if(props == null) props = System.getProperties();   
        if(need){   
            props.put("mail.smtp.auth","true");   
        }else{   
            props.put("mail.smtp.auth","false");   
        }   
    }
    
    /** 
     * �����ʼ����� 
     * @param mailSubject 
     * @return 
     */  
  	protected boolean setSubject() {   
  		Log.info("�����ʼ����⣡");   
        try{   
        	if(subject!=null || !subject.equals("")){
        		mimeMsg.setSubject(subject);  
        	}else  mimeMsg.setSubject(" "); 
            return true;   
        }   
        catch(Exception e) {   
        	System.out.println("�����ʼ����ⷢ������ ");  
            return false;   
        }   
    } 
    
    /**  
     * �����ʼ����� 
     * @param mailBody String  
     */   
  	protected boolean setBody(String text, String encoding) {   
        try{   
        	MimeBodyPart textBody = new MimeBodyPart();     
        	textBody.setContent(text, "text/html;charset=gb2312");            
            mp.addBodyPart(textBody);
            return true;   
        } catch(Exception e){   
        	System.out.println("�����ʼ�����ʱ�������� ");
	        return false;   
        }   
    }
    
    /**  
     * ��Ӹ��� 
     * @param filename String  
     */   
  	protected boolean addFileAffix() {   	      
  		Log.info("�����ʼ�������"+filePath);   
        try{   
            BodyPart bp = new MimeBodyPart();   
            FileDataSource fileds = new FileDataSource(filePath);   
            bp.setDataHandler(new DataHandler(fileds));   
            bp.setFileName(fileds.getName());   
            mp.addBodyPart(bp);   	              
            return true;   
        } catch(Exception e){
        	System.out.println("�����ʼ�������"+filePath+"�������� ");
            return false;   
        }   
    } 
    
    /**  
     * ���÷����� 
     * @param from String  
     */   
  	protected boolean setFrom() {   
  		Log.info("���÷����ˣ�");   
        try{   
        	if(senderName ==null || senderName.equals("")){
        		mimeMsg.setFrom(new InternetAddress(from)); 
        	}else mimeMsg.setFrom(new InternetAddress(from, senderName));
            return true;   
        } catch(Exception e) {
        	System.out.println("���÷������쳣 �� ");
            return false;   
        }   
    }  
    
    /**  
     * ���������� 
     * @param to String  
     */   
  	protected boolean setTo(){   
        try{   
            mimeMsg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(setTo));   
            return true;   
        } catch(Exception e) {
        	System.out.println("���������� �쳣 �� ");
            return false;   
        }     
    }  
    
    /**  
     * ���ó����� 
     * @param copyto String   
     */   
  	protected boolean setCopyTo()   
    {    
        try{   
        mimeMsg.setRecipients(Message.RecipientType.CC,(Address[])InternetAddress.parse(copyTo));   
        return true;   
        }   
        catch(Exception e) {
        	System.out.println("�����ó����� �쳣 �� ");
        	return false;
        } 
    } 
    
    /**  
     * �����ʼ� 
     * @throws MessagingException 
     */   
  	protected boolean sendOut(boolean needCopyto)   
    {   
        try{   
            mimeMsg.setContent(mp);   
            mimeMsg.saveChanges();   
            Log.info("���ڷ����ʼ�....");                
            Session mailSession = Session.getInstance(props,null);   
            Transport transport = mailSession.getTransport("smtp");   
            transport.connect((String)props.get("mail.smtp.host"),userName,password);   
            transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.TO));  
            if(needCopyto){
            	transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.CC));  
            }             
            Log.info("�����ʼ��ɹ���");   
            transport.close();   
              
            return true;   
        } catch(Exception e) {   
        	System.out.println("�ʼ�����ʧ�ܣ� ");
            return false;   
        }   
    }
  	
}
