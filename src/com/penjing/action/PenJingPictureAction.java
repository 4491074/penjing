package com.penjing.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.penjing.entity.PenJing;
import com.penjing.entity.PenJingPicture;
import com.penjing.service.PenJingPictureService;
import com.penjing.util.StringUtil;
@ResultPath("/")
@ParentPackage(value="crud-default")
@Results({  
	  @Result(name="json", type="json", params={"root", "result"}),
	  @Result(name="noLogin",type="chain",location="loginErrorAjax"),
	  @Result(name="noRoleAuthority",type="chain",location="noAuthorityAjax")
	})
public class PenJingPictureAction extends ActionSupport implements ServletRequestAware{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private PenJingPictureService penJingPictureService;
	private JSONObject result;
	private JSONArray array;
	private HttpServletRequest request;
	private File pictures;
	private String picturesFileName;
	private String pictureContentType;
	private String penjingType;
	private String picturePath;
	private String penjingId;
	private String deletePenjingIds;
	private String passPicture;
	private String unPassPicture;
	private PenJing penjing;
	private List<PenJingPicture> pics;
	
	public PenJing getPenjing() {
		return penjing;
	}
	public void setPenjing(PenJing penjing) {
		this.penjing = penjing;
	}
	public List<PenJingPicture> getPics() {
		return pics;
	}
	public void setPics(List<PenJingPicture> pics) {
		this.pics = pics;
	}
	public void setPassPicture(String passPicture) {
		this.passPicture = passPicture;
	}
	public void setUnPassPicture(String unPassPicture) {
		this.unPassPicture = unPassPicture;
	}
	public void setDeletePenjingIds(String deletePenjingIds) {
		this.deletePenjingIds = deletePenjingIds;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	public void setPenjingId(String penjingId) {
		this.penjingId = penjingId;
	}
	public void setPenjingType(String penjingType) {
		this.penjingType = penjingType;
	}
	public void setPictures(File pictures) {
		this.pictures = pictures;
	}
	public void setPicturesFileName(String picturesFileName) {
		this.picturesFileName = picturesFileName;
	}
	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}
	public JSONArray getArray() {
		return array;
	}
	public JSONObject getResult() {
		return result;
	}
	
	@Action(value="/penjingPicture/uploadPictures",
			results={@Result(name="input",location="tooLarge",type="chain")}		 
			)
	public String uploadPictures(){
		result = new JSONObject();
		String path = request.getSession().getServletContext().getRealPath("/penjingPicture/penjingType_"+penjingType);
		picturesFileName = StringUtil.penjingName(picturesFileName);
		File saveFile = new File(new File(path), picturesFileName); //根据地址以及文件名生成文件
		try {
			FileUtils.copyFile(pictures, saveFile);
			result.put("path", "penjingPicture/penjingType_"+penjingType+"/"+picturesFileName);
			result.put("result", 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "json";
	}
	
	@Action(value="/penjingPicture/updatePictures",
			results={@Result(name="input",location="tooLarge",type="chain")}				 
			)
	public String updatePictures(){
		result = new JSONObject();
		if(penjingType == null || penjingId == null){
			result.put("result", 2);
			return "json";
		}
		String path = request.getSession().getServletContext().getRealPath("/penjingPicture/penjingType_"+penjingType);
		picturesFileName = StringUtil.penjingName(picturesFileName);
		File saveFile = new File(new File(path), picturesFileName); //根据地址以及文件名生成文件
		try {
			FileUtils.copyFile(pictures, saveFile);
			PenJingPicture pjp = new PenJingPicture();
			PenJing pj = new PenJing();
			pj.setPenJingId(Integer.parseInt(penjingId));
			pjp.setPenJing(pj);
			pjp.setPictureStatus((byte)0);
			pjp.setPictureUrl("penjingPicture/penjingType_"+penjingType+"/"+picturesFileName);
			penJingPictureService.save(pjp);
			result.put("result", 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("result", 3);
			return "json";
		}
		return "json";
	}
	
	@Action(value="penjingPicture_savePictures"
			)
	public String savePictures(){
		result = new JSONObject();
		PenJing pj = new PenJing();
		pj.setPenJingId(Integer.parseInt(penjingId));
		String[] pathes = picturePath.split(",");
		for(String str:pathes){
			PenJingPicture pjp = new PenJingPicture();
			pjp.setPenJing(pj);
			pjp.setPictureStatus((byte)0);
			pjp.setPictureUrl(str);
			penJingPictureService.save(pjp);
		}
		result.put("result", 1);
		result.put("penjingId", penjingId);
		return "json";
	}
	
	
	@Action(value="penjingPicture_penjingInfo",
			results={@Result(name="success",location="jsp/penJingInfo.jsp")}
			)
	public String penjingInfo(){
		result = new JSONObject();
		if(penjing == null){
			result.put("result", 3);
			return "json";
		}
		pics = penJingPictureService.getPictureForInfo(1, penjing.getPenJingId());
		return SUCCESS;
	}
	
	@Action(value="penjingPicture_delPictures"
			)
	public String delPictures(){
		result = new JSONObject();
		List<PenJingPicture> pics = penJingPictureService.get(deletePenjingIds);
		String path = request.getSession().getServletContext().getRealPath("/penjingPicture");
		for(PenJingPicture pjp:pics){
			File delFile = new File(new File(path), pjp.getPictureUrl().replaceAll("penjingPicture/", "")); //根据地址以及文件名生成文件
			if (delFile.isFile() && delFile.exists()) {  
				delFile.delete();
		    }
		}
		result.put("delNu", penJingPictureService.delPenJingPictures(deletePenjingIds));
		result.put("result", 1);
		return "json";
	}
	
	@Action(value="penjingPicture_checkPictures"
			)
	public String checkPictures(){
		result = new JSONObject();
		if(passPicture !=null && !"".equals(passPicture)){
			penJingPictureService.updatePenjingPicture(passPicture, (byte)1);
		}
		if(unPassPicture !=null && !"".equals(unPassPicture)){
			penJingPictureService.updatePenjingPicture(unPassPicture, (byte)2);
		}
		if(deletePenjingIds !=null && !"".equals(deletePenjingIds)){
			String path = request.getSession().getServletContext().getRealPath("/penjingPicture");
			for(String str:picturePath.split(",")){
				File delFile = new File(new File(path), str.replaceAll("penjingPicture/", "")); //根据地址以及文件名生成文件
				if (delFile.isFile() && delFile.exists()) {  
					delFile.delete();
			    }
			}
			penJingPictureService.delPenJingPictures(deletePenjingIds);
		}
		result.put("result", 1);
		return "json";
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}
	
}
