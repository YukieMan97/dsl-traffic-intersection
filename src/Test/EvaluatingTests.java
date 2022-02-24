import Main.Evaluating.Evaluator;
import Main.Parsing.*;
import Main.Tokenize.DSLTokenizer;
import org.junit.Test;


public class EvaluatingTests {
    private DSLTokenizer tokenizer;
    private Parser parser;
    private ASTNode result;
    private Evaluator evaluator;


    @Test
    public void testBarrenInput(){
        String barrenInput = "Intersection {Road-List {Road N{entry-lane 1 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road E{entry-lane 2 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road S{entry-lane 3 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road W{entry-lane 4 {[N,E],[all],Loop {duration: 10.2}}}},Lights " +
                "{Loop {duration: 1.0}}}";
        tokenizer = new DSLTokenizer(barrenInput);
        tokenizer.tokenize();
        parser = new Parser(tokenizer);
        result = parser.parse();
        evaluator = new Evaluator();
        result.accept(evaluator);
    }

    @Test
    public void testStateLoop(){
        String barrenInput = "Intersection {Road-List {Road N{entry-lane 1 {[N,E],[all],Loop {duration: 10.2}}}," +
                "Road E{entry-lane 2 {[N,E],[all],Loop {duration: 8.2}}}," +
                "Road S{entry-lane 3 {[N,E],[all],Loop {duration: 4.0}}}," +
                "Road W{entry-lane 4 {[N,E],[all],Loop {duration: 16.4}}}},Lights " +
                "{Loop states {state {green-roads: [N],red-roads: [S,W],pedestrians: {false},duration: 1.0}}}}";
        tokenizer = new DSLTokenizer(barrenInput);
        tokenizer.tokenize();
        parser = new Parser(tokenizer);
        result = parser.parse();
        evaluator = new Evaluator();
        result.accept(evaluator);
    }
}