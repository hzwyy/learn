/**
 * @author: 25345
 * @date: 2026/4/20 17:49
 * @description:
 */
package Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class GetData {

    public static void main(String[] args) {
        LocalDate date = LocalDate.now().minusMonths(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String result = date.format(formatter);

        System.out.println(result);

    }
}
