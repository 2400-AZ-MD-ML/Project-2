package Project2;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
public class ArrayStackTest {
    @Test
    public void evaluatePostfixTest1(){
        ArrayStack<String> test = new ArrayStack<String>();
        assertEquals("-10", test.evaluatePostfix("234-/5*"));
    }
    @Test
    public void evaluatePostfixTest2(){
        ArrayStack<String> test = new ArrayStack<String>();
        assertEquals("19", test.evaluatePostfix("4 52* +5 +"));
    }
    @Test
    public void evaluatePostfixTestError(){
        ArrayStack<String> test = new ArrayStack<String>();
        Assertions.assertThrows(IllegalStateException.class, ()-> {test.evaluatePostfix(" ");}, "Empty String");
    }
}
