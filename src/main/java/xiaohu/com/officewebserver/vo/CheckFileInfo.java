package xiaohu.com.officewebserver.vo;

import com.alibaba.fastjson.annotation.JSONField;

public class CheckFileInfo {

	@JSONField(name = "BaseFileName")
	private String baseFileName;

	@JSONField(name = "OwnerId")
	private String ownerId;

	@JSONField(name = "Size")
	private long size;

	@JSONField(name = "SHA256")
	private String sha256;

	@JSONField(name = "Version")
	private String version;

	public String getBaseFileName() {
		return baseFileName;
	}

	public void setBaseFileName(String baseFileName) {
		this.baseFileName = baseFileName;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getSha256() {
		return sha256;
	}

	public void setSha256(String sha256) {
		this.sha256 = sha256;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
