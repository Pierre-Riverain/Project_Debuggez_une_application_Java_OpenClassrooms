package com.hemebiotech.analytics;

import java.util.Map;

/**
 * Cette classe permet le démarrage du programme.
 * @author pierr
 *
 */
public class Main {

	public static void main(String[] args) {
		AnalyticsCounter analyticsCounter = new AnalyticsCounter("symptoms.txt");
		Map<String, Integer> mSymptoms = analyticsCounter.countSymptoms();
		analyticsCounter.sortSymptomsAlphabeticallyAndPrintResultToFile("results.out", mSymptoms);
	}

}
