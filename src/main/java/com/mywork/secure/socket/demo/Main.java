package com.mywork.secure.socket.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
	public static void main(String args[]) throws IOException {		
		//File f= Paths.get("abc.txt").toFile();
		List<String> list = Files.readAllLines(Paths.get("abc.txt"));
		System.out.println(list.get(0));
		
	}
}
