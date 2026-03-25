public class Vehicle {
    private final String owner, brand, model, color;
    private final int year;
    private double x, y, direction, speed, fuel;

    public Vehicle(String owner, String brand, String model, int year, String color) {
        this.owner = (owner == null || owner.isBlank()) ? "Unknown" : owner;
        this.brand = brand; this.model = model;
        this.year  = year;  this.color = color;
    }
    public Vehicle(String owner, String brand, String model) { this(owner, brand, model, 2020, "White"); }

    public String getOwner()     { return owner; }
    public String getBrand()     { return brand; }
    public String getModel()     { return model; }
    public int    getYear()      { return year;  }
    public String getColor()     { return color; }
    public double getX()         { return x; }
    public double getY()         { return y; }
    public double getDirection() { return direction; }
    public double getSpeed()     { return speed; }
    public double getFuel()      { return fuel; }

    public void setDirection(double d) { direction = ((d % 360) + 360) % 360; }
    public void setSpeed(double s) {
        if (s < 0) System.out.println("[ERROR] Speed cannot be negative!");
        else speed = s;
    }

    public void addFuel(double amount) {
        if (amount <= 0) { System.out.println("[ERROR] Fuel must be positive!"); return; }
        fuel += amount;
        System.out.printf("  +%.1f L | Tank: %.1f L%n", amount, fuel);
    }

    public void drive(double hours) {
        if (speed <= 0) { System.out.println("[ERROR] Set speed > 0 first!"); return; }
        double dist = speed * hours, need = dist * 0.08;
        if (need > fuel) { System.out.println("[ERROR] Not enough fuel! Need: " + need + " L"); return; }
        x += dist * Math.cos(Math.toRadians(direction));
        y += dist * Math.sin(Math.toRadians(direction));
        fuel -= need;
        System.out.printf("  %.1f km | (%.2f, %.2f) | Tank: %.2f L%n", dist, x, y, fuel);
    }

    public void turn(double deg) {
        setDirection(direction + deg);
        System.out.printf("  Turn %.0f deg | Direction: %.0f deg%n", deg, direction);
    }

    public void stop() { speed = 0; System.out.println("  Stopped."); }

    public double distanceFromOrigin() { return Math.sqrt(x * x + y * y); }

    @Override
    public String toString() {
        return String.format("%d %s %s %s | Owner: %s | (%.2f,%.2f) | %.0f deg | %.1f km/h | %.2f L",
                year, color, brand, model, owner, x, y, direction, speed, fuel);
    }
}
