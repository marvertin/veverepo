package lib;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

public abstract class Predek {

	public List<String> readLines(String name) throws IOException {
		return IOUtils.readLines(getClass().getResourceAsStream(name), "UTF-8");
	}
	
	public void pl(Object obj) {
		System.out.println(obj);
	}
}
