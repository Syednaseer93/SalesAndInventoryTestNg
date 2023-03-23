package genericutilities;

public class DynamicXpathUtils {
	
	public static String getDynamicXpath(String xpath, String value) {
		String dynamicXpath = xpath.replaceAll("%replaceable%", value);
		return dynamicXpath;
	}
	
	public static String getDynamicXpath(String xpath, int value) {
		String var = String.valueOf(value);
		String dynamicXpath = xpath.replaceAll("%replaceable%", var);
		return dynamicXpath;
	}
	public static String getDynamicXpath(String xpath1, String xpath2, int value) {
		String var = String.valueOf(value);
		String dynamicXpath1 = xpath1.replaceAll("%replaceable%", var);
		String dynamicXpath2= xpath2.replaceAll("%replaceable%", var);
		return dynamicXpath1+" "+dynamicXpath2;
	}
}