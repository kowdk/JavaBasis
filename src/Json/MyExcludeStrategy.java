package Json;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class MyExcludeStrategy implements ExclusionStrategy{

	private final Class<?> typeToSkip;
	
	public MyExcludeStrategy(Class<?> typeToSkip) {
		this.typeToSkip = typeToSkip;
	}
	
	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		return f.getAnnotation(Exclude.class) != null;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return (clazz == typeToSkip);
	}

}
