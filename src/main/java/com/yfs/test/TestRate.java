package com.yfs.test;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 于凡粟
 * @Date 2020/5/18 9:45 上午
 */
public class TestRate {

    public static void main(String[] args) {
        //System.out.println(JSON.toJSONString(calculateEqualPrincipalAndInterest(1200000,360,5.39)));
        //System.out.println(JSON.toJSONString(calculateEqualPrincipalAndInterest(700000,360,60,5.39)));
        BigDecimal bigDecimal = new BigDecimal(countMoney(60000, 0.003416, 0, 36));
        System.out.println(bigDecimal.toString());
        //System.out.println(JSON.toJSONString(countMoney(10000, 0.02, 0, 360)));
        //System.out.println(JSON.toJSONString(countYearMoney(200000, 0.04, 72000, 30)));
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

        //double getMonthRate = 0.0033;
        //data.add(getString(getMonthRate));//月获得利率
        //data.add(getString(countMoney(300000, getMonthRate, preLoan, 360) - 300000 - (360 * preLoan)));//总获得利息

        return data.toArray(new String[data.size()]);
    }


    private static double countMoney(int principal, double interest_rate, double every_money, int count_month) {
        double total_sum = 0;
        int pr = principal;
        double ev = every_money;
        for (int i = 1; i <= count_month; i++) {
            if (total_sum == 0) {
                total_sum = (principal) * (1 + interest_rate);
            } else {
                total_sum = (principal + every_money) * (1 + interest_rate);
            }
            principal = (int) total_sum;
            ////if (i == count_month) {
                System.out.println("第"+i + "月的复利：" + getDoubleString(total_sum - pr - ((i - 1) * every_money)));
            //}

        }
        return total_sum;
    }
    private static double countYearMoney(int principal, double interest_rate, double every_money, int count_year) {
        double total_sum = 0;
        int pr = principal;
        double ev = every_money;
        for (int i = 1; i <= count_year; i++) {
            if (total_sum == 0) {
                total_sum = (principal) * (1 + interest_rate);
            } else {
                total_sum = (principal + every_money) * (1 + interest_rate);
            }
            principal = (int) total_sum;
            ////if (i == count_month) {
                System.out.println("第"+i + "年的复利：" + getDoubleString(total_sum - pr - ((i - 1) * every_money)));
            //}

        }
        return total_sum;
    }
    public static String getDoubleString(double number) {
        String numberStr;
        if (((int) number * 1000) == (int) (number * 1000)) {
            //如果是一个整数
            numberStr = String.valueOf((int) number);
        } else {
            DecimalFormat df = new DecimalFormat("######0");
            numberStr = df.format(number);
        }
        return numberStr;
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

    private static String getString(double d) {
        DecimalFormat format = new DecimalFormat("#.00");
        String str = format.format(d);
        return str;
    }

}
