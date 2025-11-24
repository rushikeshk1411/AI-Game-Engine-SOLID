package utils;

import java.util.function.Supplier;

public class Util {

    public static Object getIfNull(Object obj, Supplier<Object> supplier){
        if (obj == null) return supplier.get();
        else return obj;
    }
}
