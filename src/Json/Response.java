package Json;

import java.util.Map;

public class Response {
	Map<String, App> descriptor;

	public Map<String, App> getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(Map<String, App> descriptor) {
		this.descriptor = descriptor;
	}
	
}
