class Puzzle {
    private final int n;
    private final Grid2D<Integer> grid;

    Puzzle(int n) {
        this.n = n;
        this.grid = new Grid2D<Integer>(this.n).makeGrid(this.n);
    }

    Puzzle(Grid2D<Integer> grid, int n) {
        this.n = n;
        this.grid = grid;
    }

    @Override
    public String toString() {
        String str = "";
        str += "\n";
        for (int i = 0; i < grid.getList().size() - 1; i++) {
            if ((i + 1) % this.n == 0) {
                if (grid.getList().get(i) == 0) {
                    str += ".";
                } else {
                    str += grid.getList().get(i);
                }    
                str += "\n";
            } else {
                if (grid.getList().get(i) == 0) {
                    str += ".";
                } else {
                    str += grid.getList().get(i);
                }
                str += " ";
            }
        }
        if (grid.getList().get(grid.getList().size() - 1) == 0) {
            str += ".";
        } else {
            str += grid.getList().get(grid.getList().size() - 1);
        }
        return str;
    }

    public Puzzle move(int o) {
        Grid2D<Integer> puzzle = this.grid;
        int index = getIndex(this.grid, o);
        int zeroIndex = getIndex(this.grid, 0);
        int cols = puzzle.getNumOfCols();
        int zx = getX(zeroIndex, cols);
        int zy = getY(zeroIndex, cols);
        int x = getX(index, cols);
        int y = getY(index, cols);
        
        if (Math.abs((zx - x)) == 1 && zy == y) {
            puzzle = puzzle.set(zx, zy, o);
            puzzle = puzzle.set(x, y, 0);
            return new Puzzle(puzzle, this.n);
        } else if (Math.abs((zy - y)) == 1 && zx == x) {
            puzzle = puzzle.set(zx, zy, o);
            puzzle = puzzle.set(x, y, 0);
        }
        return new Puzzle(puzzle, this.n);

    }

    public int getIndex(Grid2D<Integer> grid, int p) { //get index of ?
        for (int i = 0; i < grid.getList().size(); i++) {
            if (grid.getList().get(i) == p) {
                return i;
            }
        }
        return -1; //won't reach here
    }

    public int getX(int k, int cols) {
        return k / cols;
    }

    public int getY(int k, int cols) {
        return (k % cols);
    }

    public boolean isSolved() {
        int x = 1;
        if (this.grid.getList().get(this.grid.getList().size() - 1) != 0) {
            return false;
        } else {
            for (int i = 0; i < this.grid.getList().size() - 1; i++) {
                if (this.grid.getList().get(i) != x) {
                    return false;
                } else {
                    x += 1;
                }
            }
            return true;
        }
    }
}



