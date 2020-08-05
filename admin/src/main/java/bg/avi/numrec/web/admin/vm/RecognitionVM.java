package bg.avi.numrec.web.admin.vm;

import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.image.Image;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;

import bg.avi.numrec.web.admin.dto.CountryDTO;
import bg.avi.numrec.web.admin.dto.PlatePatternDTO;
import bg.avi.numrec.web.admin.dto.RecognitionHistoryDTO;
import bg.avi.numrec.web.admin.service.CountryService;
import bg.avi.numrec.web.admin.service.PlatePatternService;
import bg.avi.numrec.web.admin.service.RecognitionHistoryService;
import bg.avi.numrec.web.admin.service.RecognitionService;

@VariableResolver(DelegatingVariableResolver.class)
public class RecognitionVM {
	
	@WireVariable
	RecognitionService recognitionService;
	@WireVariable
	PlatePatternService platePatternService;
	@WireVariable
	RecognitionHistoryService recognitionHistoryService;
	@WireVariable
	CountryService countryService;

	PlatePatternDTO searchObject;
	Image carPlate;
	String result;
	
	@Init
	public void onInit() {
		searchObject = new PlatePatternDTO();
	}

	@Command
	public void recognize(@BindingParam("uploadEvent") UploadEvent uploadEvent) {
		if(searchObject.getCountry() == null) {
			Messagebox.show("Моля, изберете държава!");
			return;
		}
		
		Media media = uploadEvent.getMedia();
		if(media instanceof Image) {
			carPlate = (Image) media;
			result = recognitionService.recognize(platePatternService.getPatternsByExample(searchObject), media.getByteData());
			if(!result.equals("???")) {
				recognitionHistoryService.saveOrUpdate(new RecognitionHistoryDTO(searchObject.getCountry(), result));
			}
		} else {
			Messagebox.show("The selected file is not an image!", "Invalid action!", Messagebox.OK, Messagebox.ERROR);
			return;
		}
		
		BindUtils.postNotifyChange(null, null, this, "carPlate");
		BindUtils.postNotifyChange(null, null, this, "result");
	}
	
	public void setCountry(CountryDTO country) {
		searchObject.setCountry(country);
	}
	
	public CountryDTO getCountry() {
		return searchObject.getCountry();
	}
	
	public List<CountryDTO> getCountries() {
		return countryService.getAllCountries();
	}
	
	public Image getCarPlate() {
		return carPlate;
	}

	public String getResult() {
		return result;
	}
}
