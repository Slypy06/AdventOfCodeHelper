package fr.slypy.advent.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.slypy.advent.AdventParser;
import fr.slypy.advent.Couple;
import fr.slypy.advent.Helper;
import fr.slypy.advent.Triplet;

public class Day8 {

	public static void main(String[] args) {

		AdventParser p = new AdventParser("day8.txt");
		
		List<Triplet<Integer,Integer,Integer>> coords = p.processLines(line -> {
			
			String[] ss = line.split(",");
			
			return new Triplet<>(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]), Integer.parseInt(ss[2]));
			
		});
		
		System.out.println("Part 1 : " + part1(coords));
		System.out.println();
		System.out.println("Part 2 : " + part2(coords));
		
	}
	
	public static String part1(List<Triplet<Integer,Integer,Integer>> coords) {
		
		List<Couple<Triplet<Integer,Integer,Integer>, Triplet<Integer,Integer,Integer>>> couples = new ArrayList<>();
		
		System.out.println("Creating couples");
		
		for(int i = 0; i < coords.size(); i++) {
			
			for(int j = i+1; j < coords.size(); j++) {
				
				couples.add(new Couple<>(coords.get(i), coords.get(j)));
				
			}
			
		}
		
		System.out.println("Sorting couples");
		
		couples = Helper.sort(couples, (c1, c2) -> distance(c2.getFirst(), c2.getSecond()) - distance(c1.getFirst(), c1.getSecond()) > 0);
		
		System.out.println("Linking couples");
		
		List<List<Triplet<Integer,Integer,Integer>>> circuits = new ArrayList<>();
		
		int max = 1000;
		
		for(int i = 0; i < max; i++) {
			
			Couple<Triplet<Integer,Integer,Integer>, Triplet<Integer,Integer,Integer>> c = couples.get(i);
			
			boolean found = false;
			List<List<Triplet<Integer,Integer,Integer>>> circuitsContainingElem = new ArrayList<>();
			
			for(int j = 0; j < circuits.size(); j++) {
				
				List<Triplet<Integer,Integer,Integer>> circuit = circuits.get(j);
				
				if(circuit.contains(c.getFirst()) || circuit.contains(c.getSecond())) {

					found = true;
					
					if(circuit.contains(c.getSecond()) && circuit.contains(c.getFirst())) {
						
						break;
						
					}

					circuits.remove(circuit);
					circuit.add(circuit.contains(c.getFirst()) ? c.getSecond() : c.getFirst());
					circuitsContainingElem.add(circuit);
					j--;
					
				}
				
			}
			
			if(!found) {
				
				List<Triplet<Integer,Integer,Integer>> newCircuit = new ArrayList<>();
				newCircuit.add(c.getFirst());
				newCircuit.add(c.getSecond());
				
				circuits.add(newCircuit);
				
			} else {
				
				Set<Triplet<Integer,Integer,Integer>> mergedCircuit = new HashSet<>();
				
				circuitsContainingElem.forEach(l -> mergedCircuit.addAll(l));
				
				circuits.add(new ArrayList<>(mergedCircuit));
				
			}
			
		}
		
		circuits = Helper.sort(circuits, (l1,l2) -> l1.size()-l2.size()>0);
		
		System.out.println("Calculating result");
		
		int mul = 1;
		
		for(int i = 0; i < Math.min(3, circuits.size()); i++) {
			
			mul *= circuits.get(i).size();
			
		}
		
		return "" + mul;
		
	}
	
	public static String part2(List<Triplet<Integer,Integer,Integer>> coords) {
		
		List<Couple<Triplet<Integer,Integer,Integer>, Triplet<Integer,Integer,Integer>>> couples = new ArrayList<>();
		
		System.out.println("Creating couples");
		
		for(int i = 0; i < coords.size(); i++) {
			
			for(int j = i+1; j < coords.size(); j++) {
				
				couples.add(new Couple<>(coords.get(i), coords.get(j)));
				
			}
			
		}
		
		System.out.println("Sorting couples");
		
		couples = Helper.sort(couples, (c1, c2) -> distance(c2.getFirst(), c2.getSecond()) - distance(c1.getFirst(), c1.getSecond()) > 0);
		
		System.out.println("Linking couples");
		
		List<List<Triplet<Integer,Integer,Integer>>> circuits = new ArrayList<List<Triplet<Integer,Integer,Integer>>>(coords.stream().map(e -> new ArrayList<Triplet<Integer,Integer,Integer>>(Arrays.asList(e))).toList());
		
		int max = couples.size();
		
		for(int i = 0; i < max; i++) {

			Couple<Triplet<Integer,Integer,Integer>, Triplet<Integer,Integer,Integer>> c = couples.get(i);

			List<List<Triplet<Integer,Integer,Integer>>> circuitsContainingElem = new ArrayList<>();
			
			for(int j = 0; j < circuits.size(); j++) {
				
				List<Triplet<Integer,Integer,Integer>> circuit = circuits.get(j);
				
				if(circuit.contains(c.getFirst()) || circuit.contains(c.getSecond())) {

					circuits.remove(circuit);
					circuit.add(circuit.contains(c.getFirst()) ? c.getSecond() : c.getFirst());
					circuitsContainingElem.add(circuit);
					j--;
					
				}
				
			}

			Set<Triplet<Integer,Integer,Integer>> mergedCircuit = new HashSet<>();
				
			circuitsContainingElem.forEach(l -> mergedCircuit.addAll(l));
				
			circuits.add(new ArrayList<>(mergedCircuit));
			
			if(circuits.size() == 1) {
				
				return "" + (c.getFirst().getFirst() * c.getSecond().getFirst());
				
			}
			
		}
		
		return "Error";
		
	}
	
	public static double distance(Triplet<Integer,Integer,Integer> t1, Triplet<Integer,Integer,Integer> t2) {
		
		return Math.sqrt(Math.pow(Math.abs(t1.getFirst() - t2.getFirst()), 2) + Math.pow(Math.abs(t1.getSecond() - t2.getSecond()), 2) + Math.pow(Math.abs(t1.getThird() - t2.getThird()), 2));
		
	}
	
}
