package com.bbva.capx.batch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import com.bbva.elara.batch.contextutils.ContextUtils;


public class Step1 implements Tasklet {

	private ContextUtils contextUtils;

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		Logger log = contextUtils.getLOGGER(chunkContext, getClass());
		String registro = "0013, 5782, 9676111213, 222946534, 2018/5/27, 21";
		File f_salida = new File("C:/Users/kluna/Desktop/registrofin.txt");
		File f_entrada = new File("C:/Users/kluna/Desktop/ContratosFeuliq2.txt");

		LinkedList<String> lista = new LinkedList<String>();

		try {

			FileReader fr = new FileReader(f_entrada);
			FileWriter fw = new FileWriter(f_salida);

			BufferedReader br = new BufferedReader(fr);
			//BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw2 = new PrintWriter(fw);

			String linea = null;
			
			double saldor = 0;

			while ((linea=br.readLine()) != null && !(linea=br.readLine()).contains("SALDO")) {
				linea = linea.replace("[", "").replace("]", "").replace(" ", "");

				String[] linean = registro.split(",");//string
				String[] linean2 = linea.split(",");//entrada
				long a = Long.parseLong(linean2[1].concat(linean2[2]).replace(" ", ""));
				if (a == Long.parseLong(linean[1].concat(linean[2]).replace(" ", ""))) {
                         pw2.println(linea);
					while ((linea=br.readLine())  != null && !(linea=br.readLine()).contains("SALDO")) {
						String[] linean21 = linea.split(",");
						double saldo =Double.parseDouble(linean21[3].replace(" ", ""));
						Date fechai = new SimpleDateFormat("yyyy/MM/dd").parse(linean[4]);
						Date fechaf = new Date();

						int diasm = (int) ((fechaf.getTime() - fechai.getTime()) / 86400000);
                        
						if (diasm >= 30 && diasm <= 59) {
							saldor = saldo + ((saldo*6)/100);
						} else if (diasm >= 60 && diasm <= 89) {
							saldor = saldo + (saldo *(4/100));
						} else {
							float aum=(float)(saldo*(6*0.01));
						saldor=saldo+aum;
						
						}
						pw2.println(linea + "," + saldor);
					}

				}
				
				
			}
			
			pw2.close();
			br.close();
			fr.close();
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			System.err.println("No se ha encontrado el fichero");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return RepeatStatus.FINISHED;
	}

	public void setContextUtils(ContextUtils contextUtils) {
		this.contextUtils = contextUtils;
	}

}
