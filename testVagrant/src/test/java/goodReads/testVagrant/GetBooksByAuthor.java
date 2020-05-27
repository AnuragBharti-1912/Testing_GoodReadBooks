package goodReads.testVagrant;

import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;
import files.resources;
import files.utilities;

public class GetBooksByAuthor {
	private static Logger log =LogManager.getLogger(GetBooksByAuthor.class.getName());

	public Properties prop;

	@BeforeTest
	public void InputFile() throws IOException {
		prop= new Properties();
		//System.getProperty("user.dir");
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\files\\file.properties");
		prop.load(fis);	

	}

	@Test(dataProvider="getData")
	public void byAuthor(String searchField, String AuthorName, String ResultPage) 
	{
		RestAssured.baseURI=prop.getProperty("URL");
		log.debug("URL is fetched");
		
		Response resp = given()
				.queryParam("key", prop.getProperty("key"))
				.queryParam("page", ResultPage)
				.queryParam("search[field]", searchField)
				.queryParam("q", AuthorName)
				.when()
				.get(resources.getDataResources())
				.then()
				.assertThat()
				.statusCode(200).and()
				.contentType(ContentType.XML)
				.extract().response();

		XmlPath response = utilities.rawToXML(resp);
		
		log.debug("response is extracted to XML from  raw");
		log.info(response.get("GoodreadsResponse.search.query"));
	}
	@DataProvider()
	public Object[][] getData() {
		Object[][] data=new Object[2][3];
		data[0][0]= "all";
		data[0][1]= "Paulo Coelho";
		data[0][2]= "1";
		data[1][0]=	"author";			
		data[1][1]= "Amish Tripathi";
		data[1][2]= "2";
		return data;

	}
}
