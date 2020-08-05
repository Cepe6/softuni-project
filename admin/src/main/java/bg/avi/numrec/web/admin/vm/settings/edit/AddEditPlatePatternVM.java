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
import bg.avi.numrec.web.admin.dto.CountryDTO;
import bg.avi.numrec.web.admin.dto.PlatePatternDTO;
import bg.avi.numrec.web.admin.service.CountryService;
import bg.avi.numrec.web.admin.service.PlatePatternService;
import bg.avi.numrec.web.admin.vm.settings.PlatePatternVM;

@VariableResolver(DelegatingVariableResolver.class)
public class AddEditPlatePatternVM {
	@Wire private Window addEditDlg;
	
	@WireVariable private PlatePatternService platePatternService;
	@WireVariable private CountryService countryService;

	private PlatePatternVM parent;
	private PlatePatternDTO addEditObject;
	private List<CountryDTO> countries;

	@SuppressWarnings("unchecked")
	@Init
	public void init() {
		Map<String, Object> args = (Map<String, Object>) Executions.getCurrent().getArg();
		parent = (PlatePatternVM) args.get(Config.PARENT_VM);
		addEditObject = (PlatePatternDTO) args.get(Config.PARAM);
		if (addEditObject == null) {
			addEditObject = new PlatePatternDTO();
		}
		
		countries = countryService.getAllCountries();
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
						platePatternService.saveOrUpdate(addEditObject);
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
		if (StringUtils.isEmpty(addEditObject.getPattern())) return false;
		if (addEditObject.getCountry() == null) return false;
		return true;
	}

	/* Getters & Setters */
	public PlatePatternDTO getAddEditObject() {
		return addEditObject;
	}

	public void setAddEditObject(PlatePatternDTO addEditObject) {
		this.addEditObject = addEditObject;
	}

	public List<CountryDTO> getCountries() {
		return countries;
	}
}
