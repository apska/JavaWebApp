package lesson05;

import com.github.apska.webapp.WebAppException;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by APS2
 * on 16.02.2016
 */
public class CalculatorTest {

    static Calculator calc;
    static {
        calc = new Calculator();
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test(expected = Error.class)
    public void testAbs() throws Exception {
        Assert.assertEquals(6, calc.abs(-5));
        throw new WebAppException("");
    }
}