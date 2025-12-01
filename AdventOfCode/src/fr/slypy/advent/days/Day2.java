package fr.slypy.advent.days;

import java.util.List;

import fr.slypy.advent.AdventParser;
import fr.slypy.advent.Couple;
import fr.slypy.advent.Helper;

public class Day2 {

	public static void main(String[] args) {

		AdventParser p = new AdventParser("day2.txt");
		
		Couple<List<List<Character>>,Couple<Integer,Integer>> res = p.getCharacterMap();
		List<List<Character>> map = res.getFirst();
		Couple<Integer, Integer> dim = res.getSecond();
		
		List<List<Integer>> aStarMap = Helper.mapMap(map, c -> c == '#' ? 1 : 0);
		List<Couple<Integer, Integer>> path = Helper.aStar(aStarMap, Helper.searchFirst(map, 'S'), Helper.searchFirst(map, 'E'), Helper.DijkstraHeuristic, Helper.StandartDirections, (dir, node) -> {
		
			
			Couple<Integer, Integer> prevDir = new Couple<>(-1, 0);
			
			if(node.parent != null)
				prevDir = new Couple<>(node.pos.getFirst() - node.parent.pos.getFirst(), node.pos.getFirst() - node.parent.pos.getFirst());
			
			System.out.println("Prevdir : " + prevDir + " | dir : " + dir);
			
			if(prevDir.equals(dir))
				return 1.0d;
			
			if(prevDir.equals(new Couple<>(-dir.getFirst(), -dir.getSecond())))
				return 2001.0d;
				
			return 1001.0d;
			
		});
		
		Helper.printMap(map, System.out::print);
		
		System.out.println();
		
		for(int i = 0; i < map.size(); i++) {
			
			List<Character> line = map.get(i);
			
			for(int j = 0; j < line.size(); j++) {
				
				Character c = line.get(j);
				
				if(c == '.' && path.contains(new Couple<Integer, Integer>(i, j)))
					c = '$';
				
				System.out.print(c);
				
			}
			
			System.out.println();
			
		}
		
		System.out.println(path.get(0));
		
	}
	
}
