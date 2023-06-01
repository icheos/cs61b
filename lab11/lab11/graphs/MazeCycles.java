package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    boolean targetFound = false;
    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        int s = maze.xyTo1D(1, 1);
        int t = maze.xyTo1D(maze.N(), maze.N());
        dfs(s, t);
    }

    // Helper methods go here
    private void dfs(int start, int end) {
        marked[start] = true;
        announce();
        if (start == end) {
            targetFound = true;
            announce();
            return;
        }
        if (detectCircle(start)) {
            System.out.println("detect the circle");
            return;
        }
        for (int w : maze.adj(start)) {
            if (!marked[w]) {
                edgeTo[w] = start;
                announce();
                distTo[w] = distTo[start] + 1;
                dfs(w, end);
                if (targetFound) {
                    return;
                }
            }
        }


    }

    private boolean detectCircle(int start) {
        for (int w : maze.adj(start)) {
            if (marked[w] && edgeTo[w] == start) {
                return true;
            }
        }
        return false;
    }

}

