package com.ashish.webservices.client;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;

import com.ashish.webservices.User;
import com.ashish.webservices.Users;

public class UserClient {

	public static void main(String[] args) {
		try {
			String uri = "http://localhost:7001/RestfullSample/rest/UserService/users";
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");
/*
//			List<T> out = new ArrayList<T>();
	        XMLInputFactory xif = XMLInputFactory.newFactory();
			InputStream xml = connection.getInputStream();
	        StreamSource source = new StreamSource(xml);
	        XMLStreamReader xsr = xif.createXMLStreamReader(source);

//	        JAXBContext jc = JAXBContext.newInstance(User.class);


	        while(xsr.hasNext()) {
//	            if(xsr.isStartElement() && "user".equals(xsr.getLocalName())) {
//	                break;
//	            }
	            printEvent(xsr);
	            xsr.next();
//	            User newUser = (User) jc.createUnmarshaller().unmarshal(xsr);
//				System.out.println("User = " + newUser.getId() + " , " + newUser.getName() + " , " + newUser.getProfession());
//	            System.out.println(xsr.getLocalName());
	        }
*/	        			
//			List<User> users = ((UserList) jc.createUnmarshaller().unmarshal(xml)).getUsers();
//			for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
//				User user = iterator.next();
//				System.out.println("User = " + user.getId() + " , " + user.getName() + " , " + user.getProfession());
//			}
//	        System.out.println("Users Size = " + users.size());
	        
	        
	        JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
	        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	        InputStream xml = connection.getInputStream();
	        Users emps = (Users) jaxbUnmarshaller.unmarshal(xml); //new File("c:/temp/employees.xml") );
	         
	        for(User user : emps.getUsers())
	        {
//	            System.out.println(emp.getId());
//	            System.out.println(emp.getName());
//	            System.out.println(emp.getProfession());
	        	System.out.println("User = " + user.getId() + " , " + user.getName() + " , " + user.getProfession());
	        }
	        
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	  private static void printEvent(XMLStreamReader xmlr) {
		     System.out.print("EVENT:["+xmlr.getLocation().getLineNumber()+"]["+
		                     xmlr.getLocation().getColumnNumber()+"] ");
		     System.out.print(" [");
		     switch (xmlr.getEventType()) {
		     case XMLStreamConstants.START_ELEMENT:
		      System.out.print("<");
		      printName(xmlr);
		      printNamespaces(xmlr);
		      printAttributes(xmlr);
		      System.out.print(">");
		      break;
		     case XMLStreamConstants.END_ELEMENT:
		      System.out.print("</");
		      printName(xmlr);
		      System.out.print(">");
		      break;
		     case XMLStreamConstants.SPACE:
		     case XMLStreamConstants.CHARACTERS:
		      int start = xmlr.getTextStart();
		      int length = xmlr.getTextLength();
		      System.out.print(new String(xmlr.getTextCharacters(),
		                                  start,
		                                  length));
		      break;
		     case XMLStreamConstants.PROCESSING_INSTRUCTION:
		      System.out.print("<?");
		      if (xmlr.hasText())
		        System.out.print(xmlr.getText());
		      System.out.print("?>");
		      break;
		     case XMLStreamConstants.CDATA:
		      System.out.print("<![CDATA[");
		      start = xmlr.getTextStart();
		      length = xmlr.getTextLength();
		      System.out.print(new String(xmlr.getTextCharacters(),
		                                  start,
		                                  length));
		      System.out.print("]]>");
		      break;
		     case XMLStreamConstants.COMMENT:
		      System.out.print("<!--");
		      if (xmlr.hasText())
		        System.out.print(xmlr.getText());
		      System.out.print("-->");
		      break;
		     case XMLStreamConstants.ENTITY_REFERENCE:
		      System.out.print(xmlr.getLocalName()+"=");
		      if (xmlr.hasText())
		        System.out.print("["+xmlr.getText()+"]");
		      break;
		     case XMLStreamConstants.START_DOCUMENT:
		      System.out.print("<?xml");
		      System.out.print(" version='"+xmlr.getVersion()+"'");
		      System.out.print(" encoding='"+xmlr.getCharacterEncodingScheme()+"'");
		      if (xmlr.isStandalone())
		        System.out.print(" standalone='yes'");
		      else
		        System.out.print(" standalone='no'");
		      System.out.print("?>");
		      break;
		     }
		    System.out.println("]");
		  }
	  
	  private static void printName(XMLStreamReader xmlr){
		    if(xmlr.hasName()){
		      String prefix = xmlr.getPrefix();
		      String uri = xmlr.getNamespaceURI();
		      String localName = xmlr.getLocalName();
		      printName(prefix,uri,localName);
		    }
		  }
		   private static void printName(String prefix,
		                                String uri,
		                                String localName) {
		    if (uri != null && !("".equals(uri)) ) System.out.print("['"+uri+"']:");
		    if (prefix != null) System.out.print(prefix+":");
		    if (localName != null) System.out.print(localName);
		  }
		   private static void printAttributes(XMLStreamReader xmlr){
		    for (int i=0; i < xmlr.getAttributeCount(); i++) {
		      printAttribute(xmlr,i);
		    }
		  }
		   private static void printAttribute(XMLStreamReader xmlr, int index) {
		    String prefix = xmlr.getAttributePrefix(index);
		    String namespace = xmlr.getAttributeNamespace(index);
		    String localName = xmlr.getAttributeLocalName(index);
		    String value = xmlr.getAttributeValue(index);
		    System.out.print(" ");
		    printName(prefix,namespace,localName);
		    System.out.print("='"+value+"'");
		  }
		   private static void printNamespaces(XMLStreamReader xmlr){
		    for (int i=0; i < xmlr.getNamespaceCount(); i++) {
		      printNamespace(xmlr,i);
		    }
		  }
		   private static void printNamespace(XMLStreamReader xmlr, int index) {
		    String prefix = xmlr.getNamespacePrefix(index);
		    String uri = xmlr.getNamespaceURI(index);
		    System.out.print(" ");
		    if (prefix == null)
		      System.out.print("xmlns='"+uri+"'");
		    else
		      System.out.print("xmlns:"+prefix+"='"+uri+"'");
		  }
}
