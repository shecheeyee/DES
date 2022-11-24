import java.util.List;

class Grid2D<T> {
    //private final List<T> list;
    private final ImList<T> imList;
    private final int numOfCols;

    Grid2D(List<T> list, int numOfCols) {
        ImList<T> imList = new ImList<T>(list);
        this.imList = imList;
        this.numOfCols = numOfCols;
    }

    Grid2D(int numOfCols) {
        this.numOfCols = numOfCols;
        ImList<T> imList = new ImList<T>();
        this.imList = imList;
    }

    Grid2D(ImList<T> imList, int numOfCols) {
        this.imList = imList;
        this.numOfCols = numOfCols;
    }

    @Override 
    public String toString() {
        String str = "{";
        if (imList.size() == 0) {
            return str + "}";
        }
        int x = this.numOfCols % this.imList.size();
        for (int i = 1; i < this.imList.size(); i++) {
            if (this.numOfCols == 1) {
                str += this.imList.get(i - 1);
                str += ";";
            } else {
                str += this.imList.get(i - 1);
                if (i % this.numOfCols == 0) {
                    str += ";";
                } else {
                    str += ",";
                }
            }
        }
        str += this.imList.get(this.imList.size() - 1);
        return str = str + "}";
    }

    public Grid2D<T> add(T elem) {    
        return new Grid2D<T>(this.imList.add(elem), this.numOfCols);
    }

    public T get(int x, int y) {
        int index = this.numOfCols * x + y;
        return this.imList.get(index);
    }

    public Grid2D<T> set(int x, int y, T z) {
        int index = this.numOfCols * x + y;
        return new Grid2D<T>(this.imList.set(index, z), this.numOfCols);
    }

    public int getNumOfCols() {
        return this.numOfCols;
    }

    public ImList<T> getList() {
        return this.imList;
    }

    public Grid2D<Integer> makeGrid(int n) {
        Grid2D<Integer> puzzleGrid = new Grid2D<Integer>(n);
        for (int i = 1;i < Math.pow(n, 2); i++) {
            puzzleGrid = puzzleGrid.add(i);
        }
        puzzleGrid = puzzleGrid.add(0);
        return puzzleGrid;
    }

}
            



