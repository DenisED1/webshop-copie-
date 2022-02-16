package controllers;

import java.util.ArrayList;

import domain.Aanbieding;

public class AanbiedingController {
	public static ArrayList<Aanbieding> getAllCurrentAanbiedingen() {
		return Aanbieding.getAllAanbiedingen();
	}
}
