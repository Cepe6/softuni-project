package bg.avi.common.web.zk;

import java.util.HashSet;
import java.util.Set;

import org.zkoss.zk.ui.Sessions;

public final class ZulLocator {
	private static final String ZUL_ROOT = "/UI/zuls";
	private static final String ZUL_EXT  = ".zul";
	private static final ZulLocator INSTANCE = new ZulLocator();
	
	private final Set<String> zuls = new HashSet<>();
	
	private ZulLocator() {
		loadAllZuls();
	}
	
	private void loadAllZuls() {
		zuls.addAll(getAllZuls(ZUL_ROOT));
	}
	
	private Set<String> getAllZuls(String root) {
		Set<String> zuls = new HashSet<>();
		for (String p : Sessions.getCurrent().getWebApp().getResourcePaths(root)) {
			if (p.endsWith(ZUL_EXT)) {
				zuls.add(p);
			} else {
				zuls.addAll(getAllZuls(p));
			}
		}
		
		return zuls;
	}
	
	private String getZulLocationByName(String zulName) {
		if (zulName != null && zulName.length() > 0) {
			if (!zulName.endsWith(ZUL_EXT)) {
				zulName += ZUL_EXT;
			}
			
			for (String string : zuls) {
				if (string.endsWith(zulName)) {
					return string;
				}
			}
			
			return null;
		}
		
		
		return zulName;
	}
	
	public static String zulLocationByName(String zulName) {
		return INSTANCE.getZulLocationByName(zulName);
	}
	
	/**
	 * Maybe you don't want to do that, but...
	 */
	public static void reloadZulLocations() {
		INSTANCE.zuls.clear();
		INSTANCE.loadAllZuls();
	}
}
