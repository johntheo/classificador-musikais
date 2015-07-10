package com.musikais.classificador;

import java.util.List;

import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.Params;
import com.echonest.api.v4.Song;
import com.musikais.db.Database;

public class App {

	private static final String API_KEY = "NNJCFLJXI6J43MNVO";

	public static void main(String[] args) throws Exception {

		EchoNestAPI echoNest = new EchoNestAPI(API_KEY);
		Params p = new Params();
		p.add("artist", "ruiz");
		p.add("title", "quirera");
		p.add("results", 1);
		List<Song> songs = echoNest.searchSongs(p);

		Database database = new Database();
		for (Song song : songs) {
			database.inserirMusicaEchonest(song.getID(), song.getArtistID(),
					song.getArtistName(), song.getTitle(), song.getTempo(),
					song.getEnergy(), song.getDouble("audio_summary.valence"),
					false);
		}

	}

}
