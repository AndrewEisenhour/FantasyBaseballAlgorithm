import java.util.*;
import java.io.*;

class batter {
	public String id;
	public String name;
	public double avg;
	public double runs;
	public double rbis;
	public double hrs;
	public double sbs;
	// public double total;

	public batter(String _id, String _name, double d, double e, double f, double g, double _avg) {
		name = _name;
		runs = d;
		rbis = e;
		hrs = f;
		sbs = g;
		avg = _avg;
		id = _id;
	}
}

class pitcher {
	public String name;
	public double ks;
	public double ws;
	public double svs;
	public double era;
	public double whip;
	public String id;
	// public double total;

	public pitcher(String _id, String _name, double d, double e, double f, double g, double _avg) {
		name = _name;
		ks = d;
		ws = e;
		svs = f;
		era = g;
		whip = _avg;
		id = _id;
	}
}

class lists {
	ArrayList<batter> batters;
	ArrayList<pitcher> pitchers;
	ArrayList<player> players;

	public lists(ArrayList<batter> _batters, ArrayList<pitcher> _pitchers, ArrayList<player> _players) {
		batters = _batters;
		pitchers = _pitchers;
		players = _players;
	}
}

class team {
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
	final double runs = 1465;
	final double hrs = 456;
	final double rbis = 1444;
	final double sbs = 173;
	final double avg = 0.269;
	final double ks = 2155;
	final double ws = 124;
	final double svs = 136;
	final double era = 3.66;
	final double whip = 1.16;
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

class player {
	public double total;
	public String name;
	public String id;

	public player(String _name, double _total, String _id) {
		name = _name;
		total = _total;
		id = _id;
	}
}

class cmpAvg implements Comparator<batter> {
	public int compare(batter E1, batter E2) {
		if (E1.avg < E2.avg)
			return 1;
		if (E1.avg > E2.avg)
			return -1;
		return 0;
	}
}

class cmpTotal implements Comparator<player> {
	public int compare(player E1, player E2) {
		if (E1.total < E2.total)
			return 1;
		if (E1.total > E2.total)
			return -1;
		return 0;
	}
}
/*
 * 1465 456 1444 173 0.269 2155 124 136 3.66 1.16
 */

public class Ranking {
	public static void main(String args[]) throws Exception {
		lists arr = run();
		Scanner scan = new Scanner(System.in);
		System.out.println("Would you like to begin a draft? (y/n)\n");
		int line = -1;
		int teamNum;
		int counter = 1;
		int draftPos;
		int pickNo = 1;
		if (scan.nextLine().equals("y")) {
			System.out.println("How many teams are in the draft?");
			teamNum = scan.nextInt();
			System.out.println("What draft position would you like?");
			draftPos = scan.nextInt();
			team myTeam = new team();
			team exTeam = new team();
			System.out.println("Input a player rank to draft them: (type 0 to stop)");
			while (line != 0) {
				line = scan.nextInt();
				if (line == 0) {
					return;
				}
				System.out.println("Pick number " + pickNo + ": ");
				if (counter % teamNum == draftPos) {
					System.out.println("It's you! Duh nu nu, duh nu nu");
					arr = draft(line - 1, arr, myTeam);
					myTeam.print();
					arr = run2(arr.batters, arr.pitchers, myTeam);

				} else {
					arr = draftNoTeam(line - 1, arr);
					arr = run2(arr.batters, arr.pitchers, exTeam);
				}

				counter++;
				pickNo++;
				if (counter > teamNum) {
					counter = 1;
					draftPos = teamNum + 1 - draftPos;
				}

			}
		}
		scan.close();
	}

	public static lists draft(int n, lists arr, team teamName) {
		String name = arr.players.get(n).name;
		for (batter a : arr.batters) {
			if (a.name.equals(name)) {
				arr.batters.remove(a);
				teamName.addBatter(a, arr.players.get(n));
				break;
			}
		}
		for (pitcher a : arr.pitchers) {
			if (a.name.equals(name)) {
				arr.pitchers.remove(a);
				teamName.addPitcher(a, arr.players.get(n));
				break;
			}
		}
		arr.players.remove(n);
		return arr;
	}

	public static lists draftNoTeam(int n, lists arr) {
		String name = arr.players.get(n).name;
		for (batter a : arr.batters) {
			if (a.name.equals(name)) {
				arr.batters.remove(a);

				break;
			}
		}
		for (pitcher a : arr.pitchers) {
			if (a.name.equals(name)) {
				arr.pitchers.remove(a);

				break;
			}
		}
		arr.players.remove(n);
		return arr;
	}

