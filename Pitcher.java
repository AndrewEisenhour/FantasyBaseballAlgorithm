public class Pitcher {
    public String name;
    public double ks;
    public double ws;
    public double svs;
    public double era;
    public double whip;
    public String id;
    public int[] positions;
    public double ips;
    public double ers;
    public double phbbs;
    // public double total;

    public Pitcher(String _id, String _name, double d, double e, double f, double g, double _avg, int[] _positions, double _ips, double _ers, double _phbbs) {
        name = _name;
        ks = d;
        ws = e;
        svs = f;
        era = g;
        whip = _avg;
        id = _id;
        positions = _positions;
        ips = _ips;
        ers = _ers;
        phbbs = _phbbs;
    }
}
