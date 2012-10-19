package com.mmounirou.spoty4j.api;

import com.mmounirou.spoty4j.core.Album;
import com.mmounirou.spoty4j.core.Artist;
import com.mmounirou.spoty4j.core.Track;
import com.mmounirou.spoty4j.xml.lookup.LookupAlbumResultProvider;
import com.mmounirou.spoty4j.xml.lookup.LookupArtistResultProvider;
import com.mmounirou.spoty4j.xml.lookup.LookupTrackResultProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class Lookup
{
	private static final String BASE_URL = "http://ws.spotify.com/lookup/1/";

	public static Artist fetchArtist(Artist artist)
	{
		ClientConfig config = new DefaultClientConfig(LookupArtistResultProvider.class);
		Client client = Client.create(config);
		WebResource resource = client.resource(BASE_URL);
		return resource.queryParam("uri", artist.getHref()).queryParam("extras", "albumdetail").get(Artist.class);

	}

	public static Album fetchAlbum(Album album)
	{
		ClientConfig config = new DefaultClientConfig(LookupAlbumResultProvider.class);
		Client client = Client.create(config);
		WebResource resource = client.resource(BASE_URL);
		return resource.queryParam("uri", album.getHref()).queryParam("extras", "trackdetail").get(Album.class);
	}

	public static Track fetchTrack(Track track)
	{
		ClientConfig config = new DefaultClientConfig(LookupTrackResultProvider.class);
		Client client = Client.create(config);
		WebResource resource = client.resource(BASE_URL);
		return resource.queryParam("uri", track.getHref()).get(Track.class);
	}
}
