package com.three.zhongdian.config;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ToolPage {

	public static void page(HttpServletRequest request, int currentPage, int pageSize, String url, int listCount, List list) {
		//4个元素的字符串数组, 用来存放html页面上的首页、上一页、下一页和末页
		String[] pageArray = new String[4];
		//总页数
		int pageCount =  listCount / pageSize + (listCount % pageSize != 0 ? 1 : 0);
		//url传参标记
		String flag = url.indexOf("?") > -1 ? "&" : "?";

		if (currentPage == 0) {
			pageArray[0] = "首页";
		} else {
			pageArray[0] = "<a href=\"" + request.getContextPath() + "/" + url + flag + "currentPage=0\">首页</a>";
		}

		if (currentPage == 0) {
			pageArray[1] = "上一页";
		} else {
			pageArray[1] = "<a href=\"" + request.getContextPath() + "/" + url + flag + "currentPage=" + (currentPage - 1) + "\">上一页</a>";
		}

		if (currentPage < pageCount - 1) {
			pageArray[2] = "<a href=\"" + request.getContextPath() + "/" + url + flag + "currentPage=" + (currentPage + 1) + "\">下一页</a>";
		} else {
			pageArray[2] = "下一页";
		}

		if (currentPage < pageCount - 1) {
			pageArray[3] = "<a href=\"" + request.getContextPath() + "/" + url + flag + "currentPage=" + (pageCount - 1) + "\">末页</a>";
		} else {
			pageArray[3] = "末页";
		}
		//首页
		request.setAttribute("firstPage", pageArray[0]);
		//上一页
		request.setAttribute("precursorPage", pageArray[1]);
		//下一页
		request.setAttribute("nextPage", pageArray[2]);
		//末页
		request.setAttribute("lastPage", pageArray[3]);
		//当前页
		request.setAttribute("currentPage", String.valueOf(currentPage + 1));
		//总页数
		request.setAttribute("pageCount", String.valueOf(pageCount));
		//总记录数
		request.setAttribute("listCount", listCount);
		//每一页显示记录
		request.setAttribute("pageSize", pageSize);

		request.setAttribute("list", list);
	}
}