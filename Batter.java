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

    public Batter(String _id, String _name, double _runs, double _hrs, double _rbis, double _sbs, double _avg, int[] _positions, double _abs, double _hs) {
        name = _name;
        _runs = _runs;
        rbis = _rbis;
        hrs = _hrs;
        sbs = _sbs;
        avg = _avg;
        id = _id;
        positions = _positions;
        abs = _abs;
        hs = _hs;
    }
}
