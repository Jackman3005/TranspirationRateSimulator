package plot;

/**
 * @author: Levi
 * @version: 2/15/14
 */
public class LineBuilder {
    private double dwv, ast, gst, gias, gleaf, gbl, gttal, cias, cair, dc, jwv, wl, cwv;
    private static final double DIAS = 6 * Math.pow(10, -5);
    private static final double GC = 0.001;
    private static final double HIAS = 1.0;
    private static final double MP = 0.4470;
    private double waterloss;
    private SimulationParameters indVariable;

    public LineBuilder(SimulationParameters indVariable, double leafWidth, double leafArea, double stomaDensity,
                       double stomaRadius, double stomaDepth, double temperature, double humidity, double windspeed) {

        this.indVariable = indVariable;

        // These only rely on user input for calculations
        this.dwv = (2.13*(10^-5))*(Math.pow((temperature+273)/273,1.8));
        this.ast = Math.PI*(Math.pow(Math.pow(stomaRadius*10,-6),2));
        this.cwv = 2.848 + (0.1082 * temperature) + ((3.283 * Math.pow(10, -4)) * Math.pow(temperature, 2))
                + ((2.06 * Math.pow(10, -5)) * Math.pow(temperature, 3));
        this.gbl = dwv /(4*Math.pow((leafWidth/100)/((windspeed+0.00001) * MP),0.5));

        // These rely on the above
        this.gst = (dwv * (stomaDensity * Math.pow(10, 6)) * ast) / ((stomaDepth * Math.pow(10, -6))
                + (stomaRadius * Math.pow(10, -6)));
        this.cias = cwv * HIAS;
        this.cair = (humidity / 100) * cwv;
        this.gias = dwv / DIAS;

        // These rely on each other, in order
        this.gleaf = ((gias * gst)/(gias + gst)) + GC;
        this.gttal = (gleaf * gbl) / (gleaf + gbl);
        this.dc = cias - cair;
        this.jwv = gttal * dc * 18;
        this.wl = (jwv * (leafArea * Math.pow(10,-4)) * 3.6 * Math.pow(10,3));

        // Waterloss for 100-leaf plant
        this.waterloss = 100 * wl;

    }



}
