import java.util.*;
import java.io.*;

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
				System.out.println("Pick number " + pickNo + ": ");
				if (counter % (teamNum + 1) == draftPos)
					System.out.println("It's you! Duh nu nu, duh nu nu");
				line = scan.nextInt();
				if (line == 0) {
					return;
				}
				if (counter % (teamNum + 1) == draftPos) {
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
		String id = arr.players.get(n).id;
		boolean added = false;
		for (batter a : arr.batters) {
			if (a.id.equals(id)) {
				arr.batters.remove(a);
				teamName.addBatter(a, arr.players.get(n));
				added = true;
				break;
			}
		}
		for (pitcher a : arr.pitchers) {
			if (a.id.equals(id)) {
				arr.pitchers.remove(a);
				if (!added)
					teamName.addPitcher(a, arr.players.get(n));
				break;
			}
		}
		player temp = arr.players.remove(n);
		System.out.println("Drafted: " + temp.name + " ");
		return arr;
	}

	public static lists draftNoTeam(int n, lists arr) {
		String id = arr.players.get(n).id;
		for (batter a : arr.batters) {
			if (a.id.equals(id)) {
				arr.batters.remove(a);
				break;
			}
		}
		for (pitcher a : arr.pitchers) {
			if (a.id.equals(id)) {
				arr.pitchers.remove(a);
				break;
			}
		}
		player temp = arr.players.remove(n);
		System.out.println("Drafted: " + temp.name);
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
				info.add(new batter(batterInfo[1], batterInfo[3], Double.parseDouble(batterInfo[4]),
						Double.parseDouble(batterInfo[5]), Double.parseDouble(batterInfo[6]),
						Double.parseDouble(batterInfo[7]), Double.parseDouble(batterInfo[8]),
						Arrays.stream(batterInfo[2].split(" ")).mapToInt(Integer::parseInt).toArray()));
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
			double value;
			for (batter i : info) {
				temp = new batter(i.id, i.name, (i.runs - gary.runs) / runSD, (i.hrs - gary.hrs) / hrSD,
						(i.rbis - gary.rbis) / rbiSD, (i.sbs - gary.sbs) / sbSD, (i.avg - gary.avg) / avgSD,
						i.positions);
				value = temp.runs + temp.hrs + temp.rbis + temp.sbs + temp.avg;
				temp2 = new player(temp.name, value, temp.id, temp.positions);
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
				info2.add(new pitcher(pitcherInfo[1], pitcherInfo[3], Double.parseDouble(pitcherInfo[4]),
						Double.parseDouble(pitcherInfo[5]), Double.parseDouble(pitcherInfo[6]),
						Double.parseDouble(pitcherInfo[7]), Double.parseDouble(pitcherInfo[8]),
						Arrays.stream(pitcherInfo[2].split(" ")).mapToInt(Integer::parseInt).toArray()));
			}
			pitcher joe = comp2(info2);
			pitcher temp3;
			double kSD = ksSD(info2, joe.ks);
			double wSD = wsSD(info2, joe.ws);
			double svSD = svsSD(info2, joe.svs);
			double eraSD = eraSD(info2, joe.era);
			double whipSD = whipSD(info2, joe.whip);
			a: for (pitcher i : info2) {
				temp3 = new pitcher(i.id, i.name, (i.ks - joe.ks) / kSD, (i.ws - joe.ws) / wSD,
						(i.svs - joe.svs) / svSD,
						(joe.era - i.era) / eraSD, (joe.whip - i.whip) / whipSD, i.positions);
				temp2 = new player(temp3.name, temp3.ks + temp3.ws + temp3.svs + temp3.era + temp3.whip, temp3.id,
						temp3.positions);
				for (player a : stats) {
					if (a.id.equals(temp2.id)) {
						a.total += temp2.total;
						continue a;
					}
				}
				stats.add(temp2);
			}

			double[][] positionalValue = positionAvg(stats);
			for (player i : stats) {
				for (int position : i.positions) {
					i.bestPositionValue = Math.max(i.bestPositionValue,
							(i.total - positionalValue[position][0]) / positionalValue[position][1]);
				}
				i.total += i.bestPositionValue;
			}
			Collections.sort(stats, new cmpTotal());
			int counter = 1;
			for (player i : stats) {
				System.out.println(i.id + ". " + i.name + " " + i.total + " " + i.bestPositionValue);
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
					myTeam.sbNeed * (i.sbs - gary.sbs) / sbSD, myTeam.avgNeed * (i.avg - gary.avg) / avgSD,
					i.positions);
			temp2 = new player(temp.name, temp.runs + temp.hrs + temp.rbis + temp.sbs + temp.avg, temp.id,
					temp.positions);
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
		a: for (pitcher i : info2) {
			temp3 = new pitcher(i.id, i.name, myTeam.kNeed * (i.ks - joe.ks) / kSD,
					myTeam.wNeed * (i.ws - joe.ws) / wSD,
					myTeam.svNeed * (i.svs - joe.svs) / svSD, myTeam.eraNeed * (joe.era - i.era) / eraSD,
					myTeam.whipNeed * (joe.whip - i.whip) / whipSD, i.positions);
			temp2 = new player(temp3.name, temp3.ks + temp3.ws + temp3.svs + temp3.era + temp3.whip, temp3.id,
					temp3.positions);
			for (player a : stats) {
				if (a.id.equals(temp2.id)) {
					a.total += temp2.total;
					continue a;
				}
			}
			stats.add(temp2);
		}

		double[][] positionalValue = positionAvg(stats);
		for (player i : stats) {
			for (int position : i.positions) {
				i.bestPositionValue = Math.max(i.bestPositionValue,
						(i.total - positionalValue[position][0]) / positionalValue[position][1]);
			}
			i.total += i.bestPositionValue;
		}

		Collections.sort(stats, new cmpTotal());
		int counter = 1;
		for (player i : stats) {
			System.out.println(i.id + ". " + i.name + " " + i.total);
			counter++;
			if (counter > 10) {
				break;
			}
		}
		lists list = new lists(info, info2, stats);

		return list;

	}

	private static batter comp(ArrayList<batter> info) {
		batter generic = new batter("0", "Boring Gary", runs(info), hrs(info), rbis(info), sbs(info), avg(info),
				new int[] { 0 });
		return generic;
	}

	private static pitcher comp2(ArrayList<pitcher> info) {
		pitcher generic = new pitcher("0", "Vanilla Joe", ks(info), ws(info), svs(info), era(info), whip(info),
				new int[] { 0 });
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

	private static double[][] positionAvg(ArrayList<player> info) {
		double[] ns = new double[20];
		double[] totals = new double[20];
		double[] totalSquare = new double[20];
		for (player i : info) {
			for (int p : i.positions) {
				ns[p]++;
				totals[p] += i.total;
			}
		}
		double[][] finals = new double[20][2];
		for (int i = 0; i < ns.length; i++) {
			if (ns[i] > 0) {
				finals[i][0] = totals[i] / ns[i];
			}
		}
		for (player i : info) {
			for (int p : i.positions) {
				totalSquare[p] += Math.pow(i.total - totals[p], 2);
			}
		}
		for (int i = 0; i < ns.length; i++) {
			if (ns[i] > 0) {
				finals[i][1] = Math.sqrt(totalSquare[i] / ns[i]);
			}
		}
		return finals;
	}
}
