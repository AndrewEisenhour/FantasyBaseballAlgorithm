import java.util.*;

public class team {
    ArrayList<batter> batters;
    ArrayList<pitcher> pitchers;
    ArrayList<player> players;
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

    public team() {
        batters = new ArrayList<batter>();
        pitchers = new ArrayList<pitcher>();
        players = new ArrayList<player>();
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

    public team(ArrayList<batter> _batters, ArrayList<pitcher> _pitchers, ArrayList<player> _players) {
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
        batter a = batters.get(batters.size() - 1);
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
        pitcher a = pitchers.get(pitchers.size() - 1);
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

    public void addPitcher(pitcher a, player b) {
        pitchers.add(a);
        players.add(b);
        pitcherCalculate();
    }

    public void addBatter(batter a, player b) {
        batters.add(a);
        players.add(b);
        batterCalculate();
    }

    public void print() {
        for (player i : players) {
            System.out.println(i.name);
        }
    }
}
