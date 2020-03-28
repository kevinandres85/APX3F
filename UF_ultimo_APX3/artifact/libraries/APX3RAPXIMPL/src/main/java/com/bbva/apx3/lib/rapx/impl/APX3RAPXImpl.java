package com.bbva.apx3.lib.rapx.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.bbva.apx3.dto.apx3.DataOut;
import com.bbva.apx3.lib.rapx.APX3RAPX;
import com.google.gson.Gson;

public abstract class APX3RAPXImpl extends APX3RAPXAbstract {

	private static final Logger LOGGER = LoggerFactory.getLogger(APX3RAPX.class);

	@Override
	public DataOut execute(String origin, String dest, String PAN, int nfees) {
		DataOut dato = new DataOut();
		RestTemplate restTemplate = new RestTemplate();
		String json = restTemplate.getForObject("https://maps.googleapis.com/maps/api/directions/json?origin=" + origin
				+ "&destination=" + dest + "&key=AIzaSyByPeqwGB3sb1BXGhEkzfzmnOodqugTM6Q", String.class);

		dato.setPAN(PAN);
		dato.setNrocoutas(PAN.toString());

		try {
			jsonValues(dato, json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		execute2();
		return dato;
		
	}
	/*
	 * method to return values origin and destiny from DataIn
	 */

	private static void jsonValues(DataOut dato, String json) throws JsonProcessingException, IOException {
		JsonNode arrNode = new ObjectMapper().readTree(json).get("routes");

		JsonNode arr2 = null;
		String s3 = null, s4 = null;
		String distance="";
		int time=0;
		for (JsonNode objNode : arrNode) {

			String json2 = objNode.toString();
			arr2 = new ObjectMapper().readTree(json2).get("legs");
			for (JsonNode objNod : arr2) {

				s3 = new ObjectMapper().readTree(objNod.toString()).get("distance").toString();

				String[] ar = s3.split(",");
				String[] a2 = Arrays.toString(ar).split(":");
				distance=a2[2].replaceAll("}", "").replaceAll("]", "");
				s4 = new ObjectMapper().readTree(objNod.toString()).get("duration").toString();

				String[] ar2 = s4.split(",");
				String[] a3 = Arrays.toString(ar2).split(":");
			    time=Integer.parseInt(a3[2].replaceAll("}", "").replaceAll("]", ""));
			}
		}
		
		double costo= calcularcosto(Long.parseLong(distance),time);
		infoTitular(dato,costo);
	}

	/*
	 * Method to calculate cost with distance like parameter
	 */

	private static double calcularcosto(long distance,int time) {
		double res = 0;

		res=((distance/1000)/(time/3600))*5000;

		return res;

	}

	public static void infoTitular(DataOut dato,double costo) {
		String cadena;
		
	     double[] pagos = new double[Integer.parseInt(dato.getNrocoutas())];
		FileReader freader = null;
		FileWriter fwriter = null;
		try {
			freader = new FileReader("C:/Users/kluna/Desktop/ListadoTarjetas2.txt");
			fwriter = new FileWriter("C:/Users/kluna/Desktop/infoTitular.json");
			BufferedReader b = new BufferedReader(freader);
			BufferedWriter bfwriter = new BufferedWriter(fwriter);
			try {
				while ((cadena = b.readLine()) != null) {
					double tasa=0.0;
					String datajson = "";
					if (!cadena.contains("BIN") && cadena.contains(dato.getPAN())) {
						String[] reg = cadena.split(";");
						String[] data = new String[9];
						String PANs2 = reg[0].concat(reg[1]).replace(" ", "");
						
						if (PANs2 == dato.getPAN()) {

							int x = 0;
							for (int i = 0; i < reg.length; i++) {
								
								data[x]=dato.getPAN();
								dato.setEntidad(reg[2]);
								data[x]=dato.getEntidad();
								dato.setCentro(reg[3]);
								data[x]=dato.getCentro();
								dato.setProducto(reg[4]);
								data[x]=dato.getProducto();
								dato.setId_contrato(reg[5]);
								data[x]=dato.getId_contrato();
								dato.setTitular(reg[6]);
								data[x]=dato.getTitular();
								dato.setDireccion(reg[7].replace("□", "á"));
								data[x]=dato.getDireccion();
								tasa =Double.parseDouble(reg[8].replace(",", "."));
								data[x]=dato.getNrocoutas();
								x++;
							}
						}

						
					
						
						double couta= costo/Double.parseDouble(dato.getNrocoutas());
						for(int i=0;i<pagos.length;i++){
							
							pagos[i] = (couta*(tasa/100));
							
						}
						
						Gson gson = new Gson();
						String g = gson.toJson(pagos);
						data[8] = g;
						datajson = gson.toJson(data);

						System.out.println(datajson);

					}

					bfwriter.write(datajson);
				}
				bfwriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				b.close();
				fwriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			if (fwriter != null) {
				try {// cierra el flujo principal
					fwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public abstract String execute2();
}
