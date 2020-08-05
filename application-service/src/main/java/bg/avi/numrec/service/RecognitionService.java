package bg.avi.numrec.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import bg.avvm.numrec.recognition.RecognizeUtil;

@Service
public class RecognitionService {
	public String recognize(List<String> regexes, byte[] data) throws IOException {
		FileUtils.cleanDirectory(new File(RecognizeUtil.RES_PATH));
		return RecognizeUtil.recognizeContours(regexes, data);
	}
}
