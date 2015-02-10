package search;

import java.util.ArrayList;

public class SearchQueryParsed {

	private ArrayList<String> nameWords = new ArrayList<String>();
	private ArrayList<String> lastNameWords = new ArrayList<String>();
	private ArrayList<String> emailWords = new ArrayList<String>();
	private ArrayList<String> phoneWords = new ArrayList<String>();
	private ArrayList<String> streetWords = new ArrayList<String>();
	private ArrayList<String> cityWords = new ArrayList<String>();
	private ArrayList<String> stateWords = new ArrayList<String>();
	private ArrayList<String> zipWords = new ArrayList<String>();
	
	private Integer from = 0;
	private Integer displayLimit = 10;
	
	public SearchQueryParsed(){}
	
	public ArrayList<String> getNameWords() {
		return nameWords;
	}
	
	public void addNameWord(String word) {
		this.nameWords.add(word);
	}
	
	public ArrayList<String> getLastNameWords() {
		return lastNameWords;
	}
	
	public void addLastNameWord(String word) {
		this.lastNameWords.add(word);
	}
	
	public ArrayList<String> getEmailWords() {
		return emailWords;
	}
	
	public void addEmailWord(String word) {
		this.emailWords.add(word);
	}
	
	public ArrayList<String> getPhoneWords() {
		return phoneWords;
	}
	
	public void addPhoneWords(String word) {
		this.phoneWords.add(word);
	}
	
	public ArrayList<String> getStreetWords() {
		return streetWords;
	}
	
	public void addStreetWords(String word) {
		this.streetWords.add(word);
	}
	
	public ArrayList<String> getCityWords() {
		return cityWords;
	}
	
	public void addCityWords(String word) {
		this.cityWords.add(word);
	}
	
	public ArrayList<String> getStateWords() {
		return stateWords;
	}
	
	public void addStateWords(String word) {
		this.stateWords.add(word);
	}
	
	public ArrayList<String> getZipWords() {
		return zipWords;
	}
	
	public void addZipWords(String word) {
		this.zipWords.add(word);
	}
	
	public Integer getFrom() {
		return from;
	}

	public Integer getDisplayLimit() {
		return displayLimit;
	}
}
