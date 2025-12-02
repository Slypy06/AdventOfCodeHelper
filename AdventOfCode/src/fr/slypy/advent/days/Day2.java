package fr.slypy.advent.days;

import java.util.List;

import fr.slypy.advent.AdventParser;
import fr.slypy.advent.Couple;

public class Day2 {

	public static void main(String[] args) {

		AdventParser p = new AdventParser("day2.txt");
		
		p.autoPrepareInput();
		
		List<Couple<Long,Long>> l = p.getLongCoupleInlined();

		System.out.println("Part 1 : " + part1(l));
		System.out.println("Part 2 : " + part2(l));
		
	}
	
	public static String part1(List<Couple<Long,Long>> input) {
		
		long sum = 0;
		
		for(Couple<Long, Long> c : input) {
			
			for(long l = c.getFirst(); l <= c.getSecond(); l++) {
				
				if(("" + l).matches("^(\\d+)\\1{2}$")) {

					sum+=l;
					
				}
				
			}

		}
		
		return "" + sum;
		
	}
	
	public static String part2(List<Couple<Long,Long>> input) {
		
		long sum = 0;
		
		for(Couple<Long, Long> c : input) {
			
			for(long l = c.getFirst(); l <= c.getSecond(); l++) {
				
				if(("" + l).matches("^(\\d+)\\1+$")) {

					sum+=l;
					
				}
				
			}

		}
		
		return "" + sum;
		
	}
	
}
