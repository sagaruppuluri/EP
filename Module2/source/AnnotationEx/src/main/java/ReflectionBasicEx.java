import java.lang.reflect.Method;

class Sample {
    public void hello() {
        System.out.println("Hello World!!!");
    }
}

public class ReflectionBasicEx {
    public static void main(String[] args) throws Exception {

//        Sample s = new Sample();
//        s.hello();

        Class<Sample> clazz = Sample.class;
        Object obj = clazz.getDeclaredConstructor().newInstance();
        Method m = clazz.getDeclaredMethod("hello");
        m.invoke(obj);
    }
}
