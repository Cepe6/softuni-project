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
import bg.avi.numrec.web.admin.dto.PlatePatternDTO;
import bg.avi.numrec.web.admin.service.CountryService;
import bg.avi.numrec.web.admin.service.PlatePatternService;

@VariableResolver(DelegatingVariableResolver.class)
public class PlatePatternVM {
	@WireVariable private PlatePatternService platePatternService;
	@WireVariable private CountryService countryService;
	
	private List<PlatePatternDTO> patterns;
	private List<CountryDTO> countries;
	
	private PlatePatternDTO searchObject;
	
	@Init
	public void init() {
		patterns = platePatternService.getAllPatterns();
		countries = countryService.getAllCountries();
		
		searchObject = new PlatePatternDTO();
	}
	
	@AfterCompose
	public void afterComposeMenu(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
	}
	
	@Command
	@NotifyChange("patterns")
	public void search() {
		patterns = platePatternService.getPatternsByExample(searchObject);
	}
	
	@Command
	@NotifyChange({ "searchObject", "patterns" })
	public void clear() {
		searchObject = new PlatePatternDTO();
		patterns = platePatternService.getPatternsByExample(searchObject);
	}
	
	@Command
	public void addEdit(@BindingParam("object") PlatePatternDTO object) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Config.PARENT_VM, this);
		params.put(Config.PARAM, object);
		
		Window window = (Window) Executions.createComponents(ZulLocator.zulLocationByName("addEditPlatePattern.zul"), null, params);
		window.doModal();
	}
	
	public void refresh() {
		patterns = platePatternService.getAllPatterns();
        BindUtils.postNotifyChange(null, null, this, "patterns");
	}

	/* Getters & Setters */
	public List<PlatePatternDTO> getPatterns() {
		return patterns;
	}

	public List<CountryDTO> getCountries() {
		return countries;
	}

	public PlatePatternDTO getSearchObject() {
		return searchObject;
	}

	public void setSearchObject(PlatePatternDTO searchObject) {
		this.searchObject = searchObject;
	}
}
