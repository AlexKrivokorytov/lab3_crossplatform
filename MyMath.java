public class MyMath {
    static double L = Math.PI;

    static double Exp(double x, int N) {
        double s = 0, q = 1;
        for (int i = 0; i < N; i++) { s += q; q *= x / (i + 1); }
        return s + q;
    }
    static double Sin(double x, int N) {
        double s = 0, q = x;
        for (int i = 0; i < N; i++) { s += q; q *= (-1) * x * x / (2*i+2) / (2*i+3); }
        return s + q;
    }
    static double Cos(double x, int N) {
        double s = 0, q = 1;
        for (int i = 0; i < N; i++) { s += q; q *= (-1) * x * x / (2*i+1) / (2*i+2); }
        return s + q;
    }
    static double BesselJ(double x, int N) {
        double s = 0, q = 1;
        for (int i = 0; i < N; i++) { s += q; q *= (-1) * x * x / 4 / (i+1) / (i+1); }
        return s + q;
    }
    static double FourSin(double x, double[] a) {
        double s = 0;
        for (int i = 0; i < a.length; i++) s += a[i] * Math.sin(Math.PI * x * (i+1) / L);
        return s;
    }
    static double FourCos(double x, double[] a) {
        double s = 0;
        for (int i = 0; i < a.length; i++) s += a[i] * Math.cos(Math.PI * x * i / L);
        return s;
    }
}