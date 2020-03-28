package com.bbva.apx3;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.bbva.elara.domain.transaction.Context;
import com.bbva.elara.domain.transaction.ParameterTable;
import com.bbva.elara.domain.transaction.TransactionParameter;
import com.bbva.elara.domain.transaction.request.TransactionRequest;
import com.bbva.elara.domain.transaction.request.body.CommonRequestBody;
import com.bbva.elara.test.osgi.DummyBundleContext;


/**
 * Test for transaction APX3T00001COTransaction
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/elara-test.xml",
		"classpath:/META-INF/spring/APX3T00001COTest.xml" })
public class APX3T00001COTransactionTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(APX3T00001COTransactionTest.class);
	
	@Autowired
	private APX3T00001COTransaction transaction;
	
	@Resource(name="dummyBundleContext")
	private DummyBundleContext bundleContext;
	
	private TransactionRequest transactionRequest;
	
	
	@Before
	public void initializeClass() throws Exception {		
		this.transaction.start(bundleContext);
		
		// Initializing transaction to test
		this.transaction.setContext(new Context());
		this.transaction.getContext().setLanguageCode("ES");
		
		transactionRequest = Mockito.mock(TransactionRequest.class);
		CommonRequestBody commonRequestBody = new CommonRequestBody();
		commonRequestBody.setTransactionParameters(new ArrayList<TransactionParameter>());
		transactionRequest.setBody(commonRequestBody);
		Mockito.when(transactionRequest.getRestfulMethod()).thenReturn(null);
		this.transaction.getContext().setTransactionRequest(transactionRequest);
				
	}
	
	@Test
	public void testNotNull(){
		Assert.assertNotNull(this.transaction);
		//The method getRestfulMethod() will return null by default. To test other methods add a line like this
		//Mockito.when(transactionRequest.getRestfulMethod()).thenReturn("getBancos");
		this.transaction.execute();
	}	
	

	private void addTable(final String key, final List<Map<String, Object>> table) {	
		ParameterTable parameterTable = new ParameterTable();		
		parameterTable.setParameterTableList(table);
		this.addParameter(key, parameterTable);		
	}
	
	private void addParameter(final String parameter, final Object value) {
		final TransactionParameter tParameter = new TransactionParameter(
				parameter, value);
		transaction.getContext().getParameterList().put(parameter, tParameter);
	}

	private Object getParameter(final String parameter) {
		final TransactionParameter param = transaction.getContext()
				.getParameterList().get(parameter);
		return param.getValue();
	}
	
	private Object getTableParameter(final int rowNumber, final String table, final String key)
	{
		ParameterTable param = (ParameterTable)getParameter(table);
		Map<String,Object> row = param.get(rowNumber);
		return row.get(key);
	}
}
