package fr.slypy.advent.days;

import java.util.List;

import fr.slypy.advent.AdventParser;
import fr.slypy.advent.Couple;
import fr.slypy.advent.Helper;

public class Day5 {

	public static void main(String[] args) {

		AdventParser p = new AdventParser("day5.txt");
		AdventParser p2 = new AdventParser("day5.txt");
		
		p.editInput(str -> str.split("\r\n\r\n")[0]);
		p2.editInput(str -> str.split("\\r\\n\\r\\n")[1]);

		p.autoPrepareInput();
		List<Couple<Long, Long>> ranges = p.getLongCoupleInlined();
		
		List<Long> ids = p2.getLongList();
		
		System.out.println("Part 1 : " + part1(ranges, ids));
		System.out.println();
		System.out.println("Part 2 : " + part2(ranges));
		
	}

	public static String part2(List<Couple<Long, Long>> ranges) {

		List<Couple<Long, Long>> sortedRanges = Helper.sort(ranges, (c1, c2) -> (c2.getFirst()-c1.getFirst()) >= 0);
		
		long prevMax = 0;
		
		long sum = 0;
		
		for(Couple<Long, Long> range : sortedRanges) {
			
			sum += Math.max(0, (range.getSecond() + 1) - Math.max(range.getFirst(), prevMax+1));
			prevMax = (long) Math.max(prevMax, range.getSecond());
			
		}
		
		return "" + sum;

	}

	public static String part1(List<Couple<Long, Long>> ranges, List<Long> ids) {

		int count = 0; 
		
		for(long l : ids) {
			
			boolean spoiled = true;
			
			for(Couple<Long, Long> range : ranges) {
				
				if(l>=range.getFirst() && l <= range.getSecond()) {
					
					spoiled = false;
					break;
					
				}
				
			}
			
			if(!spoiled) {
				
				count++;
				
			}
			
		}
		
		return "" + count;

	}
	
}
