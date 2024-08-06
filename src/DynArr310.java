public class DynArr310<T> {
    private T[] storage;    //underlying array
    private static final int MINCAP = 2;    //default initial capacity / minimum capacity

    private int size = 0;
    private int initCap;
            
    @SuppressWarnings("unchecked")
    public DynArr310(){
        //constructor
        //initial capacity of the array is MINCAP
        this.initCap = MINCAP;
        storage = (T[]) new Object[MINCAP]; /*Generic Casting Object array with capacity of MINCAP*/
    }
    @SuppressWarnings("unchecked")
    public DynArr310(int initCap){
        // Initial capacity of the storage should be initCap.
        this.initCap = initCap;

        if(initCap < MINCAP) {
            throw new IllegalArgumentException("Capacity must be at least 2!");
        }
        storage = (T[])new Object[initCap];            
    }
    
    public int size() { 
        //reports current number of elements
        return size; 
    }   
    
    public int capacity() {
        //reports max number of elements
        return initCap;
    }
    
    
    public T set(int index, T value) {
        // Replace's the item at the given index to be the given value.
        // Return's the old item at that index.
        // Note: New items cannot be added with this method.
        
        T holder = null;
        //Makes sure that the index is valid
        if(index < 0 || index >=size) {
            throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
        }
        //Setting holder to old value, than replacing the item at given index to be the given value
        if(value != null){
            holder = storage[index];
            storage[index] = value;
        }
        //If the value parameter is null, than an illegalArgument Exception is thrown
        else {
            throw new IllegalArgumentException("Null values not accepted!");
        }

        return holder;//default return, remove/change as needed
        
    }
    public T get(int index){
        // Return's the item at the given index
        
        if(index < 0 || index >=size) {
            throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
        }
                
        return storage[index];
    }
    public void add(T value){
        // Append's an element to the end of the storage.     
        // Double's the capacity if no space available.
        
        if(value == null) {
            throw new IllegalArgumentException("Null values not accepted!");
        }
        //Doubles the size of the array if its full
        //Makes sure that the capacity never grows beyond the Max Value(Max Capacity)
        if(size == storage.length && (storage.length*2) <= Integer.MAX_VALUE) {
            expand(storage);
        }
        storage[size] = value;
        size++;
        
    }


    private T[] expand(T[] storage){
        initCap*=2;
        @SuppressWarnings("unchecked")
		T[] newStorage = (T[]) new Object[size*2];
            
        for(int i = 0; i<size; i++) {
            newStorage[i] = storage[i];
        }
            
        this.storage = newStorage;
        return storage;
    }
    
    public void insert(int index, T value){
        // Insert's the given value at the given index and shift's elements if needed.
        // If no space available, we grow the storage in the same way as required by add().
        
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
        }
        if(value == null) {
            throw new IllegalArgumentException("Null values not accepted!");
        }else if(size == storage.length){
            expand(storage);
        }
        int i = index;
        T holder = null;
        while(storage[i] != null){
            holder = storage[i];
            storage[i] = value;
            value = holder;
            i++;
        }
        storage[i] = value;
        size++;
                
    }
    
    
    public T remove(int index){
        // Remove's and return's the element at the given index. Shift's elements
        
        // If the number of elements after removal falls below or at 1/3 of the capacity,
        // We halve capacity (rounding down) of the storage.
        // However, capacity does not go below MINCAP.
    
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException("Index: \" + index + \" out of bounds!");
        }
        int i = index;
        T holder = storage[i];
        T next = null;
        while(i+1 < size){
            next = storage[i+1];
            storage[i] = next;
            i++;
        }
        storage[i] = null;
        size--;
        if(size <= (initCap/3) && (initCap/2) >= MINCAP){
            halve(storage);
        }
        return holder;
                        
    }

    private void halve(T[] storage){
        initCap /=2;
        @SuppressWarnings("unchecked")
		T[] newStorage = (T[]) new Object[initCap];
        for(int i = 0; i<size;i++){
            newStorage[i] = storage[i];
        }
        this.storage = newStorage; 
    }
    


    @Override
    public String toString() {
        
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < size(); i++) {
            s.append(get(i));
            if (i<size()-1)
                s.append(", ");
        }
        s.append("]");
        return s.toString().trim();
        
    }
    
    //Code Testing Below this line

    public String toStringDebug() {
        //This method is written for debugging purposes
        //it prints out the DynArr310 details for easy viewing
        StringBuilder s = new StringBuilder("DynArr310 with " + size()
            + " items and a capacity of " + capacity() + ":");
        for (int i = 0; i < size(); i++) {
            s.append("\n ["+i+"]: " + get(i));
        }
        return s.toString().trim();
        
    }
    
    public static void main (String args[]){
        //Sample tests
        DynArr310<Integer> ida = new DynArr310<>();
        if ((ida.size() == 0) && (ida.capacity() == 2)){
            System.out.println("Yay 1");
        }
        //add some numbers at the end
        for (int i=0; i<3; i++)
            ida.add(i*5);
        //uncomment to check details
        // System.out.println(ida);
        
        //checking dynamic array details
        if (ida.size() == 3 && ida.get(2) == 10 && ida.capacity() == 4){
            System.out.println("Yay 2");
        }
        
        //insert, set, get
        ida.insert(1,-10);
        ida.insert(4,100);
        if (ida.set(1,-20) == -10 && ida.get(2) == 5 && ida.size() == 5
            && ida.capacity() == 8 ){
            System.out.println("Yay 3");
        }
        
        //creating a DynArr310 of strings
        DynArr310<String> letters = new DynArr310<>(6);
        
        //inserting some strings
        letters.insert(0,"c");
        letters.insert(0,"a");
        letters.insert(1,"b");
        letters.insert(3,"z");
        
        //get, toString()
        if (letters.get(0).equals("a") && letters.toString().equals("[a, b, c, z]")){
            System.out.println("Yay 4");
        }
        
        //remove
        if (letters.remove(0).equals("a") && letters.remove(1).equals("c") &&
            letters.get(1).equals("z") && letters.size()==2 && letters.capacity()==3){
            System.out.println("Yay 5");            
        }
        //exception checking
        try{
            letters.set(-1,null);
        }
        catch (IndexOutOfBoundsException ex){
            if (ex.getMessage().equals("Index: -1 out of bounds!")){
                System.out.println("Yay 6");            
            }
        }
        
    }
}