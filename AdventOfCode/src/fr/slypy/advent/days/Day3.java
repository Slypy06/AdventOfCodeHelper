package fr.slypy.advent.days;

import java.util.ArrayList;
import java.util.List;

import fr.slypy.advent.AdventParser;

public class Day3 {

	public static void main(String[] args) {

		AdventParser p = new AdventParser("day3.txt");
		
		List<List<Integer>> l = p.processLines(line -> {
			
			List<Integer> lin = new ArrayList<>();
			
			for(char c : line.toCharArray()) {
				
				lin.add(Integer.parseInt("" + c));
				
			}
			
			return lin;
			
		});
		
		System.out.println("Part 1 : " + part1(l));
		System.out.println();
		System.out.println("Part 2 : " + part2(l));
		
	}
	
	public static String part1(List<List<Integer>> input) {
		
		int sum = 0;
		
		for(List<Integer> line : input) {
			
			int max = 0;
			
			for(int i = 0; i < line.size(); i++) {
				
				for(int j = 0; j < line.size(); j++) {
					
					if(j == i)
						continue;
					
					if(line.get(i < j ? i : j)*10+line.get(i < j ? j : i) > max) {
						
						max = line.get(i < j ? i : j)*10+line.get(i < j ? j : i);
						
					}
					
				}
				
			}
			
			sum += max;
			
		}
		
		return "" + sum;
		
	}
	
	public static String part2(List<List<Integer>> input) {
		
		long sum = 0;
		
		for(List<Integer> line : input) {
			
			String integer = "";
			int lastI = 0;
			
			for(int x = 0; x < 12; x++) {
			
				int max = lastI;

				for(int i = lastI; i <= line.size() - (12-x); i++) {
					
					if(line.get(i) > line.get(max)) {
						
						max = i;
						
					}
					
				}
				
				integer += line.get(max);
				lastI = max + 1;
			
			}
			
			sum += Long.parseLong(integer);
			
		}
		
		return "" + sum;
		
	}
	
}
