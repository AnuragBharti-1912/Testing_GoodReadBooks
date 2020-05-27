package goodReads.testVagrant;

import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.logging.log4j.*;
import org.testng.annotations.BeforeTest;
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
		FileInputStream fis= new FileInputStream("C:\\Users\\Anurag Bharti\\git\\testVagrant\\testVagrant\\src\\test\\java\\files\\file.properties");
		prop.load(fis);	
	
	}

	@Test
	public void byAuthor() {
		RestAssured.baseURI=prop.getProperty("URL");
		Response resp = given()
				.queryParam("key", prop.getProperty("key"))
				.queryParam("page", prop.getProperty("ResultPage"))
				.queryParam("search[field]", prop.getProperty("searchField"))
				.queryParam("q", prop.getProperty("AuthorName"))
				.when()
				.get(resources.getDataResources())
				.then()
				.assertThat()
				.statusCode(200).and()
				.contentType(ContentType.XML)
				.extract().response();

		XmlPath response = utilities.rawToXML(resp);

		log.debug(response.get("GoodreadsResponse.search.query"));
	}
}
