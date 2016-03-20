package pharmaceuticalCo;

import java.util.Comparator;

public class CompanyComparator implements Comparator<CompanyCodeIndex> {

	public CompanyComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(CompanyCodeIndex o1, CompanyCodeIndex o2) {
		return o1.getCompanyCode().compareToIgnoreCase(o2.getCompanyCode());
	}

}
