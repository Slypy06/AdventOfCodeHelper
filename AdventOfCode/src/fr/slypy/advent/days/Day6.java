package fr.slypy.advent.days;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.slypy.advent.AdventParser;
import fr.slypy.advent.Couple;

public class Day6 {

	public static void main(String[] args) {

		AdventParser p = new AdventParser("day6.txt");
		
		List<String> lines = p.getLines();
		
		System.out.println("Part 1 : " + part1(lines));
		System.out.println();
		System.out.println("Part 2 : " + part2(lines));
		
	}
	
	public static String part1(List<String> lines) {
		
		
		List<Scanner> scanners = new ArrayList<>();
				
		for(String line : lines) {
			
			scanners.add(new Scanner(line.replaceAll(" +", " ")));
			
		}
		
		long tot = 0;
		
		while(scanners.get(scanners.size()-1).hasNext()) {
			
			switch(scanners.get(scanners.size()-1).next()) {
				
				case "+":
					
					long sum = 0;
					
					for(Scanner scan : scanners.subList(0, scanners.size()-1)) {
						
						sum += AdventParser.nextInt(scan);
						
					}
					
					tot += sum;
					
					break;
				case "*":
					
					long mul = 1;
					
					for(Scanner scan : scanners.subList(0, scanners.size()-1)) {
						
						int i = AdventParser.nextInt(scan);
						mul *= i;
						
					}
					
					tot += mul;
					
					break;
				
			}
			
		}
					
		return "" + tot;
		
	}
	
	public static String part2(List<String> lines) {
		
		List<List<String>> map = new ArrayList<>();
		List<Couple<Integer, Integer>> pos = new ArrayList<>();
		int lastI = lines.get(lines.size()-1).length();
		
		for(int i = lines.get(lines.size()-1).length()-1; i >= 0; i--) {
			
			if(lines.get(lines.size()-1).charAt(i) != ' ') {
				
				pos.add(new Couple<>(i, lastI));
				lastI = i - 1;
				
			}
			
		}
		
		for(Couple<Integer, Integer> c : pos) {
			
			List<String> column = new ArrayList<>();
			
			for(String line : lines) {
				
				if(lines.indexOf(line) == lines.size()-1) {
					column.add(line.substring(c.getFirst(), c.getSecond()).replaceAll(" ", ""));
				} else {
					column.add(line.substring(c.getFirst(), c.getSecond()));
				}
				
			}
			
			map.add(column);
			
		}
		
		long res = 0;
		
		for(List<String> prob : map) {
			
			if(prob.get(prob.size()-1).equals("+")) {
				
				long sum = 0;
				
				for(int i = prob.get(0).length() - 1; i >= 0; i--) {
					
					String s = "";
					
					for(String elem : prob.subList(0, prob.size()-1)) {
						
						s += elem.charAt(i);
						
					}
					
					sum += Integer.parseInt(s.replaceAll(" ", ""));
					
				}
				
				res += sum;
				
			} else {
				
				long mul = 1;
				
				for(int i = prob.get(0).length() - 1; i >= 0; i--) {
					
					String s = "";
					
					for(String elem : prob.subList(0, prob.size()-1)) {
						
						s += elem.charAt(i);
						
					}
					
					mul *= Integer.parseInt(s.replaceAll(" ", ""));
					
				}
				
				res += mul;
				
			}
			
		}
		
		return "" + res;
		
	}
	
}
