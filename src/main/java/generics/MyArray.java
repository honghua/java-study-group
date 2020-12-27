package generics;


import java.util.Collections;


public class MyArray<T> {
    private T[] array;

    public MyArray(T[] array) {
        this.array = array;
    }


    public T get(int index) {
        return array[index];
    }

    // compare with MyArray<T>
    public static <T> MyArray create(T[] array) {
        return new MyArray(array);
    }

//    public static <E> MyArray<E> create(E... elements) {
//        return new MyArray<>(elements);
////        return new MyArray<E>((Object[]) elements); // check type in compile time
//
////        return new MyArray(elements);
////        return new MyArray((Object[]) elements); // no safety check in compile time
//    }

    public static void main(String[] args) {
        MyArray<String> testArray = new MyArray<String>(new String[]{});

        // 没有<> 的 create method，没有约束， 什么都可以装 ！！！
        MyArray<String> array = MyArray.create(new Person[]{});

        // 不贴标签，装什么都行
        MyArray array2 = new MyArray(new Integer[]{Integer.MAX_VALUE});

        // 贴标签 不负责 检查 原来瓶子里的东西
        MyArray<Object> myArray = (MyArray<Object>) array2;

        // runtime (i.e., 真的喝时) 才报错
        System.out.println(myArray.get(0)); // runtime error


        Collections.reverseOrder();
    }
}


