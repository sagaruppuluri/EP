
class BasicCalc {

    public int add(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }
}

// Inheritance
// is a
class ScientificCalc extends BasicCalc {

    public double sin(int deg) {
        double rad = deg * 3.14159 / 180;
        return Math.sin(rad);
    }
}

public class InheritanceEx1 {

    public static void main(String[] args) {

        BasicCalc bcalc1 = new BasicCalc();

        // Through BasicCalc reference
        // we can call add and sub methods.

        System.out.println(bcalc1.add(10, 20));
        System.out.println(bcalc1.sub(10, 20));

        ScientificCalc sCalc1 = new ScientificCalc();

        // Through ScientificCalc reference
        // we can call add, sub and sin methods.

        System.out.println(sCalc1.add(10, 20));
        System.out.println(sCalc1.sub(10, 20));
        System.out.println(sCalc1.sin(90));


        BasicCalc bcalc2 = new ScientificCalc();

        // Through BasicCalc reference
        // we can call only add and sub methods though the actual object is of ScientificCalc.

        System.out.println(bcalc2.add(10, 20));
        System.out.println(bcalc2.sub(10, 20));
    }
}