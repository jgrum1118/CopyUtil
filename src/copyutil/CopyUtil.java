/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package copyutil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author jgrum
 */
public class CopyUtil {
    /**
     * @param args the command line arguments
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
       
        Properties p = new Properties();
        p.load(new FileInputStream("copyUtil.ini"));
        String inputFile = p.getProperty("inputFile");
        String outputFile = p.getProperty("outputFile");
        int nbrOfRecsToSkip = Integer.parseInt(p.getProperty("nbrOfRecsToSkip"));
        int nbrOfRecsToCopy = Integer.parseInt(p.getProperty("nbrOfRecsToCopy"));
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile), 16384);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));
        
        System.out.println("Input file: " + inputFile);
        System.out.println("Output file: " + outputFile);
        System.out.println("Skipping the first " + nbrOfRecsToSkip + " records");
        
        String inRec = "";
        int skipCnt = 0;
        int copyCnt = 0;
        int readCnt = 0;
        
        

        while ((inRec = bufferedReader.readLine()) != null) {
           	if (nbrOfRecsToSkip > 0) {
           		skipCnt++;
           		if (skipCnt <= nbrOfRecsToSkip) {
           			continue;
           		} 
           	}
            	
           	readCnt++;
            	
           	if (readCnt > nbrOfRecsToCopy) {
           		break;
           	}
            	
           	bufferedWriter.write(inRec);
           	bufferedWriter.newLine();
           	bufferedWriter.flush();
           	copyCnt++;
        }
        
        bufferedReader.close();
        bufferedWriter.close();
        
        System.out.println("Number of records copied: " + copyCnt);
        
    } /** END MAIN */
}
    
