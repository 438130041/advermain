package com.jys.service;

import com.jys.pojo.Advertisement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvertisementService {
    void insert(Advertisement advertisement);

    List<Advertisement> listAll();

    void  deleteById(String id);

    int  update(Advertisement advertisement);

    int aaacounts();

    List<Advertisement> aaagetProducta(@Param("start") int start, @Param("limit") int limit);

    Advertisement findById(String id);

    Advertisement findByshow(String showpage);

    int updateclick(Advertisement advertisement);

    Integer findclick(String id);
}
