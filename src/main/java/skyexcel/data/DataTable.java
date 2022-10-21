package skyexcel.data;

/***
 * <p>ㅁ  ㅁ  ㅁ</p>
 * <p>ㅁ  ㅁ  ㅁ</p>
 * <p>ㅁ  ㅁ  ㅁ</p>
 * 위와 같은 형식으로 데이터를 저장해 주는 클래스 이다.
 * 열과 행의 갯수를 지정 할 수 있고, 모든 오브젝트도 등록 가능하다.
 * 열을 통해 행을 찾고, 오브젝트를 불러 올 수 있다.
 *
 * 특정 행과 행을 합쳐 다른 행으로 옮길 수 있고, 열 이동도 가능하다.
 *
 *
 */
public class DataTable {

    private Column column;

    private Row row;


    public DataTable() {
        row = new Row(0);
        this.column = new Column(0);
    }


    public DataTable(int column, int row) {
        this.row = new Row(row);
        this.column = new Column(column);
    }

    public void find() {

    }

    public void moveTo() {

    }

    public void delete() {

    }

    public static class Column {
        private int size;

        private int column;

        public Column(int column) {
            this.column = column;
        }


        public int getSize() {
            return size;
        }
    }

    public static class Row {
        private int size;

        private int row;

        public Row(int row) {
            this.row = row;
        }

        public int getSize() {
            return size;
        }
    }

    public Row getRow() {
        return row;
    }

    public Column getColumn() {
        return column;
    }
}
