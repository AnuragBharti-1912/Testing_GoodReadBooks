package goodReads.testVagrant;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CarLoan {

	@Test
	public void monthLoanCar() {
		System.out.println("monthly loan car");
		
	}
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("print everytime before method");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("print everytime after method");
	}
	@Test
	public void yearlyLoanCar() {
		System.out.println("yearly loan car");
	}
		
     @BeforeTest
		public void oneYearBefore() {
			System.out.println("before test I am");
		
	}
	@Test
	public void halfyearloanCar() {
		System.out.println("half yearly loan car");
		
	}
}
