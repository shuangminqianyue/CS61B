public class NBody {
    /** 返回文件中宇宙的半径 */
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int num = in.readInt();
        return in.readDouble();
    }

    /** 返回文件中的所有行星 */
    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[num];
        for (int i = 0; i < num; i++) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xp, yp, xv, yv, m, img);
        }
        return planets;
    }

    public static void main(String[] args) {
        /* 收集所有输入 */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radius = readRadius(fileName);
        Planet[]  planets = readPlanets(fileName);

        /* 设置尺寸 */
        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);
        StdDraw.enableDoubleBuffering();

        double t = 0;
        int num = planets.length;
        while (t <= T) {
            double[] xForces = new double[num];
            double[] yForces = new double[num];
            for (int i = 0; i < num; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < num; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            /* 绘制背景 */
            StdDraw.picture(0, 0, "images/starfield.jpg");

            /* 绘制所有行星 */
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        /* 打印宇宙 */
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
