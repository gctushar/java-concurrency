package threadlocal;

import java.text.SimpleDateFormat;

public class ThreadSafeFormatter {

    public static ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>() {


        @Override
        protected SimpleDateFormat initialValue() {
            System.out.println("Initializing.....");
            return new SimpleDateFormat("yyyy-MM-dd");
        }

        @Override
        public SimpleDateFormat get() {
            return super.get();
        }

    };

//    public static ThreadLocal<SimpleDateFormat> dateFormatterWithLambda = ThreadLocal.withInitial(()
//                                                                        -> new SimpleDateFormat("yyyy/MM/dd"));

}