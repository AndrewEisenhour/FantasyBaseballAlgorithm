public class Batter {
    public String id;
    public String name;
    public double avg;
    public double runs;
    public double rbis;
    public double hrs;
    public double sbs;
    public int[] positions;
    public double abs;
    public double hs;
    // public double total;

    public Batter(String _id, String _name, double d, double e, double f, double g, double _avg, int[] _positions, double _abs, double _hs) {
        name = _name;
        runs = d;
        rbis = e;
        hrs = f;
        sbs = g;
        avg = _avg;
        id = _id;
        positions = _positions;
        abs = _abs;
        hs = _hs;
    }
}
