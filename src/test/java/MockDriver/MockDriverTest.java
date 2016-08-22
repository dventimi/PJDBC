import java.sql.*;
import org.junit.*;
import org.pjdbc.drivers.*;
import static org.junit.Assert.*;

public class MockDriverTest {
    @Test
    public void loadable () {
	try {DriverManager.getDriver("jdbc:mock:foo");}
	catch (Exception e) {fail(e.getMessage());}}}
	
