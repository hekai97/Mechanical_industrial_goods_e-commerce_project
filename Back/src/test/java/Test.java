import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: hekai
 * @Date: 2022/6/11
 */
public class Test {
    public static void main(String[] args) {
        String s="resultStatus={9000};memo={111};result={{\"alipay_trade_app_pay_response\":{\"code\":\"10000\",\"msg\":\"Success\",\"app_id\":\"2021000120614325\",\"auth_app_id\":\"2021000120614325\",\"charset\":\"utf-8\",\"timestamp\":\"2022-06-11 13:10:53\",\"out_trade_no\":\"1654924238971\",\"total_amount\":\"216.00\",\"trade_no\":\"2022061122001461421000317306\",\"seller_id\":\"2088621959359744\"},\"sign\":\"i6r9gU6UQNXrej/qm86+sc6E5O5JG93nzXp05kql+OS4fQ+IlSzwiCD5hbzIQA+fbAGm37raoJT+Gxra5LXnTVmSu8PXQQhQRASJRQIdNM0M+lu8MR4xHfmm3qCPOc+PkAEs6r/lBr3hRPuY3WhDPPV2MPJ1NEGHmSrSZE2FuQVhsIPkyouG35jZVggQuS/n2Z8ECRldVXigZPfoXIDBwMSwKphjy9ECfBtv9ZgFxh3x6kxqHe30+o29/QIHozjGKDyYmPHDSpFskfWHg8EgZNBjNDU3gZNOEvB08K+m4xBJfUI0IdfcftBgRy/s/gK+R/x7qwCeYQ+3+IAq9d34lA==\",\"sign_type\":\"RSA2\"}}}";
        Pattern pattern=Pattern.compile("\"out_trade_no\":\"[0-9]*\"");
        Matcher matcher = pattern.matcher(s);
        if(matcher.find()){
            String s1=matcher.group(0);
            String[] res=s1.split(":");
            String orderNo=res[1].substring(1,res[1].length()-1);
            System.out.println(orderNo);
        }
        System.out.println("123");
    }
}
