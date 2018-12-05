/**
 * @mm.he
 * @since 2015年7月22日
 */
package com.mjh.utils;

/**
 * <pre>
 * 输入pageNo,pageSize,
 * return startNo,endNo 分页使用
 * 
 * </pre>
 * 
 * @since
 */
public class PageParse {

	public enum DBType { // 数据库类型
		MYSQL, ORACLE
	}

	/** 默认页码 */
	public final static int DEFAULT_PAGENO = 1;
	/** 默认每页记录数 */
	public final static int DEFAULT_PAGESIZE = 20;

	public final static String STARTNO = "startNo";
	public final static String ENDNO = "endNo";

	public final static String PARAM_PAGENO = "pageNo";
	public final static String PARAM_PAGESIZE = "pageSize";

	int pageNo = 1; // 页码
	int pageSize = 10; // 每页大小

	int startNo = 0; // 分页开始Index
	int endNo = 0; // 结束Index

	DBType dbType = DBType.MYSQL; // 数据库类型

	/**
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页大小
	 */
	public PageParse(int pageNo, int pageSize) {
		this.pageNo = (pageNo == 0) ? DEFAULT_PAGENO : pageNo;
		this.pageSize = (pageSize == 0) ? DEFAULT_PAGESIZE : pageSize;
	}

	/**
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页大小
	 * @param dbType
	 *            库类型,默认为0:mysql,1:oracle
	 */
	public PageParse(int pageNo, int pageSize, DBType dbType) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.dbType = dbType;
	}

	public int getStartNo() {
		if (DBType.MYSQL == dbType) {
			startNo = ((pageNo - 1) * pageSize); // mysql,limit_m,n;m为记录开始的index(从0开始),n为n条
		} else if (DBType.ORACLE == dbType) {
			// startNo = ((pageNo - 1) * pageSize) + 1; // oracle分页,pageIndex>=startNo,pageIndex<endNo
			startNo = ((pageNo - 1) * pageSize); // oracle分页,pageIndex>startNo,pageIndex<=endNo
		}
		return startNo;
	}

	/**
	 * mysql库直接传入pageSize作为limit第二个参数
	 * 
	 * @return
	 */
	public int getEndNo() {
		endNo = pageNo * pageSize;
		return endNo;
	}

	/**
	 * 获取分页总数
	 * 
	 * @param pageSize
	 * @param total
	 * @return
	 */
	public static int getPages(int pageSize, int total) {
		if (total % pageSize == 0) {
			return total / pageSize;
		} else {
			return total / pageSize + 1;
		}
	}

}
