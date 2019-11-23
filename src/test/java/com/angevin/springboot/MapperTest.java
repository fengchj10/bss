package com.angevin.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;

/**
 * Created  by  Angevin.
 * Date: 2019-11-07 19:28
 * description:
 *
 * @author Angevin
 */
public class MapperTest {


    @GetMapping
    private  void  test()throws IOException{
        String str ="{\n" +
                "\t\"UNI_BSS_BODY\": {\n" +
                "\t\t\"PRO_ORDER_REQ\": {\n" +
                "\t\t\t\"SERIAL_NUMBER\": \"cCelktwq3qzqkIv\",\n" +
                "\t\t\t\"ACT_TYPE\": \"0\",\n" +
                "\t\t\t\"CUST_ID\": \"8B09L6H\",\n" +
                "\t\t\t\"LOCAL_NET_ID\": \"f\",\n" +
                "\t\t\t\"EXP_DATE\": \"k23PO0foxM2bfCaxKuA\",\n" +
                "\t\t\t\"PARA\": [{\n" +
                "\t\t\t\t\"PARA_ID\": \"K6hBaLpa45ix\",\n" +
                "\t\t\t\t\"PARA_VALUE\": \"0jvae8vjtElzvbdhxLe8usx94n5AC5jt8vkidx6Mj8ModD\"\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"PRICE_PLAN_INFOS\": [{\n" +
                "\t\t\t\t\"PROD_ID\": \"s4qoEo7HqKIxD3i\",\n" +
                "\t\t\t\t\"PRICE_PLAN_ID\": \"jjGmzqxxIyi3fJsIl8nKF\"\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"EFF_DATE\": \"vAAAlKvnjaMKJdAIxO8\",\n" +
                "\t\t\t\"ACCEPT_DATE\": \"5aelNdBvdD2jE590cMd\",\n" +
                "\t\t\t\"INSTITUTION_CODE\": \"tmwv\",\n" +
                "\t\t\t\"CUST_NAME\": \"\"\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t\"UNI_BSS_HEAD\": {\n" +
                "\t\t\"APP_ID\": \"rJoJ2IbLxDiqyNGsBHCqc\",\n" +
                "\t\t\"TIMESTAMP\": \"iOdy5IsDcsDlxjDmzLx\",\n" +
                "\t\t\"TRANS_ID\": \"l7CespKzgvPuOuxkppO3yF\",\n" +
                "\t\t\"TOKEN\": \"M729tIkLN0p47o7n6Pva0cqsK74M0x\"\n" +
                "\t}\n" +
                "}";

//        BssDataInfo bss = new BssDataInfo();
//        MappingJackson2HttpMessageConverter mh = new MappingJackson2HttpMessageConverter();
//        mh.setJsonPrefix(str);
//


    }

}
