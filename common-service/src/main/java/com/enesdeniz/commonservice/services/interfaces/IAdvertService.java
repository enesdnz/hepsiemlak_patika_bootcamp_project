package com.enesdeniz.commonservice.services.interfaces;

import com.enesdeniz.commonservice.enums.AdvertStatus;
import com.enesdeniz.commonservice.core.Result;
import com.enesdeniz.commonservice.dto.request.AdvertRequest;
import com.enesdeniz.commonservice.dto.response.AdvertResponse;

import java.util.List;

public interface IAdvertService {

    List<AdvertResponse> getAllAdverts();
    AdvertResponse getAdvertByAdvertNo(int advertNo);
    Result saveAdvert(AdvertRequest request) throws Exception;
    Result updateAdvert(Long advertNo, AdvertRequest request);
    Result deleteAdvert(Long advertNo);
    List<AdvertResponse> getAdvertByAdvertStatus(AdvertStatus advertStatus);
}
