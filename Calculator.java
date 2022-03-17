package Project2;

public class Calculator {
    public static void main(String[] args){
        LinkedStack<String> result =  new LinkedStack<String>();
        System.out.println(result.converttoPostFix(" a *b/( c - a)+d*e "));
        ArrayStack<String> test2 =  new ArrayStack<String>();
        System.out.println(test2.evaluatePostfix("23*42-/56*+"));
     }
}
