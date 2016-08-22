import java.sql.*;
import org.junit.*;
import org.pjdbc.drivers.*;
import static org.junit.Assert.*;

public class CatDriverTest {
    @Test
    public void acceptsURL () {
	try {
	    assertFalse(new CatDriver().acceptsURL("jdbc:cat"));
	    assertFalse(new CatDriver().acceptsURL("jdbc:cat:"));
	    assertFalse(new CatDriver().acceptsURL("jdbc:cat:foo"));
	    assertTrue(new CatDriver().acceptsURL("jdbc:cat:jdbc:mock:foo"));}
	catch (Exception e) {
	    fail(e.getMessage());}}}
