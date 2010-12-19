package app.models;

@javax.persistence.Entity
public class Code extends Entity {
	
	private String name;
	private String tags;
	private String lang;
	private String snippet;
	private String searchTags;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public String getTags() {
		return tags;
	}
	
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	public String getLang() {
		return lang;
	}
	
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	
	public String getSnippet() {
		return snippet;
	}
	
	public void clean()
	{
		this.setId(null);
		this.name = "";
		this.lang = "";
		this.snippet = "";
		this.tags = "";
		//this.searchTags = "";
	}

	public String getSearchTags() {
		return searchTags;
	}

	public void setSearchTags(String searchTags) {
		this.searchTags = searchTags;
	}
	
	
}
