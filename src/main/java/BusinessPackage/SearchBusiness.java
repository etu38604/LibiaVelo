package BusinessPackage;

import DataAccessPackage.SearchDataAccess;
import ExceptionPackage.ConnectionException;
import ModelPackage.SearchModel;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SearchBusiness {

    private SearchDataAccess searchDataAccess;

    public SearchBusiness () throws ConnectionException
    {
        searchDataAccess = new SearchDataAccess();
    }

    public ArrayList<SearchModel> searchListing (Integer idEmployee, GregorianCalendar dateBegin,GregorianCalendar dateEnd) throws ConnectionException
    {
        return searchDataAccess.searchListing(idEmployee,dateBegin,dateEnd);
    }

}
