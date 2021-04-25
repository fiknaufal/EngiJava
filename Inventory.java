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
    public T getElement(T t){
        return list.get(list.indexOf(t));
    }
    public boolean isExist(T t){
        return list.contains(t);
    }
}
//    private:
//    vector<T> list;
//
//template <typename T>
//Inventory<T>::Inventory(){}
//
//        template <typename T>
//Inventory<T>::~Inventory(){}
//
//        template <typename T>
//vector<T>& Inventory<T>::getVector(){
//        return this->list;
//        }
//
//        template <typename T>
//void Inventory<T>::add(T t){
//        this->list.push_back(t);
//        }
//
//        template <typename T>
//void Inventory<T>::removeAtIdx(int n){
//        this->list.erase(this->list.begin()+n);
//        }
//
//        template <typename T>
//int Inventory<T>::getSize(){
//        return this->list.size();
//        }
//
//        template <typename T>
//T Inventory<T>::getElement(int i){
//        return list[i];
//        }
//
//        }
