package core.wrapper;

public class CommonContainer<T> {

    T object = null;

    public void set(T object) {
        this.object = object;
    }

    public T get() {
        return this.object;
    }
}
