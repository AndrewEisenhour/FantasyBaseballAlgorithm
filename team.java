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
    public double teamks;
    public double teamws;
    public double teamsvs;
    public double teamera;
    public double teamwhip;

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
        teamruns += a.runs;
        runNeed -= teamruns / runs;
        teamhrs += a.hrs;
        hrNeed -= teamhrs / hrs;
        teamrbis += a.rbis;
        rbiNeed -= teamrbis / rbis;
        teamsbs += a.sbs;
        sbNeed -= teamsbs / sbs;
        teamavg = (teamavg * batters.size() + a.avg) / batters.size();
        avgNeed = avg / teamavg;
    }

    public void pitcherCalculate() {
        Pitcher a = pitchers.get(pitchers.size() - 1);
        teamks += a.ks;
        kNeed -= teamks / ks;
        teamws += a.ws;
        wNeed -= teamws / ws;
        teamsvs += a.svs;
        svNeed -= teamsvs / svs;
        teamera = (teamera * pitchers.size() + a.era) / pitchers.size();
        eraNeed = teamera / era;
        teamwhip = (teamwhip * pitchers.size() + a.whip) / pitchers.size();
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
}