	public static lists run() throws Exception {
		Scanner scan = new Scanner(System.in);
		try {
			Scanner fileScan = new Scanner(new File("batters2.txt"));
			String[] batterInfo;
			// int counter = 0;
			ArrayList<batter> info = new ArrayList<batter>();
			while (fileScan.hasNextLine()) {
				batterInfo = fileScan.nextLine().split(":");
				// System.out.println(counter);
				// counter++;
				info.add(new batter(batterInfo[0], batterInfo[3], Double.parseDouble(batterInfo[4]),
						Double.parseDouble(batterInfo[5]), Double.parseDouble(batterInfo[6]),
						Double.parseDouble(batterInfo[7]), Double.parseDouble(batterInfo[8])));
			}
			batter gary = comp(info);
			double runSD = runsSD(info, gary.runs);
			double rbiSD = rbisSD(info, gary.rbis);
			double hrSD = hrsSD(info, gary.hrs);
			double sbSD = sbsSD(info, gary.sbs);
			double avgSD = avgSD(info, gary.avg);
			ArrayList<player> stats = new ArrayList<player>();
			batter temp;
			player temp2;
			for (batter i : info) {
				temp = new batter(i.id, i.name, (i.runs - gary.runs) / runSD, (i.hrs - gary.hrs) / hrSD,
						(i.rbis - gary.rbis) / rbiSD, (i.sbs - gary.sbs) / sbSD, (i.avg - gary.avg) / avgSD);
				temp2 = new player(temp.name, temp.runs + temp.hrs + temp.rbis + temp.sbs + temp.avg, temp.id);
				stats.add(temp2);
			}

			// Pitchers next
			fileScan = new Scanner(new File("pitchers2.txt"));
			String[] pitcherInfo;
			// int counter = 0;
			ArrayList<pitcher> info2 = new ArrayList<pitcher>();
			while (fileScan.hasNextLine()) {
				pitcherInfo = fileScan.nextLine().split(":");
				// System.out.println(counter);
				// counter++;
				info2.add(new pitcher(pitcherInfo[0], pitcherInfo[3], Double.parseDouble(pitcherInfo[4]),
						Double.parseDouble(pitcherInfo[5]), Double.parseDouble(pitcherInfo[6]),
						Double.parseDouble(pitcherInfo[7]), Double.parseDouble(pitcherInfo[8])));
			}
			pitcher joe = comp2(info2);
			pitcher temp3;
			double kSD = ksSD(info2, joe.ks);
			double wSD = wsSD(info2, joe.ws);
			double svSD = svsSD(info2, joe.svs);
			double eraSD = eraSD(info2, joe.era);
			double whipSD = whipSD(info2, joe.whip);
			for (pitcher i : info2) {
				temp3 = new pitcher(i.id, i.name, (i.ks - joe.ks) / kSD, (i.ws - joe.ws) / wSD, (i.svs - joe.svs) / svSD,
						(joe.era - i.era) / eraSD, (joe.whip - i.whip) / whipSD);
				temp2 = new player(temp3.name, temp3.ks + temp3.ws + temp3.svs + temp3.era + temp3.whip, temp3.id);
				stats.add(temp2);
			}

			Collections.sort(stats, new cmpTotal());
			int counter = 1;
			for (player i : stats) {
				System.out.println(counter + ". " + i.name + " " + i.total);
				counter++;
			}
			lists list = new lists(info, info2, stats);
			return list;
		} catch (FileNotFoundException e) {
			System.out.println("Bad file");
			return null;
		}
	}

	public static lists run2(ArrayList<batter> info, ArrayList<pitcher> info2, team myTeam) throws Exception {
		// File batters = new File("");
		// System.out.println(batters.getAbsolutePath());
		// Scanner scan = new Scanner(System.in);

		batter gary = comp(info);
		double runSD = runsSD(info, gary.runs);
		double rbiSD = rbisSD(info, gary.rbis);
		double hrSD = hrsSD(info, gary.hrs);
		double sbSD = sbsSD(info, gary.sbs);
		double avgSD = avgSD(info, gary.avg);
		ArrayList<player> stats = new ArrayList<player>();
		batter temp;
		player temp2;
		for (batter i : info) {
			temp = new batter(i.id, i.name, myTeam.runNeed * (i.runs - gary.runs) / runSD,
					myTeam.hrNeed * (i.hrs - gary.hrs) / hrSD, myTeam.rbiNeed * (i.rbis - gary.rbis) / rbiSD,
					myTeam.sbNeed * (i.sbs - gary.sbs) / sbSD, myTeam.avgNeed * (i.avg - gary.avg) / avgSD);
			temp2 = new player(temp.name, temp.runs + temp.hrs + temp.rbis + temp.sbs + temp.avg, temp.id);
			stats.add(temp2);
		}

		// Pitchers next

		pitcher joe = comp2(info2);
		pitcher temp3;
		double kSD = ksSD(info2, joe.ks);
		double wSD = wsSD(info2, joe.ws);
		double svSD = svsSD(info2, joe.svs);
		double eraSD = eraSD(info2, joe.era);
		double whipSD = whipSD(info2, joe.whip);
		for (pitcher i : info2) {
			temp3 = new pitcher(i.id, i.name, myTeam.kNeed * (i.ks - joe.ks) / kSD, myTeam.wNeed * (i.ws - joe.ws) / wSD,
					myTeam.svNeed * (i.svs - joe.svs) / svSD, myTeam.eraNeed * (joe.era - i.era) / eraSD,
					myTeam.whipNeed * (joe.whip - i.whip) / whipSD);
			temp2 = new player(temp3.name, temp3.ks + temp3.ws + temp3.svs + temp3.era + temp3.whip, i.name);
			stats.add(temp2);
		}

		Collections.sort(stats, new cmpTotal());
		int counter = 1;
		for (player i : stats) {
			System.out.println(counter + ". " + i.name + " " + i.total);
			counter++;
			if (counter > 10) {
				break;
			}
		}
		lists list = new lists(info, info2, stats);

		return list;

	}

