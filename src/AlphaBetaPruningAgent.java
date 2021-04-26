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
        return 0;
    }

    private double maxValue(Board state, int depth, double alpha, double beta) {
        return 0;
    }
}
