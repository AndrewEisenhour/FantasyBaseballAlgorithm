import java.util.*;
import java.io.*;

class cmpAvg implements Comparator<Batter> {
	public int compare(Batter E1, Batter E2) {
		if (E1.avg < E2.avg)
			return 1;
		if (E1.avg > E2.avg)
			return -1;
		return 0;
	}
}

class cmpTotal implements Comparator<Player> {
	public int compare(Player E1, Player E2) {
		if (E1.total < E2.total)
			return 1;
		if (E1.total > E2.total)
			return -1;
		return 0;
	}
}

public class Ranking {
	public static void main(String args[]) throws Exception {
		Lists arr = run();
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
			Team myTeam = new Team();
			Team exTeam = new Team();
			System.out.println("Input a player rank to draft them: (type 0 to stop)");
			String inputLineOne = "";
			String inputLineTwo = "";
			String teamName;
			String playerName;
			while (line != 0) {
				System.out.println("Pick number " + pickNo + ": ");
				if (counter % (teamNum + 1) == draftPos)
					System.out.println("It's you! Duh nu nu, duh nu nu");
				inputLineOne = scan.nextLine();
				if (inputLineOne.equals("")) {
					inputLineOne = scan.nextLine();
				}
				boolean manualEntry = isInt(inputLineOne);
				if (manualEntry) {
					line = Integer.parseInt(inputLineOne);
				}
				if (manualEntry && line == 0) {
					return;
				}
				if (counter % (teamNum + 1) == draftPos) {
					// System.out.println("It's you! Duh nu nu, duh nu nu");
					if (manualEntry) {
						arr = draft(String.valueOf(line), arr, myTeam);
						myTeam.print();
					} else {
						inputLineTwo = scan.nextLine();
						playerName = inputLineOne.split("/")[0].trim();
						teamName = inputLineOne.split("/")[1].split(" ")[0].trim();
						for (int i = 0; i < arr.players.size(); i++) {
							Player player = arr.players.get(i);
							if (player.name.equals(playerName)) {
								arr = draft(player.id, arr, myTeam);
								myTeam.print();
								myTeam.printNeed();
								break;
							}
						}
					}
					arr = run2(arr.batters, arr.pitchers, myTeam);
				} else {
					if (manualEntry) {
						arr = draftNoTeam(String.valueOf(line), arr);
						myTeam.print();
					} else {
						inputLineTwo = scan.nextLine();
						playerName = inputLineOne.split("/")[0].trim();
						teamName = inputLineOne.split("/")[1].split(" ")[0];
						for (int i = 0; i < arr.players.size(); i++) {
							Player player = arr.players.get(i);
							if (player.name.equals(playerName)) {
								arr = draftNoTeam(player.id, arr);
								break;
							}
						}
					}
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

	public static Lists draft(String id, Lists arr, Team teamName) {
		boolean added = false;
		Player temp = null;
		for (Player a : arr.players) {
			if (a.id.equals(id)) {
				System.out.println("Drafted: " + a.name + " ");
				temp = a;
				arr.players.remove(a);
				break;
			}
		}
		for (Batter a : arr.batters) {
			if (a.id.equals(id)) {
				arr.batters.remove(a);
				teamName.addBatter(a, temp);
				added = true;
				break;
			}
		}
		for (Pitcher a : arr.pitchers) {
			if (a.id.equals(id)) {
				arr.pitchers.remove(a);
				if (!added)
					teamName.addPitcher(a, temp);
				break;
			}
		}

		return arr;
	}

	public static Lists draftNoTeam(String id, Lists arr) {
		for (Batter a : arr.batters) {
			if (a.id.equals(id)) {
				arr.batters.remove(a);
				break;
			}
		}
		for (Pitcher a : arr.pitchers) {
			if (a.id.equals(id)) {
				arr.pitchers.remove(a);
				break;
			}
		}
		for (Player a : arr.players) {
			if (a.id.equals(id)) {
				System.out.println("Drafted: " + a.name + " ");
				arr.players.remove(a);
				break;
			}
		}
		return arr;
	}

	public static Lists run() throws Exception {
		Scanner scan = new Scanner(System.in);
		try {
			Scanner fileScan = new Scanner(new File("batters2.txt"));
			String[] batterInfo;
			// int counter = 0;
			ArrayList<Batter> info = new ArrayList<Batter>();
			while (fileScan.hasNextLine()) {
				batterInfo = fileScan.nextLine().split(":");
				// System.out.println(counter);
				// counter++;
				info.add(new Batter(batterInfo[1], batterInfo[3], Double.parseDouble(batterInfo[4]),
						Double.parseDouble(batterInfo[5]), Double.parseDouble(batterInfo[6]),
						Double.parseDouble(batterInfo[7]), Double.parseDouble(batterInfo[8]),
						Arrays.stream(batterInfo[2].split(" ")).mapToInt(Integer::parseInt).toArray(),
						Double.parseDouble(batterInfo[9]), Double.parseDouble(batterInfo[10])));
			}
			Batter gary = comp(info);
			double runSD = runsSD(info, gary.runs);
			double rbiSD = rbisSD(info, gary.rbis);
			double hrSD = hrsSD(info, gary.hrs);
			double sbSD = sbsSD(info, gary.sbs);
			double avgSD = avgSD(info, gary.avg);
			ArrayList<Player> stats = new ArrayList<Player>();
			Batter temp;
			Player temp2;
			double value;
			for (Batter i : info) {
				temp = new Batter(i.id, i.name, (i.runs - gary.runs) / runSD, (i.hrs - gary.hrs) / hrSD,
						(i.rbis - gary.rbis) / rbiSD, (i.sbs - gary.sbs) / sbSD, (i.avg - gary.avg) / avgSD,
						i.positions, i.abs, i.hs);
				value = temp.runs + temp.hrs + temp.rbis + temp.sbs + temp.avg;
				temp2 = new Player(temp.name, value, temp.id, temp.positions);
				stats.add(temp2);
			}

			// Pitchers next
			fileScan = new Scanner(new File("pitchers2.txt"));
			String[] pitcherInfo;
			// int counter = 0;
			ArrayList<Pitcher> info2 = new ArrayList<Pitcher>();
			while (fileScan.hasNextLine()) {
				pitcherInfo = fileScan.nextLine().split(":");
				// System.out.println(counter);
				// counter++;
				info2.add(new Pitcher(pitcherInfo[1], pitcherInfo[3], Double.parseDouble(pitcherInfo[4]),
						Double.parseDouble(pitcherInfo[5]), Double.parseDouble(pitcherInfo[6]),
						Double.parseDouble(pitcherInfo[7]), Double.parseDouble(pitcherInfo[8]),
						Arrays.stream(pitcherInfo[2].split(" ")).mapToInt(Integer::parseInt).toArray(),
						Double.parseDouble(pitcherInfo[9]) / 3, Double.parseDouble(pitcherInfo[10]), Double.parseDouble(pitcherInfo[11])+Double.parseDouble(pitcherInfo[12])));
			}
			Pitcher joe = comp2(info2);
			Pitcher temp3;
			double kSD = ksSD(info2, joe.ks);
			double wSD = wsSD(info2, joe.ws);
			double svSD = svsSD(info2, joe.svs);
			double eraSD = eraSD(info2, joe.era);
			double whipSD = whipSD(info2, joe.whip);
			a: for (Pitcher i : info2) {
				temp3 = new Pitcher(i.id, i.name, (i.ks - joe.ks) / kSD, (i.ws - joe.ws) / wSD,
						(i.svs - joe.svs) / svSD,
						(joe.era - i.era) / eraSD, (joe.whip - i.whip) / whipSD, i.positions, i.ips, i.ers, i.phbbs);
				temp2 = new Player(temp3.name, temp3.ks + temp3.ws + temp3.svs + temp3.era + temp3.whip, temp3.id,
						temp3.positions);
				for (Player a : stats) {
					if (a.id.equals(temp2.id)) {
						a.total += temp2.total;
						continue a;
					}
				}
				stats.add(temp2);
			}

			double[][] positionalValue = positionAvg(stats);
			for (Player i : stats) {
				for (int position : i.positions) {
					i.bestPositionValue = Math.max(i.bestPositionValue,
							(i.total - positionalValue[position][0]) / positionalValue[position][1]);
				}
				i.total += i.bestPositionValue;
			}
			Collections.sort(stats, new cmpTotal());
			int counter = 1;
			for (Player i : stats) {
				System.out.println(i.id + ". " + i.name + " " + i.total + " " + i.bestPositionValue);
				counter++;
				if (counter > 10) {
					break;
				}
			}
			Lists list = new Lists(info, info2, stats);
			return list;
		} catch (FileNotFoundException e) {
			System.out.println("Bad file");
			return null;
		}
	}

	public static Lists run2(ArrayList<Batter> info, ArrayList<Pitcher> info2, Team myTeam) throws Exception {
		// File batters = new File("");
		// System.out.println(batters.getAbsolutePath());
		// Scanner scan = new Scanner(System.in);

		Batter gary = comp(info);
		double runSD = runsSD(info, gary.runs);
		double rbiSD = rbisSD(info, gary.rbis);
		double hrSD = hrsSD(info, gary.hrs);
		double sbSD = sbsSD(info, gary.sbs);
		double avgSD = avgSD(info, gary.avg);
		ArrayList<Player> stats = new ArrayList<Player>();
		Batter temp;
		Player temp2;
		for (Batter i : info) {
			temp = new Batter(i.id, i.name, myTeam.runNeed * (i.runs - gary.runs) / runSD,
					myTeam.hrNeed * (i.hrs - gary.hrs) / hrSD, myTeam.rbiNeed * (i.rbis - gary.rbis) / rbiSD,
					myTeam.sbNeed * (i.sbs - gary.sbs) / sbSD, myTeam.avgNeed * (i.avg - gary.avg) / avgSD,
					i.positions, i.abs, i.hs);
			temp2 = new Player(temp.name, temp.runs + temp.hrs + temp.rbis + temp.sbs + temp.avg, temp.id,
					temp.positions);
			stats.add(temp2);
		}

		// Pitchers next

		Pitcher joe = comp2(info2);
		Pitcher temp3;
		double kSD = ksSD(info2, joe.ks);
		double wSD = wsSD(info2, joe.ws);
		double svSD = svsSD(info2, joe.svs);
		double eraSD = eraSD(info2, joe.era);
		double whipSD = whipSD(info2, joe.whip);
		a: for (Pitcher i : info2) {
			temp3 = new Pitcher(i.id, i.name, myTeam.kNeed * (i.ks - joe.ks) / kSD,
					myTeam.wNeed * (i.ws - joe.ws) / wSD,
					myTeam.svNeed * (i.svs - joe.svs) / svSD, myTeam.eraNeed * (joe.era - i.era) / eraSD,
					myTeam.whipNeed * (joe.whip - i.whip) / whipSD, i.positions, i.ips, i.ers, i.phbbs);
			temp2 = new Player(temp3.name, temp3.ks + temp3.ws + temp3.svs + temp3.era + temp3.whip, temp3.id,
					temp3.positions);
			for (Player a : stats) {
				if (a.id.equals(temp2.id)) {
					a.total += temp2.total;
					continue a;
				}
			}
			stats.add(temp2);
		}

		double[][] positionalValue = positionAvg(stats);
		for (Player i : stats) {
			for (int position : i.positions) {
				i.bestPositionValue = Math.max(i.bestPositionValue,
						(i.total - positionalValue[position][0]) / positionalValue[position][1]);
			}
			i.total += i.bestPositionValue;
		}

		Collections.sort(stats, new cmpTotal());
		int counter = 1;
		for (Player i : stats) {
			System.out.println(i.id + ". " + i.name + " " + i.total);
			counter++;
			if (counter > 10) {
				break;
			}
		}
		Lists list = new Lists(info, info2, stats);

		return list;

	}

	private static Batter comp(ArrayList<Batter> info) {
		Batter generic = new Batter("0", "Boring Gary", runs(info), hrs(info), rbis(info), sbs(info), avg(info),
				new int[] { 0 }, 0, 0);
		return generic;
	}

	private static Pitcher comp2(ArrayList<Pitcher> info) {
		Pitcher generic = new Pitcher("0", "Average Joe", ks(info), ws(info), svs(info), era(info), whip(info),
				new int[] { 0 }, 0, 0, 0);
		return generic;
	}

	private static double runsSD(ArrayList<Batter> info, double comparison) {
		double sum = 0;
		for (Batter i : info) {
			sum += Math.pow(i.runs - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double rbisSD(ArrayList<Batter> info, double comparison) {
		double sum = 0;
		for (Batter i : info) {
			sum += Math.pow(i.rbis - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double hrsSD(ArrayList<Batter> info, double comparison) {
		double sum = 0;
		for (Batter i : info) {
			sum += Math.pow(i.hrs - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double sbsSD(ArrayList<Batter> info, double comparison) {
		double sum = 0;
		for (Batter i : info) {
			sum += Math.pow(i.sbs - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double avgSD(ArrayList<Batter> info, double comparison) {
		double sum = 0;
		for (Batter i : info) {
			sum += Math.pow(i.avg - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double runs(ArrayList<Batter> info) {
		double n = 0;
		double total = 0;
		for (Batter i : info) {
			n++;
			total += i.runs;
		}
		return total / n;
	}

	private static double hrs(ArrayList<Batter> info) {
		double n = 0;
		double total = 0;
		for (Batter i : info) {
			n++;
			total += i.hrs;
		}
		return total / n;
	}

	private static double rbis(ArrayList<Batter> info) {
		double n = 0;
		double total = 0;
		for (Batter i : info) {
			n++;
			total += i.rbis;
		}
		return total / n;
	}

	private static double sbs(ArrayList<Batter> info) {
		double n = 0;
		double total = 0;
		for (Batter i : info) {
			n++;
			total += i.sbs;
		}
		return total / n;
	}

	private static double avg(ArrayList<Batter> info) {
		double n = 0;
		double total = 0;
		for (Batter i : info) {
			n++;
			total += i.avg;
		}
		return total / n;
	}

	private static double ksSD(ArrayList<Pitcher> info, double comparison) {
		double sum = 0;
		for (Pitcher i : info) {
			sum += Math.pow(i.ks - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double wsSD(ArrayList<Pitcher> info, double comparison) {
		double sum = 0;
		for (Pitcher i : info) {
			sum += Math.pow(i.ws - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double svsSD(ArrayList<Pitcher> info, double comparison) {
		double sum = 0;
		for (Pitcher i : info) {
			sum += Math.pow(i.svs - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double eraSD(ArrayList<Pitcher> info, double comparison) {
		double sum = 0;
		for (Pitcher i : info) {
			sum += Math.pow(i.era - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double whipSD(ArrayList<Pitcher> info, double comparison) {
		double sum = 0;
		for (Pitcher i : info) {
			sum += Math.pow(i.whip - comparison, 2);
		}
		sum /= info.size();
		return Math.sqrt(sum);
	}

	private static double ks(ArrayList<Pitcher> info) {
		double n = 0;
		double total = 0;
		for (Pitcher i : info) {
			n++;
			total += i.ks;
		}
		return total / n;
	}

	private static double ws(ArrayList<Pitcher> info) {
		double n = 0;
		double total = 0;
		for (Pitcher i : info) {
			n++;
			total += i.ws;
		}
		return total / n;
	}

	private static double svs(ArrayList<Pitcher> info) {
		double n = 0;
		double total = 0;
		for (Pitcher i : info) {
			n++;
			total += i.svs;
		}
		return total / n;
	}

	private static double era(ArrayList<Pitcher> info) {
		double n = 0;
		double total = 0;
		for (Pitcher i : info) {
			n++;
			total += i.era;
		}
		return total / n;
	}

	private static double whip(ArrayList<Pitcher> info) {
		double n = 0;
		double total = 0;
		for (Pitcher i : info) {
			n++;
			total += i.whip;
		}
		return total / n;
	}

	private static double[][] positionAvg(ArrayList<Player> info) {
		double[] ns = new double[20];
		double[] totals = new double[20];
		double[] totalSquare = new double[20];
		for (Player i : info) {
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
		for (Player i : info) {
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

	public static boolean isInt(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			int d = Integer.parseInt(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
