
public class player {
    public double total;
    public String name;
    public String id;
    public int[] positions;
    public double bestPositionValue;

    public player(String _name, double _total, String _id, int[] _positions) {
        name = _name;
        total = _total;
        id = _id;
        positions = _positions;
        bestPositionValue = 0;
    }
}
