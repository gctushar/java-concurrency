package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UserService {

    private Date getBirthDateFromDB(int id) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, (2000 + id));
        return calendar.getTime();
    }

    public String birthDate(int useID) {

        Date birthDate =getBirthDateFromDB(useID);
         SimpleDateFormat simpleDateFormat = ThreadSafeFormatter.dateFormatter.get();
         return simpleDateFormat.format(birthDate);
    }

    class Another{
        int x;

    }
}
