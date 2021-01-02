public class Planet {
    static final double g = 6.67e-11;

    public double xxPos, yyPos;
    public double xxVel, yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet other) {
        double dx = this.xxPos - other.xxPos;
        double dy = this.yyPos - other.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet other) {
        double r = this.calcDistance(other);
        return g * other.mass * this.mass / (r * r);
    }

    public double calcForceExertedByX(Planet other) {
        double dx = other.xxPos - this.xxPos;
        return this.calcForceExertedBy(other) * dx / this.calcDistance(other);
    }

    public double calcForceExertedByY(Planet other) {
        double dy = other.yyPos - this.yyPos;
        return this.calcForceExertedBy(other) * dy / this.calcDistance(other);
    }

    public double calcNetForceExertedByX(Planet[] others) {
        double net = 0.0;
        for (Planet other: others) {
            if (!this.equals(other)) {
                net += calcForceExertedByX(other);
            }
        }
        return net;
    }

    public double calcNetForceExertedByY(Planet[] others) {
        double net = 0.0;
        for (Planet other: others) {
            if (!this.equals(other)) {
                net += calcForceExertedByY(other);
            }
        }
        return net;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += aX * dt;
        yyVel += aY * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }
}