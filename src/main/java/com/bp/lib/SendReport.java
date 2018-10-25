package com.bp.lib;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class SendReport 
{
	public void sendReportToMail() throws EmailException 
	{
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(".\\zip\\Report.zip");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Execution Report");
		attachment.setName("Report.zip");
		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("extentreport@gmail.com", "Zxcv@1234"));
		email.setSSLOnConnect(true);
		// email.addTo("sobehura@in.ibm.com", "Sourav Kumar Behura");
		// email.addTo("sourav.behura@bp.com","sourav behura");
		email.addTo("sourav187@gmail.com", "Sourav Kumar Behura");
		email.addTo("prosenjitmondal90@gmail.com", "Sourav Kumar Behura");
		email.addTo("shankar.duddu@in.ibm.com","Shankar Duddu");
		email.addTo("anitabrao95@gmail.com","Anitha");
		//email.addTo("Suzhen.Moey@bp.com","Su Zhen");
		email.setFrom("extentreport@gmail.com", "Saviynt QA Report");	
		email.setSubject("Saviynt Execution Report");
		email.setMsg("This is an auto generated email from QA team regarding the execution report");
		// add the attachment
		email.attach(attachment);
		// send the email
		email.send();
	}
}
