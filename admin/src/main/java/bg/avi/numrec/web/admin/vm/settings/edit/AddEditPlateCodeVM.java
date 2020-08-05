package bg.avi.numrec.web.admin.vm.settings.edit;

import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

import bg.avi.numrec.web.admin.config.Config;
import bg.avi.numrec.web.admin.dto.CodeTypeDTO;
import bg.avi.numrec.web.admin.dto.CountryDTO;
import bg.avi.numrec.web.admin.dto.PlateCodeDTO;
import bg.avi.numrec.web.admin.service.CodeTypeService;
import bg.avi.numrec.web.admin.service.CountryService;
import bg.avi.numrec.web.admin.service.PlateCodeService;
import bg.avi.numrec.web.admin.vm.settings.PlateCodesVM;

@VariableResolver(DelegatingVariableResolver.class)
public class AddEditPlateCodeVM {
	@Wire private Window addEditDlg;
	
	@WireVariable private PlateCodeService plateCodeService;
	@WireVariable private CountryService countryService;
	@WireVariable private CodeTypeService codeTypeService;

	private PlateCodesVM parent;
	private PlateCodeDTO addEditObject;
	
	private List<CountryDTO> countries;
	private List<CodeTypeDTO> codeTypes;
	
	@SuppressWarnings("unchecked")
	@Init
	public void init() {
		Map<String, Object> args = (Map<String, Object>) Executions.getCurrent().getArg();
		parent = (PlateCodesVM) args.get(Config.PARENT_VM);
		addEditObject = (PlateCodeDTO) args.get(Config.PARAM);
		if (addEditObject == null) {
			addEditObject = new PlateCodeDTO();
		}
		
		countries = countryService.getAllCountries();
		codeTypes = codeTypeService.getAllCodeTypes();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	public void save() {
		if (!validate()) {
			Messagebox.show(Labels.getLabel("validation.fillAllMandatoryFields"), Labels.getLabel("error"), Messagebox.OK, Messagebox.ERROR);
			return;
		} else {
			Messagebox.show(Labels.getLabel("validation.save"), Labels.getLabel("validation.confirmOperation"),
				new Messagebox.Button[] {Messagebox.Button.YES, Messagebox.Button.NO},
				new String[] {Labels.getLabel("yes"), Labels.getLabel("no")},
				Messagebox.QUESTION, null, (EventListener<ClickEvent>) e -> {
					if (Messagebox.ON_YES.equals(e.getName())) {
						plateCodeService.saveOrUpdate(addEditObject);
						parent.refresh();
						addEditDlg.detach();
					}
				}
			);
		}
	}

	@Command
	public void cancel() {
		Messagebox.show(Labels.getLabel("validation.cancel"), Labels.getLabel("validation.confirmOperation"),
			new Messagebox.Button[] {Messagebox.Button.YES, Messagebox.Button.NO},
			new String[] {Labels.getLabel("yes"), Labels.getLabel("no")},
			Messagebox.QUESTION, null, (EventListener<ClickEvent>) e -> {
				if (Messagebox.ON_YES.equals(e.getName())) {
					parent.refresh();
					addEditDlg.detach();
				}
			}
		);
	}
	
	private boolean validate() {
		if (StringUtils.isEmpty(addEditObject.getCode())) return false;
		if (addEditObject.getCountry() == null) return false;
		if (addEditObject.getType() == null) return false;
		return true;
	}

	/* Getters & Setters */
	public List<CountryDTO> getCountries() {
		return countries;
	}

	public List<CodeTypeDTO> getCodeTypes() {
		return codeTypes;
	}

	public PlateCodeDTO getAddEditObject() {
		return addEditObject;
	}

	public void setAddEditObject(PlateCodeDTO addEditObject) {
		this.addEditObject = addEditObject;
	}
}
