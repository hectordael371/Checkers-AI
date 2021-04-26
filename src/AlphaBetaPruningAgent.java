import java.util.ArrayList;
import java.util.Random;

public class AlphaBetaPruningAgent {

    private int player;
    private int depth;

    public AlphaBetaPruningAgent(int depth){
        this.depth = depth;
        this.player = 1;
    }

    public AlphaBetaPruningAgent(int depth, int player){
        this.depth = depth;
        this.player = player;
    }

    public Board move(Board state){
        Move action =  makeDecision(state);
        System.out.println(action.printMove());
        state.performMove(action);
        return state;
    }

    private Move makeDecision(Board state){
        return null;
    }

    private double minValue(Board state, int depth, double alpha, double beta) {
        //Check for Terminal condition. If met, return heuristic evaluation value.
        if(depth == 0 || state.isGameOver())
            return state.heuristic();

        double value = Double.POSITIVE_INFINITY;
        for(Move action: state.getLegalMoves()){
            Board nextState = new Board(state);
            nextState.performMove(action);

            value = Math.min(value, maxValue(nextState, depth-1, alpha, beta));
            if(value <= alpha)
                //Prune Beta
                return value;

            beta = Math.min(beta, value);
        }

        return value;
    }

    private double maxValue(Board state, int depth, double alpha, double beta) {
        //Check for Terminal condition. If met, return heuristic evaluation value.
        if(depth == 0 || state.isGameOver())
            return state.heuristic();

        double value = Double.NEGATIVE_INFINITY;
        for(Move action: state.getLegalMoves()){
            Board nextState = new Board(state);
            nextState.performMove(action);

            value = Math.max(value, minValue(nextState, depth-1, alpha, beta));
            if(value >= beta)
                // Prune Alpha
                return value;

            alpha = Math.max(alpha, value);
        }

        return value;
    }
}
