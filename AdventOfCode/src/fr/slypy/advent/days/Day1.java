package fr.slypy.advent.days;

import java.util.List;

import fr.slypy.advent.AdventParser;
import fr.slypy.advent.Couple;

public class Day1 {
	
	public static void main(String[] args) {

		AdventParser p = new AdventParser("day1.txt"); 
		
		List<Couple<Character, Integer>> l = p.processLines(line -> {
			
			char c = line.charAt(0);
			int i = Integer.parseInt(line.substring(1));
			
			return new Couple<Character, Integer>(c,i);
			
		});
		
		System.out.println("Part 1 : " + part1(l));
		System.out.println("Part 2 : " + part2(l));
		
	}
	
	public static int part1(List<Couple<Character, Integer>> input) {
		
		int sum = 0;
		int dial = 50;
		
		for(Couple<Character, Integer> c : input) {
			
			if(c.getFirst() == 'R') {
				
				dial = incr(dial, c.getSecond());
				
				if(dial == 0)
					sum++;
				
			} else {
				
				dial = decr(dial, c.getSecond());
				
				if(dial == 0)
					sum++;

			}
			
		}
		
		return sum;
		
	}
	
	public static int part2(List<Couple<Character, Integer>> input) {
		
		int sum = 0;
		int dial = 50;
		
		for(Couple<Character, Integer> c : input) {
			
			if(c.getFirst() == 'R') {
				
				sum += (c.getSecond() + dial) / 100;
				
				dial = incr(dial, c.getSecond());
				
			} else {
				
				if(dial == 0)
					sum--; //to prevent counting the actual 0 a second time
				
				sum += (c.getSecond() + (100-dial)) / 100;
				
				dial = decr(dial, c.getSecond());

			}
			
		}
		
		return sum;
		
	}
	
	public static int incr(int dial) {
		
		return (dial+1)%100;
		
	}
	
	public static int decr(int dial) {
		
		return (dial-1)%100 >= 0 ? (dial-1)%100 : (dial+100-1)%100;
		
	}
	
	public static int incr(int dial, int amt) {
		
		return (dial+amt)%100;
		
	}
	
	public static int decr(int dial, int amt) {
		
		return (dial-amt)%100 >= 0 ? (dial-amt)%100 : ((dial-amt)%100)+100;
		
		List<Couple<Character, Integer>> list = p.processLines(line -> new Couple<>(line.charAt(0), Integer.parseInt(line.substring(1))));
		
		int sum = 0;
		int dial = 50;
		
		for(Couple<Character, Integer> mov : list) {
			
			if(mov.getFirst() == 'R') {
				
				sum += (mov.getSecond() + dial) / 100;
				dial = incr(dial, mov.getSecond());
				
			} else {
				
				if(dial == 0)
					sum--; //to prevent double counting 0
				
				sum += (mov.getSecond() + (100-dial)) / 100;
				dial = decr(dial, mov.getSecond());
				
			}
			
		}
		
		System.out.println(sum);
		
	}
	
	public static int incr(int dial, int amt) {
		
		return (dial+amt)%100;
		
	}
	
	public static int decr(int dial, int amt) {
		
		return (dial-amt)%100 >= 0 ? (dial-amt)%100 : ((dial-amt)%100)+100;
		
	}

}
