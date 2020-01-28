package com.hemebiotech.analytics;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Cette classe permet de compter le nombre d'occurrences de chaque effet secondaire, les tries en ordre alphab�tique et supprimes les doublons.
 * @author pierre
 *
 */
public class AnalyticsCounter {
	
	/**
	 * Cette liste contient tous les effets secondaires � trier et � compter.
	 */
	private List<String> listSymptoms = null;
	
	/**
	 * Cet objet permet d'ouvrir le fichier, d'extraire les effets secondaires et de les inclure dans la liste <code>listSymptom</code> afin d'�tre trait�.
	 */
	private ISymptomReader symptomReader = null;
	
	/**
	 * Ce constructeur va ouvrir le fichier indiqu�, extraire les effets secondaires et de les inclure dans une liste.
	 * @param filePath chemin du fichier.
	 */
	public AnalyticsCounter(String filePath) {
		symptomReader = new ReadSymptomDataFromFile(filePath);
		listSymptoms = symptomReader.getSymptoms();
	}
	
	/**
	 * Cette m�thode compte les effets secondaires.
	 */
	public Map<String, Integer> countSymptoms() {
		Map<String, Integer> listCountSymptom = new HashMap<String, Integer>();
		listSymptoms.stream().forEach(symptom->{
			if(listCountSymptom.containsKey(symptom)) {
				listCountSymptom.put(symptom, listCountSymptom.get(symptom) + 1);
			} else {
				listCountSymptom.put(symptom, 1);
			}
		});
		
		return listCountSymptom;
	}
	
	/**
	 * Cette m�thode va trier les effets secondaires par ordre alphab�tique et enregistrer le r�sultat dans le fichier pass� en param�tre.
	 * @param fileOutName chemin du fichier.
	 */
	public void sortSymptomsAlphabeticallyAndPrintResultToFile(String fileOutName, Map<String, Integer> listCountSymptom) {
		try {
			if (fileOutName == null || listCountSymptom == null) {
				throw new IllegalArgumentException("Les param�tres de la m�thode sortSymptomsAlphabeticallyAndPrintResultToFile ne doivent pas �tre nulle.");
			}
			PrintStream printStream = new PrintStream(fileOutName);
			listCountSymptom.keySet().stream().sorted((String o1, String o2) -> o1.compareToIgnoreCase(o2)).forEach((String symptom) -> {
				printStream.println(symptom + " : " + listCountSymptom.get(symptom));
			});
			printStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ISymptomReader getSymptomReader() {
		return symptomReader;
	}

	public void setSymptomReader(ISymptomReader symptomReader) {
		this.symptomReader = symptomReader;
	}
}
