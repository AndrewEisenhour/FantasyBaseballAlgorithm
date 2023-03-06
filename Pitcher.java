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

    public Pitcher(String _id, String _name, double _ks, double _ws, double _svs, double _era, double _whip, int[] _positions, double _ips, double _ers, double _phbbs) {
        name = _name;
        ks = _ks;
        ws = _ws;
        svs = _svs;
        era = _era;
        whip = _whip;
        id = _id;
        positions = _positions;
        ips = _ips;
        ers = _ers;
        phbbs = _phbbs;
    }

    public void printPitcher(){
        System.out.println(name + " " + ks + " " + ws + " " + svs + " " + era + " " + whip);
    }
}
