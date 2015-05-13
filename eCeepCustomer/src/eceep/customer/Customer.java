package eceep.customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import eceep.customer.domain.CustomerActivity;
import eceep.customer.domain.CustomerContact;
import eceep.customer.domain.CustomerDetail;

public interface Customer {
	boolean initial(String jdbcDriver, String url, String userName, String password);
	
	void setPolicy_CustomersByUserID(int userID);
	
	int getPolicy_CustomersByUserID();
	
	// ** For customer generic *****************************************
	List<CustomerDetail> getCustomers(String byCustomerName) throws SQLException;
	
	int newCustomer(int byUserID) throws SQLException;
	
	void removeCustomer(int customerID, int byUserID) throws SQLException;
	
	boolean updateCustomer(CustomerDetail customerDetail, int byUserID) throws SQLException;

	boolean updateContact(CustomerContact contact) throws SQLException;
	
	int newContact(int customerID, String customerName, int byUserID) throws SQLException;
	
	void removeContact(int contactID, int byUserID) throws SQLException;
	
	boolean updateActivity(CustomerActivity activity) throws SQLException;

	int newActivity(int customerID, String customerName, int byUserID) throws SQLException;
	
	void removeActivity(int activityID, int byUserID) throws SQLException;
	
	Map<Integer,String> getActivityTypeList() throws SQLException;
}