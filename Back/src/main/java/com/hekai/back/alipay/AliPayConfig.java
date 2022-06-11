package com.hekai.back.alipay;

import org.springframework.stereotype.Component;

/**
 * @author: hekai
 * @Date: 2022/6/8
 */
@Component
public class AliPayConfig {

    // 应用ID,APPID,开发时是沙箱提供的APPID
    private static final String baseUrl="http://192.168.43.74:8080/mall/";
    public static String APP_ID = "2021000120614325";

    // 商户私钥，之前所生成的密钥中的私钥
    public static String APP_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCtmmwyWmH9ODxs59kIxFY4MQ9CWHEOUJ2pv8Y7LLTLsBgsORpLhHcvx6auG0N7I6iaOH/UEoi8vv2NAQhsKEhvMHyaSOisp1LQuQ9MRmGkjfAlU7icALsjkz/F28yU3E8uW5i7Fw8J/L49/f2T3PlSSWb7NfoioiEfeZQ9I78rzxzHNzlXwy49KX9ybchDyRLnsR4WKU8x0eyYKYmlDdKAUSR+zmrTaa77Gbt/op23aWiICwIv1cMr8SrQ3ifJhbr8x9CMNHd+dvw8fEijN9zN5gxlopH9tzT8E9tMfsDgREUkrgmYg1hD1s5zJIjVA9q3+Swj5LdYUOhj0QMbQm4ZAgMBAAECggEBAIvM12SVNGPebYqmCjjtIyGNIGSlJCYK9WxFazUsA0wkOtMiCPLXccOgW7HJcsYvKQ9IgGgfpM2aYES0mDvBFNO1kCIOpJClDiPXY2JhKzCAvOrSSNAYJpu3cJu9LTqTT5BdYtkG8K1yQyFY8GOqh3ykb7HM/en19gaKub2ik8iKYSJ21bRsYTSjuz+URuWO9dTFXykJ1Gk3Ea/p3UfTA1vVYVifhDGaRMDDmxQZuqIEPJink2iCC2SB1HAEEhCYkg9L/wAtLsJgwHYA5+/VpSkA9ffnistdg6eWDcgUwXFVipXUuIcRxd01SlbOUTFdrNa0fTkeMg5RjbxmHw3tz6ECgYEA9y09BUV0m6vQCVCvbIa/NbQ7GtiIFdTv2MsCGf/slgooOMHkt9r0epd5zrIX6UJN0rK8SANXA/GZiZo7Ab55zuj8bq2K++0Hmd0MC7IRTA8nEIKt/XTgwgvcTUex+m2xDvjx9HcW+4VJuhCDwlit6zqpNcgCo77BVBH5YY8Nf8MCgYEAs8zZ/ncdEXT1a98AGi/f3CyJvt+Fh4CbKmQhC/v5o5y6nEYgAOL3j3N0o2JgX83wZsb30m1GHF5AObXM7psyP8gONrKi5BLQp+sXxX65k0YlyORTXnbehni5/iz9d/34Kh/w8ZD6XdX5bUTSfDalftrJ1Nex500E1HmjV+AKuPMCgYEAgouE/+S3+oPawyC3FdVw5XdEBCC6dvVYm6JUgNDW7kN9DMTy/FuK5bhc39rVoB+hpJc7s7j32yBd2/v2ECy+fpY0xGupzPZaHlxNwVuRoR8bna+3Hvb8wd+rwbENAduvON4oQ9ol7D9wOfIA9MMLUAJSXhI9uqfDyZgYovr/wSsCgYEAlXRWUBsa4oPh7QS0xQ++ePnclfieuf/A+v+4KIoO/F0vEO+JcrM3LNq/vX/stDYu+n+xSAzlFneR02a3Li97GWFlzzm2BPdO0gWtANFR2IWaqt6AHimY1xZLJGipWLCXNAzQ7zufoZhXeOiYPf/+mO+sjWutyaJPvX5OtKDayNkCgYBTxgrIEBIsD/OO8on23p/VX0FWdT+pS40QF4CKcVM1SZuJqGglUcGwcc+95I+LieaAuxueiu1yU6KV/q7K3vlpWJPkVnWO6RBiEjMY6BX23W1O5j9FX/ZZQIYYOyqlCspCGvttgyXfu77ttaEJEIpFWGGH+n1COa3dCbXBG019LQ==";

    // 支付宝公钥,注意是APPID下的支付宝公钥，不是应用的公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkhKbcbyTGkYXYlh92TUrrkvnLcEjIsTAWsz8qv3CxSbIazpANwtvzisnlOFfD/ThOflqETBQF8yRbsryaxzgFiNG9TwUbDpRkLkQuMlsbhYYVewQE2fMtP62mBxsneUlNaTu40iqzj33r1valBhwb4JEfjjwz7LhoiNVioc/eid7en11JViqFCrKEACpMZGSx6EomF8Nu0hzaa6BxM+j/kFoR5aGvhswra+QliVF09utLTAxXux6WnLPz0g9uDZa7Km0bu6Ybm+qO968N9TxRxbV9autLjXWj3yX/wzHwwenBxZIdUNVHf+P3Gln4DyY54jNclRQxv17mKbGHIFf8QIDAQAB";

    // 服务器异步通知页面路径,需要用http://格式的完整路径，不要加自定义参数，需要外网可以正常访问
    public static String notify_url = baseUrl+"alipay/notify.do";

    // 同步通知页面跳转路径 需要用http://格式的完整路径，不要加自定义参数，用来显示支付成功后返回的页面
    public static String return_url = baseUrl+"alipay/return.do";
    public static String mobile_url = baseUrl+"alipay/mobileresult.do";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 返回格式
    public static String FORMAT = "json";

}
