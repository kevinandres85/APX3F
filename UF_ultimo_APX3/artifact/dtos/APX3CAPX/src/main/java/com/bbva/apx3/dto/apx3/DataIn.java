package com.bbva.apx3.dto.apx3;

import com.bbva.apx.dto.AbstractDTO;

public class DataIn extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2754916460913401662L;
	private String origin;
	private String destiny;
	private String PAN;
	private int nfees;

	/**
	 * Constructor class
	 */

	public DataIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * origin to get
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * destiny to get
	 */

	public String getDestiny() {
		return destiny;
	}

	/**
	 * destiny to set
	 */
	public void setDestiny(String destiny) {
		this.destiny = destiny;
	}

	/**
	 * PAN to get
	 */

	public String getPAN() {
		return PAN;
	}

	/**
	 * PAN to set
	 */
	public void setPAN(String pAN) {
		PAN = pAN;
	}

	/**
	 * number fee to get
	 */

	public int getNfees() {
		return nfees;
	}
	/**
	 * number fee to set
	 */
	public void setNfees(int nfees) {
		this.nfees = nfees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PAN == null) ? 0 : PAN.hashCode());
		result = prime * result + ((destiny == null) ? 0 : destiny.hashCode());
		result = prime * result + nfees;
		result = prime * result + ((origin == null) ? 0 : origin.hashCode());
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
		DataIn other = (DataIn) obj;
		if (PAN == null) {
			if (other.PAN != null)
				return false;
		} else if (!PAN.equals(other.PAN))
			return false;
		if (destiny == null) {
			if (other.destiny != null)
				return false;
		} else if (!destiny.equals(other.destiny))
			return false;
		if (nfees != other.nfees)
			return false;
		if (origin == null) {
			if (other.origin != null)
				return false;
		} else if (!origin.equals(other.origin))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DataIn [origin=" + origin + ", destiny=" + destiny + ", PAN=" + PAN + ", nfees=" + nfees + "]";
	}

}
