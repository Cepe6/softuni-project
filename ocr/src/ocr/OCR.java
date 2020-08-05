package ocr;

import java.io.File;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCR {

    public static void main(String[] args) {
        File image = new File("C:/Users/Alexander Verbovskiy/Desktop/2.png");
        Tesseract tessInst = new Tesseract();
        tessInst.setDatapath("C:/Users/Alexander Verbovskiy/Desktop/Tess4J");
        try {
            String result= tessInst.doOCR(image);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }

    }
}
