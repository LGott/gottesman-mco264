package pharmaceuticalCo;

import java.io.Serializable;

public class CompanyNameIndex implements Comparable<CompanyNameIndex>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String companyName;
	private Long location;
	private boolean isActive;

	public CompanyNameIndex(String companyName, Long location) {

		this.companyName = companyName;
		this.location = location;
		this.isActive = true;
	}

	public String getCompanyName() {
		return companyName;
	}

	public Long getLocation() {
		return location;
	}

	public boolean isActive() {
		return isActive;
	}

	public boolean setNotActive() {
		return this.isActive = false;

	}

	@Override
	public int compareTo(CompanyNameIndex other) {
		return this.companyName.compareTo(other.getCompanyName());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((companyName == null) ? 0 : companyName.hashCode());
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
		CompanyNameIndex other = (CompanyNameIndex) obj;
		if (companyName == null) {
			if (other.companyName != null) {
				return false;
			}
		} else if (!companyName.equals(other.companyName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CompanyNameIndex [company Name:" + companyName + ", location=" + location + ", isActive=" + isActive
				+ "]";
	}

}