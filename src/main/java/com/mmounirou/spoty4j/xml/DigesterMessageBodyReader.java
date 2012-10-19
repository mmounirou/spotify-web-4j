package com.mmounirou.spoty4j.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

public abstract class DigesterMessageBodyReader<T> implements MessageBodyReader<T>
{

	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
	{
		return getGenericClass().isAssignableFrom(type);
	}

	public T readFrom(Class<T> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException
	{
		Digester digester = new Digester();
		addRules(digester);

		try
		{
			return digester.parse(cleanStream(entityStream));
		}
		catch ( SAXException e )
		{
			throw new IOException(e);
		}
	}


	private ByteArrayInputStream cleanStream(InputStream entityStream) throws IOException, UnsupportedEncodingException
	{
		String strStream = CharStreams.toString(new InputStreamReader(entityStream, Charsets.UTF_8.name()));
		String clean = strStream.replace("&", "-");
		return new ByteArrayInputStream(clean.getBytes());
	}

	
	protected abstract void addRules(Digester digester);

	protected abstract Class<T> getGenericClass();

}
