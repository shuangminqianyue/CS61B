/**
 * The goal of this class is to simulate a universe
 * specified in one of the data files.
 */
public class NBody {

    /**
     * Given a file name, it will return a double corresponding to the radius of
     * the universe in that file.
     */
    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt();
        double radius = in.readDouble();
        in.close();
        return radius;
    }

    /**
     * Given a file name, it will return an array of Planets corresponding to the
     * planets in the file.
     */
    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int numPlanets = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[numPlanets];
        for (int i = 0; i < numPlanets; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        in.close();
        return planets;
    }

    public static void main(String[] args) {

        /* Collect input from command line arguments. */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        /* Read planets and universe radius from the file. */
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);

        /* Set up the canvas size and scale. */
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();

        double time = 0;

        while (time < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            /* Calculate net forces for each planet. */
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            /* Update each planet's position, velocity, and acceleration. */
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            /* Draw the background image. */
            StdDraw.picture(0, 0, "images/starfield.jpg");

            /* Draw each planet. */
            for (Planet p : planets) {
                p.draw();
            }

            /* Show the offscreen buffer. */
            StdDraw.show();

            /* Pause for a short time. */
            StdDraw.pause(10);

            /* Increase time by dt. */
            time += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
