package com.penjing.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
@ResultPath("/")
@ParentPackage(value="crud-default")
@Results({  
	  @Result(name="json", type="json", params={"root", "result"}),
	  @Result(name="noUserAuthority",type="chain",location="noAuthority"),
	  @Result(name="noLogin", type="chain",location="loginError")
	})
public class HeadImageAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File file;
	// 得到文件的名字[FileName]
	private String imgName;
	

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}


	public void setFile(File file) {
		this.file = file;
	}
	
	
	@Action(value="updateImg",results={@Result(name="success", location="WEB-INF/backManage/HeadImageManage.jsp")})
	public String updateImage() throws Exception{
		imgName=imgName+".png";
		if(file!=null){
			@SuppressWarnings("deprecation")
			String root = ServletActionContext.getRequest().getRealPath("/headImg");
			// 写入文件的路径
			// 得到输入流对象
			InputStream is = new FileInputStream(file);
			// 创建File对象
			File destFile = new File(root, imgName);
			// 得到输出流对象
			OutputStream os = new FileOutputStream(destFile);
			// 创建字节流对象
			byte[] buffer = new byte[500];
			int length = 0;
			while ((length = is.read(buffer)) != -1) {
				os.write(buffer, 0, length);
			}
			// 操作完成关闭对象
			is.close();
			os.close();
		}else{
			return "fail";
		}
		return "success";
	}
	
}
