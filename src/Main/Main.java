package Main;

import Main.Parsing.ASTIntersection;
import Main.Parsing.Parser;
import Main.Tokenize.DSLTokenizer;

import Main.Validation.*;
import simulation.IntersectionEvaluator;
import simulation.Simulation;
import simulation.Simulator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String input = null;
        try {
            input = Files.readString(Paths.get("src/input.txt"));
        } catch (IOException e) {
            System.out.println("Could not find input.txt");
        }
        DSLTokenizer tokenizer = new DSLTokenizer(input);
        tokenizer.tokenize();
        System.out.println("Done tokenizing");

        Parser parser = new Parser(tokenizer);
        ASTIntersection ast = (ASTIntersection) parser.parse();

        DSLValidator validator = new DSLValidator(ast);
        try {
            validator.validate();
        } catch (IncorrectNumberOfRoadsException | DuplicationDirectionException | ExceededMaxLanes | InvalidLaneException | InvalidStateConfigException e) {
            e.printStackTrace();
            return;
        }

        Simulation simulation = new Simulation();

        IntersectionEvaluator evaluator = new IntersectionEvaluator(simulation.getHandler());

        evaluator.evaluateIntersection(ast);

        Simulator simulator = new Simulator(simulation);

    }
}
