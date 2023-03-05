import java.util.*;

public class Team {
    ArrayList<Batter> batters;
    ArrayList<Pitcher> pitchers;
    ArrayList<Player> players;
    double runNeed;
    double hrNeed;
    double rbiNeed;
    double sbNeed;
    double avgNeed;
    double kNeed;
    double wNeed;
    double svNeed;
    double eraNeed;
    double whipNeed;
    final double runs = 954.5;
    final double hrs = 283;
    final double rbis = 919;
    final double sbs = 124.9;
    final double avg = 0.265;
    final double ks = 1190.8;
    final double ws = 68;
    final double svs = 55.9;
    final double era = 3.479;
    final double whip = 1.125;
    public double teamavg;
    public double teamruns;
    public double teamrbis;
    public double teamhrs;
    public double teamsbs;
    public double teamabs;
    public double teamhs;
    public double teamks;
    public double teamws;
    public double teamsvs;
    public double teamera;
    public double teamwhip;
    public double teamips;
    public double teamers;
    public double teamphbbs;

    public Team() {
        batters = new ArrayList<Batter>();
        pitchers = new ArrayList<Pitcher>();
        players = new ArrayList<Player>();
        runNeed = 1.0;
        hrNeed = 1.0;
        rbiNeed = 1.0;
        sbNeed = 1.0;
        avgNeed = 1.0;
        kNeed = 1.0;
        wNeed = 1.0;
        svNeed = 1.0;
        eraNeed = 1.0;
        whipNeed = 1.0;
    }

    public Team(ArrayList<Batter> _batters, ArrayList<Pitcher> _pitchers, ArrayList<Player> _players) {
        batters = _batters;
        pitchers = _pitchers;
        players = _players;
        runNeed = 1.0;
        hrNeed = 1.0;
        rbiNeed = 1.0;
        sbNeed = 1.0;
        avgNeed = 1.0;
        kNeed = 1.0;
        wNeed = 1.0;
        svNeed = 1.0;
        eraNeed = 1.0;
        whipNeed = 1.0;
    }

    public void batterCalculate() {
        Batter a = batters.get(batters.size() - 1);
        teamabs += a.abs;
        teamhs += a.hs;
        teamruns += a.runs;
        runNeed = 1 - teamruns / runs;
        teamhrs += a.hrs;
        hrNeed = 1 - teamhrs / hrs;
        teamrbis += a.rbis;
        rbiNeed = 1 - teamrbis / rbis;
        teamsbs += a.sbs;
        sbNeed = 1 - teamsbs / sbs;
        teamavg = teamhs / teamabs;
        avgNeed = avg / teamavg;
    }

    public void pitcherCalculate() {
        Pitcher a = pitchers.get(pitchers.size() - 1);
        teamips += a.ips;
        teamers += a.ers;
        teamphbbs += a.phbbs;
        teamks += a.ks;
        kNeed = 1 - teamks / ks;
        teamws += a.ws;
        wNeed = 1 - teamws / ws;
        teamsvs += a.svs;
        svNeed = 1 - teamsvs / svs;
        teamera = 9 * teamers / teamips;
        eraNeed = teamera / era;
        teamwhip = teamphbbs / teamips;
        whipNeed = teamwhip / whip;
    }

    public void addPitcher(Pitcher a, Player b) {
        pitchers.add(a);
        players.add(b);
        pitcherCalculate();
    }

    public void addBatter(Batter a, Player b) {
        batters.add(a);
        players.add(b);
        batterCalculate();
    }

    public void print() {
        for (Player i : players) {
            System.out.println(i.name);
        }
    }

    public void printNeed() {
        System.out.println(runNeed + " " + hrNeed + " " + rbiNeed + " " + sbNeed + " " + avgNeed);
        System.out.println(kNeed + " " + wNeed + " " + svNeed + " " + eraNeed + " " + whipNeed);
    }
}
