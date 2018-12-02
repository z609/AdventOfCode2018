package me.z609.adventofcode2018;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Copyright (C) 2015-2018 z609 and zGames, all rights reserved. See
 * project license at https://www.z609.me/project/AdventOfCode2018
 * Created by Albert: December 02, 2018 - 09:56
 */
public class Day2 {

	public static void main(String[] args) throws IOException{
		long ms = System.currentTimeMillis();

		// general way of reading a file
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream in = Day1.class.getResourceAsStream("Day2.txt");
		int b;
		byte[] data = new byte[1024];
		while((b = in.read(data, 0, data.length)) != -1){
			out.write(data, 0, b);
		}
		out.flush();

		String input = new String(out.toByteArray());
		System.err.println((System.currentTimeMillis() - ms) + " ms to read file");
		ms = System.currentTimeMillis();
		String[] inputs = input.split("\\n");

		int result = 1;
		int runTimes = 2;
		Map<Integer, Integer> results = new HashMap<Integer, Integer>();
		for(int i = 0 ; i < inputs.length; i++){
			String s = inputs[i].trim();
			char[] chars = s.toCharArray();
			Map<Character, Integer> characters = new HashMap<Character, Integer>();
			for(int ii = 0; ii < chars.length; ii++){
				char c = chars[ii];
				characters.put(c, characters.getOrDefault(c, 0) + 1);
			}
			int run = 1;
			while(run <= runTimes){
				final int timesNeeded = run + 1;
				Set<Character> keys = characters.keySet();
				for(char c : keys){
					if(characters.get(c) == timesNeeded){
						//System.err.println(c + " happens " + characters.get(c) + " in " + s);
						results.put(timesNeeded, results.getOrDefault(timesNeeded, 0) + 1);
						break;
					}
				}
				run++;
			}
		}

		int run = 1;
		while(run++ <= runTimes){
			System.err.println("Results for " + run + ": " +results.get(run));
			result *= results.get(run);
		}

		System.err.println((System.currentTimeMillis() - ms) + " ms to calculate results");
		System.err.println("Final result: " + result);

	}

}
