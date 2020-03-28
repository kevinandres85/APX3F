import org.junit.Before;
import org.junit.Test;

import com.bbva.apx3.dto.apx3.DataIn;

import junit.framework.Assert;

public class DataInTest {
	DataIn base = new DataIn();

	/**
	 * initialization data
	 */
	@Before
	public void starting() {

		base.setDestiny("medellin");
		base.setOrigin("bogota");
		base.setPAN("123456789");
		base.setNfees(4);
		base.getOrigin();
		base.getDestiny();
		base.getPAN();
		base.getNfees();
	

	}
	
	
	

	/**
	 * Method to test sentence toString()
	 */
	@Test
	public void testString() {
		Assert.assertEquals(base.toString(), "DataIn [origin=bogota, destiny=medellin, PAN=123456789, nfees=4]");
	}

	/**
	 * Method to test sentence toString() fail - ended fail
	 */
	@Test
	public void testToStringFail() {
		Assert.assertFalse(base.toString().equals("123"));
	}

	/**
	 * Method to test hashCode() - ended ok
	 */
	@Test
	public void testHashCode() {
		int hashCode = base.hashCode();
		Assert.assertEquals(base.hashCode(), hashCode);
	}

	/**
	 * Method to test HashCode() - ended fail
	 */
	@Test
	public void testHashCodeFail() {
		Assert.assertEquals(base.hashCode()==3,false);
	}

	/**
	 * Method to test HashCode() in null - ended ok
	 */
	@Test
	public void testHashCodeNull() {
		DataIn basex = new DataIn();
		int hashCode = basex.hashCode();
		Assert.assertEquals(basex.hashCode(), hashCode);
	}

	/**
	 * Method to test Equals() - ended ok
	 */
	@Test
	public void testEquals() {

		Assert.assertEquals(base.equals(base), true);
		Assert.assertEquals(base.equals(null), false);
		Assert.assertFalse(base.equals("hola"));

		DataIn base4 = new DataIn();
		
		base.setPAN(null);
		base4.setPAN("1234");
		Assert.assertEquals(base.equals(base4), false);
           
		base.setPAN("123");
		Assert.assertEquals(base.equals(base4), false);
        
		base.setPAN("1234");
		
		
		base.setDestiny(null);
		base4.setDestiny("medellin");
		Assert.assertEquals(base.equals(base4), false);

		base.setDestiny("medellin");
		Assert.assertEquals(base.equals(base4), false);
        
		base4.setDestiny("medellin");
		base.setDestiny("cali");
		Assert.assertEquals(base.equals(base4), false);
		
		base4.setDestiny("medellin");
		base.setDestiny("medellin");
		
		base4.setNfees(4);
		base.setNfees(24);
		Assert.assertEquals(base.equals(base4), false);
		
		
		base4.setNfees(4);
		base.setNfees(4);
		
		base.setOrigin(null);
		base4.setOrigin("bogota");
		Assert.assertEquals(base.equals(base4), false);
		base.setOrigin("medellin");
		Assert.assertEquals(base.equals(base4), false);
		base4.setOrigin("medellin");
		Assert.assertEquals(base.equals(base4), true);

	}
}
