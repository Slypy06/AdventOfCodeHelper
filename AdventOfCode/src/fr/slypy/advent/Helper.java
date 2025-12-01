package fr.slypy.advent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Helper {
	
	public static BiFunction<Integer, Integer, Boolean> IntegerComparator = (a, b) -> a>b;
	public static BiFunction<Double, Double, Boolean> DoubleComparator = (a, b) -> a>b;
	public static BiFunction<Long, Long, Boolean> LongComparator = (a, b) -> a>b;
	public static BiFunction<String, String, Boolean> StringComparator = (a, b) -> a.compareTo(b) > 0;
	public static BiFunction<Character, Character, Boolean> CharacterComparator = (a, b) -> a.compareTo(b) > 0;

	public static BiFunction<Couple<Integer, Integer>, Couple<Integer, Integer>, Double> ManhattanHeuristic = (start, goal) -> Math.abs((double) start.getFirst() - goal.getFirst()) + Math.abs((double) start.getSecond() - goal.getSecond());
	public static BiFunction<Couple<Integer, Integer>, Couple<Integer, Integer>, Double> ChebyshevHeuristic = (start, goal) -> Math.max(Math.abs((double) start.getFirst() - goal.getFirst()), Math.abs((double) start.getSecond() - goal.getSecond()));
	public static BiFunction<Couple<Integer, Integer>, Couple<Integer, Integer>, Double> DijkstraHeuristic = (_1, _2) -> 0.0d;
	
	public static List<Couple<Integer, Integer>> StandartDirections = Arrays.asList(new Couple<Integer, Integer>(-1, 0), new Couple<Integer, Integer>(1, 0), new Couple<Integer, Integer>(0, 1), new Couple<Integer, Integer>(0, -1));
	public static List<Couple<Integer, Integer>> DiagonalDirections = Arrays.asList(new Couple<Integer, Integer>(-1, 0), new Couple<Integer, Integer>(1, 0), new Couple<Integer, Integer>(0, 1), new Couple<Integer, Integer>(0, -1), new Couple<Integer, Integer>(-1, 1), new Couple<Integer, Integer>(1, 1), new Couple<Integer, Integer>(-1, -1), new Couple<Integer, Integer>(-1, -1));
	
	public static <T> Couple<T, Integer> max(List<T> list, BiFunction<T, T, Boolean> comparator) {

		T max = null;
		int index = -1;

		for (int i = 0; i < list.size(); i++) {
			
			T t = list.get(i);

			if (comparator.apply(t, max)) {

				max = t;
				index = i;

			}

		}

		return new Couple<>(max, index);

	}

	public static <T> Couple<T, Integer> min(List<T> list, BiFunction<T, T, Boolean> comparator) {

		T min = null;
		int index = -1;

		for (int i = 0; i < list.size(); i++) {
			
			T t = list.get(i);

			if (comparator.apply(min, t)) {

				min = t;
				index = i;

			}

		}

		return new Couple<>(min, index);

	}

	public static <T> List<T> sort(List<T> input, BiFunction<T, T, Boolean> comparator) {

		if (input.size() <= 1) {

			return new ArrayList<>(input);

		}

		T pivot = input.get(0);

		List<T> less = new ArrayList<>();
		List<T> greater = new ArrayList<>();

		for (int i = 1; i < input.size(); i++) {

			T element = input.get(i);

			if (comparator.apply(element, pivot)) {

				less.add(element);

			} else {

				greater.add(element);

			}

		}

		List<T> sorted = new ArrayList<>();
		sorted.addAll(sort(less, comparator));
		sorted.add(pivot);
		sorted.addAll(sort(greater, comparator));

		return sorted;

	}

	public static <T> int count(List<T> list, T element) {

		int i = 0;

		for (T t : list) {

			if (element.equals(t))
				i++;

		}

		return i;

	}

	public static List<Couple<Integer, Integer>> aStar(List<List<Integer>> maze, Couple<Integer, Integer> start, Couple<Integer, Integer> goal, BiFunction<Couple<Integer, Integer>, Couple<Integer, Integer>, Double> heuristic, List<Couple<Integer, Integer>> directions) {

		int rows = maze.size();
		int cols = maze.get(0).size();

		PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingDouble(Node::f));
		boolean[][] closed = new boolean[rows][cols];

		Node startNode = new Node(start, null, 0, heuristic.apply(start, goal));
		open.add(startNode);

		while (!open.isEmpty()) {

			Node current = open.poll();

			if (current.pos.getFirst().equals(goal.getFirst()) && current.pos.getSecond().equals(goal.getSecond())) {

				// Reconstruct path
				List<Couple<Integer, Integer>> path = new ArrayList<>();

				while (current != null) {

					path.add(current.pos);
					current = current.parent;

				}

				Collections.reverse(path);
				return path;

			}

			int x = current.pos.getFirst();
			int y = current.pos.getSecond();
			closed[x][y] = true;

			for (Couple<Integer, Integer> dir : directions) {

				int nx = x + dir.getFirst();
				int ny = y + dir.getSecond();

				if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && maze.get(nx).get(ny) == 0 && !closed[nx][ny]) {

					double gCost = current.g + 1; // assuming cost to move = 1
					Couple<Integer, Integer> neighborPos = new Couple<>(nx, ny);
					double hCost = heuristic.apply(neighborPos, goal);
					Node neighbor = new Node(neighborPos, current, gCost, hCost);

					boolean skip = open.stream().anyMatch(
							n -> n.pos.getFirst().equals(nx) && n.pos.getSecond().equals(ny) && n.f() <= neighbor.f());

					if (!skip) {

						open.add(neighbor);

					}

				}

			}

		}

		return Collections.emptyList(); // No path found

	}
	
	public static List<Couple<Integer, Integer>> aStar(List<List<Integer>> maze, Couple<Integer, Integer> start, Couple<Integer, Integer> goal, BiFunction<Couple<Integer, Integer>, Couple<Integer, Integer>, Double> heuristic, List<Couple<Integer, Integer>> directions, BiFunction<Couple<Integer, Integer>, Node, Double> costFunction) {

		int rows = maze.size();
		int cols = maze.get(0).size();

		PriorityQueue<Node> open = new PriorityQueue<>(Comparator.comparingDouble(Node::f));
		boolean[][] closed = new boolean[rows][cols];

		Node startNode = new Node(start, null, 0, heuristic.apply(start, goal));
		open.add(startNode);

		while (!open.isEmpty()) {

			Node current = open.poll();

			if (current.pos.getFirst().equals(goal.getFirst()) && current.pos.getSecond().equals(goal.getSecond())) {

				// Reconstruct path
				List<Couple<Integer, Integer>> path = new ArrayList<>();

				while (current != null) {

					path.add(current.pos);
					current = current.parent;

				}

				Collections.reverse(path);
				return path;

			}

			int x = current.pos.getFirst();
			int y = current.pos.getSecond();
			closed[x][y] = true;

			for (Couple<Integer, Integer> dir : directions) {

				int nx = x + dir.getFirst();
				int ny = y + dir.getSecond();

				if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && maze.get(nx).get(ny) == 0 && !closed[nx][ny]) {

					double gCost = current.g + costFunction.apply(dir, current);
					Couple<Integer, Integer> neighborPos = new Couple<>(nx, ny);
					double hCost = heuristic.apply(neighborPos, goal);
					Node neighbor = new Node(neighborPos, current, gCost, hCost);

					boolean skip = open.stream().anyMatch(
							n -> n.pos.getFirst().equals(nx) && n.pos.getSecond().equals(ny) && n.f() <= neighbor.f());

					if (!skip) {

						open.add(neighbor);

					}

				}

			}

		}

		return Collections.emptyList(); // No path found

	}
	
	public static <T> void printMap(List<List<T>> map, Consumer<T> elemPrinter) {
		
		for(List<T> line : map) {
			
			line.forEach(elemPrinter);
			System.out.println();
			
		}
		
	}
	
	public static <T> Couple<Integer, Integer> searchFirst(List<List<T>> map, T elem) {

		for(List<T> line : map) {
			
			for(T t : line) {
				
				if(t.equals(elem)) {
					
					return new Couple<>(map.indexOf(line), line.indexOf(t));
					
				}
				
			}
			
		}
		
		return null;
		
	}
	
	public static <T> List<Couple<Integer, Integer>> search(List<List<T>> map, T elem) {

		List<Couple<Integer, Integer>> res = new ArrayList<>();
		
		for(List<T> line : map) {
			
			for(T t : line) {
				
				if(t.equals(elem)) {
					
					res.add(new Couple<>(map.indexOf(line), line.indexOf(t)));
					
				}
				
			}
			
		}
		
		return res;
		
	}
	
	public static <K, V> List<List<V>> mapMap(List<List<K>> map, Function<K, V> func) {
		
		List<List<V>> res = new ArrayList<>();
		
		for(List<K> line : map) {
			
			List<V> newLine = new ArrayList<>();
			
			line.forEach(e -> newLine.add(func.apply(e)));
			
			res.add(newLine);
			
		}
		
		return res;
		
	}

	public static class Node {

		public Couple<Integer, Integer> pos;
		public Node parent;
		public double g; // Cost from start to this node
		public double h; // Heuristic cost to goal

		public Node(Couple<Integer, Integer> pos, Node parent, double g, double h) {

			this.pos = pos;
			this.parent = parent;
			this.g = g;
			this.h = h;

		}

		public double f() {

			return g + h;

		}

	}

}
