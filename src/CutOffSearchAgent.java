import java.util.ArrayList;
import java.util.Random;

public class CutOffSearchAgent {

    private int player;
    private int depth;
    private int cutOff;

    public CutOffSearchAgent(int depth){
        this.depth = depth;
        this.player = 1;
        this.cutOff = 5;
    }

    public CutOffSearchAgent(int depth, int player, int cutOff){
        this.depth = depth;
        this.player = player;
        this.cutOff = cutOff;
    }

    public Board move(Board state){
        Move action =  makeDecision(state);
        System.out.println(action.printMove());
        state.performMove(action);
        return state;
    }

    private Move makeDecision(Board state){
        double resultValue = Double.NEGATIVE_INFINITY;
        int player = 1;
        Move result = new Move();
        ArrayList<Move> actions = state.getLegalMoves();
        for(Move action: actions){
            Board nextState =  new Board(state);
            nextState.performMove(action);
            double value = minValue(nextState, depth, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY );  //changed
//            nextState.displayBoard();
            if (value > resultValue) {  //changed
                result = action;
                resultValue = value;
            }
        }
        System.out.println("Heuristic val(AI is winning if +ve): "+Double.toString(resultValue));
        return result;

    }

    private double minValue(Board state, int depth, double alpha, double beta) {
        if(depth <= this.cutOff) {
            return state.evaluation(); //change
        }
        double value = Double.POSITIVE_INFINITY;
        for(Move action: state.getLegalMoves()){
            Board nextState =  new Board(state);
            nextState.performMove(action);
            value = Math.min(value, maxValue(
                    nextState,
                    depth-1,
                    alpha,
                    beta
            ));
        }
        return value;
    }

    private double maxValue(Board state, int depth, double alpha, double beta) {
        if(depth <= this.cutOff) {
            return state.evaluation(); //change
        }
        double value = Double.NEGATIVE_INFINITY;
        for(Move action: state.getLegalMoves()){
            Board nextState =  new Board(state);
            nextState.performMove(action);
            value = Math.max(value, minValue(
                    nextState,
                    depth-1,
                    alpha,
                    beta
            ));
        }
        return value;
    }
}
