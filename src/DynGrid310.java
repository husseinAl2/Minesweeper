public class DynGrid310<T> {
	private DynArr310<DynArr310<T>> storage; ////underlying 2-d array for storage	
	
	public DynGrid310(){
		// create's an empty grid (no content)	
		this.storage = new DynArr310<>();
	}
	public int getNumRow() {
		// report's number of rows with contents in the grid
		return storage.size();
	}
	
	public int getNumCol() {
		// report's number of columns with contents in the grid
		if(storage.size() == 0) {
			return 0;
		}
		return storage.get(0).size();
	}
	
	public boolean isValidCell(int row, int col){
	// check's whether (row,col) corresponds to a cell with content
		if( (row < 0 || row > getNumRow()) || (col<0 || col > getNumCol()) || (storage.get(row).get(col) == null)) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public T get(int row, int col){
		// report's cell value at (row, col)

		if(row <0 || row >= storage.size()){
			throw new IndexOutOfBoundsException("Index(" + row + "," + col + ") out of bounds!");
		}

		DynArr310<T> rowArray = storage.get(row);
		if(rowArray.size() <= col || col < 0){
			throw new IndexOutOfBoundsException("Index(" + row + "," + col + ") out of bounds!");
		}

		T val = rowArray.get(col);
		if(val == null){
			throw new IndexOutOfBoundsException("Index(" + row + "," + col + ") out of bounds!");
		}else{
			return storage.get(row).get(col);
		}
	}
	
	public T set(int row, int col, T value){
		// change's cell value at (row, col) to be value, and return's the old cell value
		T holder = null;
		if(storage.get(row).get(col) == null) {
			throw new IllegalArgumentException("Null values not accepted");
		}
		else {
			holder = storage.get(row).get(col);
			storage.get(row).set(col, value);
		}
		
		return holder;
	}
	
	public boolean addRow(int index, DynArr310<T> newRow){
		// insert's newRow into the grid at index, shifting rows if needed
		// a new row can be appended
		
		if(newRow == null || newRow.size() == 0) {
			return false;
		}
		
		if(index<0 || index > getNumRow()) {
			return false;
		}
		
		if(storage.size()== 0 && index==0) {
			storage.add(newRow);
			return true;
		}
		
		if(newRow.size() != getNumCol()) {
			return false;
		}
		
		if(index == getNumRow()) {
			storage.add(newRow);
		}else {
			storage.insert(index, newRow);
		}
		
		return true;
		
	}
	
	public boolean addCol(int index, DynArr310<T> newCol){
		// insert's newCol as a new column into the grid at index, shifting cols if needed
		// a new column can be appended
		
		if(index < 0 || index > getNumCol()){
			return false;
		}
		if(newCol == null || newCol.size()==0) {
			return false;
		}
		if(newCol.size() != getNumRow()) {
			return false;
		}
		
		for(int i = 0; i < getNumRow();i++) {
			DynArr310<T> row = storage.get(i);
			row.insert(index, newCol.get(i));
		}
		
		return true;
	}
	
	public DynArr310<T> removeRow(int index){
		// remove's and return's a row at index, shift rows as needed to remove the gap		
				
		if(index < 0|| index > getNumRow()) {
			return null;
		}
		DynArr310<T> holderRow = storage.get(index);
		storage.remove(index);
	
		return holderRow;
	}
	public DynArr310<T> removeCol(int index){
		// remove's and return's a column at index, shift cols as needed to remove the gap
		if(index<0 || index >= getNumCol()) {
			return null;
		}
		DynArr310<T> removedCol = new DynArr310<>();
		for(int i = 0; i< getNumRow();i++) {
			T val = storage.get(i).remove(index);
			removedCol.add(val);
			if(storage.get(i).size() == 0) {
				storage.remove(i);
				i--;
			}
		}
		return removedCol;
		
	}
	
	
	@Override
	public String toString(){
		if(getNumRow() == 0 || getNumCol() == 0 ){ return "empty board"; }
	StringBuilder sb = new StringBuilder();
	for(int i=0; i<getNumRow(); i++){
		sb.append("|");
		for (int j=0;j<getNumCol(); j++){
			sb.append(get(i,j).toString());
		 sb.append("|");
		}
		sb.append("\n");
	}
	return sb.toString().trim();
	}

	//Testing code below this line
	public static void main(String[] args){
		//Sample tests
		DynGrid310<String> sgrid = new DynGrid310<>();
		
		//prepare one row to add
		DynArr310<String> srow = new DynArr310<>();
		srow.add("English");
		srow.add("Spanish");
		srow.add("German");
		
		
		//addRow and checking
		if (sgrid.getNumRow() == 0 && sgrid.getNumCol() == 0 && !sgrid.addRow(1,srow)
			&& sgrid.addRow(0,srow) && sgrid.getNumRow() == 1 && sgrid.getNumCol() == 3){
			System.out.println("Yay 1");
		}
		
		//get, set, isValidCell
		if (sgrid.get(0,0).equals("English") && sgrid.set(0,1,"Espano").equals("Spanish")
			&& sgrid.get(0,1).equals("Espano") && sgrid.isValidCell(0,0)
			&& !sgrid.isValidCell(-1,0) && !sgrid.isValidCell(3,2)) {
			System.out.println("Yay 2");
		}
		
		
		
		//a grid of integers
		DynGrid310<Integer> igrid = new DynGrid310<Integer>();
		boolean ok = true;
		//add some rows (and implicitly some columns)
		for (int i=0; i<3; i++){
			DynArr310<Integer> irow = new DynArr310<>();
			irow.add((i+1) * 10);
			irow.add((i+1) * 11);
			ok = ok && igrid.addRow(igrid.getNumRow(),irow);
		}
		
		//toString
		//System.out.println(igrid);
		if (ok && igrid.toString().equals("|10|11|\n|20|22|\n|30|33|")){
			System.out.println("Yay 3");		
		}
				
		//prepare a column
		DynArr310<Integer> icol = new DynArr310<>();
		
		//add two rows
		icol.add(-10);
		icol.add(-20);
		
		//attempt to add, should fail
		ok = igrid.addCol(1,icol);
		
		//expand column to three rows
		icol.add(-30);
		
		//addCol and checking
		if (!ok && !igrid.addCol(1,null) && igrid.addCol(1,icol) &&
			igrid.getNumRow() == 3 && igrid.getNumCol() == 3){
			System.out.println("Yay 4");		
		}
		
		
		//removeRow
		if (igrid.removeRow(5) == null &&
			igrid.removeRow(1).toString().equals("[20, -20, 22]") &&
			igrid.getNumRow() == 2 && igrid.getNumCol() == 3 ){
			System.out.println("Yay 5");	
		}
		
		//removeCol
		if (igrid.removeCol(0).toString().equals("[10, 30]") &&
			igrid.removeCol(1).toString().equals("[11, 33]") &&
			igrid.removeCol(0).toString().equals("[-10, -30]") &&
			igrid.getNumRow() == 0 && igrid.getNumCol() == 0 ){
			System.out.println("Yay 6");	
		}
		
				
	}
	
}