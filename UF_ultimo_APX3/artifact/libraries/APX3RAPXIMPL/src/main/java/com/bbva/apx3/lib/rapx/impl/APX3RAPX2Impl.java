package com.bbva.apx3.lib.rapx.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bbva.apx3.dto.apx3.DataOut;
import com.bbva.apx3.lib.rapx.APX3RAPX;

public class APX3RAPX2Impl extends  APX3RAPXImpl{

	private static final Logger LOGGER = LoggerFactory.getLogger(APX3RAPX.class);
	@Override
	public String execute2() {
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



}
