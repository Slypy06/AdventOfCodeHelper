package fr.slypy.advent.days;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import fr.slypy.advent.AdventParser;
import fr.slypy.advent.Helper;

public class Day7 {

	public static void main(String[] args) {

		AdventParser p = new AdventParser("day7.txt");
		
		List<List<Character>> map = p.getCharacterMap().getFirst();

		System.out.println("Part 1 : " + part1(map));
		System.out.println();
		System.out.println("Part 2 : " + part2(map));

		
	}
	
	public static String part1(List<List<Character>> map) {
		
		Set<Integer> coords = new HashSet<>();
		
		coords.add(Helper.searchFirst(map, 'S').getSecond()); //get horizontal coord of the starting 'S'
		
		int sum = 0;
		
		for(int i = 1; i < map.size(); i++) {
			
			Set<Integer> newCoords = new HashSet<>();
			
			for(int j : coords) {
				
				if(map.get(i).get(j) == '.' && !newCoords.contains(j)) {
					
					newCoords.add(j); //nothing under so we just extends the line to the line bellow
					
				} else if(map.get(i).get(j) == '^') {  //we split and do not worry about duplicate because we are using a set
					
					sum += 1;

					newCoords.add(j-1);

					newCoords.add(j+1);
					
				}
				
			}
			
			coords = newCoords;
			
		}
		
		return "" + sum;
		
	}
	
	public static String part2(List<List<Character>> map) {
		
		Map<Integer, Long> coords = new HashMap<>();
		
		coords.put(Helper.searchFirst(map, 'S').getSecond(), 1l);
		
		long sum = 0;
		
		for(int i = 1; i < map.size(); i++) {
			
			Map<Integer, Long> newCoords = new HashMap<>();
			
			for(int j : coords.keySet()) {
				
				if(map.get(i).get(j) == '.') {
					
					newCoords.put(j, newCoords.getOrDefault(j, 0l) + coords.get(j));
					
				} else if(map.get(i).get(j) == '^') {
					
					newCoords.put(j-1, newCoords.getOrDefault(j-1, 0l) + coords.get(j));
					newCoords.put(j+1, newCoords.getOrDefault(j+1, 0l) + coords.get(j));
					
				}
				
			}
			
			coords = newCoords;
			
		}
		
		for(Entry<Integer, Long> e : coords.entrySet()) {
			
			sum += e.getValue();
			
		}
		
		return "" + sum;

	}
	
}
