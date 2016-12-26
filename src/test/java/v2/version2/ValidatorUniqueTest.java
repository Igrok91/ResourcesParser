package v2.version2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Igor Ryabtsev on 17.12.2016.
 */
public class ValidatorUniqueTest {

    private StringBuilder s;

    private Validator validator;
    @Before
    public void setUp() throws Exception {
        validator = new ValidatorUnique();
        s = new StringBuilder("Иннополис - город высоких технологий ");
    }

    @Test
    public void validateT() throws Exception {
        boolean result = validator.validate(s.toString());
        assertTrue(result);
    }

    @Test
    public void validateF() throws Exception {
        boolean result = validator.validate(s.append("иннополис").toString());
        assertFalse(result);
    }

}