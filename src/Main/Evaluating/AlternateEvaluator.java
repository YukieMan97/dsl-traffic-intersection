//package Main.Evaluating;
//
//import Main.Parsing.*;
//import simulation.IntersectionGenerator;
//import simulation.objects.IntersectionObject;
//
//import java.util.ArrayList;
//
//public class AlternateEvaluator {
//    private int roadN_entry = 0;
//    private int roadN_exit  = 0;
//    private int roadS_entry = 0;
//    private int roadS_exit  = 0;
//    private int roadW_entry = 0;
//    private int roadW_exit  = 0;
//    private int roadE_entry = 0;
//    private int roadE_exit  = 0;
//    private IntersectionGenerator generator;
//
//    public IntersectionObject evaluateIntersection(ASTIntersection intersection) {
//        ArrayList<ASTRoad> roads = intersection.getRoadList().getRoads();
//        for (ASTRoad r : roads) {
//            String dir = r.getDirection().getCardinal();
//            ArrayList<ASTLane> lanes = r.getLanes();
//            for (ASTLane l : lanes) {
//                if (l instanceof ASTEntryLane) {
//                    countEntryLane(dir);
//                } else {
//                    countExitLane(dir);
//                }
//            }
//        }
//        IntersectionObject finalIntersection =  generator.generateIntersection(roadN_entry,roadN_exit,
//                roadS_entry,roadS_exit,roadW_entry,roadW_exit,roadE_entry,roadE_exit);
//        return finalIntersection;
//
//    }
//
//    public void countEntryLane(String dir) {
//        switch(dir) {
//            case "N":
//                roadN_entry ++;
//                break;
//            case "E":
//                roadE_entry++;
//                break;
//            case "S":
//                roadS_entry++;
//                break;
//            case "W":
//                roadW_entry++;
//                break;
//        }
//    }
//
//    public void countExitLane(String dir) {
//        switch(dir) {
//            case "N":
//                roadN_exit ++;
//                break;
//            case "E":
//                roadE_exit++;
//                break;
//            case "S":
//                roadS_exit++;
//                break;
//            case "W":
//                roadW_exit++;
//                break;
//        }
//    }
//}
