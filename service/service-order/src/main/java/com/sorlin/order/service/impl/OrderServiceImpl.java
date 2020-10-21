package com.sorlin.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sorlin.order.client.EduClient;
import com.sorlin.order.client.UcenterClient;
import com.sorlin.order.entity.Order;
import com.sorlin.order.mapper.OrderMapper;
import com.sorlin.order.service.OrderService;
import com.sorlin.order.utils.OrderNoUtil;
import com.sorlin.vo.CourseWebInfo;
import com.sorlin.vo.UcenterMemberWebInfo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author sorlin
 * @since 2020-08-27
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final EduClient eduClient;
    private final UcenterClient ucenterClient;

    public OrderServiceImpl(EduClient eduClient, UcenterClient ucenterClient) {
        this.eduClient = eduClient;
        this.ucenterClient = ucenterClient;
    }

    @Override
    public String createOrders(String courseId, String memberIdByJwtToken) {

        CourseWebInfo courseWebInfo = eduClient.getCourseWebInfo(courseId);

        UcenterMemberWebInfo userInfo = ucenterClient.getUserInfo(memberIdByJwtToken);

        if (courseWebInfo == null || userInfo == null) {
            return null;
        }
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseWebInfo.getTitle());
        order.setCourseCover(courseWebInfo.getCover());
        order.setTeacherName(courseWebInfo.getTeacherName());
        order.setTotalFee(courseWebInfo.getPrice());
        order.setMemberId(memberIdByJwtToken);
        order.setMobile(userInfo.getMobile());
        order.setNickname(userInfo.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
