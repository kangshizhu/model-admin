package modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import modules.entity.CompanyRegisterInfo;
import modules.entity.ShopEvaluationList;
import modules.mapper.CompanyRegisterInfoMapper;
import modules.mapper.ShopEvaluationListMapper;
import modules.service.IShopEvaluationListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import modules.util.EvaluationConstant;
import modules.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 商品测评列表 服务实现类
 * </p>
 *
 * @author chenguitong
 * @since 2022-09-22
 */
@Service
public class ShopEvaluationListServiceImpl extends ServiceImpl<ShopEvaluationListMapper, ShopEvaluationList> implements IShopEvaluationListService {

    @Resource
    ShopEvaluationListMapper listMapper;

    @Resource
    CompanyRegisterInfoMapper companyRegisterInfoMapper;


    @Override
    public Result pushShop(ShopEvaluationList shopEvaluationList) {
        String companyId = shopEvaluationList.getCompanyId();
        //有该商户才能发布测评商品
        QueryWrapper<CompanyRegisterInfo> companyRegisterInfoQueryWrapper=new QueryWrapper();
        companyRegisterInfoQueryWrapper.eq("company_id",companyId);
        CompanyRegisterInfo companyRegisterInfo = companyRegisterInfoMapper.selectOne(companyRegisterInfoQueryWrapper);
        if(companyRegisterInfo != null){
            listMapper.insert(shopEvaluationList);
            return Result.OK("发布成功!");
        }else{
            return Result.OK("没有查询到对应的商户,发布失败!");
        }
    }
}
