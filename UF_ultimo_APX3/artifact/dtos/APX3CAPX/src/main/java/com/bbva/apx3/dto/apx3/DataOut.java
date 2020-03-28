package com.bbva.apx3.dto.apx3;

import com.bbva.apx.dto.AbstractDTO;

public class DataOut extends AbstractDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8277525709767761996L;
    private String PAN;
    private String entidad;
    private String centro;
    private String producto;
    private String id_contrato;
    private String titular;
    private String direccion;
    private String nrocoutas;
    private String coutas;
	public String getPAN() {
		return PAN;
	}
	public void setPAN(String pAN) {
		PAN = pAN;
	}
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public String getCentro() {
		return centro;
	}
	public void setCentro(String centro) {
		this.centro = centro;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getId_contrato() {
		return id_contrato;
	}
	public void setId_contrato(String id_contrato) {
		this.id_contrato = id_contrato;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNrocoutas() {
		return nrocoutas;
	}
	public void setNrocoutas(String nrocoutas) {
		this.nrocoutas = nrocoutas;
	}
	public String getCoutas() {
		return coutas;
	}
	public void setCoutas(String coutas) {
		this.coutas = coutas;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PAN == null) ? 0 : PAN.hashCode());
		result = prime * result + ((centro == null) ? 0 : centro.hashCode());
		result = prime * result + ((coutas == null) ? 0 : coutas.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((entidad == null) ? 0 : entidad.hashCode());
		result = prime * result + ((id_contrato == null) ? 0 : id_contrato.hashCode());
		result = prime * result + ((nrocoutas == null) ? 0 : nrocoutas.hashCode());
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
		result = prime * result + ((titular == null) ? 0 : titular.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataOut other = (DataOut) obj;
		if (PAN == null) {
			if (other.PAN != null)
				return false;
		} else if (!PAN.equals(other.PAN))
			return false;
		if (centro == null) {
			if (other.centro != null)
				return false;
		} else if (!centro.equals(other.centro))
			return false;
		if (coutas == null) {
			if (other.coutas != null)
				return false;
		} else if (!coutas.equals(other.coutas))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (entidad == null) {
			if (other.entidad != null)
				return false;
		} else if (!entidad.equals(other.entidad))
			return false;
		if (id_contrato == null) {
			if (other.id_contrato != null)
				return false;
		} else if (!id_contrato.equals(other.id_contrato))
			return false;
		if (nrocoutas == null) {
			if (other.nrocoutas != null)
				return false;
		} else if (!nrocoutas.equals(other.nrocoutas))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		if (titular == null) {
			if (other.titular != null)
				return false;
		} else if (!titular.equals(other.titular))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DataOut [PAN=" + PAN + ", entidad=" + entidad + ", centro=" + centro + ", producto=" + producto
				+ ", id_contrato=" + id_contrato + ", titular=" + titular + ", direccion=" + direccion + ", nrocoutas="
				+ nrocoutas + ", coutas=" + coutas + "]";
	}
	public DataOut() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
    
    
    
    
	
	
}
