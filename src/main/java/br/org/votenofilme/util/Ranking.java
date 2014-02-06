package br.org.votenofilme.util;

import java.io.Serializable;

public class Ranking implements Serializable {
	private String filmName;
	private int numberOfVotes;
	
	public Ranking(String filmName) {
		this.filmName = filmName;
	}
	
	public Ranking(String filmName, int numberOfVotes) {
		this.filmName = filmName;
		this.numberOfVotes = numberOfVotes;
	}
	
	public String getFilm() {
		return filmName;
	}
	public void setFilm(String filmName) {
		this.filmName = filmName;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((filmName == null) ? 0 : filmName.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ranking other = (Ranking) obj;
		if (filmName == null) {
			if (other.filmName != null)
				return false;
		} else if (!filmName.equals(other.filmName))
			return false;
		return true;
	}

	public int getNumberOfVotes() {
		return numberOfVotes;
	}
	public void setNumberOfVotes(int numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}
}