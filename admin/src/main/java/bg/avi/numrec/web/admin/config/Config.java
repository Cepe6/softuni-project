package bg.avi.numrec.web.admin.config;

public class Config {
	// Zul Params
	public static final String PARAM = "param";
	public static final String PARENT_VM = "parent";
	
	//Paths
	public static final String BASE_URL = "http://localhost:8080/admin/";
	public static final String MAIN_PATH = "/admin/";
	
	public static final String APPLICATION_BASE_PATH = "http://localhost:8081/num-rec/v1.0";

	public static final int PAGE_SIZE = 8;
	
	//Icon paths
	public static final String ICON_MAIN_PATH = "UI/icons/";
	
	public static final String ICON_LOGO = ICON_MAIN_PATH + "logo.png";
	
	public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DATE_FORMAT = "dd.MM.yyyy";
	
	// HTTP Settings
	public static final int HTTP_CONN_TIMEOUT = 10000;
	public static final int	HTTP_READ_TIMEOUT = 17000;
}
