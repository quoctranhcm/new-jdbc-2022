package com.tranvanquoc.paging;

import com.tranvanquoc.sort.Sorter;

public interface Pageble {
	Integer getPage();
	Integer getOffset();
	Integer getLimit();
	Sorter getSorter();

}
