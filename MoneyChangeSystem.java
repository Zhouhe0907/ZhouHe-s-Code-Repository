/*
 *@ZhouHe
 *和朱思名一起写代码的纪念
 */
import java.util.Scanner;

public class MoneyChangeSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);   //从键盘获取内容的方式

        int oneYuan , fiveYuan , tenYuan , twentyYuan , fiftyYuan ,hundredYuan,fiveJiao , oneJiao;
        float cost , change , payment , repay = 0;
        //cost:应付金额   change:应找零   payment：已支付金额  repay：支付金额不够再次支付的金额
        System.out.println("欢迎使用金正大超市自助找零系统" +
                "\n-------------------------");//问候语

        while(true)//把操作放在一直判断为true的循环里便于进行连续操作（也可以写一个退出方式）
        {
            System.out.print("请输入商品价格：");
            cost = sc.nextFloat();//获取应付金额

            while(MoneyChangeSystem.LeaglOrNot(cost))//调用下方所写的【LeaglOrNot方法】判断输入的金额是否合法，不合法方法返回值为ture
            {                                   // 判断为'true'则进行循环体内的操作，为false则跳过
                System.out.print("价格输入不合法！请重新输入：");
                float recost = sc.nextFloat();//再次从键盘获取数据
                cost = recost; //cost=新获得的数据
                if(!MoneyChangeSystem.LeaglOrNot(cost))//再次调用LeaglOrNot判断cost是否合法,合法的话方法返回值是【false】
                    //如果为false是不会进行if里面的操作的，所以又加了一个'！'，判断由false变成了true
                    break;                          //if（）语句判断为true，进行if里面的代码，触发break；跳出循环
                //假如不合法LeaglOrNot方法返回的为false，跳过
            }

            System.out.print("请输入顾客实付金额：");
            payment = sc.nextFloat();

            while(MoneyChangeSystem.LeaglOrNot(payment))//这个步骤和上一步一样
            {
                System.out.print("所付金额不合法！请重新输入：");
                float repayment = sc.nextFloat();
                cost = repayment;
                if(!MoneyChangeSystem.LeaglOrNot(repayment))
                    break;
            }

            //判断所付金额是否大于应付金额，当实付金额大于等于应付金额时跳出循环，一开始就大于等于就忽略此循环
            boolean leagal = true;
            while(payment < cost)
            {
                System.out.print("顾客支付金额不足！再多支付：");
                repay = sc.nextFloat();
                while (MoneyChangeSystem.LeaglOrNot(repay))
                {
                    System.out.print("支付金额不合法！再次支付:");
                    repay = sc.nextFloat();
                }
                payment += repay;

            }
            change = payment - cost;//change：找零  实付金额-应付金额



            //---------------找零算法核心：拿整数12346.6为例----------------
            int integer = (int)change;//integer用来存储整数部分 原理：（int）强制把float类型的change转化为int类型，丢失小数点后的数据
            // 即所谓的丢失精度，这样就得到了整数部分

            hundredYuan = integer/100;// 12346/100 = 123   hundredYuan = 123 (丢失精度的原理)

            fiftyYuan = (integer - hundredYuan*100) / 50; //（12346-12300）/50 = 0 ,fiftyYuan = 0 (丢失精度的原理)

            twentyYuan = (integer - hundredYuan*100 - 50*fiftyYuan) / 20;//同上，twentyYuan = 2

            tenYuan = (integer - hundredYuan*100 - 50*fiftyYuan - 20*twentyYuan) / 10;//tenYuan = 0

            fiveYuan = (integer - hundredYuan*100 - 50*fiftyYuan - 20*twentyYuan - 10*tenYuan) / 5;//fiveYuan = 1

            oneYuan = integer - hundredYuan*100 - 50*fiftyYuan - 20*twentyYuan - 10*tenYuan - 5*fiveYuan;//oneYuan = 1

            //------------小数部分同上-----------
            int index = (int) (change*10 - integer*10);//index = (12346.6 - 12346) * 10 = 6
            fiveJiao = index / 5;//fiveJiao = 1
            oneJiao = index - 5*fiveJiao;//oneJiao = 1

            //-----------输出，其实还可以在根据为'0'进行选择进行输出----------
            String[] yuanAndJiao = {"¥100:","¥50:","¥20:","¥10:","¥5:","¥1:","¥[0.5]:","¥[0.1]:"};

            int[] cout = {hundredYuan,fiftyYuan,twentyYuan,tenYuan,fiveYuan,oneYuan,fiveJiao,oneJiao};

            System.out.print("推荐找零方案 ");
            for(int i = 0; i < 8;i++)
            {
                if(cout[i]!=0)
                    System.out.print(yuanAndJiao[i] + cout[i] + "  " );
            }

            System.out.println("\n");
        }

    }

    //这是判断入参是否符合规格的方法，考虑到实际情况，金额以元为单位所以只判断到小数点后一位，即'角'（忽略不常用的'分'）
    static  boolean LeaglOrNot(float toBeJudged)//括号内必须为一个float类型的数值
    {
        //拿12.334举例
        if( toBeJudged < 0 )  //如果入参小于'0'，那么返回true，而程序中的while（）括号中条件判断为真，执行while循环里的代码
            return true;
        int test = (int)(toBeJudged * 10);//拿12.334为例 （int）12.334*10 = 123：即把float类型的数转为整数，小数点后的0.334会丢失
        toBeJudged *= 10;  //float类型的 12.334*10 = 123.34在计算时不丢失小数点之后的0.34
        return ( (toBeJudged - test > 0) ); //toBeJudged - test = 123.34 - 123 = 0.34 > 0
        // (toBeJudged - test>0判断为ture),即返回的值是true，
        // 程序中的while（）括号中条件判断为真，执行while循环里的代码
    }

}
