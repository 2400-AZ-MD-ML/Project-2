public class Manual {
    public static void main(String[] args){
        LinkedStack<String> stack = new LinkedStack<String>();
        String result = "";
        String input =  "a*b/(c-a)+d*e";
        result += "a";
        stack.push("*");
        result+= "b";
        result += stack.pop();
        stack.push("/");
        stack.push("(");
        result += "c";
        stack.push("-");
        result += "a";
        result += stack.pop();
        stack.pop();
        result += stack.pop();
        stack.push("+");
        result += "d";
        stack.push("*");
        result += "e";
        result += stack.pop();
        result += stack.pop();
        System.out.print("Infix: "+ input + "\n" + "Postfix: " + result);
    } 
}
