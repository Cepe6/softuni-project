package bg.avi.numrec.web.admin.vm;

import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import bg.avi.numrec.web.admin.dto.CarPlateDTO;
import bg.avi.numrec.web.admin.dto.CountryDTO;
import bg.avi.numrec.web.admin.service.CarPlateService;
import bg.avi.numrec.web.admin.service.CountryService;

@VariableResolver(DelegatingVariableResolver.class)
public class CarPlatesVM {
	@WireVariable private CarPlateService carPlateService;
	@WireVariable private CountryService countryService;

	private List<CarPlateDTO> plates;
	private List<CountryDTO> countries;
	
	private CarPlateDTO searchObject;
	
	@Init
	public void init() {
		plates = carPlateService.getAllPlates();
		countries = countryService.getAllCountries();
		
		searchObject = new CarPlateDTO();
	}
	
	@AfterCompose
	public void afterComposeMenu(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
	}
	
	@Command
	@NotifyChange("plates")
	public void search() {
		plates = carPlateService.getPlatesByExample(searchObject);
	}
	
	@Command
	@NotifyChange({ "searchObject", "plates" })
	public void clear() {
		searchObject = new CarPlateDTO();
		plates = carPlateService.getPlatesByExample(searchObject);
	}

	/* Getters & Setters */
	public List<CarPlateDTO> getPlates() {
		return plates;
	}
	
	public List<CountryDTO> getCountries() {
		return countries;
	}

	public CarPlateDTO getSearchObject() {
		return searchObject;
	}

	public void setSearchObject(CarPlateDTO searchObject) {
		this.searchObject = searchObject;
	}
}
