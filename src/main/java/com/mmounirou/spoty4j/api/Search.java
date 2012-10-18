package com.mmounirou.spoty4j.api;

import com.google.common.collect.ImmutableList;
import com.mmounirou.spoty4j.core.Album;
import com.mmounirou.spoty4j.core.Artist;
import com.mmounirou.spoty4j.core.Track;
import com.mmounirou.spoty4j.xml.AlbumSearchResultProvider;
import com.mmounirou.spoty4j.xml.TrackSearchResultProvider;
import com.mmounirou.spoty4j.xml.AlbumSearchResultProvider.AlbumSearchResult;
import com.mmounirou.spoty4j.xml.ArtistSearchResultProvider;
import com.mmounirou.spoty4j.xml.ArtistSearchResultProvider.ArtistSearchResult;
import com.mmounirou.spoty4j.xml.TrackSearchResultProvider.TrackSearchResult;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class Search
{
	private static final String BASE_URL = "http://ws.spotify.com/search/1/";

	public static ImmutableList<Album> searchAlbum(String name)
	{
		ClientConfig config = new DefaultClientConfig(AlbumSearchResultProvider.class);
		Client client = Client.create(config);
		WebResource resource = client.resource(BASE_URL);
		AlbumSearchResult albumSearchResult = resource.path("albums").queryParam("q", name).get(AlbumSearchResult.class);
		return albumSearchResult.getAlbums();
	}

	public static ImmutableList<Artist> searchArtist(String name)
	{
		ClientConfig config = new DefaultClientConfig(ArtistSearchResultProvider.class);
		Client client = Client.create(config);
		WebResource resource = client.resource(BASE_URL);
		ArtistSearchResult artistSearchResult = resource.path("artists").queryParam("q", name).get(ArtistSearchResult.class);
		return artistSearchResult.getArtists();
	}

	public static ImmutableList<Track> searchTrack(String name)
	{
		ClientConfig config = new DefaultClientConfig(TrackSearchResultProvider.class);
		Client client = Client.create(config);
		WebResource resource = client.resource(BASE_URL);
		TrackSearchResult trackSearchResult = resource.path("tracks").queryParam("q", name).get(TrackSearchResult.class);
		return trackSearchResult.getTracks();
	}

}
