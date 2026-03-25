public class Polynom {
    private int n;
    private double[] a;

    void set(double[] a) {
        if (a == null || a.length == 0) { this.n = 1; this.a = new double[]{0}; return; }
        this.n = a.length; this.a = new double[n];
        for (int i = 0; i < n; i++) this.a[i] = a[i];
    }
    void set(int n, double z) { this.n = n; this.a = new double[n]; for (int i = 0; i < n; i++) this.a[i] = z; }
    void set(int n) { set(n, 0); }

    Polynom(double[] a) { set(a); }
    Polynom(int n, double z) { set(n, z); }
    Polynom(int n) { set(n); }

    double value(double x) {
        double z = 0, q = 1;
        for (int i = 0; i < n; i++) { z += a[i] * q; q *= x; }
        return z;
    }

    void show() {
        System.out.print("Degree of x:\t");
        for (int i = 0; i < n - 1; i++) System.out.print(" " + i + "\t");
        System.out.println(" " + (n - 1));
        System.out.print("Coefficient:\t");
        for (int i = 0; i < n - 1; i++) System.out.print(a[i] + "\t");
        System.out.println(a[n - 1]);
    }
    void show(double x) { System.out.println("x=" + x + " | P(x)=" + value(x)); }

    Polynom diff() {
        Polynom t = new Polynom(n - 1);
        for (int i = 0; i < n - 1; i++) t.a[i] = a[i + 1] * (i + 1);
        return t;
    }
    Polynom diff(int k) {
        if (k >= n) return new Polynom(1);
        return k > 0 ? diff().diff(k - 1) : new Polynom(a);
    }

    Polynom plus(Polynom Q) {
        Polynom t = n >= Q.n ? new Polynom(a) : new Polynom(Q.a);
        Polynom s = n >= Q.n ? Q : this;
        for (int i = 0; i < s.n; i++) t.a[i] += s.a[i];
        return t;
    }
    Polynom minus(Polynom Q)  { return plus(Q.prod(-1)); }
    Polynom div(double z)     { return prod(1.0 / z); }
    Polynom prod(double z)    { Polynom t = new Polynom(a); for (int i = 0; i < t.n; i++) t.a[i] *= z; return t; }
    Polynom prod(Polynom Q) {
        Polynom t = new Polynom(n + Q.n - 1);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < Q.n; j++) t.a[i + j] += a[i] * Q.a[j];
        return t;
    }

    // Task 3: static versions
    static Polynom   add(Polynom P, Polynom Q)       { return P.plus(Q); }
    static Polynom   sub(Polynom P, Polynom Q)       { return P.minus(Q); }
    static Polynom   multiply(Polynom P, Polynom Q)  { return P.prod(Q); }
    static Polynom   derivative(Polynom P, int k)    { return P.diff(k); }
    static double    evaluate(Polynom P, double x)   { return P.value(x); }

    // Task 4: new static methods
    static Polynom integrate(Polynom P) {
        Polynom t = new Polynom(P.n + 1);
        for (int i = 0; i < P.n; i++) t.a[i + 1] = P.a[i] / (i + 1);
        return t;
    }
    static double definiteIntegral(Polynom P, double from, double to) {
        Polynom I = integrate(P); return I.value(to) - I.value(from);
    }
    static double norm(Polynom P) {
        double s = 0; for (double c : P.a) s += c * c; return Math.sqrt(s);
    }
    static Polynom fromRoots(double[] roots) {
        Polynom r = new Polynom(new double[]{1.0});
        for (double root : roots) r = r.prod(new Polynom(new double[]{-root, 1.0}));
        return r;
    }
}
