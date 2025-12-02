package fr.slypy.advent;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class AdventParser {
	
	private InputStream input;
	
	public AdventParser(String file) {
		
		try {
		
			File f = new File(file);
			
			if(!f.exists()) {
				
				f.createNewFile();
				
			}
			
			if(f.length() == 0)
				new ProcessBuilder("notepad.exe", f.getAbsolutePath()).start();
			
			while(f.length() == 0) {
				
				System.out.println("Waiting for data");
				Thread.sleep(1000);
				
			}
			
			this.input = new FileInputStream(new File(file));
			
		} catch(IOException | InterruptedException e) {
			
			e.printStackTrace();
			System.exit(1);
			
		}
		
	}
	
	public void editInput(Function<String, String> func) {
		
		try {

			input = new ByteArrayInputStream(func.apply(new String(input.readAllBytes(), StandardCharsets.UTF_8)).getBytes(StandardCharsets.UTF_8));

		} catch (IOException e) {

			e.printStackTrace();

		}
		
	}
	
	public List<String> getLines() {
		
		Scanner scan = new Scanner(input);
		
		List<String> l = new ArrayList<String>();
		
		while(scan.hasNext()) {
			
			l.add(scan.nextLine());
			
		}
		
		scan.close();
		
		return l;
		
	}
	
	public List<Integer> getIntegerList() {
		
		Scanner scan = new Scanner(input);
		
		List<Integer> l = new ArrayList<>();
		
		Integer a;
		
		while((a = nextInt(scan)) != null) {
			
			l.add(a);
			
		}
		
		scan.close();
		
		return l;
		
	}
	
	public List<Double> getDoubleList() {
		
		Scanner scan = new Scanner(input);
		
		List<Double> l = new ArrayList<>();
		
		Double a;
		
		while((a = nextDouble(scan)) != null) {
			
			l.add(a);
			
		}
		
		scan.close();
		
		return l;
		
	}
	
	public List<Long> getLongList() {
		
		Scanner scan = new Scanner(input);
		
		List<Long> l = new ArrayList<>();

		Long a;
		
		while((a = nextLong(scan)) != null) {
			
			l.add(a);
			
		}
		
		scan.close();
		
		return l;
		
	}
	
	public List<Couple<Integer, Integer>> getIntegerCoupleInlined() {

	    Scanner scan = new Scanner(input);
	    List<Couple<Integer, Integer>> list = new ArrayList<>();

	    Integer a, b;
	    
	    while ((a = nextInt(scan)) != null && (b = nextInt(scan)) != null) {

	        list.add(new Couple<>(a, b));
	        
	    }

	    scan.close();
	    return list;
	    
	}
	
	public List<Couple<Double, Double>> getDoubleCoupleInlined() {

	    Scanner scan = new Scanner(input);
	    List<Couple<Double, Double>> list = new ArrayList<>();

	    Double a, b;
	    
	    while ((a = nextDouble(scan)) != null && (b = nextDouble(scan)) != null) {

	        list.add(new Couple<>(a, b));
	        
	    }

	    scan.close();
	    return list;
	    
	}
	
	public List<Couple<Long, Long>> getLongCoupleInlined() {

	    Scanner scan = new Scanner(input);
	    List<Couple<Long, Long>> list = new ArrayList<>();

	    Long a, b;
	    
	    while ((a = nextLong(scan)) != null && (b = nextLong(scan)) != null) {

	        list.add(new Couple<>(a, b));
	        
	    }

	    scan.close();
	    return list;
	    
	}
	
	public List<Couple<Long, Long>> getLongCouplesColumned() {
	
	    Scanner scan = new Scanner(input);

	    String lineA = scan.nextLine();
	    String lineB = scan.nextLine();
	
	    scan.close();
	
	    Scanner sa = new Scanner(lineA);
	    Scanner sb = new Scanner(lineB);
	
	    List<Couple<Long, Long>> list = new ArrayList<>();
	
	    Long a, b;
	    
	    while ((a = nextLong(sa)) != null && (b = nextLong(sb)) != null) {
	
	        list.add(new Couple<>(a, b));
	        
	    }
	
	    sa.close();
	    sb.close();
	
	    return list;
	    
	}
	
	public List<Couple<Integer, Integer>> getIntegerCouplesColumned() {
		
	    Scanner scan = new Scanner(input);

	    String lineA = scan.nextLine();
	    String lineB = scan.nextLine();
	
	    scan.close();
	
	    Scanner sa = new Scanner(lineA);
	    Scanner sb = new Scanner(lineB);
	
	    List<Couple<Integer, Integer>> list = new ArrayList<>();
	
	    Integer a, b;
	    
	    while ((a = nextInt(sa)) != null && (b = nextInt(sb)) != null) {
	
	        list.add(new Couple<>(a, b));
	        
	    }
	
	    sa.close();
	    sb.close();
	
	    return list;
	    
	}
	
	public List<Couple<Double, Double>> getDoubleCouplesColumned() {
		
	    Scanner scan = new Scanner(input);

	    String lineA = scan.nextLine();
	    String lineB = scan.nextLine();
	
	    scan.close();
	
	    Scanner sa = new Scanner(lineA);
	    Scanner sb = new Scanner(lineB);
	
	    List<Couple<Double, Double>> list = new ArrayList<>();
	
	    Double a, b;
	    
	    while ((a = nextDouble(sa)) != null && (b = nextDouble(sb)) != null) {
	
	        list.add(new Couple<>(a, b));
	        
	    }
	
	    sa.close();
	    sb.close();
	
	    return list;
	    
	}
	
	public Couple<List<List<Character>>, Couple<Integer, Integer>> getCharacterMap(char blank) {
		
		int width = 0;
		
		Scanner scan = new Scanner(input);
		List<List<Character>> l = new ArrayList<>();
		
		while(scan.hasNext()) {
			
			String s = scan.nextLine();
			
			l.add(s.chars().mapToObj(i -> (char) i).toList());
			
			if(s.length() > width)
				width = s.length();
			
		}
		
		for(List<Character> line : l) {
			
			for(int i = 0; i < width - line.size(); i++) {
				
				line.add(blank);
				
			}
			
		}
		
		scan.close();
		
		return new Couple<>(l, new Couple<>(l.size(), width));
		
	}
	
	public Couple<List<List<Character>>, Couple<Integer, Integer>> getCharacterMap() {
		
		return getCharacterMap('\0');
		
	}

	public <T> List<T> processLines(Function<String, T> processor) {
		
		return getLines().stream().map(processor).toList();
		
	}
	
	public void autoPrepareInput() {
		
		editInput(string -> string.replaceAll(",", " , ").replaceAll("=", " = ").replaceAll(":", " : ").replaceAll("-", " - "));
		
	}
	
	public String getInput() {
		
		try {

			return new String(input.readAllBytes(), StandardCharsets.UTF_8);

		} catch (IOException e) {

			e.printStackTrace();
			return "";

		}
		
	}
	
	public static Couple<Integer, Integer> nextIntCouple(Scanner s) {
	    Integer a = nextInt(s);
	    Integer b = nextInt(s);
	    return (a != null && b != null) ? new Couple<>(a, b) : null;
	}

	public static Couple<Long, Long> nextLongCouple(Scanner s) {
	    Long a = nextLong(s);
	    Long b = nextLong(s);
	    return (a != null && b != null) ? new Couple<>(a, b) : null;
	}

	public static Couple<Double, Double> nextDoubleCouple(Scanner s) {
	    Double a = nextDouble(s);
	    Double b = nextDouble(s);
	    return (a != null && b != null) ? new Couple<>(a, b) : null;
	}
	
	public static Couple<Integer, Integer> nextIntCouple(Scanner sa, Scanner sb) {
	    Integer a = nextInt(sa);
	    Integer b = nextInt(sb);
	    return (a != null && b != null) ? new Couple<>(a, b) : null;
	}

	public static Couple<Long, Long> nextLongCouple(Scanner sa, Scanner sb) {
	    Long a = nextLong(sa);
	    Long b = nextLong(sb);
	    return (a != null && b != null) ? new Couple<>(a, b) : null;
	}

	public static Couple<Double, Double> nextDoubleCouple(Scanner sa, Scanner sb) {
	    Double a = nextDouble(sa);
	    Double b = nextDouble(sb);
	    return (a != null && b != null) ? new Couple<>(a, b) : null;
	}
	
	public static Long nextLong(Scanner s) {
	    while (s.hasNext()) {
	        if (s.hasNextLong()) {
	            return s.nextLong();
	        } else {
	            s.next(); // skip invalid token
	        }
	    }
	    return null; // no more numbers
	}
	
	public static Double nextDouble(Scanner s) {
	    while (s.hasNext()) {
	        if (s.hasNextDouble()) {
	            return s.nextDouble();
	        } else {
	            s.next(); // skip invalid token
	        }
	    }
	    return null; // no more numbers
	}
	
	public static Integer nextInt(Scanner s) {
	    while (s.hasNext()) {
	        if (s.hasNextInt()) {
	            return s.nextInt();
	        } else {
	            s.next(); // skip invalid token
	        }
	    }
	    return null; // no more numbers
	}
	
}
