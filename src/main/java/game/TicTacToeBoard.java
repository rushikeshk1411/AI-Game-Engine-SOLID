package game;

public class TicTacToeBoard implements Board {
    String[][] cells = new String[3][3];

    public String getCell(int rowIndex, int colIndex) {
        return cells[rowIndex][colIndex];
    }

    public void setCell(String symbol, Cell cell) {
        cells[cell.rowIndex][cell.colIndex] = symbol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int rowIndex=0; rowIndex<3; rowIndex++){
            for(int colIndex=0; colIndex<3; colIndex++){
                if(cells[rowIndex][colIndex] != null)
                    sb.append(cells[rowIndex][colIndex] + " ");
                else
                    sb.append("- ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void move(Move move) {
        setCell(move.getPlayer().playerSymbol, move.getCell());
    }

    @Override
    public TicTacToeBoard copy(){

    }
}
