package com.du.order.dist.client;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.du.order.dist.interfaces.ISalesForceClient;
import com.du.order.dist.model.entity.Order;
import com.du.order.dist.model.entity.OrderDetail;
import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.ASiparis__c;
import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.soap.enterprise.sobject.Sipari_Kalem__c;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

@Component
public class SalesForceClient implements ISalesForceClient {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private Environment env;

	static EnterpriseConnection connection;

	@Override
	public String returnAccountId(String userName, String password) {
		String accountId = null;
		try {

			ConnectorConfig config = new ConnectorConfig();
			config.setUsername(env.getRequiredProperty("salesforce.api.name"));
			config.setPassword(env.getRequiredProperty("salesforce.api.password"));
			config.setTraceMessage(true);
			connection = Connector.newConnection(config);
			logger.info("Auth EndPoint: " + config.getAuthEndpoint());
			logger.info("Service EndPoint: " + config.getServiceEndpoint());
			logger.info("Username: " + config.getUsername());
			logger.info("SessionId: " + config.getSessionId());

			accountId = queryContact(userName, password);
			
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		if(StringUtils.isNotBlank(accountId)){
			return accountId;
		}
		return null;
	}

	@Override
	public String returnAccountId(String contactId) {
		String accountId = null;
		try {

			ConnectorConfig config = new ConnectorConfig();
			config.setUsername(env.getRequiredProperty("salesforce.api.name"));
			config.setPassword(env.getRequiredProperty("salesforce.api.password"));
			config.setTraceMessage(true);
			connection = Connector.newConnection(config);
			logger.info("Auth EndPoint: " + config.getAuthEndpoint());
			logger.info("Service EndPoint: " + config.getServiceEndpoint());
			logger.info("Username: " + config.getUsername());
			logger.info("SessionId: " + config.getSessionId());

			accountId = queryContact(contactId);
			
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		if(StringUtils.isNotBlank(accountId)){
			return accountId;
		}
		return null;
	}
	private String queryContact(String contactId) {
		QueryResult qResult = null;
		   Contact retCon = null;
		   try {
			   //AccountId, FirstName, LastName
			   
//			   String soqlQuery = "SELECT AccountId, FirstName, LastName FROM Contact";
		      String soqlQuery = "SELECT Id, Name FROM Account";
		      String whereClause = String.format(" WHERE (Id = '%s')", contactId);
		      qResult = connection.query(soqlQuery + whereClause);
		      boolean done = false;
		      if (qResult.getSize() > 0) {
		         System.out.println("Logged-in user can see a total of "
		            + qResult.getSize() + " contact records.");
		         while (!done) {
		            SObject[] records = qResult.getRecords();
		            for (int i = 0; i < records.length; ++i) {
		               Contact con = (Contact) records[i];
		               retCon = con;
		               String name = con.getName();
//		               String lName = con.getLastName();
		               if (name == null) {
		                  System.out.println("Contact " + (i + 1) + ": " + name);
		               }
//		               else {
//		                  System.out.println("Contact " + (i + 1) + ": " + fName
//		                        + " " + lName);
//		               }
		            }
		            if (qResult.isDone()) {
		               done = true;
		            } else {
		               qResult = connection.queryMore(qResult.getQueryLocator());
		            }
		         }
		      } else {
		         System.out.println("No records found.");
		      }
		      System.out.println("\nQuery succesfully executed.");
		   } catch (ConnectionException ce) {
		      ce.printStackTrace();
		   }
		   
		   if(retCon != null){
			   return retCon.getId();
		   } else { return null; }
	}
//	private String queryContact(String contactId) {
//		QueryResult qResult = null;
//		   Contact retCon = null;
//		   try {
//		      String soqlQuery = "SELECT AccountId, FirstName, LastName FROM Contact";
//		      String whereClause = String.format(" WHERE (Id = '%s')", contactId);
//		      qResult = connection.query(soqlQuery + whereClause);
//		      boolean done = false;
//		      if (qResult.getSize() > 0) {
//		         System.out.println("Logged-in user can see a total of "
//		            + qResult.getSize() + " contact records.");
//		         while (!done) {
//		            SObject[] records = qResult.getRecords();
//		            for (int i = 0; i < records.length; ++i) {
//		               Contact con = (Contact) records[i];
//		               retCon = con;
//		               String fName = con.getFirstName();
//		               String lName = con.getLastName();
//		               if (fName == null) {
//		                  System.out.println("Contact " + (i + 1) + ": " + lName);
//		               } else {
//		                  System.out.println("Contact " + (i + 1) + ": " + fName
//		                        + " " + lName);
//		               }
//		            }
//		            if (qResult.isDone()) {
//		               done = true;
//		            } else {
//		               qResult = connection.queryMore(qResult.getQueryLocator());
//		            }
//		         }
//		      } else {
//		         System.out.println("No records found.");
//		      }
//		      System.out.println("\nQuery succesfully executed.");
//		   } catch (ConnectionException ce) {
//		      ce.printStackTrace();
//		   }
//		   
//		   if(retCon != null){
//			   return retCon.getAccountId();
//		   } else { return null; }
//	}

	public String queryContact(String username, String password) {
		   QueryResult qResult = null;
		   Contact retCon = null;
		   try {
		      String soqlQuery = "SELECT AccountId, FirstName, LastName FROM Contact";
		      String whereClause = String.format(" WHERE (Contact_Username__c = '%s')AND(Contact_Password__c = '%s')", username, password);
		      qResult = connection.query(soqlQuery + whereClause);
		      boolean done = false;
		      if (qResult.getSize() > 0) {
		         System.out.println("Logged-in user can see a total of "
		            + qResult.getSize() + " contact records.");
		         while (!done) {
		            SObject[] records = qResult.getRecords();
		            for (int i = 0; i < records.length; ++i) {
		               Contact con = (Contact) records[i];
		               retCon = con;
		               String fName = con.getFirstName();
		               String lName = con.getLastName();
		               if (fName == null) {
		                  System.out.println("Contact " + (i + 1) + ": " + lName);
		               } else {
		                  System.out.println("Contact " + (i + 1) + ": " + fName
		                        + " " + lName);
		               }
		            }
		            if (qResult.isDone()) {
		               done = true;
		            } else {
		               qResult = connection.queryMore(qResult.getQueryLocator());
		            }
		         }
		      } else {
		         System.out.println("No records found.");
		      }
		      System.out.println("\nQuery succesfully executed.");
		   } catch (ConnectionException ce) {
		      ce.printStackTrace();
		   }
		   
		   if(retCon != null){
			   return retCon.getAccountId();
		   } else { return null; }
		}
	@Override
	public Order updateStatus(Order order) {
		try {

			ConnectorConfig config = new ConnectorConfig();
			config.setUsername(env.getRequiredProperty("salesforce.api.name"));
			config.setPassword(env.getRequiredProperty("salesforce.api.password"));
			config.setTraceMessage(true);

			connection = Connector.newConnection(config);

			// display some current settings
			logger.info("Auth EndPoint: " + config.getAuthEndpoint());
			logger.info("Service EndPoint: " + config.getServiceEndpoint());
			logger.info("Username: " + config.getUsername());
			logger.info("SessionId: " + config.getSessionId());

			ASiparis__c[] remoteOrderList = new ASiparis__c[1];

			ASiparis__c remoteOrder = new ASiparis__c();
			remoteOrder.setId(order.getRemoteId().trim());
			remoteOrder.setSiparis_Durum__c(order.getSiparisDurum());
			if(order.getSiparisTeslimTarihi() != null){
				Calendar cal3 = Calendar.getInstance();
				cal3.setTime(order.getSiparisTeslimTarihi());
				remoteOrder.setTeslim_Tarihi__c(cal3);
			}
			/*	
			}
			
			remoteOrder.setAdres__c(order.getAdres());
			remoteOrder.setAdres_Aciklama__c(order.getAdresAciklama());

			remoteOrder.setAra_Toplam__c(order.getAraToplam().doubleValue());

			Calendar cal = Calendar.getInstance();
			cal.setTime(order.getCreated());
			remoteOrder.setCreatedDate(cal);

			remoteOrder.setCurrencyIsoCode("TRY");
			remoteOrder.setTedarik_Eden_Firma__c(order.getTedarikEdenFirma());
			remoteOrder.setTedarik_Eden_Kisi__c(order.getTedarikEdenKisi());

			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(order.getSiparisOlusmaTarihi());
			remoteOrder.setSiparis_Tarihi__c(cal2);

			Calendar cal3 = Calendar.getInstance();
			cal3.setTime(order.getSiparisTalepTeslimTarihi());
			remoteOrder.setTalep_Tarihi__c(cal3);

			remoteOrder.setKDV__c(order.getIndirim().doubleValue());
			remoteOrder.setIndirim__c(order.getIndirim().doubleValue());
			remoteOrder.setGenel_Toplam__c(order.getGenelToplam().doubleValue());*/
//			remoteOrder.setOwnerId(env.getRequiredProperty("salesforce.ownerId")); ****
			/*
			QueryResult siparisKalemWrapper = new QueryResult();
			Sipari_Kalem__c[] siparisKalemList = new Sipari_Kalem__c[order.getOrderDetailList().size()];
			for (OrderDetail od : order.getOrderDetailList()) {
				Sipari_Kalem__c sk = new Sipari_Kalem__c();

				sk.setAdet__c(od.getAdet().doubleValue());
				sk.setAra_Toplam__c(od.getAraToplam().doubleValue());
				sk.setBirim_Fiyat__c(od.getBirimFiyati().doubleValue());
				sk.setUrun__c(od.getUrunAdi());

				Calendar cal4 = Calendar.getInstance();
				cal4.setTime(od.getCreated());
				sk.setCreatedDate(cal4);

				sk.setIndirim__c(od.getIndirim().doubleValue());
				sk.setKalem_Fiyat__c(od.getKalemGenelToplam().doubleValue());
				sk.setSiparis__c(order.getRemoteId());
				int i = 0;
				siparisKalemList[i] = sk;
				i++;
			}
			siparisKalemWrapper.setRecords(siparisKalemList);
			remoteOrder.setSiparisKalem__r(siparisKalemWrapper);
*/
			remoteOrderList[0] = remoteOrder;

			SaveResult[] svArr = connection.update(remoteOrderList);

			for (int i = 0; i < svArr.length; i++) {
				if (svArr[i].isSuccess()) {
					logger.info(i + ". Successfully created record - Id: " + svArr[i].getId());

				} else {
					com.sforce.soap.enterprise.Error[] errors = svArr[i].getErrors();
					for (int j = 0; j < errors.length; j++) {
						logger.info("ERROR creating record: " + errors[j].getMessage());
					}
				}
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return order;
	}
}
