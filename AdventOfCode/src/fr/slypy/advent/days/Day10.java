package fr.slypy.advent.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.slypy.advent.AdventParser;

public class Day10 {
	
	public static Map<String, Integer> memo = new HashMap<>();

	public static void main(String[] args) {

		AdventParser p = new AdventParser("day10.txt");
		
		List<List<Boolean>> lights = new ArrayList<>();
		List<List<List<Integer>>> buttons = new ArrayList<>();
		List<List<Integer>> joltages = new ArrayList<>();
		
		p.getLines().forEach(line -> {
			
			List<Boolean> light = new ArrayList<>();
			List<List<Integer>> button = new ArrayList<>();
			List<Integer> joltage = new ArrayList<>();
			
			line = line.replaceAll("\\) \\{", "\\)\\|\\{").replaceAll("\\] \\(", "\\]\\|\\(");
			
			String[] ss = line.split("\\|");
			ss[0].replaceAll("\\[", "").replaceAll("\\]", "").chars().forEach(ch -> light.add(ch == (int) '#'));
			String[] sss = ss[1].replaceFirst("\\(", "").split("\\) \\(");
			Arrays.asList(sss).forEach(str -> button.add(new ArrayList<String>(Arrays.asList(str.replaceAll("\\)", "").replaceAll("\\(", "").split(","))).stream().map(Integer::parseInt).toList()));
			Arrays.asList(ss[2].replaceAll("\\{", "").replaceAll("\\}", "").split(",")).forEach(str -> joltage.add(Integer.parseInt(str)));
			
			lights.add(light);
			buttons.add(button);
			joltages.add(joltage);
			
		});
		
		System.out.println(lights);
		System.out.println(buttons);
		System.out.println(joltages);
		
		System.out.println("Part 1 : " + part1(lights, buttons));
		System.out.println();
		System.out.println("Part 2 : " + part2(joltages, buttons));
		
	}
	
	public static String part1(List<List<Boolean>> lights, List<List<List<Integer>>> buttons) {
		
		long sum = 0;
		
		for(int i = 0; i < lights.size(); i++) {

			int min = Integer.MAX_VALUE;
			
			for(int j = 0; j < Math.pow(2, buttons.get(i).size()); j++) {
				
				String bin = String.format("%" + buttons.get(i).size() + "s", Integer.toBinaryString(j)).replace(" ", "0");
				
				int cost = 0;
				List<Boolean> current = new ArrayList<>();
				
				for(int k = 0; k < lights.get(i).size(); k++) {
					
					current.add(false);
					
				}
				
				for(int k = 0; k < bin.length(); k++) {
					
					if(bin.charAt(k) == '1') {
						
						cost++;

						for(int l = 0; l < buttons.get(i).get(k).size(); l++) {
							
							current.set(buttons.get(i).get(k).get(l), !current.get(buttons.get(i).get(k).get(l)));
							
						}
						
					}
					
				}
				
				//System.out.println("With " + bin + " got " + current + " out of " + lights.get(i));
				
				if(current.toString().equals(lights.get(i).toString()) && cost < min) {
					
					min = cost;
					
				}
				
			}
			
			//System.out.println(min);
			sum += min;
			
		}
		
		return "" + sum;
		
	}
	
	public static boolean isSolvable(List<Integer> goal) {

		for(int l : goal) {
			
			if(l < 0)
				return false;
			
		}
		
		return true;
		
	}
	
	public static boolean isSolved(List<Integer> goal) {

		for(int l : goal) {
			
			if(l != 0)
				return false;
			
		}
		
		return true;
		
	}
	
	public static void apply(List<Integer> coef, List<Integer> goal) {
		
		for(int l : coef) {
			
			goal.set(l, goal.get(l) - 1);
			
		}
		
	}
	
	public static int max(List<Integer> goal) {
		
		int max = 0;
		
		for(int l : goal) {
			
			if(l > max)
				max = l;
			
		}
		
		return max;
		
	}
	
	/*public static int solve(List<List<Integer>> coefs, List<Integer> goal) {
		
		if(coefs.isEmpty())
			return 0;
		
		if(!isSolvable(goal))
			return 10_000_000;
		
		if(memo.containsKey(goal.toString() + coefs.size())) {
			
			return memo.get(goal.toString() + coefs.size());
			
		}
		
		if(coefs.size() == 1) {
			
			int tot = 0;
			int m = max(goal);
			
			for(int i = 0; i <= m && !isSolved(goal); i++) {
				
				apply(coefs.get(0), goal);
				tot++;
				
			}
			
			memo.put(goal.toString() + coefs.size(), isSolved(goal) ? tot : 10_000_000);
			
			return memo.get(goal.toString() + coefs.size());
			
		}
		
		int min = 10_000_000;
		
		for(int i = 0; i <= max(goal); i++) {

			List<Integer> newGoal = new ArrayList<>(goal);
			
			for(int j = 0; j < i; j++)
				apply(coefs.get(coefs.size() - 1), newGoal);
			
			if(!isSolvable(newGoal))
				break;
			
			List<List<Integer>> newCoefs = new ArrayList<>(coefs);
			newCoefs.remove(newCoefs.size() - 1);
			int res = solve(newCoefs, newGoal) + i;

			if(res >= 10_000_000)
				continue;
			
			System.out.println("Potential : " + res);
			
			if(res < min) {
				
				min = res;
				
			}
			
		}
		
		memo.put(goal.toString() + coefs.size(), min);
		
		return min;
		
	}*/
	
	public static int solve(List<List<Integer>> coefs, List<Integer> goal) {
	    String key = goal.toString();
	    if (memo.containsKey(key))
	        return memo.get(key);

	    if (isSolved(goal)) {
	        memo.put(key, 0);
	        return 0;
	    }

	    if (!isSolvable(goal)) {
	        memo.put(key, 10_000_000);
	        return 10_000_000;
	    }

	    int best = 10_000_000;

	    // try each vector once
	    for (List<Integer> v : coefs) {

	        List<Integer> newGoal = new ArrayList<>(goal);
	        apply(v, newGoal);

	        if (!isSolvable(newGoal)) continue;

	        int sub = solve(coefs, newGoal);
	        if (sub >= 10_000_000) continue;

	        best = Math.min(best, sub + 1);
	        
	    }

	    memo.put(key, best);
	    return best;
	    
	}
	
	public static String part2(List<List<Integer>> joltages, List<List<List<Integer>>> buttons) {
		
		
		long sum = 0;
		
		for(int i = 0; i < joltages.size(); i++) {
			
			memo = new HashMap<>();
			
			int min = solve(buttons.get(i), joltages.get(i));
			
			System.out.println(min);
			sum += min;
			
		}
		
		return "" + sum;
		
	}
	
}
