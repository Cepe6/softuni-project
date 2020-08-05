package bg.avvm.numrec.main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JFileChooser;

import org.apache.commons.io.FileUtils;

import bg.avvm.numrec.recognition.RecognizeUtil;
import net.sourceforge.tess4j.TesseractException;

public class EntryPoint {
	
	public static final String BG_REGEX = "([A-Z]{1,2})(\\d{4})(([A-Z]{1}\\d{1})|([A-Z]{1,2}))";
	
	public static void main(String[] args) throws IOException, TesseractException {
		System.out.println("=== BEGIN ===");
		FileUtils.cleanDirectory(new File(RecognizeUtil.RES_PATH));
		
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(RecognizeUtil.SRC_PATH));
		int returnVal = fc.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			byte[] byteData = null;
			try {
				byteData = Files.readAllBytes(fc.getSelectedFile().toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}

			String response = RecognizeUtil.recognizeContours(null, byteData);
			System.out.println(response);
			System.out.println("===  END  ===");
		}
	}
}