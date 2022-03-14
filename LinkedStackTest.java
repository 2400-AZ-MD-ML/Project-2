package Project2;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
public class LinkedStackTest {
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
