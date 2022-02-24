import Main.Parsing.*;
import Main.Tokenize.DSLTokenizer;
import org.junit.Test;

public class ParsingTests {
    private DSLTokenizer tokenizer;
    private Parser parser;
    private ASTNode result;

    @Test
    public void testBarrenInput(){
        String barrenInput = "Intersection {Road-List {Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}},Lights " +
                "{Loop {duration: 1.0}}}";
        tokenizer = new DSLTokenizer(barrenInput);
        tokenizer.tokenize();
        parser = new Parser(tokenizer);
        result = parser.parse();
        System.out.println(result);
    }

    @Test
    public void testComplexLightInput(){
        String complexInput = "Intersection {Road-List {Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}},Lights " +
                "{Loop states {state {green-roads: [N,E],red-roads: [S,W],duration: 10.2}}}}";

        tokenizer = new DSLTokenizer(complexInput);
        tokenizer.tokenize();
        parser = new Parser(tokenizer);
        result = parser.parse();
    }

    /*@Test
    public void testMultipleLaneAndCrosswalkRoads() {
        String multipleLane = "Intersection {Road-List {Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}," +
                "exit-lane 4,entry-lane 3 {[W,S],[buses,trucks,bikes,cars],Loop {duration: 9.0}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}},Lights " +
                "{Loop states {state {green-roads: [N,E],red-roads: [S,W],pedestrians: {false},duration: 10.2}}}}";

        tokenizer = new DSLTokenizer(multipleLane);
        tokenizer.tokenize();
        parser = new Parser(tokenizer);
        result = parser.parse();

    }*/


    @Test
    public void testMultipleStateLoop() {
        String multipleLane = "Intersection {Road-List {Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}," +
                "exit-lane 4,entry-lane 3 {[W,S],[buses,trucks,bikes,cars],Loop {duration: 9.0}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}},Lights " +
                "{Loop states {state {green-roads: [N,E],red-roads: [S,W],duration: 10.2}," +
                "state {green-roads: [S,W],red-roads: [N,E],duration: 9.6}" +
                "}}}";

        tokenizer = new DSLTokenizer(multipleLane);
        tokenizer.tokenize();
        parser = new Parser(tokenizer);
        result = parser.parse();

    }
   /* @Test
    public void testSingleCrosswalk() {
        String multipleLane = "Intersection {Road-List {Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}," +
                "exit-lane 4,entry-lane 3 {[W,S],[buses,trucks,bikes,cars],Loop {duration: 9.0}},Crosswalk {Loop {duration: 10.2}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}},Lights " +
                "{Loop states {state {green-roads: [N,E],red-roads: [S,W],pedestrians: {false},duration: 10.2}," +
                "state {green-roads: [S,W],red-roads: [N,E],pedestrians: {true},duration: 9.6}" +
                "}}}";

        tokenizer = new DSLTokenizer(multipleLane);
        tokenizer.tokenize();
        parser = new Parser(tokenizer);
        result = parser.parse();

    }*/

  /*  @Test
    public void testMultipleCrosswalk() {
        String multipleLane = "Intersection {Road-List {Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}," +
                "exit-lane 4,entry-lane 3 {[W,S],[buses,trucks,bikes,cars],Loop {duration: 9.0}},Crosswalk {Loop {duration: 10.2}},Crosswalk {Loop {duration: 10.2}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road N{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}},Lights " +
                "{Loop states {state {green-roads: [N,E],red-roads: [S,W],pedestrians: {false},duration: 10.2}," +
                "state {green-roads: [S,W],red-roads: [N,E],pedestrians: {true},duration: 9.6}" +
                "}}}";

        tokenizer = new DSLTokenizer(multipleLane);
        tokenizer.tokenize();
        parser = new Parser(tokenizer);
        result = parser.parse();

    }*/


}