package bg.avi.numrec.web.admin.vm.settings;

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

import bg.avi.numrec.web.admin.dto.UserDTO;
import bg.avi.numrec.web.admin.service.UserDetailsService;

@VariableResolver(DelegatingVariableResolver.class)
public class UsersVM {
	@WireVariable private UserDetailsService userDetailsService;

	private List<UserDTO> users;
	
	private UserDTO searchObject;
	
	@Init
	public void init() {
		users = userDetailsService.getAllUsers();
		
		searchObject = new UserDTO();
	}
	
	@AfterCompose
	public void afterComposeMenu(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
	}
	
	@Command
	@NotifyChange("users")
	public void search() {
		users = userDetailsService.getUsersByExample(searchObject);
	}
	
	@Command
	@NotifyChange({ "searchObject", "users" })
	public void clear() {
		searchObject = new UserDTO();
		users = userDetailsService.getUsersByExample(searchObject);
	}
	
	/* Getters & Setters */
	public List<UserDTO> getUsers() {
		return users;
	}

	public UserDTO getSearchObject() {
		return searchObject;
	}

	public void setSearchObject(UserDTO searchObject) {
		this.searchObject = searchObject;
	}
}
