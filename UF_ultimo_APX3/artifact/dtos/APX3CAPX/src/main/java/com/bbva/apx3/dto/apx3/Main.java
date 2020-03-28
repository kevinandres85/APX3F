package com.bbva.apx3.dto.apx3;

public class Main {

	public static void main(String[] args) {
		DataIn base = new DataIn();
		base.setDestiny("medellin");
		base.setOrigin("bogota");
		base.setPAN("123456789");
		base.setNfees(4);
		System.out.print(base.toString());
	}

}