	private static batter comp(ArrayList<batter> info) {
		batter generic = new batter("0", "Boring Gary", runs(info), hrs(info), rbis(info), sbs(info), avg(info));
		return generic;
	}

	private static pitcher comp2(ArrayList<pitcher> info) {
		pitcher generic = new pitcher("0", "Vanilla Joe", ks(info), ws(info), svs(info), era(info), whip(info));
		return generic;
	}

	private static double runsSD(ArrayList<batter> info, double comparison) {
		double sum = 0;
		for (batter i : info) {
			sum += Math.pow(i.runs - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double rbisSD(ArrayList<batter> info, double comparison) {
		double sum = 0;
		for (batter i : info) {
			sum += Math.pow(i.rbis - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double hrsSD(ArrayList<batter> info, double comparison) {
		double sum = 0;
		for (batter i : info) {
			sum += Math.pow(i.hrs - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double sbsSD(ArrayList<batter> info, double comparison) {
		double sum = 0;
		for (batter i : info) {
			sum += Math.pow(i.sbs - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double avgSD(ArrayList<batter> info, double comparison) {
		double sum = 0;
		for (batter i : info) {
			sum += Math.pow(i.avg - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double runs(ArrayList<batter> info) {
		double n = 0;
		double total = 0;
		for (batter i : info) {
			n++;
			total += i.runs;
		}
		return total / n;
	}

	private static double hrs(ArrayList<batter> info) {
		double n = 0;
		double total = 0;
		for (batter i : info) {
			n++;
			total += i.hrs;
		}
		return total / n;
	}

	private static double rbis(ArrayList<batter> info) {
		double n = 0;
		double total = 0;
		for (batter i : info) {
			n++;
			total += i.rbis;
		}
		return total / n;
	}

	private static double sbs(ArrayList<batter> info) {
		double n = 0;
		double total = 0;
		for (batter i : info) {
			n++;
			total += i.sbs;
		}
		return total / n;
	}

	private static double avg(ArrayList<batter> info) {
		double n = 0;
		double total = 0;
		for (batter i : info) {
			n++;
			total += i.avg;
		}
		return total / n;
	}

	private static double ksSD(ArrayList<pitcher> info, double comparison) {
		double sum = 0;
		for (pitcher i : info) {
			sum += Math.pow(i.ks - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double wsSD(ArrayList<pitcher> info, double comparison) {
		double sum = 0;
		for (pitcher i : info) {
			sum += Math.pow(i.ws - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double svsSD(ArrayList<pitcher> info, double comparison) {
		double sum = 0;
		for (pitcher i : info) {
			sum += Math.pow(i.svs - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double eraSD(ArrayList<pitcher> info, double comparison) {
		double sum = 0;
		for (pitcher i : info) {
			sum += Math.pow(i.era - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double whipSD(ArrayList<pitcher> info, double comparison) {
		double sum = 0;
		for (pitcher i : info) {
			sum += Math.pow(i.whip - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double ks(ArrayList<pitcher> info) {
		double n = 0;
		double total = 0;
		for (pitcher i : info) {
			n++;
			total += i.ks;
		}
		return total / n;
	}

	private static double ws(ArrayList<pitcher> info) {
		double n = 0;
		double total = 0;
		for (pitcher i : info) {
			n++;
			total += i.ws;
		}
		return total / n;
	}

	private static double svs(ArrayList<pitcher> info) {
		double n = 0;
		double total = 0;
		for (pitcher i : info) {
			n++;
			total += i.svs;
		}
		return total / n;
	}

	private static double era(ArrayList<pitcher> info) {
		double n = 0;
		double total = 0;
		for (pitcher i : info) {
			n++;
			total += i.era;
		}
		return total / n;
	}

	private static double whip(ArrayList<pitcher> info) {
		double n = 0;
		double total = 0;
		for (pitcher i : info) {
			n++;
			total += i.whip;
		}
		return total / n;
	}
}
