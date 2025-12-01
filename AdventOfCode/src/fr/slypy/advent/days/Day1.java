package fr.slypy.advent.days;

import java.util.List;

import fr.slypy.advent.AdventParser;
import fr.slypy.advent.Couple;

public class Day1 {
	
	public static void main(String[] args) {

		AdventParser p = new AdventParser("day1.txt");
		
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
