package fr.slypy.advent.days;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import fr.slypy.advent.AdventParser;
import fr.slypy.advent.Couple;

public class Day9 {

	public static void main(String[] args) {

		AdventParser p = new AdventParser("day9.txt");
		
		p.autoPrepareInput();
		
		List<Couple<Long,Long>> coords = p.getLongCoupleInlined();
		
		System.out.println("Part 1 : " + part1(coords));
		System.out.println();
		System.out.println("Part 2 : " + part2(coords));
		
	}
	
	public static String part1(List<Couple<Long,Long>> coord) {
		
		long max = 0;
		
		for(Couple<Long, Long> c1 : coord) {
			
			for(Couple<Long, Long> c2 : coord) {
				
				if(Math.abs(c1.getFirst() - c2.getFirst()) * Math.abs(c1.getSecond() - c2.getSecond()) > max) {
					
					max = (Math.abs(c1.getFirst() - c2.getFirst()) + 1) * (Math.abs(c1.getSecond() - c2.getSecond()) + 1);
					
				}
				
			}
			
		}
		
		return "" + max;
		
	}
	
	public static String part2(List<Couple<Long,Long>> coord) {
		
		List<Couple<Couple<Long, Long>, Couple<Long, Long>>> horizontalLines = new ArrayList<>();
		List<Couple<Couple<Long, Long>, Couple<Long, Long>>> verticalLines = new ArrayList<>();
		
		List<Couple<Long, Long>> stack = new ArrayList<>();
		
		for(Couple<Long, Long> c1 : coord) {
			
			stack.add(c1);
			
			if(stack.size() == 2) {
				
				if(stack.get(0).getFirst() != c1.getFirst()) {
					
					if(stack.get(0).getFirst() < c1.getFirst()) {
						
						horizontalLines.add(new Couple<Couple<Long, Long>, Couple<Long, Long>>(stack.get(0), c1));
						
					} else {
						
						horizontalLines.add(new Couple<Couple<Long, Long>, Couple<Long, Long>>(c1, stack.get(0)));
						
					}
					
				} else {
					
					if(stack.get(0).getSecond() < c1.getSecond()) {
						
						verticalLines.add(new Couple<Couple<Long, Long>, Couple<Long, Long>>(stack.get(0), c1));
						
					} else {
						
						verticalLines.add(new Couple<Couple<Long, Long>, Couple<Long, Long>>(c1, stack.get(0)));
						
					}
					
				}
				
			}
			
		}
		
		
		
		return "";
		
	}
	
}
