public class GridPath {
  private int[][] grid;

  public GridPath(int[][] g) {
    grid = g;
  }

  public Location getNextLoc(int r, int c) {
    int row;
    int col;
    
    if (r == grid.length-1) {
      row = r;
      col = c+1;
    }
    
    else if (c == grid[0].length-1) {
      row = r+1;
      col = c;
    }

    else if (grid[r+1][c] < grid[r][c+1]) {
      row = r+1;
      col = c;
    }

    else {
      row = r;
      col = c+1;
    }

    return new Location(row,col);
  }

  public int sumPath(int r, int c) {
    int sum = grid[r][c];
    int row=r;
    int col=c;
    Location l;
    
    while(row != grid.length-1 && col != grid[0].length-1) {
      l = getNextLoc(row,col);
      row = l.getRow();
      col = l.getCol();
      sum += grid[row][col];
    }
    return sum + grid[grid.length-1][grid[0].length-1];
  }
}
