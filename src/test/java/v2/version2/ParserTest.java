package v2.version2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Igor Ryabtsev on 17.12.2016.
 */
public class ParserTest {
    public static final String FILE_REGEX = "[а-яА-Я0-9—\\p{Punct}\\s]+";
    private StringBuilder s;
    private Parse parse;

    @Before
    public void setUp() throws Exception {
        parse = new Parser(FILE_REGEX);
        s = new StringBuilder("Иннополис - город высоких технологий ");
    }

    @Test
    public void parseWordsT() throws Exception {
        boolean result = parse.parseWords(s.toString());
        assertTrue(result);
    }
    @Test
    public void parseWordsF() throws Exception {
        boolean result = parse.parseWords(s.append("Innopolis").toString());
        assertFalse(result);
    }



}