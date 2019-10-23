package com.demo.springboot.vue.base.servcie.bo;


import com.demo.springboot.vue.common.Constants;

import java.util.List;

/**
 * 分页实体类，用于View层向Service层、Dao层传递分页数据<br/>
 * 
 * pageNum和pageSize由view层设置，而total由service调用dao的count方法后设置
 * 
 * @author 王庆丰
 */
public class Page<E> {
	/**
	 * 页码，从1开始
	 */
	private int pageNum = 1;

	/**
	 * 页面大小
	 */
	private int pageSize = 0;

	/**
	 * 起始行
	 */
	private long startRow = 0;
	/**
	 * 末行
	 */
	private long endRow = 0;

	/**
	 * 总行数,默认为-1,表示没有被设置过
	 */
	private long total = -1;

	/**
	 * 总页数
	 */
	private int pages = 0;

	// 结果集
	private List<E> result = null;

	/**
	 * 默认构造函数
	 */
	public Page() {
		this(1, Constants.DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 构造函数，
	 * 
	 * @param pageNum
	 *            从1开始
	 * @param pageSize
	 */
	public Page(int pageNum, int pageSize) {
		this.setPageNum(pageNum);
		this.setPageSize(pageSize);
	}

	public List<E> getResult() {
		return result;
	}

	public void setResult(List<E> result) {
		this.result = result;
	}

	public int getPages() {
		return pages;
	}

	public Page<E> setPages(int pages) {
		this.pages = pages;
		return this;
	}

	public long getEndRow() {
		return endRow;
	}

	public Page<E> setEndRow(long endRow) {
		this.endRow = endRow;
		return this;
	}

	public int getPageNum() {
		return pageNum;
	}

	public Page<E> setPageNum(int pageNum) {
		this.pageNum = (pageNum <= 0 ? 1 : pageNum);
		calculateStartAndEndRow();
		return this;
	}

	public int getPageSize() {
		return pageSize;
	}

	public Page<E> setPageSize(int pageSize) {
		this.pageSize = (pageSize <= 0 ? 10 : pageSize);

		calculateStartAndEndRow();

		return this;
	}

	public long getStartRow() {
		return startRow;
	}

	public Page<E> setStartRow(int startRow) {
		this.startRow = startRow;
		return this;
	}

	public long getTotal() {
		return total;
	}

	/**
	 * 设置记录总行数,并且计算总页数
	 * 
	 * @param total
	 */
	public void setTotal(long total) {
		this.total = total;
		//如果结果总行数为0，则设置总页数和起始行、结束行
		if (total == 0) {
			this.setPages(0);
			this.setStartRow(0);
			this.setEndRow(0);
			return;
		}
		this.setPages((int) (total / pageSize + ((total % pageSize == 0) ? 0
				: 1)));

		//如果请求查询的pageNum大于总页数，则重置pageNum
		if (pageNum > pages) {
			this.setPageNum(pages);
		}
		// 如果当前获取的是最后一页的数据，而且最后一页不满时，原来的endRow是大于真实的endRow的，
		if (pageNum == pages) {
			this.setEndRow(total);
		}

	}

	/**
	 * 计算起止行号
	 */
	private void calculateStartAndEndRow() {
		this.startRow = (this.getPageNum() - 1) * this.getPageSize() + 1;
		if (this.getPageNum() == this.getPages()
				&& this.getTotal() % this.getPageSize() != 0) {
			this.setEndRow(this.startRow + this.getTotal() % this.getPageSize()
					- 1);
		} else {
			this.setEndRow(this.getPageNum() * this.getPageSize());
		}

	}

	public int getSize() {
		return result.size();
	}
}