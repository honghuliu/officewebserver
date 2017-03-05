package xiaohu.com.officewebserver.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import xiaohu.com.officewebserver.vo.CheckFileInfo;

import com.alibaba.fastjson.JSON;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(URLEncoder.encode("http://web.xiaohu.com/officewebserver/wopi/files/a.docx", "utf-8"));
		System.out.println(URLEncoder.encode("http://web.xiaohu.com/officewebserver/wopi/files/a.xlsx", "utf-8"));
		System.out.println(URLEncoder.encode("http://web.xiaohu.com/officewebserver/wopi/files/a.pptx", "utf-8"));
		CheckFileInfo checkFileInfo = new  CheckFileInfo();
		checkFileInfo.setBaseFileName("fdsfdsaf");
		checkFileInfo.setOwnerId("fdsafds");
		checkFileInfo.setSha256("fadsfdasf");
		checkFileInfo.setVersion("fdasfdas");
		System.out.println(JSON.toJSONString(checkFileInfo,true));
	}
}
