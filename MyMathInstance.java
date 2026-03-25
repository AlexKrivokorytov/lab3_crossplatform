// Task 7: MyMath without static - needs an object: new MyMathInstance().Sin(x, N)
// Difference: field L belongs to the instance, so every object can have its own L value
public class MyMathInstance {
    double L = Math.PI;

    double Exp(double x, int N) {
        double s = 0, q = 1;
        for (int i = 0; i < N; i++) { s += q; q *= x / (i+1); }
        return s + q;
    }
    double Sin(double x, int N) {
        double s = 0, q = x;
        for (int i = 0; i < N; i++) { s += q; q *= (-1) * x * x / (2*i+2) / (2*i+3); }
        return s + q;
    }
    double Cos(double x, int N) {
        double s = 0, q = 1;
        for (int i = 0; i < N; i++) { s += q; q *= (-1) * x * x / (2*i+1) / (2*i+2); }
        return s + q;
    }
    double BesselJ(double x, int N) {
        double s = 0, q = 1;
        for (int i = 0; i < N; i++) { s += q; q *= (-1) * x * x / 4 / (i+1) / (i+1); }
        return s + q;
    }
    double FourSin(double x, double[] a) {
        double s = 0;
        for (int i = 0; i < a.length; i++) s += a[i] * Math.sin(Math.PI * x * (i+1) / L);
        return s;
    }
    double FourCos(double x, double[] a) {
        double s = 0;
        for (int i = 0; i < a.length; i++) s += a[i] * Math.cos(Math.PI * x * i / L);
        return s;
    }
}
