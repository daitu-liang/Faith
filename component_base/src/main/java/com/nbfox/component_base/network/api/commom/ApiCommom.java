package com.nbfox.component_base.network.api.commom;


import com.nbfox.component_base.module.HttpResult;
import com.nbfox.component_base.module.UserInfo;
import com.nbfox.component_base.network.api.NetApi;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiCommom {

    @FormUrlEncoded
    @POST(NetApi.USER_LOGIN)
    Observable<HttpResult<UserInfo>> getLogin(@FieldMap Map<String, String> map);
    @FormUrlEncoded
    @POST(NetApi.USER_LOGIN)
    Observable<ResponseBody> getLogin1(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(NetApi.USER_LOGIN)
    Observable<ResponseBody> getLoginTest(@FieldMap Map<String, Object> map);

}
