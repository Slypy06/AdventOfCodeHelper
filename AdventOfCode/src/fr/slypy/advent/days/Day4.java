package fr.slypy.advent.days;

import java.util.ArrayList;
import java.util.List;

import fr.slypy.advent.AdventParser;
import fr.slypy.advent.Couple;
import fr.slypy.advent.Helper;

public class Day4 {

	public static void main(String[] args) {

		AdventParser p = new AdventParser("day4.txt");
		
		Couple<List<List<Character>>, Couple<Integer, Integer>> res = p.getCharacterMap();
		List<List<Character>> map = res.getFirst();
		Couple<Integer, Integer> dim = res.getSecond();
 		
		System.out.println("Part 1 : " + part1(map, dim));
		System.out.println();
		System.out.println("Part 2 : " + part2(map, dim));
		
	}
	
	public static String part1(List<List<Character>> map, Couple<Integer, Integer> dim) {
		
		return "" + get(map, dim).size();
		
	}
	
	public static String part2(List<List<Character>> map, Couple<Integer, Integer> dim) {
		
		List<Couple<Integer, Integer>> toRemove = get(map, dim);
		int sum = 0;
		
		while(toRemove.size() > 0) {
			
			sum += toRemove.size();
			
			List<List<Character>> newL = new ArrayList<>();
			
			for(int i = 0; i < dim.getFirst(); i++) {
				
				List<Character> line = new ArrayList<>();
				
				for(int j = 0; j < dim.getSecond(); j++) {
					
					line.add(toRemove.contains(new Couple<>(i, j)) ? '.' : map.get(i).get(j));
					
				}
				
				newL.add(line);
				
			}
			
			map = newL;
			toRemove = get(map, dim);
			
		}
			
		return "" + sum;
		 
	}
	
	public static List<Couple<Integer, Integer>> get(List<List<Character>> map, Couple<Integer, Integer> dim) {
		
		List<Couple<Integer, Integer>> res = new ArrayList<>();

		for(int i = 0; i < dim.getFirst(); i++) {
			
			for(int j = 0; j < dim.getSecond(); j++) {
				
				if(map.get(i).get(j) != '@')
					continue;
				
				int neighbours = 0;
				
				neighbours = (int) Helper.getNeighbours(map, new Couple<>(i, j)).stream().filter(c -> map.get(c.getFirst()).get(c.getSecond()) == '@').count();
				
				if(neighbours < 4) 
					res.add(new Couple<>(i, j));
				
			}
			
		}
		
		return res;
		
	}
	
}
