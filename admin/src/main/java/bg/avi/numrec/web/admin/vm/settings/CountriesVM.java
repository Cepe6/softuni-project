package bg.avi.numrec.web.admin.vm.settings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import bg.avi.common.web.zk.ZulLocator;
import bg.avi.numrec.web.admin.config.Config;
import bg.avi.numrec.web.admin.dto.CountryDTO;
import bg.avi.numrec.web.admin.service.CountryService;

@VariableResolver(DelegatingVariableResolver.class)
public class CountriesVM {
	@WireVariable private CountryService countryService;

	private List<CountryDTO> countries;
	
	private CountryDTO searchObject;
	
	@Init
	public void init() {
		countries = countryService.getAllCountries();
		
		searchObject = new CountryDTO();
	}
	
	@AfterCompose
	public void afterComposeMenu(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
	}
	
	@Command
	@NotifyChange("countries")
	public void search() {
		countries = countryService.getCountriesByExample(searchObject);
	}
	
	@Command
	@NotifyChange({ "searchObject", "countries" })
	public void clear() {
		searchObject = new CountryDTO();
		countries = countryService.getCountriesByExample(searchObject);
	}
	
	@Command
	public void addEdit(@BindingParam("object") CountryDTO object) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Config.PARENT_VM, this);
		params.put(Config.PARAM, object);
		
		Window window = (Window) Executions.createComponents(ZulLocator.zulLocationByName("addEditCountry.zul"), null, params);
		window.doModal();
	}
	
	public void refresh() {
		countries = countryService.getAllCountries();
        BindUtils.postNotifyChange(null, null, this, "countries");
	}

	/* Getters & Setters */
	public List<CountryDTO> getCountries() {
		return countries;
	}

	public CountryDTO getSearchObject() {
		return searchObject;
	}

	public void setSearchObject(CountryDTO searchObject) {
		this.searchObject = searchObject;
	}
}
