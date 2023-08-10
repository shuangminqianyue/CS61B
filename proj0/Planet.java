/* The Planet Class and its constructor */

public class Planet {

    /* Basic Properties of Planet. */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /* The value of the gravitational constant G. */
    public static final double G = 6.67e-11;

    /* One method of constructing a Planet. */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /* Another method of constructing a Planet. */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /* Calculate the distance between this Planet and given Planet . */
    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        double r_squre = dx * dx + dy * dy;
        double r = Math.sqrt(r_squre);
        return r;
    }

    /* Calculate the force exerted on this planet by the given planet. */
    public double calcForceExertedBy(Planet p) {
        double r = p.calcDistance(this);
        double F = G * (p.mass * this.mass) / (r * r);
        return F;
    }

    /* Calculate the force exerted in the X direction. */
    public double calcForceExertedByX(Planet p) {
        double F = p.calcForceExertedBy(this);
        double dx = p.xxPos - this.xxPos;
        double r = p.calcDistance(this);
        double Fx = F * dx / r;
        return Fx;
    }

    /* Calculate the force exerted in the Y direction. */
    public double calcForceExertedByY(Planet p) {
        double F = p.calcForceExertedBy(this);
        double dy = p.yyPos - this.yyPos;
        double r = p.calcDistance(this);
        double Fy = F * dy / r;
        return Fy;
    }

    /**
     * Take in an array of Planets and calculate the net X force exerted
     * by all planets in that array upon the current Planet.
     */
    public double calcNetForceExertedByX(Planet[] all) {
        double netfx = 0.0;
        for (Planet p : all) {
            if (!this.equals(p)) {
                netfx += this.calcForceExertedByX(p);
            }
        }
        return netfx;
    }

    /**
     * Take in an array of Planets and calculate net Y force exerted
     * by all planets in that array upon the current Planet.
     */
    public double calcNetForceExertedByY(Planet[] all) {
        double netfy = 0.0;
        for (Planet p : all) {
            if (!this.equals(p)) {
                netfy += this.calcForceExertedByY(p);
            }
        }
        return netfy;
    }

    /**
     * Take in a small period of time dt, the forces exerted on the planet
     * both in X and Y directions,
     * and update the planet’s position and velocity instance variables.
     */
    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        double new_vx = this.xxVel + dt * ax;
        double new_vy = this.yyVel + dt * ay;
        this.xxVel = new_vx;
        this.yyVel = new_vy;
        double new_px = this.xxPos + dt * this.xxVel;
        double new_py = this.yyPos + dt * this.yyVel;
        this.xxPos = new_px;
        this.yyPos = new_py;

    }

    /* Draw one Planet. */
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
