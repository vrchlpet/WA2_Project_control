package org.cvut.wa2.projectcontrol.entities;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class DocumentEntity {
	
	@PrimaryKey
	private Key docKey;
	
	@Persistent
	private String docName;
	
	@Persistent
	private String href;

	
	
	public Key getDocKey() {
		return docKey;
	}

	public void setDocKey(Key docKey) {
		this.docKey = docKey;
	}
	
	
	
	public String getHref() {
		return href;
	}
	
	public void setHref(String href) {
		this.href = href;
	}
	
	public String getDocName() {
		return docName;
	}
	
	public void setDocName(String docName) {
		this.docName = docName;
	}

}
