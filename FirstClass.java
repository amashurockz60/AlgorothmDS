package FirstPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

public class FirstClass {
	public static void main(String args[]) {
		//Un-comment for 1st problem
		//System.out.println(FirstClass.CurrenyCheck("ETB2010400G"));
		
		//Un-comment for 2nd problem
		//int[] start = {1, 2, 5, 8};
		//int[] finish = {2, 2, 6, 10};
		//System.out.println(FirstClass.GapsInTraffic(10, start, finish));
		
		//Un-comment for 3rd problem
		/*String[] input = new String[10];
		input[0] = "# Games";
		input[1] = "## Board";
		input[2] = "## Computer";
		input[3] = "## Zero sum";
		input[4] = "## Multiplayer";
		input[5] = "# Strategies";
		input[6] = "## Greedy";
		input[7] = "## Tree pruning";
		input[8] = "## Others";
		input[9] = "# Summary";
				
		String[] result = FirstClass.TableOfContents(input);
		for(String x: result) {
			System.out.println(x);
		}*/
		
		//Un-comment for 5th problem
		/*int[] projectIDs = {2, 0, 1, 2};
		int[] bids = {8, 7, 6, 9};
		System.out.println(FirstClass.freelancingPlatform(2, projectIDs, bids));*/
		
		//Un-comment for 6th problem
		/*int[] ratings = {2, 4, 5, 3, 7, 8};
		System.out.println(FirstClass.ProgrammingContest(ratings));*/
		
		//Un-comment for 7th problem
		/*int[] teamA = {2, 10, 5, 4, 8};
		int[] teamB = {3, 1, 7, 8};
		Integer[] result = FirstClass.FootballScore(teamA, teamB);
		for(Integer x: result) {
			System.out.println(x);
		}*/
		
		//Un-comment for 9th problem
		/*int[] stocks = {6, 6, 3, 9, 3, 5, 1};
		System.out.println(FirstClass.TargetProfit(stocks, 12));*/
		
	}
	
	public static Boolean  CurrenyCheck(String val) {
		
		//Length 10 to 12 check
		if(val.length() < 10 || val.length() > 12) {
			return false;
		}
		
		//check first 3 char uppercase
		if(!Character.isUpperCase(val.charAt(0)) || !Character.isUpperCase(val.charAt(1)) || !Character.isUpperCase(val.charAt(2))){
			return false;
		}
		
		//Not Distinct check
		if(val.charAt(0) == val.charAt(1) || val.charAt(0) == val.charAt(2) || val.charAt(1) == val.charAt(2)){
			return false;
		}
		
		int year = 0;
		//if 4 digit not year
		for(int i = 3; i < 7; i++) {
			if(!Character.isDigit(val.charAt(i))){
				return false;
			}
		}
		
		//Year not between 1900 and 2019 then error
		year = Integer.parseInt(val.substring(3, 7));
		if(year < 1900 || year > 2019) {
			return false;
		}
		char last = val.substring(val.length()-1).charAt(0);
		if(!Character.isUpperCase(last)) {
			return false;
		}
		
		//valid note {10, 20, 50, 100, 200, 500, 1000}
		String note = val.substring(7, val.length() - 1);
		for(int i = 0; i < note.length(); i++) {
			if(!Character.isDigit(note.charAt(i))){
				return false;
			}
		}
		
		int denom = Integer.parseInt(note);
		int[] deno = {10, 20, 50, 100, 200, 500, 1000};
		for(int i = 0 ; i < deno.length; i++) {
			if(denom == deno[i]) {
				return true;
			}
		}
		return false;
	}
	
	public static Integer GapsInTraffic(int n, int[] start, int[] finish) {
		int length = start.length;
		//Create a List containg 1 to n values
		List<Integer> space = new ArrayList<Integer>();
		//Add values to space
		for(int i = 1; i <= n; i++) {
			space.add(i);
		}
		
		//set all the occupied spaces as 0 in space
		for(int i = 0; i < length; i++) {
			//for all the spots between start[i] and finish[i]
			for(int j = start[i]-1; j <= finish[i]-1; j++) {
				space.set(j, 0);
			}
		}
		
		//calculate highest gap without 0
		int maxgap = 0;
		int currentgap = 0;
		for(int i = 0 ; i < space.size(); i++) {
			if(space.get(i) == 0) {
				if(currentgap > maxgap) {
					maxgap = currentgap;
				}
				currentgap = 0;
			}
			else {
				currentgap += 1;
			}
		}
		
		return maxgap;
		
	}

	public static String[] TableOfContents(String[] text) {
		List<String> result = new ArrayList<String>();
		int title = 0;
		int subtitle = 0;
		Boolean newTitle = false;
		for(int i = 0; i < text.length; i++) {
			//If starts with single ##
			if(text[i].charAt(0) == '#' && text[i].charAt(1) != '#') {
				title += 1;
				newTitle = true;
				result.add(Integer.toString(title) + "." + text[i].substring(1));
			}
			//if starts with double ##
			else if(text[i].substring(0,2).equals("##")) {
				if(newTitle) {
					subtitle = 1;
					newTitle = false;
				}
				else {
					subtitle += 1;
				}
				result.add(Integer.toString(title) + "." + Integer.toString(subtitle) + "." + text[i].substring(2));
			}
		}
		
		return result.toArray(new String[0]);
	}

	public static long freelancingPlatform(int n, int[] projectIDs, int[] bids) {
		Hashtable<Integer, Long> result = new Hashtable<Integer, Long>();
		//Create a Hash table, Maintain key as projectID, Bid as value
		for(int i = 0; i < projectIDs.length; i++) {
			if(result.containsKey(projectIDs[i])) {
				Long currentVal = result.get(projectIDs[i]);
				//If there is another bid for same project then
				//update key's value with the minimum bid
				result.put(projectIDs[i], Math.min(currentVal, bids[i]));
			}
			else {
				result.put(projectIDs[i], (long)bids[i]);
			}
		}
		if(result.keySet().size() < n) {
			return -1;
		}
		else {
			long sum  = 0;
			for(long x: result.values()) {
				sum += x;
			}
			return sum;
		}
	}
	
	public static int ProgrammingContest(int[] ratings) {
		int result = 0;
		Arrays.sort(ratings);
		for(int i = 0; i < ratings.length - 1; i = i + 2) {
			result += ratings[i + 1] - ratings[i];
		}
		
		return result;
	}

	public static Integer[] FootballScore(int[] teamA, int[] teamB) {
		List<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i < teamB.length; i++) {
			int currentMatchGoal = 0;
			for(int j = 0; j < teamA.length; j++) {
				if(teamB[i] >= teamA[j]) {
					currentMatchGoal += 1;
				}
			}
			result.add(currentMatchGoal);
		}
		
		return result.toArray(new Integer[0]);
	}

	public static int TargetProfit(int[] stocksProfit, int target) {
		int result = 0;
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < stocksProfit.length; i++) {
			if(set.contains(target - stocksProfit[i])) {
				result += 1;
				set.remove(target - stocksProfit[i]);
			}
			else {
				set.add(stocksProfit[i]);
			}
		}
		return result;
	}
}


