import java.util.ArrayList;

public class AlphaBetaSearchAgent {

    private int player;
    private int depth;

    public AlphaBetaSearchAgent(int depth){
        this.depth = depth;
        this.player = 1;
    }

    public AlphaBetaSearchAgent(int depth, int player){
        this.depth = depth;
        this.player = player;
    }

    public Board move(Board state){
        Move action = AlphaBetaSearch(state);
        System.out.println(action.printMove());

        state.performMove(action);
        return state;
    }

    private boolean terminalTest(Board state, int depth) {
        if(depth == 0 || state.isGameOver())
            return true;

        return false;
    }

    private double utility(Board state) {
        return state.evaluation();
    }

    private ArrayList<Move> actions(Board state) {
        return state.getLegalMoves();
    }

    private Board result(Board state, Move action) {
        state.performMove(action);
        return state;
    }

    private Move AlphaBetaSearch(Board state){
        double resultVal = player == 1 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
        Move result = new Move();

        for(Move action: actions(state)){
            Board nextState = new Board(state, this.player);

            double value = 0;
            if(player == 1) {
                value = maxValue(result(nextState, action),
                        depth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

                if(value >= resultVal) {
                    result = action;
                    resultVal = value;
                }
            } else {
                value = minValue(result(nextState, action),
                        depth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);

                if(value <= resultVal) {
                    result = action;
                    resultVal = value;
                }
            }
        }
        System.out.println("Heuristic value is: " + Double.toString(resultVal));
        return result;
    }

    private double maxValue(Board state, int depth, double alpha, double beta) {
        //Check for Terminal condition. If met, return heuristic evaluation value.
        if(terminalTest(state, depth))
            return utility(state);

        double value = Double.NEGATIVE_INFINITY;
        for(Move action: actions(state)){
            Board nextState = new Board(state, this.player);

            value = Math.max(value,
                    minValue(result(nextState, action),
              depth-1, alpha, beta));

            if(value >= beta)
                return value;

            alpha = Math.max(alpha, value);
        }

        return value;
    }

    private double minValue(Board state, int depth, double alpha, double beta) {
        //Check for Terminal condition. If met, return heuristic evaluation value.
        if(terminalTest(state, depth))
            return utility(state);

        double value = Double.POSITIVE_INFINITY;
        for(Move action: actions(state)){
            Board nextState = new Board(state, this.player);

            value = Math.min(value,
                    maxValue(result(nextState, action),
            depth-1, alpha, beta));

            if(value <= alpha)
                return value;

            beta = Math.min(beta, value);
        }

        return value;
    }
}
