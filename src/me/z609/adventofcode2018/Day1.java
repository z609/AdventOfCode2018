package me.z609.adventofcode2018;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Copyright (C) 2015-2018 z609 and zGames, all rights reserved. See
 * project license at https://www.z609.me/project/AdventOfCode2018
 * Created by Albert: December 02, 2018 - 09:00
 */
public class Day1 {

	public static void main(String[] args) throws IOException{
		long ms = System.currentTimeMillis();

		// general way of reading a file
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream in = Day1.class.getResourceAsStream("Day1.txt");
		int b;
		byte[] data = new byte[1024];
		while((b = in.read(data, 0, data.length)) != -1){
			out.write(data, 0, b);
		}
		out.flush();

		String input = new String(out.toByteArray());
		System.err.println((System.currentTimeMillis() - ms) + " ms to read file");
		ms = System.currentTimeMillis();

		Map<Integer, Integer> seen = new HashMap<Integer, Integer>();
		int result = 0;
		int realResult = Integer.MIN_VALUE;
		seen.put(0, 1);
		int duplicate = Integer.MIN_VALUE;
		String[] inputs = input.split("\\n");
		while(duplicate == Integer.MIN_VALUE){
			for(int i = 0; i < inputs.length; i++){
				String s = inputs[i].trim();
				if(!(s.startsWith("+") || s.startsWith("-")))
					continue;
				int num;
				try {
					num = Integer.parseInt(s.substring(1));
				} catch (NumberFormatException ex) {
					System.err.println("Error parsing \"" + s + "\"");
					continue;
				}
				if(s.startsWith("-"))
					num *= -1;
				result += num;

				if(realResult == Integer.MIN_VALUE && i == (inputs.length - 1))
					realResult = result;

				int timesSeen = seen.getOrDefault(result, 0) + 1;
				if(timesSeen == 2){
					duplicate = result;
					break;
				}
				seen.put(result, timesSeen);
			}
		}

		System.err.println((System.currentTimeMillis() - ms) + " ms to calculate results");
		System.err.println("Part 1 Result: " + realResult);
		System.err.println("Part 2 Result: " + duplicate);


	}

}
