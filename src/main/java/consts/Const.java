package consts;

public class Const {
	public static final String URL  = "jdbc:mysql://fukushima-pc/Ex_bus_System";
	public static final String USER = "teamF";
	public static final String PASS = "figMySQL";

	public static final String[] ignoreURL = new String[] {"img", "css", "js", "login", "CreateUser"};
	
	public enum ProductCategories {
	    LIMITED_TIME(1),
	    BENTO(2),
	    SIDE_DISH_ONLY(3),
	    SIDE_MENU(4),
	    ALL(5);

	    private final int value;

	    ProductCategories(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	}
}