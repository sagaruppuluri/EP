
class Base {
    public void f() {
        System.out.println("f() in Base");
    }

    public void g() {
        System.out.println("g() in Base");
    }
}

// Derived inherits the functionality from Base.

class Derived extends Base {

    // overrides the definition g()
    public void g() {
        System.out.println("g() in Derived");
    }

    public void h() {
        System.out.println("h() in Derived");
    }
}

public class OverridingEx1 {

    public static void main(String[] args) {

        Base b1 = new Base();
        b1.f(); // in Base
        b1.g(); // in Base

        System.out.println();

        Derived d1 = new Derived();
        d1.f(); // in Base
        d1.g(); // in Derived
        d1.h(); // in Derived

        // Here b2 is a reference variable of type Base
        // Object is of type Derived (RHS).

        Base b2 = new Derived();
        b2.f(); // in Base
        b2.g(); // in Derived because Object is of type Derived.
    }
}
