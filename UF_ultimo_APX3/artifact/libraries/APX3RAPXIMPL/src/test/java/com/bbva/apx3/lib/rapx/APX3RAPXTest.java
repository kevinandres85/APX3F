package com.bbva.apx3.lib.rapx;

import javax.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bbva.apx3.dto.apx3.DataOut;
import com.bbva.apx3.lib.rapx.impl.APX3RAPXImpl;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/APX3RAPX-app.xml",
		"classpath:/META-INF/spring/APX3RAPX-app-test.xml",
		"classpath:/META-INF/spring/APX3RAPX-arc.xml",
		"classpath:/META-INF/spring/APX3RAPX-arc-test.xml" })
public class APX3RAPXTest {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(APX3RAPX.class);
	
	@Resource(name = "apx3RAPX")
	private APX3RAPX apx3RAPX;
	
	@Before
	public void setUp() throws Exception {		
		getObjectIntrospection();
	}
	
	private Object getObjectIntrospection() throws Exception{
		Object result = this.apx3RAPX;
		if(this.apx3RAPX instanceof Advised){
			Advised advised = (Advised) this.apx3RAPX;
			result = advised.getTargetSource().getTarget();
		}
		return result;
	}
	
	@Test
	public void executeTest(){
		LOGGER.info("Executing the test...");
		
		
		DataOut data=apx3RAPX.execute("BOGOTA", "MEDELLIN", "5020691183562315", 3);
		
		Assert.assertEquals(data.getNrocoutas(), "3");
	
	}
	
}
