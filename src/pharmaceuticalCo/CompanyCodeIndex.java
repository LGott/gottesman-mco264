package pharmaceuticalCo;

import java.io.Serializable;

public class CompanyCodeIndex implements Comparable<CompanyCodeIndex>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companyCode;
	private Long location;
	private boolean isActive;

	public CompanyCodeIndex(String companyCode, Long location) {

		this.companyCode = companyCode;
		this.location = location;
		this.isActive = true;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public Long getLocation() {
		return location;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setDeleted() {
		this.companyCode = "Deleted";
	}

	public boolean setNotActive() {
		return this.isActive = false;
	}

	@Override
	public int compareTo(CompanyCodeIndex other) {
		return companyCode.compareTo(other.getCompanyCode());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((companyCode == null) ? 0 : companyCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CompanyCodeIndex other = (CompanyCodeIndex) obj;
		if (companyCode == null) {
			if (other.companyCode != null) {
				return false;
			}
		} else if (!companyCode.equals(other.companyCode)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CompanyCodeIndex [companyCode=" + companyCode + ", location=" + location + ", isActive=" + isActive
				+ "]";
	}

}
