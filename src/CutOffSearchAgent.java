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
        Move action =  miniMaxDecision(state);
        System.out.println(action.printMove());
        state.performMove(action);
        return state;
    }

    private boolean cutOffTest(Board state, int depth) {
        if(depth <= this.cutOff || state.isGameOver())
            return true;

        return false;
    }

    private double eval(Board state) {
        return state.eval();
    }

    private ArrayList<Move> actions(Board state) {
        return state.getLegalMoves();
    }

    private Board result(Board state, Move action) {
        state.performMove(action);
        return state;
    }

    private Move miniMaxDecision(Board state){
        double resultVal = Double.NEGATIVE_INFINITY;
        Move result = new Move();

        for(Move action: actions(state)){
            Board nextState = new Board(state);

            double value = minValue(result(nextState, action), depth);

            if(value > resultVal) {
                result = action;
                resultVal = value;
            }
        }
        System.out.println("Evaluation value is: " + Double.toString(resultVal));
        return result;

    }

    private double minValue(Board state, int depth) {
        if(cutOffTest(state, depth)){
            return eval(state); //change
        }
        double value = Double.POSITIVE_INFINITY;
        for(Move action: actions(state)) {
            Board nextState = new Board(state);

            value = Math.min(value,
                    maxValue(result(nextState, action), depth - 1));
        }
        return value;
    }

    private double maxValue(Board state, int depth) {
        if(cutOffTest(state, depth)){
            return eval(state); //change
        }
        double value = Double.NEGATIVE_INFINITY;
        for(Move action: actions(state)) {
            Board nextState = new Board(state);

            value = Math.max(value,
                    minValue(result(nextState, action), depth - 1));
        }
        return value;
    }
}
