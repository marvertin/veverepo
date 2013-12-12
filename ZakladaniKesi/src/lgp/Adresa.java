package lgp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Adresa {
	
	public static final Adresa ZERO = Adresa.from(0);
	
	private static Pattern pat = Pattern.compile("(\\d\\d)(\\d\\d)");
	int iAdr;

	private Adresa(int aAdr) {
		iAdr= aAdr;
	}
	
	
	private Adresa(String aAdr) {
		String s = aAdr.trim();
		Matcher mat = pat.matcher(s);
		if (!mat.matches()) throw new IllegalArgumentException("Illegal adress: \"" + s + "\"");
		int stopa = Integer.parseInt(mat.group(1));
		int sector = Integer.parseInt(mat.group(2));
		if (stopa < 0 || stopa > 63) throw new IllegalArgumentException("Spatna stopa v adrese \"" + s + "\"");
		if (sector < 0 || sector > 63) throw new IllegalArgumentException("Spatny sector v adrese \"" + s + "\"");
		iAdr = stopa * 64 + sector;
	}
	
	public static boolean canFrom(String aAdr) {
		String s = aAdr.trim();
		return pat.matcher(s).matches();
	}
	
	public static Adresa from(String s) {
		return s == null ? null : new Adresa(s);
	}

	public static Adresa from(int aAdr) {
		return new Adresa(aAdr);
	}
	
	public int toInt() {
		return iAdr;
	}
	
	public String toString() {
		return String.format("%02d%02d", iAdr / 64, iAdr % 64);
	}
	
	public Adresa inc() {
		return Adresa.from(iAdr+1);
	}
	

}
