package gerenciamentodefrota.infra;

import java.util.List;

public class Pagination<T> {

	private List<T> list;
	private Integer pageSize;
	private Integer pageNum;
	private Integer totalPage;
	private Integer totalCount;

	public Pagination(List<T> list, Integer pageSize, Integer pageNum,
			Integer totalPage, Integer totalCount) {
		this.list = list;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
	}

	public List<T> getList() {
		return list;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public Integer getPageNum() {
		return pageNum;
	}

}
