/*
 *利用系统时间获取当前时间代码（工具类）
 *@zhouHe
 *感谢陈震晗学长和吴少强老师的指导
 *这是那么多代码里面最喜欢的一串；生活应该是优雅的，代码亦应如此
 *每一个不曾起舞的日子都是对生命的辜负
 *
 */

import java.util.Random;

public class Test
{
    static void getTime()
    {
        //自写得到时间的代码
        long totalMiniSecond = System.currentTimeMillis() ;  //totalMiniSecond:总微秒
        long totalSecond = totalMiniSecond / 1000 ; //totalSecond:总秒
        long totalMinute = totalSecond / 60;       //totalMinute:总分钟
        long totalHour = totalMinute / 60 + 8 ;    //totalHour:总小时，+ 8是从格林尼治时间转成东八区时间
        long totalDay = totalHour / 24;   //totalDay：总天数

        long currentDay = totalDay;  //currentzDay：用来存放计算后的当前'日'
        long currentHour = totalHour % 24;//currentHour：得到当前的小时
        long currentMinute = totalMinute % 60;  //currentMinute:当前分钟
        long currentSecond = totalSecond % 60;  //currentSecond:当前秒

        //------------得到当前星期：currentWeek------------------------
        long weekDay = totalDay ;
        weekDay %= 7;
        char[] week = {'四','五','六','天','一','二','三'};
        char currentWeek = week[(int) weekDay];


        //------------将24小时制转化为12小时制，并美化小时的显示------------
        String  amPm;

        amPm = (currentHour>=5 && currentHour < 8) ? "早晨" : (currentHour>=8 && currentHour<12) ? "上午" :
               (currentHour>=12 && currentHour<14) ? "中午" : (currentHour>=14 && currentHour<18) ?"下午" :
               (currentHour == 18) ? "傍晚" : (currentHour>=19 && currentHour<=22) ? "晚上" :
               (currentHour>22 || currentHour<=2) ? "深夜" : "凌晨";

        if(currentHour>= 13)
            currentHour -= 12;


        //------------得到当前日：currentDay------------------------------
        int currentYear,currentMonth;
        boolean leapYear = false;// leapYear作用：将闰年普通年的标记下来用于递减月份天数
        for(currentYear = 1970;currentDay >= 365;currentYear++) //判断闰年，闰年总天-366，年数+1，普通年总天-365，年数+1，
        {
            if(currentYear % 100 == 0 ? currentYear % 400 == 0 : currentYear % 4 == 0 && currentYear % 100 != 0)
            { currentDay -= 366;leapYear = true;}
            else
            { currentDay -= 365;leapYear = false;}
        }

        //月份是大月总天数减31天，小月减30天，2月用leapYear标识是否为闰年后再加减28或29
        for(currentMonth=1;currentDay>28;currentMonth++)
        {
            if(currentMonth==4 || currentMonth==6 || currentMonth==9 || currentMonth==11 )
            { currentDay -= 30; } //小月日期减30
            else
            {
                if(currentMonth != 2)
                {currentDay -= 31;}//大月日期减31
                else
                {
                    if(leapYear)
                    { currentDay -= 29; }//闰年2月日期减29
                    else
                    { currentDay -= 28; }//平年2月日期减28
                }
            }
        }

        //-----------天干地支纪年法转换公式（农历月份转换失败）--------------
        char[] stars = {'癸','甲','乙','丙','丁','戊','己','庚','辛','壬'};
        char[] land  = {'亥','子','丑','寅','卯','辰','已','午','未','申','酉','戌'};

        int lunarYearS = (currentYear - 3) % 10;//得到序数
        char lunarYearStar = stars[lunarYearS];

        int lunarYearL = (currentYear - 3) % 12;
        char lunarYearLand = land[lunarYearL];


        //----------------输出时间格式----------------------
        System.out.println("现在是 " + currentYear + "年" + currentMonth + "月" + currentDay + "日 "
                + lunarYearStar + lunarYearLand +"年 " +"星期"
                + currentWeek +" " + amPm + " "
                + currentHour + "点" + currentMinute + "分" + currentSecond + "秒" + "\n");
        TheBeauty.printPoem();
    }


    public static void main(String[] args) {
            getTime();
    }

}

//----------是这一段代码的让我感受到了编程的优雅----------
class TheBeauty
{
    static void printPoem()
    {
        Random r = new Random();
        int luck = r.nextInt(theBeauty.length);
        System.out.println(theBeauty[luck]);
    }


     static String[] theBeauty = {
            "诗酒社，水云乡。可堪醉墨几淋浪。\n画图恰似归家梦，千里河山寸许长。",//0
            "每一个不曾起舞的日子都是对生命的辜负",//2
            "无半点闲愁去处，问三生醉梦何如。",//3
            "红泥小火炉，绿螘新醅酒。晚来天欲雪，能饮一杯无？",
            "梦里不知身是客，一晌贪欢。",
            "醉后不知天在水，满船清梦压星河。",
            "愿你所有的努力都不白费，所想的都能如愿，所做的都能实现。\n愿有人待你如初，疼你入骨，愿深情总不会被辜负。",
            "一个人只要知道去哪里，全世界都会给他让步。",//8
            "你日渐平庸，甘于平庸，将继续平庸。",
            "Be yourself,everyone else is already taken." +
             "\n\t\t\t----Oscar Fingal O’Flahertie WillsWilde",
             "半山腰总是挤的，你得去山顶看看",
             "勤劳 大智慧 诚心有为",
             "有感即通千江水有千山月，无机不被万里无云万里天",
             "宝剑锋从磨砺出，梅花香自苦寒来",//索引13
             "无限风光在顶峰！",
             "午后是一日里正过到途中，是一日里希望接近尾声的等待。不耐和消沉相继而来，希望也是挣扎的希望。" +
                     "\n它是闺阁里的苍凉暮年，心都要老了，做人却还像没开头似的。" +
                     "想到这。心都要绞起来了，却又不能与人说，说也说不明的。\n" +
                     "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t --扎西阿姆《当你途径我的绽放》",//索引15

    };


}
