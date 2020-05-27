package goodReads.testVagrant;

import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.logging.log4j.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;
import files.resources;
import files.utilities;

public class GetBooksByAuthor {
	 private static Logger log =LogManager.getLogger(GetBooksByAuthor.class.getName());//logManager is an APi and getLogger is a function

	Properties prop;
	@BeforeTest
	public void InputFile() throws IOException {
		prop= new Properties();
		FileInputStream fis= new FileInputStream("C:\\Users\\Anurag Bharti\\eclipse-workspace\\testVagrant\\src\\test\\java\\files\\file.properties");
		prop.load(fis);	
		prop.getProperty("searchBy");
		System.out.println(prop.getProperty("searchBy"));
	}

	//@Test
	public void byAuthor() {
		//	RestAssured.baseURI="https://www.goodreads.com";
		RestAssured.baseURI=prop.getProperty("URL");
		//https://www.goodreads.com
		//	https://www.goodreads.com/search/index.xml?key=WjuLRmN9T3kyt5wMrCgJQ&page=1&search[field]=all&q=The Alchemist
		Response resp = given()
				.queryParam("key", prop.getProperty("key"))
				//.queryParam("key", "WjuLRmN9T3kyt5wMrCgJQ")
				.queryParam("page", prop.getProperty("ResultPage"))
				.queryParam("search[field]", prop.getProperty("searchBy"))
				.queryParam("q", prop.getProperty("authorName"))
				.when()
				//.get("/search/index.xml")
				.get(resources.getDataResources())
				.then()
				.assertThat()
				.statusCode(200).and()
				.contentType(ContentType.XML)
				.extract().response();

//		String respo= resp.asString();
//		System.out.println(respo);
//		XmlPath xml = new XmlPath(respo);
		XmlPath respon = utilities.rawToXML(resp);
		
		log.info("respon.get(\"GoodreadsResponse.search.query\")");
	}

	public static String GenerateStringFromResource(String path) throws IOException { //this is used for changing the body for xml(not used here)

		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
