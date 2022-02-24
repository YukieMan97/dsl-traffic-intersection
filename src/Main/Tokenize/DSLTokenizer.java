package Main.Tokenize;

public class DSLTokenizer {
    private String input;
    private String[] tokens;
    private int currentToken = 0;

    private static final String[] CONSTANT_LITERALS = {"Intersection ", "Roads", "Lights ", "Loop ","Road-List ",
            "entry-lane ","exit-lane ", "N","E","S","W","Crosswalk ", "{","}","]","[",
            "cars", "buses", "trucks", "bikes","all", "duration: ", "state ","states ",
            "green-roads: ", "red-roads: ", ",","."
    };

    //Code from tinyHTML in lecture 2
    public DSLTokenizer(String input) {
        this.input = input;
    }
    //simple tokenizer implementation
    public void tokenize(){
        input = input.replace("\n", "");
        for(String s : CONSTANT_LITERALS) {
            input = input.replace(s, "_" + s + "_");
        }
        input = input.replace("__", "_");
        if((input.length() > 0) && (input.charAt(0) == '_')) {
            input = input.substring(1);
        }
        tokens = input.split("_");
        int j = 0;
        System.out.println(tokens.toString());
    }

    //Code from tinyHTML in lecture 2
    private String checkNext(){
        String token="";
        if (currentToken<tokens.length){
            token = tokens[currentToken];
        }
        else
            token="NO_MORE_TOKENS";
        return token;
    }

    //Code from tinyHTML in lecture 2
    public String getNext(){
        String token="";
        if (currentToken<tokens.length){
            token = tokens[currentToken];
            currentToken++;
        }
        else
            token="NULLTOKEN";
        return token;
    }

    //Code from tinyHTML in lecture 2
    public boolean checkToken(String regexp){
        String s = checkNext();
        System.out.println("comparing: |"+s+"|  to  |"+regexp+"|");
        return (s.matches(regexp));
    }



    //Code from tinyHTML in lecture 2
    public String getAndCheckNext(String regexp){
        String s = getNext();
        if (!s.matches(regexp)) {
            throw new RuntimeException("Unexpected next token for Parsing! Expected something matching: " + regexp + " but got: " + s);
        }
        System.out.println("matched: "+s+"  to  "+regexp);
        return s;
    }


    public boolean moreTokens(){
        return currentToken<tokens.length;
    }









}