package bg.avi.numrec.web.admin.vm;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;

import bg.avi.common.web.zk.ZulLocator;
import bg.avi.numrec.web.admin.config.Config;

public class IndexVM {
	@Wire private Component centerContent;
	
	@Init
	public void init() {}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@GlobalCommand
	public void changeCenterContent(@BindingParam("zulName") String zulName, @BindingParam("param") Object param) {
		// Detach center content
		if (centerContent != null) {
			centerContent.getChildren().clear();
		}
		
		Map<String, Object> params = new HashMap<>();
		if (param != null) {
			params.put(Config.PARAM, param);
		}
		
		Executions.createComponents(ZulLocator.zulLocationByName(zulName), centerContent, params);
	}
}
