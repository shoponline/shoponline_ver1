package cn.shop.bean;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {

	private Integer currentPage;

	private Integer pageSize = 10;

	private Integer rowCounts = 0;

	private Integer prePage = 1;

	private Integer nextPage = 1;

	private Integer pageCount = 0;

	private int currentRow = 0;
	
	public PageBean(){}
	
	private List<T> lists = new ArrayList<T>();

	public int getCurrentRow() {
		return (currentPage - 1) * pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getRowCounts() {
		return rowCounts;
	}

	public void setRowCounts(Integer rowCounts) {
		this.rowCounts = rowCounts;
	}

	public Integer getPrePage() {
		return (getCurrentPage() - 1) < 1 ? 1 : (getCurrentPage() - 1);
	}

	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}

	public Integer getNextPage() {
		return (getCurrentPage() + 1) > pageCount ? pageCount
				: (getCurrentPage() + 1);
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public List<T> getLists() {
		return lists;
	}

	public void setLists(List<T> lists) {
		this.lists = lists;
	}

	public Integer getPageCount() {
		if (rowCounts % pageSize == 0 && rowCounts > pageSize) {
			pageCount = rowCounts / pageSize;
		} else if (rowCounts % pageSize != 0 && rowCounts > pageSize) {
			pageCount = rowCounts / pageSize + 1;

		} else {
			pageCount = 1;
		}
		return pageCount;
	}
}
