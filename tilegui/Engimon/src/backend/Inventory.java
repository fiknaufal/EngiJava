package backend;

import java.util.ArrayList;

public class Inventory<T> {
    private ArrayList<T> list = new ArrayList<T>();

    public Inventory(){}

    public ArrayList<T> getArray(){
        return list;
    }

    public void add(T t){
        list.add(t);
    }

    public void removeAtIdx(int idx){
        list.remove(idx);
    }

    public int getSize(){
        return list.size();
    }

    public T getElement(int i){
        return list.get(i);
    }

}
