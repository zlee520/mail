import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;

public class Test {

	public static void main(String[] args) throws MessagingException, IOException {
		// TODO Auto-generated method stub
		
		Properties props = new Properties();
		props.put("mail.pop3.host", "pop.yeah.net");
		
		Session session = Session.getInstance(props, null);
	    //session.setDebug(true);
	    
	    Store store = session.getStore("pop3");
	    store.connect("zl_hello", "mmzhengd1");
	    
	    Folder folder = store.getFolder("INBOX");
	    
	    folder.open(Folder.READ_ONLY);  

	    
	    Message messages[] = folder.getMessages();  
        System.out.println("Messages's length: " + messages.length);  
	    
        String dateformat = "yyyy-MM-dd HH:mm"; 
        SimpleDateFormat format = new SimpleDateFormat(dateformat);
	    
	    for(Message message : messages){
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    		if(message.getMessageNumber() == 1){
	    			continue;
	    		}
	    	
	    		
	    		
	    		//System.out.println(message.getMessageNumber());
	    		//System.out.println(format.format(message.getSentDate()));
	    		System.out.println(message.getSubject());
 		
	    		//MimeMultipart part = (MimeMultipart)message.getContent();
	    		//System.err.println(part.getBodyPart(0).getContent());
	    		//System.out.println(part.getBodyPart(1).getContentType());
	    		
	    		dumpPart(message);
	    		break;
	    	
	    }
	    
	    
	}
	
	private static void dumpPart(Part p) throws MessagingException, IOException{
    	
    	String ct = p.getContentType();
    	System.err.println(ct);
    	
    	if (p.isMimeType("text/plain")) {
    		System.err.println("This is plain text");
    		System.out.println((String)p.getContent());
    		
    	} else if (p.isMimeType("multipart/*")) {
    		System.err.println("This is a Multipart");
    		System.err.println("---------------------------");
    	    Multipart mp = (Multipart)p.getContent();
    	    
    	    int count = mp.getCount();
    	    for (int i = 0; i < count; i++)
    		dumpPart(mp.getBodyPart(i));
    	}else{
    		
    		System.err.println(((MimeBodyPart)p).getContentID());
    		
    		Object o = p.getContent();
    		if (o instanceof String) {
    			System.err.println("This is a string");
    			System.err.println("---------------------------");
    		    System.out.println((String)o);
    		} else if (o instanceof InputStream) {
    			System.err.println("This is just an input stream");
    			System.err.println("---------------------------");
    		    InputStream is = (InputStream)o;
    		    int c;
    		    
    		    File file = new File("d:/mv.jpg");
    		    FileOutputStream fOut = new FileOutputStream(file);
    		    
    		    while ((c = is.read()) != -1)
    		    	fOut.write(c);
    		    is.close();
    		    fOut.close();
    		} else {
    			System.err.println("This is an unknown type");
    			System.err.println("---------------------------");
    			System.err.println(o.toString());
    		}
    		
    		
    	}
    	
    	
    	
    }
	

}
