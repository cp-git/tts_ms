
package com.cpa.ttsms.dto;

public class CompanyAndCompanyPhotosDTO {
	private int companyId;
	private String companyCode;
	private String companyName;
	private String companyContactEmail;
	private String companyContactPhone;
	private String companyAddress;
	private String companyZip;
	private int companyCountryId;
	private int photoId;
	private String photoFilename;

	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the companyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * @param companyCode the companyCode to set
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the companyContactEmail
	 */
	public String getCompanyContactEmail() {
		return companyContactEmail;
	}

	/**
	 * @param companyContactEmail the companyContactEmail to set
	 */
	public void setCompanyContactEmail(String companyContactEmail) {
		this.companyContactEmail = companyContactEmail;
	}

	/**
	 * @return the companyContactPhone
	 */
	public String getCompanyContactPhone() {
		return companyContactPhone;
	}

	/**
	 * @param companyContactPhone the companyContactPhone to set
	 */
	public void setCompanyContactPhone(String companyContactPhone) {
		this.companyContactPhone = companyContactPhone;
	}

	/**
	 * @return the companyAddress
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}

	/**
	 * @param companyAddress the companyAddress to set
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	/**
	 * @return the companyZip
	 */
	public String getCompanyZip() {
		return companyZip;
	}

	/**
	 * @param companyZip the companyZip to set
	 */
	public void setCompanyZip(String companyZip) {
		this.companyZip = companyZip;
	}

	/**
	 * @return the companyCountryId
	 */
	public int getCompanyCountryId() {
		return companyCountryId;
	}

	/**
	 * @param companyCountryId the companyCountryId to set
	 */
	public void setCompanyCountryId(int companyCountryId) {
		this.companyCountryId = companyCountryId;
	}

	/**
	 * @return the photoId
	 */
	public int getPhotoId() {
		return photoId;
	}

	/**
	 * @param photoId the photoId to set
	 */
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	/**
	 * @return the photoFilename
	 */
	public String getPhotoFilename() {
		return photoFilename;
	}

	/**
	 * @param photoFilename the photoFilename to set
	 */
	public void setPhotoFilename(String photoFilename) {
		this.photoFilename = photoFilename;
	}

	/**
	 * @param companyId
	 * @param companyCode
	 * @param companyName
	 * @param companyContactEmail
	 * @param companyContactPhone
	 * @param companyAddress
	 * @param companyZip
	 * @param companyCountryId
	 * @param photoId
	 * @param photoFilename
	 */
	public CompanyAndCompanyPhotosDTO(int companyId, String companyCode, String companyName, String companyContactEmail,
			String companyContactPhone, String companyAddress, String companyZip, int companyCountryId, int photoId,
			String photoFilename) {
		super();
		this.companyId = companyId;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.companyContactEmail = companyContactEmail;
		this.companyContactPhone = companyContactPhone;
		this.companyAddress = companyAddress;
		this.companyZip = companyZip;
		this.companyCountryId = companyCountryId;
		this.photoId = photoId;
		this.photoFilename = photoFilename;
	}

	/**
	 *
	 */
	public CompanyAndCompanyPhotosDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CompanyAndCompanyPhotosDTO [companyId=" + companyId + ", companyCode=" + companyCode + ", companyName="
				+ companyName + ", companyContactEmail=" + companyContactEmail + ", companyContactPhone="
				+ companyContactPhone + ", companyAddress=" + companyAddress + ", companyZip=" + companyZip
				+ ", companyCountryId=" + companyCountryId + ", photoId=" + photoId + ", photoFilename=" + photoFilename
				+ "]";
	}
}