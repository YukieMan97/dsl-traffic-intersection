import Main.Tokenize.DSLTokenizer;
import org.junit.*;


public class TokenizerTests {
    private DSLTokenizer tokenizer;


@Test
public void testBarrenInput(){
    String barrenInput = "Intersection {Road-List {Road N{entry-lane 2 {[N,E],[all],Loop{duration: 10.2}}," +
            "Road N{entry-lane 2 {[N,E],[all],Loop{duration: 10.2}}," +
            "Road N{entry-lane 2 {[N,E],[all],Loop{duration: 10.2}}," +
            "Road N{entry-lane 2 {[N,E],[all],Loop{duration: 10.2}}}}, Lights " +
            "{Loop {duration: 1.0}}}";
    tokenizer = new DSLTokenizer(barrenInput);
    tokenizer.tokenize();
}

    @Test
    public void testComplexLightInput(){
        String complexInput = "Intersection {Road-List {Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}},Lights " +
                "{Loop states {state {green-roads: [N,E]}, state {red-roads: [W,S]}}";

        tokenizer = new DSLTokenizer(complexInput);
        tokenizer.tokenize();
        System.out.println("Test");
    }

    @Test
    public void testComplexLightInputParse(){
        String complexInput = "Intersection {Road-List {Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}},Lights " +
                "{Loop states {state {green-roads: [N,E]}, state {red-roads: [W,S]}}";

        tokenizer = new DSLTokenizer(complexInput);
        tokenizer.tokenize();
        System.out.println("Test");
    }


}