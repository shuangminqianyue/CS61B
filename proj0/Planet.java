public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    /** 初始化Planet */
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** 接受一个 Planet 对象并初始化一个相同的 Planet 对象 */
    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    /** 接受单个行星，并应返回等于提供的行星与正在进行计算的行星之间的距离 */
    public double calcDistance(Planet p) {
        return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) +
                (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
    }

    /** 接受一个行星，并返回给定行星施加在该行星上的力 */
    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        return (G * this.mass * p.mass) / (r * r);
    }

    /** 返回x轴上的力 */
    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double r = calcDistance(p);
        double F = calcForceExertedBy(p);
        return (F * dx) / r;
    }

    /** 返回y轴上的力 */
    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        double r = calcDistance(p);
        double F = calcForceExertedBy(p);
        return (F * dy) / r;
    }

    /** 返回Planets中所有planet对当前行星的x轴力 */
    public double calcNetForceExertedByX(Planet[] planets) {
        double res = 0.0;
        for (Planet p : planets) {
            if (this.equals(p)) {
                continue;
            }
            res += calcForceExertedByX(p);
        }
        return res;
    }

    /** 返回Planets中所有planet对当前行星的y轴力 */
    public double calcNetForceExertedByY(Planet[] planets) {
        double res = 0.0;
        for (Planet p : planets) {
            if (this.equals(p)) {
                continue;
            }
            res += calcForceExertedByY(p);
        }
        return res;
    }

    /** 给定时间dt和xy轴的力，更新行星的位置 */
    public void update(double dt, double Fx, double Fy) {
        double ax = Fx / this.mass;
        double ay = Fy / this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    /** 绘制行星 */
    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
