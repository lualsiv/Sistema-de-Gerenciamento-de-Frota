package gerenciamentodefrota.infra;

import java.util.List;

public class Pagination<T> {

	private List<T> list;
	private Integer pageSize;
	private Integer pageNum;
	private Integer totalPage;
	private Integer totalCount;
	public static final int PAGESIZE = 15;
	
	@SuppressWarnings("static-access")
	public Pagination(List<T> list, Integer pageSize, Integer pageNum, Integer totalPage, Integer totalCount) {
		this.list = list;
		this.pageSize = pageSize == null ? this.PAGESIZE : pageSize;
		this.pageNum = pageNum;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
	}

	public List<T> getList() {
		return list;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public boolean hasNext() {
		return pageNum < totalPage;
	}
	
	public boolean hasPrev() {
		return pageNum > 1;
	}
	
}
