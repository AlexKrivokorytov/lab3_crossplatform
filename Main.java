public class Main {

    static void sep(String t) { System.out.println("\n=== " + t + " ==="); }

    public static void main(String[] args) {

        // ---- TASK 1: Vehicle ------------------------------------------------
        sep("TASK 1 - Vehicle");

        Vehicle car = new Vehicle("Oleksii", "Toyota", "Camry", 2022, "Blue");
        car.addFuel(50);
        car.addFuel(-5);          // validation
        car.setSpeed(90);
        car.setDirection(45);
        car.drive(1.0);
        car.turn(90);
        car.drive(0.5);
        System.out.println(car);
        System.out.printf("Distance from (0,0): %.2f km%n", car.distanceFromOrigin());
        car.stop();

        Vehicle car2 = new Vehicle("", "BMW", "X5"); // empty owner
        System.out.println("Owner: " + car2.getOwner()); // Unknown
        car2.drive(1.0);          // no fuel
        car2.setSpeed(-30);       // negative speed

        // ---- TASK 2: Polynom - all methods ---------------------------------
        sep("TASK 2 - Polynom");

        Polynom P = new Polynom(new double[]{1, 2, 3}); // 1 + 2x + 3x^2
        Polynom Q = new Polynom(new double[]{2, 1});    // 2 + x

        System.out.print("P: "); P.show();
        System.out.print("Q: "); Q.show();
        System.out.print("P+Q: "); P.plus(Q).show();
        System.out.print("P-Q: "); P.minus(Q).show();
        System.out.print("P*Q: "); P.prod(Q).show();
        System.out.print("P*2: "); P.prod(2.0).show();
        System.out.print("P/2: "); P.div(2.0).show();
        System.out.print("P': ");  P.diff().show();
        System.out.print("P'': "); P.diff(2).show();
        P.show(2);                                      // P(2) = 17
        System.out.print("null: "); new Polynom(null).show();

        // ---- TASK 3: static methods ----------------------------------------
        sep("TASK 3 - Static methods of Polynom");
        System.out.print("add:        "); Polynom.add(P, Q).show();
        System.out.print("sub:        "); Polynom.sub(P, Q).show();
        System.out.print("multiply:   "); Polynom.multiply(P, Q).show();
        System.out.print("derivative: "); Polynom.derivative(P, 1).show();
        System.out.println("evaluate P(2): " + Polynom.evaluate(P, 2));

        // ---- TASK 4: new static methods ------------------------------------
        sep("TASK 4 - New static methods of Polynom");
        System.out.print("integrate(P): ");    Polynom.integrate(P).show();
        System.out.printf("Integral_0^1 P(x) dx = %.4f  (expected 3.0)%n",
                Polynom.definiteIntegral(P, 0, 1));
        System.out.printf("norm(P) = %.4f  (expected %.4f)%n", Polynom.norm(P), Math.sqrt(14));
        System.out.print("fromRoots(1,-1,2): "); Polynom.fromRoots(new double[]{1, -1, 2}).show();

        // ---- TASK 5: MyMath -------------------------------------------------
        sep("TASK 5 - MyMath (static)");
        System.out.printf("Exp(1,50)    = %.10f | reference: %.10f%n", MyMath.Exp(1,50), Math.exp(1));
        System.out.printf("Sin(pi/4,50) = %.10f | reference: %.10f%n", MyMath.Sin(Math.PI/4,50), Math.sin(Math.PI/4));
        System.out.printf("Cos(pi/4,50) = %.10f | reference: %.10f%n", MyMath.Cos(Math.PI/4,50), Math.cos(Math.PI/4));
        double[] fa = {1.0, 1.0/3, 1.0/5};
        System.out.printf("FourSin(0.5) = %.6f%n", MyMath.FourSin(0.5, fa));
        System.out.printf("FourCos(0.5) = %.6f%n", MyMath.FourCos(0.5, fa));

        // ---- TASK 6: BesselJ(mu1) ~ 0 --------------------------------------
        sep("TASK 6 - BesselJ(2.404825558) ~ 0");
        double mu1 = 2.404825558;
        for (int n : new int[]{5, 10, 20, 50})
            System.out.printf("N=%2d: %+.2e  %s%n", n, MyMath.BesselJ(mu1, n),
                    Math.abs(MyMath.BesselJ(mu1, n)) < 1e-6 ? "~0 OK" : "");

        // ---- TASK 7: MyMathInstance (non-static) ---------------------------
        sep("TASK 7 - MyMathInstance (methods without static)");
        // static  -> MyMath.Sin(x, N)             - call by class name
        MyMathInstance m1 = new MyMathInstance();        // L = pi
        MyMathInstance m2 = new MyMathInstance();
        m2.L = 2 * Math.PI;                             // its own L for each object

        System.out.printf("m1.Exp(1,50)       = %.10f%n", m1.Exp(1, 50));
        System.out.printf("m1.BesselJ(mu1,50) = %+.2e (~0)%n", m1.BesselJ(mu1, 50));
        System.out.printf("m1.FourSin(0.5) L=pi   = %.6f%n", m1.FourSin(0.5, fa));
        System.out.printf("m2.FourSin(0.5) L=2pi  = %.6f  <- different result!%n", m2.FourSin(0.5, fa));
    }
}
