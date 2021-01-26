package com.jys.service.impl;

import com.jys.dao.AdvertisementMapper;
import com.jys.pojo.Advertisement;
import com.jys.service.AdvertisementService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Override
//    @CachePut(value = "redisCacheManager",key="'#advertisementiinsert'")
    public void insert(Advertisement advertisement) {

        try{
//代码区
            advertisementMapper.insert(advertisement);
        }catch(Exception e){
            System.out.println("实现类异常信息："+e.getMessage());
//异常处理
        }


    }
    @Override
    public List<Advertisement> listAll(){
       return advertisementMapper.listAll();
    }
     @Override
    public void  deleteById(String id){
        advertisementMapper.deleteById(id);
     }
    @Override
    public int  update(Advertisement advertisement){
      return   advertisementMapper.update(advertisement);
    }
    @Override
    public int aaacounts(){
        return advertisementMapper.aaacounts();
    }
    @Override
    public List<Advertisement> aaagetProducta(@Param("start") int start, @Param("limit") int limit){
        return advertisementMapper.aaagetProducta(start, limit);
    }

    @Override
//    @Cacheable(value = "redisCacheManager",key = "'#advertisementfindid.id'")
    public Advertisement findById(String id){
        Advertisement advertisement=advertisementMapper.findById(id);
        System.out.println("没有去redis，只存到数据库");
        return advertisement;
    }
    @Override
    public Advertisement findByshow(String showpage){
        return advertisementMapper.findByshow(showpage);
    }
    @Override
    public int updateclick(Advertisement advertisement){
        return advertisementMapper.updateclick(advertisement);
    }
    @Override
    public Integer findclick(String id){
        return advertisementMapper.findclick(id);
    }
}
