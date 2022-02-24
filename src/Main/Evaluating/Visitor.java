package Main.Evaluating;

import Main.Parsing.*;

public interface Visitor<T> {
    T visit(ASTBoolean astBoolean);
    T visit(ASTCrosswalk astCrosswalk);
    T visit(ASTDecimal astDecimal);
    T visit(ASTDirection astDirection);
    T visit(ASTDirectionList astDirectionList);
    T visit(ASTDuration astDuration);
    T visit(ASTEntryLane astEntryLane);
    T visit(ASTExitLane astExitLane);
    T visit(ASTGreenRoads astGreenRoads);
    T visit(ASTIntersection astIntersection);
    T visit(ASTLight astLight);
    T visit(ASTNode astNode);
    T visit(ASTNum astNum);
    T visit(ASTPedestrians astPedestrians);
    T visit(ASTRedRoads astRedRoads);
    T visit(ASTRoad astRoad);
    T visit(ASTRoadList astRoadList);
    T visit(ASTSingleLoop astSingleLoop);
    T visit(ASTState astState);
    T visit(ASTStateConfig astStateConfig);
    T visit(ASTStateLoop astStateLoop);
    T visit(ASTVehicle astVehicle);
    T visit(ASTVehicleList astVehicleList);
}
