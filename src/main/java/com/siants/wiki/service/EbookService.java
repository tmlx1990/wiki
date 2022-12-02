package com.siants.wiki.service;

import com.siants.wiki.domain.Ebook;
import com.siants.wiki.domain.EbookExample;
import com.siants.wiki.mapper.EbookMapper;
import com.siants.wiki.req.EbookReq;
import com.siants.wiki.resp.EbookResp;
import com.siants.wiki.util.CopyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        /*List<EbookResp> respList = new ArrayList<>();
        for (Ebook ebook : ebookList) {
//            EbookResp ebookResp = new EbookResp();
//            BeanUtils.copyProperties(ebook, ebookResp);
            // 对象复制
            EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
            respList.add(ebookResp);
        }*/
        // 列表复制
        List<EbookResp> list = CopyUtil.copyList(ebookList, EbookResp.class);
        return list;
    }
}
