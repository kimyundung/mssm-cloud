package com.mssm.other.feign;

import com.mssm.common.domain.Photo;
import com.mssm.common.domain.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mssm-photo-boot")
public interface PhotoFeign {

    @PutMapping("/photo/update")
    public ResponseResult updatePhoto(@RequestBody Photo photo);
}
