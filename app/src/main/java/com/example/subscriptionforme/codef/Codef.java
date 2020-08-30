package com.example.subscriptionforme.codef;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.codef.api.EasyCodef;
import io.codef.api.EasyCodefServiceType;
import io.codef.api.EasyCodefUtil;

public class Codef {

    public Codef(){

    }

    public void getCodef(){
        EasyCodef codef = new EasyCodef();
        codef.setClientInfoForDemo("7454792c-2646-4ac3-b944-ce0594c9b9d4","844bedbb-92e9-4782-b918-4ec3c0030fec");
        codef.setPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgIXikjjz1j/QdRhATsjpjJ25aS7hFnYx5E+e265nmloEVYAOwyxyRJmSgMQaWAeVtJbX8O2ZwOE0hOnsEO8h2k+sojD4WluB9TeSFhZwYAbrhvspB7nnsYKHgZMqSjyDKxdFwkrtarScMhxfYc0vn4/iHL0ldrCQQWwM949HoroihDG+1WPFklPydYoq0LAdZakQQvn0qnU99zc3zbLsvxsB+8hgbVhPHvfXPbkUHzrHj2eZgATb56QwGL4WdeEupOiUlFbYB9zWmkspX9ruXFh57odJgSukmcq1lV8EDtbEyWU8AiKINaphFxgcoebOiZUNAGr2Uk8u8XHakgZWNwIDAQAB");

        List<HashMap<String, Object>> accountList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> accountMap = new HashMap<String, Object>();

        accountMap.put("countryCode",	"KR");
        accountMap.put("businessType",	"CD");
        accountMap.put("clientType", 	"P");
        accountMap.put("organization",	"0004"); // 기관코드는 각 상품 페이지에서 확인 가능
        accountMap.put("loginType",  	"1");
        accountMap.put("id",  		"tovbskvb@daum.net");
        accountMap.put("certType",  		"1");

        try {
            accountMap.put("password",  EasyCodefUtil.encryptRSA("P@ssw0rd12", codef.getPublicKey()));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        accountList.add(accountMap);

        HashMap<String, Object> parameterMap = new HashMap<String, Object> ();
        parameterMap.put("accountList", accountList);

/** #6.계정 등록 요청(Connected ID 발급 요청)	- 서비스타입(API:정식, DEMO:데모, SANDBOX:샌드박스) */
        String result = null;

        try {
            result = codef.createAccount(EasyCodefServiceType.SANDBOX, parameterMap);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

/** #7.결과 확인	*/
        Log.d("박태순",String.valueOf(result));
    }


}
