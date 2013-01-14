package com.globo.recommender;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.mahout.cf.taste.impl.model.MemoryIDMigrator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;

class GloboComDataModel extends FileDataModel{

	private static final long serialVersionUID = 1L;

	private MemoryIDMigrator memoryIDMigrator;
	
	public GloboComDataModel(File dataFile) throws IOException {
		super(dataFile);
	}
	
	@Override
	protected long readItemIDFromString(String stringUrl) {
		if (memoryIDMigrator == null) {
			memoryIDMigrator = new MemoryIDMigrator();
		}
		
		memoryIDMigrator.initialize(Arrays.asList(stringUrl));
		 
		long id = memoryIDMigrator.toLongID(stringUrl);
		memoryIDMigrator.storeMapping(id, stringUrl);
		
		return id;
	}
	
	
	protected String reverseItemMapping(long id) {
		String stringUrl = memoryIDMigrator.toStringID(id);
		return stringUrl;
	}
	
	protected long rememberItemMapping(String stringUrl) {
		long id = memoryIDMigrator.toLongID(stringUrl);
		return id;
	}
}
