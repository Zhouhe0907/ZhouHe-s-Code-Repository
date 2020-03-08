/*
 *利用系统时间获取当前时间代码（工具类）
 *@zhouHe
 *每一个不曾起舞的日子都是对生命的辜负
 */

public class GetTimeTool
{
    static void getTime()
    {
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
            currentHour -= 12;//转化成12小时制


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
                                    +currentHour + "点" + currentMinute + "分" + currentSecond + "秒"
                                    + "\n\n每一个不曾起舞的日子都是对生命的辜负");
    }

}
