package Project2;


import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
public class CalculatorTest {
    @Test
    public void evaluatePostfixTest1(){
        ResizeableArrayStack<String> test = new ResizeableArrayStack<String>();
        assertEquals(-10.0, test.evaluatePostfix("234-/5*"), Math.abs(-10.0-test.evaluatePostfix("234-/5*")));
    }
    @Test
    public void evaluatePostfixTest2(){
        ResizeableArrayStack<String> test = new ResizeableArrayStack<String>();
        assertEquals(19.0, test.evaluatePostfix("4 52* +5 +"),Math.abs(19.0-test.evaluatePostfix("4 52* +5 +")));
    }
    @Test
    public void evaluatePostfixTestError(){
        ResizeableArrayStack<String> test = new ResizeableArrayStack<String>();
        Assertions.assertThrows(IllegalStateException.class, ()-> {test.evaluatePostfix(" ");}, "Empty String");
    }
    @Test
    public void converttoPostFixTest1(){
       LinkedStack<String> test=  new LinkedStack<String>();
       assertEquals("ab*ca-/de*+", test.converttoPostFix("a *b/( c - a)+d*e "));
    }
    @Test
    public void converttoPostFixError(){
       LinkedStack<String> test=  new LinkedStack<String>();
       Assertions.assertThrows(IllegalStateException.class, ()-> {test.converttoPostFix(" ");}, "Empty String");
    }
    @Test
    public void converttoPostFixTest2(){
        LinkedStack<String> test = new LinkedStack<String>();
        assertEquals("ab/cde-+*", test.converttoPostFix("a/b*(c+(d-e))"));
    }

}
