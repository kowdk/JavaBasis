package CollectionAndGenericity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapDemo {
	private static void equalsAndHashcodeTest() {
		Map<Person, String> map = new HashMap<Person, String>();
		map.put(new Person("xutao", 18), "good");
		map.put(new Person("xutao", 18), "bad");
		for (Entry<Person, String> e : map.entrySet())
			System.out.println(e.getKey() + " " + e.getValue());
	}

	private static void LinkedHashMapTest() {
		
		// accessOrder the ordering mode - true for access-order, false for insertion-order
		Map<String, String> map = new LinkedHashMap<String, String>(16, 0.75f, true);
		for (int i = 0; i < 10; i++) {
			map.put("key" + i, "value" + i);
		}
		map.get("key" + 3);
		for (String value : map.keySet()) {
			System.out.println(value);
		}

	}

	public static void main(String[] args) {
		equalsAndHashcodeTest();
		LinkedHashMapTest();
	}
}
