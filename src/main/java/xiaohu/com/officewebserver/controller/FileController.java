package xiaohu.com.officewebserver.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xiaohu.com.officewebserver.util.FileUtils;
import xiaohu.com.officewebserver.vo.CheckFileInfo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/wopi/files")
public class FileController {

	@RequestMapping(value = "/{name:.+}", method = RequestMethod.GET)
	public void GetFileInfo(@PathVariable("name") String name,
			String access_token, HttpServletRequest request,
			HttpServletResponse response) {
		String path = request.getServletContext().getRealPath("/App_Data");
		String fileName = path + File.separator + name;
		File file = new File(path + File.separator + name);

		byte[] contents = null;
		try {
			contents = FileUtils.getContent(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CheckFileInfo checkFileInfo = new CheckFileInfo();
		checkFileInfo.setBaseFileName(file.getName());
		checkFileInfo.setOwnerId("Administrator");
		checkFileInfo.setSize(file.length());
		checkFileInfo.setSha256(Base64.encodeBase64String(DigestUtils
                .sha256(contents)));
		checkFileInfo.setVersion(String.valueOf(file.lastModified()));

		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(JSON.toJSONString(checkFileInfo));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}

	@RequestMapping("/{name}/contents")
	public void GetFile(@PathVariable("name") String name, String access_token,
			HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");

		String path = request.getServletContext().getRealPath("/App_Data");
		File file = new File(path + File.separator + name);

		ByteArrayOutputStream bos = new ByteArrayOutputStream(
				(int) file.length());
		BufferedInputStream in = null;
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			in = new BufferedInputStream(new FileInputStream(file));
			int buf_size = 1024 * 1024 * 10;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			out.write(bos.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != bos) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
