package bg.avvm.numrec.recognition;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class RecognizeUtil {
	public static final String SRC_PATH = "/home/cepe6/Desktop/rec-images/";
	public static final String RES_PATH = "/home/cepe6/Desktop/rec-images/res/";
	
	static Tesseract tesseract = new Tesseract();
	
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		tesseract.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata");
		tesseract.setTessVariable("tessedit_char_whitelist", "acekopxyABCEHKMOPTXY0123456789 ");
	}

	public static String recognizeContours(List<String> regexes, byte[] bytes) {
		String result = null;

		List<Pattern> patterns = regexes.stream().map(regex -> Pattern.compile(regex)).collect(Collectors.toList());
		Matcher matcher = null;
		try {
			Mat inputMat = new Mat(1, bytes.length, CvType.CV_8UC1);
			inputMat.put(0, 0, bytes);
			inputMat = Imgcodecs.imdecode(inputMat, Imgcodecs.IMREAD_COLOR);
			Imgcodecs.imwrite(RES_PATH + "input.png", inputMat);

			Mat gray = new Mat();
			Imgproc.cvtColor(inputMat, gray, Imgproc.COLOR_BGR2GRAY);
			Imgcodecs.imwrite(RES_PATH + "gray.png", gray);

			Mat blur = new Mat();
			Size ksize = new Size(5, 5);
			Imgproc.GaussianBlur(gray, blur, ksize, 0);
			Imgcodecs.imwrite(RES_PATH + "blur.png", blur);
			
			Mat equalized = new Mat();
			Imgproc.equalizeHist(blur, equalized);
			Imgcodecs.imwrite(RES_PATH + "equalized.png", equalized);

			Mat target = new Mat();
			Imgproc.threshold(blur, target, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
			Imgcodecs.imwrite(RES_PATH + "bin1.png", target);

			List<MatOfPoint> contours = new ArrayList<>();
			Imgproc.findContours(target, contours, null, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

			for (int i = 0; i < contours.size(); i++) {
				double area = Imgproc.contourArea(contours.get(i));
				Rect rect = Imgproc.boundingRect(contours.get(i));

				if (area > 500 && rect.width > rect.height && rect.y > 0) {
					Mat image_output = target.submat(rect);

					StringBuilder outputFile = new StringBuilder(RES_PATH).append("out").append(i)
							.append(".png");
					Imgcodecs.imwrite(outputFile.toString(), image_output);
					
					String OCRresult = tesseract.doOCR(new File(outputFile.toString()));
					inner: for(int pI = 0; pI < patterns.size(); pI++) {
						matcher = patterns.get(pI).matcher(OCRresult);
						if(matcher.find()) {
							System.out.println(OCRresult);
							result = matcher.group(0).replaceAll("\\s+", "");
							
							break inner;
						}
					}
				}
			}
		} catch (TesseractException te) {
			te.printStackTrace();
		}
		return result == null ? "???" : result;
	}
}
