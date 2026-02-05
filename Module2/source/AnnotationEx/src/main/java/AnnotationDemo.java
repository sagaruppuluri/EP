import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

/*
An annotation helps to provide metadata information about the class, methods, fields etc. in a program.
It is defined by using the @interface keyword.

1. Marker Annotations: These annotations do not have any members. Example: @Override
2. Single-value Annotations: These annotations have only one member. Example: @SuppressWarnings("unchecked")
3. Multi-value Annotations: These annotations have more than one member. Example: @Author(name="A", company="company1")

With retention policy as RUNTIME, the annotation will be available at runtime for reflection. It is used when we want to access the annotation information during runtime.

Annotations are especially useful in frameworks like Spring and Hibernate, where they are used to configure and manage components, dependencies, and behaviors without the need for extensive XML configuration files.

*/

@Retention(RetentionPolicy.RUNTIME)
@interface Author {
    String name();
    String company();
}

@Author(name="A", company = "company1")
class DemoService {

    @Author(name="B", company = "company2")
    public String hello() {
        return "hello!!";
    }

    @Author(name="C", company = "company3")
    public String world() {
        return "world!!!";
    }
}


public class AnnotationDemo {
    public static void main(String[] args) {

        Class<DemoService> clazz = DemoService.class;

        Annotation[] annotations = clazz.getDeclaredAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation.annotationType().isAssignableFrom(Author.class)) {
                Author author = (Author) annotation;
                System.out.println("............................................................");
                System.out.println("DemoClass author name : " + author.name());
                System.out.println("DemoClass author company : " + author.company());
                System.out.println("............................................................");
            }
        }


        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Author.class)) {
                Author author = method.getAnnotation(Author.class);
                System.out.println("............................................................");
                System.out.println("Method name: " + method.getName());
                System.out.println("Method author: " + author.name());
                System.out.println("Method author company: " + author.company());
                System.out.println("............................................................");
            }
        }
    }
}
