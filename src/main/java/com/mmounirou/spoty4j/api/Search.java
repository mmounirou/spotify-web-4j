package com.mmounirou.spoty4j.api;

import com.google.common.collect.ImmutableList;
import com.mmounirou.spoty4j.core.Album;
import com.mmounirou.spoty4j.core.Artist;
import com.mmounirou.spoty4j.core.Track;
import com.mmounirou.spoty4j.xml.search.SearchAlbumResultProvider;
import com.mmounirou.spoty4j.xml.search.SearchAlbumResultProvider.AlbumSearchResult;
import com.mmounirou.spoty4j.xml.search.SearchArtistResultProvider;
import com.mmounirou.spoty4j.xml.search.SearchArtistResultProvider.ArtistSearchResult;
import com.mmounirou.spoty4j.xml.search.SearchTrackResultProvider;
import com.mmounirou.spoty4j.xml.search.SearchTrackResultProvider.TrackSearchResult;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class Search
{
	private static final String BASE_URL = "http://ws.spotify.com/search/1/";

	public static ImmutableList<Album> searchAlbum(String name)
	{
		//TODO add log

		ClientConfig config = new DefaultClientConfig(SearchAlbumResultProvider.class);
		Client client = Client.create(config);
		WebResource resource = client.resource(BASE_URL);
		AlbumSearchResult albumSearchResult = resource.path("album").queryParam("q", name).get(AlbumSearchResult.class);
		return albumSearchResult.getAlbums();
	}

	public static ImmutableList<Artist> searchArtist(String name)
	{
		//TODO add log
		ClientConfig config = new DefaultClientConfig(SearchArtistResultProvider.class);
		Client client = Client.create(config);
		WebResource resource = client.resource(BASE_URL);
		ArtistSearchResult artistSearchResult = resource.path("artist").queryParam("q", name).get(ArtistSearchResult.class);
		return artistSearchResult.getArtists();
	}

	public static ImmutableList<Track> searchTrack(String name)
	{
		//TODO add log

		ClientConfig config = new DefaultClientConfig(SearchTrackResultProvider.class);
		Client client = Client.create(config);
		WebResource resource = client.resource(BASE_URL);
		TrackSearchResult trackSearchResult = resource.path("track").queryParam("q", name).get(TrackSearchResult.class);
		return trackSearchResult.getTracks();
	}

}
