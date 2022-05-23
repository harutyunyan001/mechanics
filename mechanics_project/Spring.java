package mechanics_project;


import java.util.ArrayList;
import java.util.Objects;

    public class Spring {

        private double n = 1;

        public Spring() { }

        public Spring(double k) { this.n = k; }

        private void setN(double n) {
            this.n = n;
        }

        public double getN() { return n; }

        public double[] move(double t, double dt, double x0, double v0) {
            ArrayList<Double> coordinatesFullSize = fullCoordinatesComb(null,0, t, dt, x0, v0 );
            return coordinatesFullSize.stream().mapToDouble(i -> i).toArray();
        }

        public double[] move(double t, double dt, double x0) {
            ArrayList<Double> coordinatesFullSize = fullCoordinatesComb(null,0, t, dt, x0, 0 );
            return coordinatesFullSize.stream().mapToDouble(i -> i).toArray();
        }

        public double[] move(double t0, double t1, double dt, double x0, double v0) {
            ArrayList<Double> coordinatesFullSize = fullCoordinatesComb(null, t0, t1, dt, x0, v0 );
            return coordinatesFullSize.stream().mapToDouble(i -> i).toArray();
        }

        public double[] move(double t0, double t1, double dt, double x0, double v0, double m) {
            ArrayList<Double> coordinatesFullSize = fullCoordinatesComb(m, t0, t1, dt, x0, v0);
            return  coordinatesFullSize.stream().mapToDouble(i -> i).toArray();
        }

        public Spring inSeries(Spring that) {
            double newDouble = (this.n * that.n) / (this.n + that.n);
            return new Spring(newDouble);
        }

        public Spring inParallel(Spring that) {
            double newDouble = this.n + that.n;
            return new Spring(newDouble);
        }


        private ArrayList<Double> fullCoordinatesComb( Double unitMass, double t0, double t1,
                                                       double dt, double x0, double v0) {
            double m = unitMass == null ? 1 : unitMass;
            double omega = Math.sqrt(n / m);

            double c1 = x0;
            double c2 = v0 / omega;

            double currentTime = t0;
            ArrayList<Double> points = new ArrayList<>();

            while (currentTime < t1) {
                 double coordinate = (c1 * Math.cos(omega * currentTime)
                         + c2 * Math.sin(omega * currentTime));
                 points.add(coordinate);
                currentTime += dt;
            }
            return points;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Spring spring = (Spring) o;
            return Double.compare(spring.n, n) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(n);
        }

        @Override
        public String toString() {
            return "Spring{" + "k=" + n + '}';
        }
    }
