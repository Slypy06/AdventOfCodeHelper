package fr.slypy.advent.days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.slypy.advent.AdventParser;

public class Day3 {

	public static void main(String[] args) {

		AdventParser p = new AdventParser("day3.txt");
		
		List<List<Integer>> l = p.processLines(line -> {
			
			List<Integer> ret = new ArrayList<>();
			
			for(char c : line.toCharArray()) {
				
				ret.add(Integer.parseInt("" + c));
				
			}
			
			return ret;
			
		});
		
		System.out.println("Part 1 : " + part1(l));
		System.out.println();
		System.out.println("Part 2 : " + part2(l));
		
	}
	
	public static String part1(List<List<Integer>> input) {
		
		int sum = 0;
		
		for(List<Integer> line : input) {
			
			sum += maximise(line, 2);
			
		}
		
		return "" + sum;
		
	}
	
	public static String part2(List<List<Integer>> input) {
		
		long sum = 0;
		
		for(List<Integer> line : input) {
			
			sum += maximise(line, 12);
			
		}
		
		return "" + sum;
		
	}
	
	public static long maximise(List<Integer> list, int numbers) {
		
		if(numbers == 1)
			return Collections.max(list);
		
		int i = Collections.max(list.subList(0, list.size() - (numbers - 1)));
		
		return Long.parseLong("" + i + "" + maximise(list.subList(list.indexOf(i) + 1, list.size()), numbers-1));
		
	}
	
}
