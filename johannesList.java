import java.util.Arrays;

public class johannesList <T>
{
    private T[] array;
    protected int size;
    public johannesList(){
        size = 0;
        array = (T[]) new Object[size+1];
    }
    protected void add(T value){
        size++;
        if(size > array.length){
            array = Arrays.copyOf(array, array.length + 1);
        }
        array[size-1] = value;
    }
    protected void add(int index, T value){
        size++;
        if(size > array.length){
            array = Arrays.copyOf(array, array.length + 1);
        }
        for(int i = size; i > index; i--){
            array[i-1] = array[i-2]; 
        }
        array[index] = value;
    }
    protected T get(int i)throws Exception{
        if(i >= array.length){
            System.out.println("Du Idiot");
            throw new IndexOutOfBoundsException("Idiot am Steuer, das wird teuer(Index out of bounds: " + i + ")");
        }
        return array[i];
    }
    protected void print(){
        for(T v : array){
            System.out.println(v);
        }
    }
    public void fill(){
        for(int i = 0; i < 5; i++){
            add((T) Integer.valueOf(i)); // wtf man kann nur Objekte casten. Wer ist Carsten?
        }
    }
}
