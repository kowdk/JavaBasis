package Json;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

/**
 * Gson Demo 1. 对于固定格式的json，可以写与串格式对应的类来完成转换，包括类的嵌套 2.
 * 对于不固定格式和数据的json，可以使用逐步解析的方法完成转换
 * 
 * @author xutao
 * @date 2016年12月30日
 *
 */

public class GsonDemo {

	/**
	 * 读取固定格式的Json串
	 */
	public void readJsonUsingMapping() {
		final String jsonString = "{\"descriptor\" : " + "{\"app1\" :" + "{\"name\" : \"xutao\", " + "\"age\" : 24, "
				+ "\"messages\": " + "[ \"msg 1\", \"msg 2\", \"msg 3\"] " + "}" + "}" + "}";

		Gson gson = new Gson();

		Response res = gson.fromJson(jsonString, Response.class);
		App app = res.getDescriptor().get("app1");
		int age = app.getAge();
		System.out.println(age);
		String name = app.getName();
		System.out.println(name);
		String[] msgs = app.getMessage();
		System.out.println(Arrays.toString(msgs));
	}

	
	/**
	 * 从文件中读取json
	 * @throws IOException
	 */
	public void readFromFile() throws IOException {
		FileReader fReader = null;
		final String projectPath = new File("").getAbsolutePath();
		fReader = new FileReader(projectPath + File.separator + "res" + File.separator + "jsonFile.json");

		JsonReader jsonReader = new JsonReader(fReader);
		jsonReader.beginObject();

		while (jsonReader.hasNext()) {

			String name = jsonReader.nextName();
			if (name.equals("descriptor")) {
				readApp(jsonReader);
			}
		}

		jsonReader.endObject();
		jsonReader.close();
	}

	/**
	 * 逐层剥离，每一层都需要先beginObject，nextName是key，nextString是value
	 * @param reader
	 * @throws IOException
	 */
	private void readApp(JsonReader reader) throws IOException {
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			System.out.println(name);
			if (name.startsWith("app")) {
				reader.beginObject();
				while (reader.hasNext()) {
					String n = reader.nextName();
					if (n.equals("name")) {
						System.out.println(reader.nextString());
					}
					if (n.equals("age")) {
						System.out.println(reader.nextInt());
					}
					if (n.equals("messages")) {
						reader.beginArray();
						while (reader.hasNext()) {
							System.out.println(reader.nextString());
						}
						reader.endArray();
					}
				}
				reader.endObject();
			}
		}
		reader.endObject();
	}

	public void serilization(){
		Response res = new Response();
		
		Map<String, App> map = new HashMap<String, App>();
		App app1 = new App();
		app1.setName("weixin");
		app1.setAge(10);
		app1.setMessage(new String[]{"social", "message"});
		
		App app2 = new App();
		app2.setName("QQ");
		app2.setAge(5);
		app2.setMessage(new String[]{"social", "money"});
		
		map.put("app1", app1);
		map.put("app2", app2);
		
		res.setDescriptor(map);
		
		Gson gson = new Gson();
		String json = gson.toJson(res, Response.class);
		System.out.println(json);
	}
	
	
	
	public static void main(String[] args) {
		// new GsonDemo().readJsonUsingMapping();
		/*try {
			new GsonDemo().readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		/*new GsonDemo().serilization();*/
		
		Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new MyExcludeStrategy(String.class)).serializeNulls().create();
		ExcludeFieldsObject object = new ExcludeFieldsObject();
		String json = gson.toJson(object);
		System.out.println(json);
	}
}
