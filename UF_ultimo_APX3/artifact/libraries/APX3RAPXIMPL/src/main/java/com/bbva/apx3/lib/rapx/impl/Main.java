package com.bbva.apx3.lib.rapx.impl;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.client.RestTemplate;

import com.bbva.apx3.dto.apx3.DataOut;
import com.bbva.apx3.lib.rapx.APX3RAPX;
import com.google.gson.Gson;

public class Main {
	private static final Logger LOGGER = LoggerFactory.getLogger(APX3RAPX.class);
	public static void main(String[] args) {

		DataOut data = execute("BOGOTA", "MEDELLIN", "5020691183562315", 3);
		System.out.print(data.toString());
		execute2();

	}

	public static String execute2() {
		File j_entrada = new File("C:/Users/kluna/Desktop/infoTitular.json");
		File f_salida = new File("C:/Users/kluna/Desktop/ArchivoPortafolio.txt");
		File f_salida2 = new File("C:/Users/kluna/Desktop/ContratosFeuliq.txt");
		String datas = "";
		try {

			FileReader jr = new FileReader(j_entrada);
			FileReader fs = new FileReader(f_salida);
			FileReader fs2 = new FileReader(f_salida2);
			
			BufferedReader br = new BufferedReader(jr);
			BufferedReader br2 = new BufferedReader(fs);
			BufferedReader br3 = new BufferedReader(fs2);
			
			FileWriter fw = new FileWriter(f_salida2.getAbsoluteFile(), true);
			BufferedWriter bw=new BufferedWriter(fw);
			PrintWriter pw2 = new PrintWriter(fw);
			
			LinkedList<String> lista = new LinkedList<String>();

			String linea = null;
			String linea2 = null;
			String linea3 = null;

			linea = br.readLine();
			linea = linea.replace("[", "").replace("]", "").replace("''", "");
			linea = linea.substring(1, linea.length() - 1);

			while ((linea2 = br2.readLine()) != null) {
				if (!linea2.contains("ENTIDAD")) {
					
					String[] data = new String[6];

					String[] linean = linea.split(",");
					String[] linean2 = linea2.split(";");
					long a = Long.parseLong(linean[4].substring(1, linean[4].length() - 1));
					if (a == Long.parseLong(linean2[3])) {
						// ENTIDAD;OFICINA2;CONTRATO2;SALDO;FEULIQ;T.E.A
						// ENTIDAD;OFICINA;PRODUCTO;CONTRATO;Portafolio;OFICINA2;CONTRATO2;SALDO;FEULIQ;T.E.A
						int i = 0;

						data[i] = linean2[0];
						i++;
						data[i] = linean2[5];
						i++;
						data[i] = linean2[6];
						i++;
						data[i] = linean2[7];
						i++;
						data[i] = linean2[8];
						i++;
						data[i] = linean2[9];
						 datas = Arrays.toString(data).replace("[", "").replace("]", "").replace(",", ";").replace(" ", "");
						// Stream<String> lineas=br.lines();

						pw2.println(datas);
						

						break;

					}

				}

			}
			
			pw2.close();
			
			fs2.close();
			fw.close();
			
			
			br2.close();
			br.close();
			jr.close();
			fs.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ordenarArchivo();
		
		return datas;
	}

	private static void ordenarArchivo() {
		File f_entrada = new File("C:/Users/kluna/Desktop/ContratosFeuliq.txt");
		File f_salida = new File("C:/Users/kluna/Desktop/ContratosFeuliq2.txt");
		LinkedList<String> lista = new LinkedList<String>();
		
		try {

			FileReader fr = new FileReader(f_entrada);
			

			BufferedReader br = new BufferedReader(fr);
			

			FileWriter fw = new FileWriter(f_salida);
			

			PrintWriter pw = new PrintWriter(fw);
			

			String linea = null;
		
			while ((linea = br.readLine()) != null ) {
				
				String[] linean = linea.split(";");
				lista.add(Arrays.toString(linean));
			}
			Collections.sort(lista);
		
			Iterator iter = lista.iterator();
		
		
			String cadena;
			while (iter.hasNext()) {
				cadena = (String) iter.next();

				pw.println(cadena);

				System.out.println(cadena);

			}
		
		
	
	br.close();
	fr.close();
	
	pw.close();
	
	fw.close();
} catch (FileNotFoundException e) {
	// e.printStackTrace();
	System.err.println("No se ha encontrado el fichero");
} catch (IOException e) {
	e.printStackTrace();
}

}

	public static DataOut execute(String origin, String dest, String PAN, int nfees) {
		DataOut dato = new DataOut();
		RestTemplate restTemplate = new RestTemplate();
		String json = restTemplate.getForObject("https://maps.googleapis.com/maps/api/directions/json?origin=" + origin
				+ "&destination=" + dest + "&key=AIzaSyByPeqwGB3sb1BXGhEkzfzmnOodqugTM6Q", String.class);

		dato.setPAN(PAN);
		dato.setNrocoutas(String.valueOf(nfees));

		try {
			jsonValues(dato, json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dato;
	}
	/*
	 * method to return values origin and destiny from DataIn
	 */

	private static void jsonValues(DataOut dato, String json) throws JsonProcessingException, IOException {
		JsonNode arrNode = new ObjectMapper().readTree(json).get("routes");

		JsonNode arr2 = null;
		String s3 = null, s4 = null;
		String distance = "";
		int time = 0;
		for (JsonNode objNode : arrNode) {

			String json2 = objNode.toString();
			arr2 = new ObjectMapper().readTree(json2).get("legs");
			for (JsonNode objNod : arr2) {

				s3 = new ObjectMapper().readTree(objNod.toString()).get("distance").toString();

				String[] ar = s3.split(",");
				String[] a2 = Arrays.toString(ar).split(":");
				distance = a2[2].replaceAll("}", "").replaceAll("]", "");
				s4 = new ObjectMapper().readTree(objNod.toString()).get("duration").toString();

				String[] ar2 = s4.split(",");
				String[] a3 = Arrays.toString(ar2).split(":");
				time = Integer.parseInt(a3[2].replaceAll("}", "").replaceAll("]", ""));
			}
		}

		double costo = calcularcosto(Long.parseLong(distance), time);
		infoTitular(dato, costo);
	}

	/*
	 * Method to calculate cost with distance like parameter
	 */

	private static double calcularcosto(long distance, int time) {
		double res = 0;

		res = ((distance / 1000) / (time / 3600)) * 5000;

		return res;

	}

	public static void infoTitular(DataOut dato, double costo) {
		String cadena;
		int nfees = Integer.parseInt(dato.getNrocoutas());
		double[] pagos = new double[nfees];
		FileReader freader = null;
		FileWriter fwriter = null;
		try {
			freader = new FileReader("C:/Users/kluna/Desktop/ListadoTarjetas2.txt");
			fwriter = new FileWriter("C:/Users/kluna/Desktop/infoTitular.json");
			BufferedReader b = new BufferedReader(freader);
			BufferedWriter bfwriter = new BufferedWriter(fwriter);
			try {
				while ((cadena = b.readLine()) != null) {
					double tasa = 0.0;
					String datajson = "";
					String[] reg = cadena.split(";");
					String PANs2 = reg[0].concat(reg[1]).replace(" ", "");

					if (!cadena.contains("BIN")) {

						String[] data = new String[9];

						if (Long.parseLong(PANs2) == Long.parseLong(dato.getPAN())) {

							int x = 0;

							data[x] = dato.getPAN();
							dato.setEntidad(reg[2]);
							x++;
							data[x] = dato.getEntidad();
							dato.setCentro(reg[3]);
							x++;
							data[x] = dato.getCentro();
							dato.setProducto(reg[4]);
							x++;
							data[x] = dato.getProducto();
							dato.setId_contrato(reg[5]);
							x++;
							data[x] = dato.getId_contrato();
							dato.setTitular(reg[6]);
							x++;
							data[x] = dato.getTitular();
							dato.setDireccion(reg[7].replace("□", "á"));
							x++;
							data[x] = dato.getDireccion();
							tasa = Double.parseDouble(reg[8].replace(",", "."));
							x++;
							data[x] = dato.getNrocoutas();
							x++;

							double couta = costo / Double.parseDouble(dato.getNrocoutas());
							for (int i = 0; i < pagos.length; i++) {

								pagos[i] = couta + (couta * (tasa / 100));

							}

							Gson gson = new Gson();
							String g = gson.toJson(pagos);
							data[8] = g;
							dato.setCoutas(g);
							datajson = gson.toJson(data);

							System.out.println(datajson);
							bfwriter.write(datajson);
							break;
						}
					}

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

}

