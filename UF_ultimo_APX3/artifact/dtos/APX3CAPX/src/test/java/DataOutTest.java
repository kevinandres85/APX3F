import org.junit.Before;
import org.junit.Test;

import com.bbva.apx3.dto.apx3.DataIn;
import com.bbva.apx3.dto.apx3.DataOut;

import junit.framework.Assert;

public class DataOutTest {

	DataOut baseo = new DataOut();

	/**
	 * initialization data
	 */
	@Before
	public void starting() {

		baseo.setPAN("12323456789767543");
		baseo.setEntidad("0013");
		baseo.setCentro("345678");
		baseo.setProducto("423456789");
		baseo.setId_contrato("1234567");
		baseo.setTitular("kevin luna");
		baseo.setDireccion("calle 3");
		baseo.setNrocoutas("4");
		baseo.setCoutas("[34,43]");
		baseo.getPAN();
		baseo.getEntidad();
		baseo.getCentro();
		baseo.getProducto();
		baseo.getId_contrato();
		baseo.getTitular();
		baseo.getDireccion();
		baseo.getNrocoutas();
		baseo.getCoutas();
	}

	/**
	 * Method to test sentence toString()
	 */
	@Test
	public void testString() {
		Assert.assertEquals(baseo.toString(), "DataOut [distance=10, time=30, cost=30.0, rate=2000.0]");
	}

	/**
	 * Method to test sentence toString() fail - ended fail
	 */
	@Test
	public void testToStringFail() {
		Assert.assertFalse(baseo.toString().equals("123"));
	}

	/**
	 * Method to test hashCode() - ended ok
	 */
	@Test
	public void testHashCode() {
		int hashCode = baseo.hashCode();
		Assert.assertEquals(baseo.hashCode(), hashCode);
	}

	/**
	 * Method to test HashCode() in null - ended ok
	 */
	@Test
	public void testHashCodeNull() {
		DataOut basex = new DataOut();
		int hashCode = basex.hashCode();
		Assert.assertEquals(basex.hashCode(), hashCode);
	}

	/**
	 * Method to test equals() - ended ok
	 */

	@Test
	public void testEquals() {
		Assert.assertEquals(baseo.equals(baseo), true);
		Assert.assertEquals(baseo.equals(null), false);
		Assert.assertEquals(baseo.equals(baseo.getClass()), false);

		DataOut baseo1 = new DataOut();

		baseo.setPAN(null);
		baseo1.setPAN("234");
		Assert.assertEquals(baseo.equals(baseo1), false);

		baseo.setPAN("345");
		Assert.assertEquals(baseo.equals(baseo1), false);

		baseo1.setPAN("345");

		baseo.setCentro(null);
		baseo1.setCentro("3456");
		Assert.assertEquals(baseo.equals(baseo1), false);

		baseo.setCentro("2345");
		Assert.assertEquals(baseo.equals(baseo1), false);

		baseo1.setCentro("2345");

		baseo.setCoutas(null);
		baseo1.setCoutas("[2,4]");
		Assert.assertEquals(baseo.equals(baseo1), false);

		baseo.setCoutas("[1,5]");
		Assert.assertEquals(baseo.equals(baseo1), false);

		baseo1.setCoutas("[1,5]");

		baseo.setDireccion(null);
		baseo1.setDireccion("calle 3");
		Assert.assertEquals(baseo.equals(baseo1), false);

		baseo.setDireccion("calle 2");
		Assert.assertEquals(baseo.equals(baseo1), false);

		baseo1.setDireccion(baseo.getDireccion());

		baseo.setEntidad(null);
		baseo1.setEntidad("34567876543");
		Assert.assertEquals(baseo.equals(baseo1), false);

		baseo.setEntidad("67765");
		Assert.assertEquals(baseo.equals(baseo1), false);

		baseo1.setEntidad(baseo.getEntidad());

		baseo.setId_contrato(null);
		baseo1.setId_contrato("34567");
		Assert.assertEquals(baseo.equals(baseo1), false);

		baseo.setId_contrato("9876");
		Assert.assertEquals(baseo.equals(baseo1), false);

		baseo1.setId_contrato(baseo.getId_contrato());

		baseo.setNrocoutas(null);
		baseo1.setNrocoutas("4");
		Assert.assertEquals(baseo.equals(baseo1), false);

		baseo.setNrocoutas("3");
		Assert.assertEquals(baseo.equals(baseo1), false);

		baseo1.setNrocoutas(baseo.getNrocoutas());

		baseo.setProducto(null);
		baseo1.setProducto("87467548");
		Assert.assertEquals(baseo.equals(baseo1), false);

		baseo.setProducto("345676543");
		Assert.assertEquals(baseo.equals(baseo1), false);

		baseo1.setProducto(baseo.getProducto());

		baseo1.setTitular(baseo.getTitular());
		baseo.setTitular(null);
		Assert.assertEquals(baseo.equals(baseo1), false);
        
		baseo.setTitular("juan");
		
		Assert.assertEquals(baseo.equals(baseo1), false);

		baseo1.setTitular(baseo.getTitular());
		
		
		Assert.assertEquals(baseo.equals(baseo1), true);

	}
}