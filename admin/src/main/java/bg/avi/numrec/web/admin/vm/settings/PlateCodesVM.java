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
import bg.avi.numrec.web.admin.dto.CodeTypeDTO;
import bg.avi.numrec.web.admin.dto.CountryDTO;
import bg.avi.numrec.web.admin.dto.PlateCodeDTO;
import bg.avi.numrec.web.admin.service.CodeTypeService;
import bg.avi.numrec.web.admin.service.CountryService;
import bg.avi.numrec.web.admin.service.PlateCodeService;

@VariableResolver(DelegatingVariableResolver.class)
public class PlateCodesVM {
	@WireVariable private PlateCodeService plateCodeService;
	@WireVariable private CountryService countryService;
	@WireVariable private CodeTypeService codeTypeService;

	private List<PlateCodeDTO> codes;
	
	private List<CountryDTO> countries;
	private List<CodeTypeDTO> codeTypes;
	
	private PlateCodeDTO searchObject;
	
	@Init
	public void init() {
		codes = plateCodeService.getAllCodes();
		countries = countryService.getAllCountries();
		codeTypes = codeTypeService.getAllCodeTypes();
		
		searchObject = new PlateCodeDTO();
	}
	
	@AfterCompose
	public void afterComposeMenu(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
	}
	
	@Command
	@NotifyChange("codes")
	public void search() {
		codes = plateCodeService.getCodesByExample(searchObject);
	}
	
	@Command
	@NotifyChange({ "searchObject", "codes" })
	public void clear() {
		searchObject = new PlateCodeDTO();
		codes = plateCodeService.getCodesByExample(searchObject);
	}
	
	@Command
	public void addEdit(@BindingParam("object") PlateCodeDTO object) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Config.PARENT_VM, this);
		params.put(Config.PARAM, object);
		
		Window window = (Window) Executions.createComponents(ZulLocator.zulLocationByName("addEditPlateCode.zul"), null, params);
		window.doModal();
	}
	
	public void refresh() {
		codes = plateCodeService.getAllCodes();
        BindUtils.postNotifyChange(null, null, this, "codes");
	}

	/* Getters & Setters */
	public List<PlateCodeDTO> getCodes() {
		return codes;
	}

	public PlateCodeDTO getSearchObject() {
		return searchObject;
	}

	public void setSearchObject(PlateCodeDTO searchObject) {
		this.searchObject = searchObject;
	}

	public List<CountryDTO> getCountries() {
		return countries;
	}

	public List<CodeTypeDTO> getCodeTypes() {
		return codeTypes;
	}
}
