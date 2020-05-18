package com.yfs.test;

import com.alibaba.fastjson.JSON;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 于凡粟
 * @Date 2020/5/18 9:45 上午
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(calculateEqualPrincipalAndInterest(700000,360,5.39)));
    }

    /**
     * 计算等额本息还款
     *
     * @param principal 贷款总额
     * @param months    贷款期限
     * @param rate      贷款利率
     * @return
     */
    public static String[] calculateEqualPrincipalAndInterest(double principal, int months, double rate) {
        List<String> data = new ArrayList<String>();
        double monthRate = rate / (100 * 12);//月利率
        double preLoan = (principal * monthRate * Math.pow((1 + monthRate), months)) / (Math.pow((1 + monthRate), months) - 1);//每月还款金额
        double totalMoney = preLoan * months;//还款总额
        double interest = totalMoney - principal;//还款总利息
        data.add(getString(totalMoney));//还款总额
        data.add(getString(principal));//贷款总额
        data.add(getString(interest));//还款总利息
        data.add(getString(preLoan));//每月还款金额
        data.add(String.valueOf(months));//还款期限
        return data.toArray(new String[data.size()]);
    }

    /**
     * 一次性提前还款计算（等额本息）
     *
     * @param principal 贷款总额
     * @param months    贷款期限
     * @param payTimes  已还次数
     * @param rate      贷款利率
     * @return
     */
    public static String[] calculateEqualPrincipalAndInterest(double principal, int months, int payTimes, double rate) {
        ArrayList<String> data = new ArrayList<String>();
        double monthRate = rate / (100 * 12);//月利率
        double preLoan = (principal * monthRate * Math.pow((1 + monthRate), months)) / (Math.pow((1 + monthRate), months) - 1);//每月还款金额
        double totalMoney = preLoan * months;//还款总额
        double interest = totalMoney - principal;//还款总利息
        double leftLoan = principal * Math.pow(1 + monthRate, payTimes) - preLoan * (Math.pow(1 + monthRate, payTimes) - 1) / monthRate;//n个月后欠银行的钱
        double payLoan = principal - leftLoan;//已还本金
        double payTotal = preLoan * payTimes;//已还总金额
        double payInterest = payTotal - payLoan;//已还利息
        double totalPayAhead = leftLoan * (1 + monthRate);//剩余一次还清
        double saveInterest = totalMoney - payTotal - totalPayAhead;
        data.add(getString(totalMoney));//原还款总额
        data.add(getString(principal));//贷款总额
        data.add(getString(interest));//原还款总利息
        data.add(getString(preLoan));//原还每月还款金额
        data.add(getString(payTotal));//已还总金额
        data.add(getString(payLoan));//已还本金
        data.add(getString(payInterest));//已还利息
        data.add(getString(totalPayAhead));//一次还清支付金额
        data.add(getString(saveInterest));//节省利息
        data.add(String.valueOf(0));//剩余还款期限
        return data.toArray(new String[data.size()]);
    }
    
    private static String getString(double d){
        DecimalFormat format = new DecimalFormat("#.00");
        String str= format.format(d);
        return str;
    }

}
