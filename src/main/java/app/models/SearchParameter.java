package app.models;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;

@SessionScoped
@Component
public class SearchParameter {
	
	private String searchTag;

	public String getSearchTag() {
		return searchTag;
	}

	public void setSearchTag(String searchTag) {
		this.searchTag = searchTag;
	}
	
	

}
