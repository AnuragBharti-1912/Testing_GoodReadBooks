package files;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;

public class utilities {


	public static JsonPath rawToJson(Response resp) 
	{

		String respString=resp.asString();

		JsonPath js= new JsonPath(respString);
		return js;

	}
	
	public static XmlPath rawToXML(Response resp) 
	{

		String respString=resp.asString();

		XmlPath xp= new XmlPath(respString);
		return xp;

	}
}
